package ru.house13.engine.pad;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.Test;
import ru.house13.engine.KeyMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Инварианты таблицы маппинга: каждая кнопка определена явно,
 * каждый замапленный код понятен KeyMap (или это Shift-модификатор),
 * NINTENDO — ровно своп лицевых кнопок относительно DEFAULT.
 */
class PadMapTest {

    private static final PadBtn[] ALL = PadBtn.values();

    @Test
    void everyButtonExplicitlyDefined() {
        for (PadMap map : new PadMap[]{PadMap.DEFAULT, PadMap.NINTENDO}) {
            for (PadBtn b : ALL) {
                // либо валидный gdx-код, либо явный UNMAPPED-резерв
                int code = map.gdxCode(b);
                assertTrue(code == PadMap.UNMAPPED || (code >= 0 && code <= Input.Keys.MAX_KEYCODE),
                        map + " " + b + " -> " + code);
            }
        }
    }

    @Test
    void mappedCodesAreUnderstoodByKeyMap() {
        // «Понятный маппинг»: замапленная кнопка обязана либо попадать
        // в J2ME-семантику KeyMap, либо быть Shift-модификатором порта.
        for (PadMap map : new PadMap[]{PadMap.DEFAULT, PadMap.NINTENDO}) {
            for (PadBtn b : ALL) {
                int code = map.gdxCode(b);
                if (code == PadMap.UNMAPPED) {
                    continue;
                }
                boolean understood = KeyMap.map(code) != KeyMap.J2.NONE || KeyMap.isShift(code);
                assertTrue(understood, map + " " + b + " -> key#" + code + " не понятен KeyMap");
            }
        }
    }

    @Test
    void defaultLayoutCoreActions() {
        // A=OK(FIRE), B=отмена(правый софт), X=инвентарь('1'), START=меню(левый софт).
        assertEquals(KeyMap.J2.FIRE, PadMap.DEFAULT.j2(PadBtn.A));
        assertEquals(KeyMap.J2.SOFT_RIGHT, PadMap.DEFAULT.j2(PadBtn.B));
        assertEquals(KeyMap.J2.NUM_1, PadMap.DEFAULT.j2(PadBtn.X));
        assertEquals(KeyMap.J2.SOFT_LEFT, PadMap.DEFAULT.j2(PadBtn.START));
        // Направления.
        assertEquals(KeyMap.J2.UP, PadMap.DEFAULT.j2(PadBtn.DPAD_UP));
        assertEquals(KeyMap.J2.DOWN, PadMap.DEFAULT.j2(PadBtn.DPAD_DOWN));
        assertEquals(KeyMap.J2.LEFT, PadMap.DEFAULT.j2(PadBtn.DPAD_LEFT));
        assertEquals(KeyMap.J2.RIGHT, PadMap.DEFAULT.j2(PadBtn.DPAD_RIGHT));
    }

    @Test
    void defaultMatchesAutotestTokens() {
        // Те же gdx-коды, что у токенов скрипта: F=ENTER, Rs=ESCAPE, Ls=F1.
        // Значит, геймпадные нажатия эквивалентны проверенным автотестами.
        assertEquals(Input.Keys.ENTER, PadMap.DEFAULT.gdxCode(PadBtn.A));
        assertEquals(Input.Keys.ESCAPE, PadMap.DEFAULT.gdxCode(PadBtn.B));
        assertEquals(Input.Keys.F1, PadMap.DEFAULT.gdxCode(PadBtn.START));
    }

    @Test
    void triggersAreHoldShift() {
        // Оба триггера -> один SHIFT (удержание = ускорение x2).
        assertEquals(Input.Keys.SHIFT_LEFT, PadMap.DEFAULT.gdxCode(PadBtn.L2));
        assertEquals(Input.Keys.SHIFT_LEFT, PadMap.DEFAULT.gdxCode(PadBtn.R2));
        assertTrue(KeyMap.isShift(PadMap.DEFAULT.gdxCode(PadBtn.L2)));
        // Shift — вне J2ME-модели: j2() отдаёт NONE, но кнопка «mapped».
        assertEquals(KeyMap.J2.NONE, PadMap.DEFAULT.j2(PadBtn.L2));
        assertTrue(PadMap.DEFAULT.isMapped(PadBtn.L2));
    }

    @Test
    void reservedButtonsStayUnmapped() {
        // Y, SELECT, GUIDE, бамперы, клики стиков — резерв полной реализации.
        for (PadMap map : new PadMap[]{PadMap.DEFAULT, PadMap.NINTENDO}) {
            for (PadBtn b : new PadBtn[]{PadBtn.SELECT, PadBtn.GUIDE,
                    PadBtn.L1, PadBtn.R1, PadBtn.L3, PadBtn.R3}) {
                assertFalse(map.isMapped(b), map + " " + b + " должен быть в резерве");
            }
        }
    }

    @Test
    void nintendoIsExactlyFaceSwap() {
        // NINTENDO = DEFAULT со свопом A<->B и X<->Y; всё остальное идентично.
        assertEquals(PadMap.DEFAULT.gdxCode(PadBtn.B), PadMap.NINTENDO.gdxCode(PadBtn.A));
        assertEquals(PadMap.DEFAULT.gdxCode(PadBtn.A), PadMap.NINTENDO.gdxCode(PadBtn.B));
        assertEquals(PadMap.DEFAULT.gdxCode(PadBtn.Y), PadMap.NINTENDO.gdxCode(PadBtn.X));
        assertEquals(PadMap.DEFAULT.gdxCode(PadBtn.X), PadMap.NINTENDO.gdxCode(PadBtn.Y));
        for (PadBtn b : ALL) {
            if (b == PadBtn.A || b == PadBtn.B || b == PadBtn.X || b == PadBtn.Y) {
                continue;
            }
            assertEquals(PadMap.DEFAULT.gdxCode(b), PadMap.NINTENDO.gdxCode(b),
                    "NINTENDO затронула не только лицевые: " + b);
        }
        // Смысл свопа: OK на кнопке с МЕТКОЙ A (правая позиция у Switch).
        assertEquals(KeyMap.J2.FIRE, PadMap.NINTENDO.j2(PadBtn.B));
        assertEquals(KeyMap.J2.SOFT_RIGHT, PadMap.NINTENDO.j2(PadBtn.A));
    }
}
