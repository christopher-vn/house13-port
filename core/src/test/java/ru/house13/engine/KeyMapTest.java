package ru.house13.engine;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.Test;
import ru.house13.engine.KeyMap.J2;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Маппинг физических клавиш на J2ME-семантику (стрелки/WASD/софты/цифры). */
class KeyMapTest {

    @Test
    void arrowsMapToDirections() {
        assertEquals(J2.UP, KeyMap.map(Input.Keys.UP));
        assertEquals(J2.DOWN, KeyMap.map(Input.Keys.DOWN));
        assertEquals(J2.LEFT, KeyMap.map(Input.Keys.LEFT));
        assertEquals(J2.RIGHT, KeyMap.map(Input.Keys.RIGHT));
    }

    @Test
    void wasdMapsToDirections() {
        assertEquals(J2.UP, KeyMap.map(Input.Keys.W));
        assertEquals(J2.DOWN, KeyMap.map(Input.Keys.S));
        assertEquals(J2.LEFT, KeyMap.map(Input.Keys.A));
        assertEquals(J2.RIGHT, KeyMap.map(Input.Keys.D));
    }

    @Test
    void gameDigitsMapToItuDigits() {
        // J2ME: '2'/'4'/'6'/'8' — движение, '5' — действие, '1' — инвентарь
        assertEquals(J2.NUM_2, KeyMap.map(Input.Keys.NUM_2));
        assertEquals(J2.NUM_4, KeyMap.map(Input.Keys.NUM_4));
        assertEquals(J2.NUM_5, KeyMap.map(Input.Keys.NUM_5));
        assertEquals(J2.NUM_1, KeyMap.map(Input.Keys.NUM_1));
        assertEquals(J2.NUM_0, KeyMap.map(Input.Keys.NUM_0));
        assertEquals(J2.NUM_9, KeyMap.map(Input.Keys.NUM_9));
    }

    @Test
    void numpadMapsLikeMainRow() {
        assertEquals(J2.NUM_5, KeyMap.map(Input.Keys.NUMPAD_5));
        assertEquals(J2.NUM_8, KeyMap.map(Input.Keys.NUMPAD_8));
    }

    @Test
    void fireAndSoftKeys() {
        // FIRE (-5): центр джойстика + десктоп-эквиваленты
        assertEquals(J2.FIRE, KeyMap.map(Input.Keys.CENTER));
        assertEquals(J2.FIRE, KeyMap.map(Input.Keys.ENTER));
        assertEquals(J2.FIRE, KeyMap.map(Input.Keys.SPACE));
        assertEquals(J2.FIRE, KeyMap.map(Input.Keys.Z));
        // Софт-клавиши (-6/-7)
        assertEquals(J2.SOFT_LEFT, KeyMap.map(Input.Keys.F1));
        assertEquals(J2.SOFT_RIGHT, KeyMap.map(Input.Keys.ESCAPE));
        assertEquals(J2.SOFT_RIGHT, KeyMap.map(Input.Keys.F2));
        assertEquals(J2.SOFT_RIGHT, KeyMap.map(Input.Keys.BACKSPACE));
    }

    @Test
    void unknownKeysAreNone() {
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.TAB));
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.F10));  // отладка — вне J2ME-модели
        assertEquals(J2.NONE, KeyMap.map(Input.Keys.PAGE_UP));
        assertEquals(J2.NONE, KeyMap.map(-999));
    }
}
