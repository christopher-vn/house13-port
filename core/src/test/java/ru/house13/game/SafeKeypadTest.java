package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.house13.engine.KeyMap.J2;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Экранная клавиатура сейфа (этап 5b): навигация курсором, клампинг,
 * сброс при (пере)открытии, пульс, трансформация клавиш, полный ввод
 * кода 9819 одним D-PAD'ом, закрытие через клетку ✕.
 */
class SafeKeypadTest {

    private FakeHost host;
    private GameScene game;
    private Safe safe;
    private Inventory inv;
    private SafeKeypad kp;

    @BeforeEach
    void setUp() {
        host = new FakeHost();
        game = GameScene.postSkip(host);
        safe = game.safe;
        inv = game.inv;
        kp = game.keypad;
    }

    private void openSafeAtSpot() {
        game.room.room = 1;
        game.room.hand.j = 190;
        game.room.hand.i = 100;
        game.room.hand.dirUp = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(safe.open);
    }

    @Test
    void cellKeysMatchOriginalSemantics() {
        // 0..8 -> '1'..'9'
        for (int d = 1; d <= 9; d++) {
            assertEquals(J2.values()[J2.NUM_1.ordinal() + d - 1], SafeKeypad.keyAt(d - 1));
        }
        // нижний ряд: ✓ = '5' (подтверждение), 0, ✕ = правый софт (закрыть)
        assertEquals(J2.NUM_5, SafeKeypad.keyAt(SafeKeypad.IDX_OK));
        assertEquals(J2.NUM_0, SafeKeypad.keyAt(SafeKeypad.IDX_ZERO));
        assertEquals(J2.SOFT_RIGHT, SafeKeypad.keyAt(SafeKeypad.IDX_CLOSE));
    }

    @Test
    void arrowsMoveCursorAndAreConsumed() {
        kp.cursor = 0;
        assertNull(kp.map(J2.DOWN, true));   // 0 -> 3
        assertEquals(3, kp.cursor);
        assertNull(kp.map(J2.RIGHT, true));  // 3 -> 4
        assertEquals(4, kp.cursor);
        assertNull(kp.map(J2.UP, true));     // 4 -> 1
        assertEquals(1, kp.cursor);
        assertNull(kp.map(J2.LEFT, true));   // 1 -> 0
        assertEquals(0, kp.cursor);
    }

    @Test
    void cursorClampsAtEdges() {
        kp.cursor = 0;
        assertNull(kp.map(J2.UP, true));
        assertNull(kp.map(J2.LEFT, true));
        assertEquals(0, kp.cursor, "верхний-левый угол — некуда");
        kp.cursor = 11;
        assertNull(kp.map(J2.DOWN, true));
        assertNull(kp.map(J2.RIGHT, true));
        assertEquals(11, kp.cursor, "нижний-правый угол — некуда");
    }

    @Test
    void fireDeliversCellKey() {
        kp.map(J2.NONE, true);   // инициализация wasOpen: дальше reset не мешает
        kp.cursor = 8;                        // '9'
        assertEquals(J2.NUM_9, kp.map(J2.FIRE, true));
        kp.cursor = SafeKeypad.IDX_OK;        // ✓
        assertEquals(J2.NUM_5, kp.map(J2.FIRE, true));
        kp.cursor = SafeKeypad.IDX_CLOSE;     // ✕
        assertEquals(J2.SOFT_RIGHT, kp.map(J2.FIRE, true));
    }

    @Test
    void otherKeysPassThroughWhenOpen() {
        assertEquals(J2.NUM_7, kp.map(J2.NUM_7, true), "цифры — быстрый путь");
        assertEquals(J2.SOFT_LEFT, kp.map(J2.SOFT_LEFT, true), "софты — как в оригинале");
        assertEquals(J2.NONE, kp.map(J2.NONE, true), "не-цифры — квирк ag++ оригинала");
    }

    @Test
    void keysPassThroughWhenClosed() {
        kp.cursor = 5;
        assertEquals(J2.UP, kp.map(J2.UP, false), "вне сейфа стрелки не трогаем");
        assertEquals(5, kp.cursor);
    }

