package ru.house13.engine;

import com.badlogic.gdx.Input;

/**
 * Маппинг физических клавиш десктопа на J2ME-семантику оригинала.
 *
 * Оригинал (RU, Nokia FullCanvas) использует коды:
 *   -1 вверх, -2 вниз, -3 влево, -4 вправо, -5 FIRE (центр джойстика),
 *   -6 левая софт-клавиша, -7 правая софт-клавиша, '0'..'9' = 48..57.
 *
 * Порт дополняет (в оригинале отсутствует, описано в README):
 *   WASD — синонимы стрелок; Space/Z — синонимы FIRE; Backspace — правый софт.
 */
public final class KeyMap {

    /** Логические клавиши J2ME. */
    public enum J2 {
        UP, DOWN, LEFT, RIGHT,
        FIRE, SOFT_LEFT, SOFT_RIGHT,
        NUM_0, NUM_1, NUM_2, NUM_3, NUM_4,
        NUM_5, NUM_6, NUM_7, NUM_8, NUM_9,
        NONE
    }

    private KeyMap() {
    }

    /** Перевод keycode libGDX в логическую клавишу J2ME. */
    public static J2 map(int gdxKey) {
        switch (gdxKey) {
            case Input.Keys.UP:
            case Input.Keys.W:
                return J2.UP;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                return J2.DOWN;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                return J2.LEFT;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                return J2.RIGHT;
            // FIRE: центр джойстика (-5). Enter/Space/Z — «5»-эквиваленты выбора.
            case Input.Keys.CENTER:
            case Input.Keys.ENTER:
            case Input.Keys.SPACE:
            case Input.Keys.Z:
                return J2.FIRE;
            // Левая софт-клавиша (-6): F1. Все её функции дублированы FIRE.
            case Input.Keys.F1:
                return J2.SOFT_LEFT;
            // Правая софт-клавиша (-7): Esc/F2/Backspace (меню игры, отмена).
            case Input.Keys.ESCAPE:
            case Input.Keys.F2:
            case Input.Keys.BACKSPACE:
                return J2.SOFT_RIGHT;
            // Цифры: верхний ряд и numpad ('0'..'9' = 48..57).
            case Input.Keys.NUM_0:
            case Input.Keys.NUMPAD_0:
                return J2.NUM_0;
            case Input.Keys.NUM_1:
            case Input.Keys.NUMPAD_1:
                return J2.NUM_1;
            case Input.Keys.NUM_2:
            case Input.Keys.NUMPAD_2:
                return J2.NUM_2;
            case Input.Keys.NUM_3:
            case Input.Keys.NUMPAD_3:
                return J2.NUM_3;
            case Input.Keys.NUM_4:
            case Input.Keys.NUMPAD_4:
                return J2.NUM_4;
            case Input.Keys.NUM_5:
            case Input.Keys.NUMPAD_5:
                return J2.NUM_5;
            case Input.Keys.NUM_6:
            case Input.Keys.NUMPAD_6:
                return J2.NUM_6;
            case Input.Keys.NUM_7:
            case Input.Keys.NUMPAD_7:
                return J2.NUM_7;
            case Input.Keys.NUM_8:
            case Input.Keys.NUMPAD_8:
                return J2.NUM_8;
            case Input.Keys.NUM_9:
            case Input.Keys.NUMPAD_9:
                return J2.NUM_9;
            default:
                return J2.NONE;
        }
    }
}
