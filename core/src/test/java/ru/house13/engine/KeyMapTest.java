package ru.house13.engine;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.Test;
import ru.house13.engine.KeyMap.J2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Маппинг физических клавиш на J2ME-семантику.
 * Раскладка порта (этап 5b, «RPG Maker по-своему»):
 * стрелки / Enter+Space=OK / Z=инвентарь('1') / X+Esc=меню(правый софт) /
 * Shift=ускорение / 0-9=сейф. WASD, F2, Backspace и Z-as-FIRE убраны.
 */
class KeyMapTest {

    @Test
    void arrowsMapToDirections() {
        assertEquals(J2.UP, KeyMap.map(Input.Keys.UP));
        assertEquals(J2.DOWN, KeyMap.map(Input.Keys.DOWN));
        assertEquals(J2.LEFT, KeyMap.map(Input.Keys.LEFT));
        assertEquals(J2.RIGHT, KeyMap.map(Input.Keys.RIGHT));
    }

    @Test
    void wasdIsRemovedByDesign() {
        // Этап 5b: WASD сознательно не задействован (решение пользователя)
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.W));
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.A));
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.S));
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.D));
    }

    @Test
    void digitsMapToItuDigits() {
        // Цифры остались только для ввода кода сейфа (+ '5' = подтверждение)
        for (int d = 0; d <= 9; d++) {
            assertEquals(J2.values()[J2.NUM_0.ordinal() + d],
                    KeyMap.map(Input.Keys.NUM_0 + d), "цифра " + d);
        }
    }

    @Test
    void numpadMapsLikeMainRow() {
        assertEquals(J2.NUM_5, KeyMap.map(Input.Keys.NUMPAD_5));
        assertEquals(J2.NUM_8, KeyMap.map(Input.Keys.NUMPAD_8));
    }

    @Test
    void okKeysAreFire() {
        // OK: центр джойстика + Enter/Space (Z больше НЕ FIRE)
        assertEquals(J2.FIRE, KeyMap.map(Input.Keys.CENTER));
        assertEquals(J2.FIRE, KeyMap.map(Input.Keys.ENTER));
        assertEquals(J2.FIRE, KeyMap.map(Input.Keys.SPACE));
    }

    @Test
    void zOpensInventory() {
        // Z = '1' оригинала: карусель предметов (Inventory/byte 1067)
        assertEquals(J2.NUM_1, KeyMap.map(Input.Keys.Z));
    }

    @Test
    void cancelAndMenuAreRightSoft() {
        // Отмена/меню: X и Esc = правый софт (-7); F2/Backspace убраны
        assertEquals(J2.SOFT_RIGHT, KeyMap.map(Input.Keys.X));
        assertEquals(J2.SOFT_RIGHT, KeyMap.map(Input.Keys.ESCAPE));
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.F2));
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.BACKSPACE));
    }

    @Test
    void f1StaysAsDebugSoftLeft() {
        // Левый софт: только F1 (токен автотеста Ls)
        assertEquals(J2.SOFT_LEFT, KeyMap.map(Input.Keys.F1));
    }

    @Test
    void shiftIsAccelerator() {
        assertTrue(KeyMap.isShift(Input.Keys.SHIFT_LEFT));
        assertTrue(KeyMap.isShift(Input.Keys.SHIFT_RIGHT));
        assertFalse(KeyMap.isShift(Input.Keys.Z));
        assertFalse(KeyMap.isShift(Input.Keys.CONTROL_LEFT));
        // Shift — не J2ME-клавиша: в map() не участвует
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.SHIFT_LEFT));
    }

    @Test
    void unknownKeysAreNone() {
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.TAB));
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.F10));  // отладка — вне J2ME-модели
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.PAGE_UP));
        assertEquals(J2.NONE, KeyMap.map(-999));
    }
}