    @Test
    void cursorResetsOnReopen() {
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.RIGHT}, new int[0]);   // курсор 0 -> 1
        assertEquals(1, kp.cursor);
        // левый софт закрывает сейф БЕЗ открытия паузы (в отличие от правого —
        // тот в оригинале проваливается в aR=true, b.java 985)
        game.tick(new int[]{Input.Keys.F1}, new int[0]);
        assertFalse(safe.open);
        assertFalse(game.pause, "левый софт не открывает паузу");
        openSafeAtSpot();                                     // (пере)открытие
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);   // FIRE -> клетка '1'
        assertEquals(0, kp.cursor, "после повторного открытия курсор снова на '1'");
        assertEquals(2, safe.ag, "клетка '1' дошла до try-хвоста (ag 1 -> 2)");
        assertEquals(1, inv.aQ[9], "и записалась в первую ВИДИМУЮ позицию");
    }

    @Test
    void pulseIsTwoBrightOneDim() {
        assertFalse(SafeKeypad.pulseBright(0), "тик 0 — тускло");
        assertTrue(SafeKeypad.pulseBright(1), "тик 1 — ярко");
        assertTrue(SafeKeypad.pulseBright(2), "тик 2 — ярко");
        assertFalse(SafeKeypad.pulseBright(3), "тик 3 — снова тускло");
    }

    @Test
    void fullCode9819ByDPadOnly() {
        // Весь код вводится ТОЛЬКО стрелками и OK — сценарий Android-тача.
        openSafeAtSpot();                          // ag=1 ('5' в невидимой aQ[8])
        press(9);
        press(8);
        press(1);
        press(9);
        assertEquals(5, safe.ag);
        // курсор на '9' (idx 8); ✓ — idx 9 (ЛЕВЫЙ столбец нижнего ряда):
        // влево-влево-вниз — 8 -> 7 -> 6 -> 9
        game.tick(new int[]{Input.Keys.LEFT}, new int[0]);
        game.tick(new int[]{Input.Keys.LEFT}, new int[0]);
        game.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        assertEquals(SafeKeypad.IDX_OK, kp.cursor);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertFalse(safe.open);
        assertTrue(game.dB, "код 9819 принят вводом с одной клавиатуры");
        assertEquals(9, inv.aQ[9]);
        assertEquals(8, inv.aQ[10]);
        assertEquals(1, inv.aQ[11]);
        assertEquals(9, inv.aQ[12]);
    }

    @Test
    void crossCellClosesByRightSoftSemantics() {
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.NUMPAD_9}, new int[0]);  // что-то в aQ[9]
        inv.aQ[14] = 2;
        // курсор 0 -> ✕ (11): вниз,вниз,вниз,вправо,вправо
        for (int i = 0; i < 3; i++) {
            game.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        }
        game.tick(new int[]{Input.Keys.RIGHT}, new int[0]);
        game.tick(new int[]{Input.Keys.RIGHT}, new int[0]);
        assertEquals(SafeKeypad.IDX_CLOSE, kp.cursor);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertFalse(safe.open, "✕ = правый софт — закрытие");
        assertEquals(0, inv.aQ[8], "byte 954: сброс aQ[8..14]");
        assertEquals(0, inv.aQ[14]);
    }

    /** Навигация курсором к ЦИФРЕ d (int!) и нажатие OK. */
    private void press(int d) {
        int target = d == 0 ? SafeKeypad.IDX_ZERO : d - 1;
        while (kp.cursor / SafeKeypad.COLS < target / SafeKeypad.COLS) {
            game.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        }
        while (kp.cursor / SafeKeypad.COLS > target / SafeKeypad.COLS) {
            game.tick(new int[]{Input.Keys.UP}, new int[0]);
        }
        while (kp.cursor % SafeKeypad.COLS < target % SafeKeypad.COLS) {
            game.tick(new int[]{Input.Keys.RIGHT}, new int[0]);
        }
        while (kp.cursor % SafeKeypad.COLS > target % SafeKeypad.COLS) {
            game.tick(new int[]{Input.Keys.LEFT}, new int[0]);
        }
        assertEquals(target, kp.cursor, "курсор на цифре " + d);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
    }
}
