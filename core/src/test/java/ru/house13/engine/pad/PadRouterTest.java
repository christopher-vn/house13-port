package ru.house13.engine.pad;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.Test;
import ru.house13.engine.InputQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Поведение маршрутизатора: press/release в InputQueue, счётчик ссылок
 * (D-pad + стик, L2 + R2), гистерезис стика, releaseAll при отключении.
 * InputQueue — чистый класс (без Gdx.*), поэтому тесты без headless-стабов.
 */
class PadRouterTest {

    private final InputQueue input = new InputQueue();
    private final PadRouter pad = new PadRouter(input);

    /** Слить всё накопленное в одну ленту событий: "code:down"/"code:up". */
    private static List<String> drain(InputQueue q) {
        List<String> out = new ArrayList<>();
        for (int c : q.drainKeyDowns()) out.add(c + ":down");
        for (int c : q.drainKeyUps()) out.add(c + ":up");
        return out;
    }

    private static List<String> events(int... codesAndDirs) {
        List<String> out = new ArrayList<>();
        for (int i = 0; i < codesAndDirs.length; i += 2) {
            out.add(codesAndDirs[i] + ":" + (codesAndDirs[i + 1] > 0 ? "down" : "up"));
        }
        return out;
    }

    @Test
    void buttonPressReleaseGoesToQueue() {
        pad.button(PadBtn.A, true);
        assertEquals(events(Input.Keys.ENTER, +1), drain(input));
        pad.button(PadBtn.A, false);
        assertEquals(events(Input.Keys.ENTER, -1), drain(input));
    }

    @Test
    void duplicatePressAndOrphanReleaseAreIgnored() {
        pad.button(PadBtn.B, true);
        pad.button(PadBtn.B, true);   // дребезг: состояние не изменилось
        drain(input);
        pad.button(PadBtn.B, false);  // честное отпускание
        pad.button(PadBtn.B, false);  // повторный up без down
        pad.button(PadBtn.X, false);  // up без down вовсе
        assertEquals(events(Input.Keys.ESCAPE, -1), drain(input));
    }

    @Test
    void unmappedButtonsDoNotInject() {
        pad.button(PadBtn.Y, true);
        pad.button(PadBtn.SELECT, true);
        pad.button(PadBtn.GUIDE, true);
        assertTrue(drain(input).isEmpty());
        // но физическое состояние помнится (оверлей/дебаг)
        assertTrue(pad.isHeld(PadBtn.Y));
        pad.button(PadBtn.Y, false);
        assertFalse(pad.isHeld(PadBtn.Y));
    }

    @Test
    void dpadAndStickShareOneDirectionKey() {
        // Оба источника на RIGHT: одно нажатие, отпускание — когда ушли оба.
        pad.button(PadBtn.DPAD_RIGHT, true);
        assertEquals(events(Input.Keys.RIGHT, +1), drain(input));
        pad.leftStick(0.9f, 0f);          // стик тоже вправо — слияние
        assertTrue(drain(input).isEmpty());
        pad.button(PadBtn.DPAD_RIGHT, false); // отпустили крестовину — ещё держит стик
        assertTrue(drain(input).isEmpty());
        pad.leftStick(0f, 0f);            // стик в центр — теперь отпускание
        assertEquals(events(Input.Keys.RIGHT, -1), drain(input));
    }

    @Test
    void twoTriggersShareOneShift() {
        pad.button(PadBtn.L2, true);
        assertEquals(events(Input.Keys.SHIFT_LEFT, +1), drain(input));
        pad.button(PadBtn.R2, true);         // второй триггер — слияние
        assertTrue(drain(input).isEmpty());
        pad.button(PadBtn.L2, false);        // первый отпущен — Shift ещё активен
        assertTrue(drain(input).isEmpty());
        pad.button(PadBtn.R2, false);        // последний ушёл — отпускаем
        assertEquals(events(Input.Keys.SHIFT_LEFT, -1), drain(input));
    }

    @Test
    void stickHysteresisNoChatterNoEarlyRelease() {
        // Мертвая зона: 0.4 не включает (порог 0.5)...
        pad.leftStick(0f, 0.4f);
        assertTrue(drain(input).isEmpty());
        // ...включение на 0.6...
        pad.leftStick(0f, 0.6f);
        assertEquals(events(Input.Keys.DOWN, +1), drain(input));
        // ...0.4 ещё держит (гистерезис: отпускание ниже 0.35)...
        pad.leftStick(0f, 0.4f);
        assertTrue(drain(input).isEmpty());
        // ...а 0.3 отпускает.
        pad.leftStick(0f, 0.3f);
        assertEquals(events(Input.Keys.DOWN, -1), drain(input));
    }

    @Test
    void stickUpIsNegativeY() {
        // GLFW: y = -1 при стике вверх (как у осей геймпада в целом).
        pad.leftStick(0f, -0.8f);
        assertEquals(events(Input.Keys.UP, +1), drain(input));
        pad.leftStick(0f, 0f);
        assertEquals(events(Input.Keys.UP, -1), drain(input));
    }

    @Test
    void stickDiagonalPressesBothAxes() {
        pad.leftStick(-0.7f, 0.7f);
        // Порядок внутри leftStick: сначала X (LEFT), затем Y (DOWN).
        assertEquals(events(Input.Keys.LEFT, +1, Input.Keys.DOWN, +1), drain(input));
        pad.leftStick(0f, 0f);
        assertEquals(events(Input.Keys.LEFT, -1, Input.Keys.DOWN, -1), drain(input));
    }

    @Test
    void stickReversalBothEventsInSameTick() {
        pad.leftStick(0.9f, 0f);
        drain(input);
        // Резкий разворот вправо -> влево: оба события в одном тик-окне
        // (down нового + up старого). InputQueue отдаёт все down'ы, затем
        // все up'ы — GameScene обрабатывает их в этом же порядке, как при
        // клавиатурном развороте стрелок (идентичное поведение = цель).
        pad.leftStick(-0.9f, 0f);
        assertEquals(events(Input.Keys.LEFT, +1, Input.Keys.RIGHT, -1), drain(input));
        // Стик удерживается — новых событий нет; следующий тик чист.
        pad.leftStick(-0.9f, 0f);
        assertTrue(drain(input).isEmpty());
    }

    @Test
    void disconnectReleasesEverythingHeld() {
        pad.button(PadBtn.A, true);
        pad.button(PadBtn.DPAD_RIGHT, true);
        pad.button(PadBtn.L2, true);
        pad.leftStick(0f, -0.9f);
        drain(input);
        pad.padConnected("Test Pad");
        assertTrue(pad.padActive());

        pad.padDisconnected();
        // Всё зажатое отпущено одним пакетом (ups), ничего не залипло.
        List<String> ups = drain(input);
        assertTrue(ups.containsAll(events(Input.Keys.ENTER, -1)));
        assertTrue(ups.containsAll(events(Input.Keys.RIGHT, -1)));
        assertTrue(ups.containsAll(events(Input.Keys.SHIFT_LEFT, -1)));
        assertTrue(ups.containsAll(events(Input.Keys.UP, -1)));
        assertEquals(4, ups.size());
        assertFalse(pad.padActive());
        assertFalse(pad.isHeld(PadBtn.A));
    }

    @Test
    void releaseAllAfterDisconnectKeepsRouterUsable() {
        pad.button(PadBtn.START, true);
        drain(input);
        pad.releaseAll();
        assertEquals(events(Input.Keys.F1, -1), drain(input));
        // Новый цикл press/release работает штатно.
        pad.button(PadBtn.START, true);
        assertEquals(events(Input.Keys.F1, +1), drain(input));
        pad.button(PadBtn.START, false);
        assertEquals(events(Input.Keys.F1, -1), drain(input));
    }

    @Test
    void layoutSwitchReleasesAndRemaps() {
        pad.setMap(PadMap.NINTENDO); // до нажатий — просто смена
        assertTrue(drain(input).isEmpty());
        // В NINTENDO OK(FIRE) — на кнопке B (метка «A» у Switch).
        pad.button(PadBtn.B, true);
        assertEquals(events(Input.Keys.ENTER, +1), drain(input));
        // Смена раскладки зажатую кнопку отпускает.
        pad.setMap(PadMap.DEFAULT);
        assertEquals(events(Input.Keys.ENTER, -1), drain(input));
        // Теперь B в DEFAULT = отмена.
        pad.button(PadBtn.B, true);
        assertEquals(events(Input.Keys.ESCAPE, +1), drain(input));
    }

    @Test
    void describeShowsNameHeldAndStick() {
        pad.padConnected("Xbox Series X");
        pad.button(PadBtn.A, true);
        pad.leftStick(0.8f, -0.8f);
        String d = pad.describe();
        assertTrue(d.contains("Xbox Series X"));
        assertTrue(d.contains("A"));
        assertTrue(d.contains("→") && d.contains("↑"));
    }

    @Test
    void safeKeypadFlowWithDpadAndA() {
        // Сквозной сценарий: ввод цифры на сейф-клавиатуре крестовиной + OK.
        // Курсор стартует на '1'; маршрут: вправо-вниз -> '5', OK.
        // Контракт InputQueue: все нажатия (порядок прихода), затем все
        // отпускания — GameScene обрабатывает так же (J2ME-порядок).
        pad.button(PadBtn.DPAD_RIGHT, true);
        pad.button(PadBtn.DPAD_RIGHT, false);
        pad.button(PadBtn.DPAD_DOWN, true);
        pad.button(PadBtn.DPAD_DOWN, false);
        pad.button(PadBtn.A, true);
        pad.button(PadBtn.A, false);
        List<String> all = drain(input);
        assertEquals(Arrays.asList(
                Input.Keys.RIGHT + ":down",
                Input.Keys.DOWN + ":down",
                Input.Keys.ENTER + ":down",
                Input.Keys.RIGHT + ":up",
                Input.Keys.DOWN + ":up",
                Input.Keys.ENTER + ":up"), all);
    }
}
