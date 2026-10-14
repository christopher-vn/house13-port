package ru.house13.engine;

import com.badlogic.gdx.Input;

/**
 * Маппинг физических клавиш десктопа на J2ME-семантику оригинала.
 *
 * Оригинал (RU, Nokia FullCanvas) использует коды:
 *   -1 вверх, -2 вниз, -3 влево, -4 вправо, -5 FIRE (центр джойстика),
 *   -6 левая софт-клавиша, -7 правая софт-клавиша, '0'..'9' = 48..57.
 *
 * Раскладка ПОРТА (этап 5b, «RPG Maker по-своему»; см. README):
 *   стрелки        — движение руки / навигация меню
 *   Enter, Space   — OK (FIRE)
 *   Z              — инвентарь (он же '1' оригинала — карусель предметов)
 *   X, Esc         — отмена / меню игры (правый софт)
 *   Shift (удерж.) — ускорение руки x2 (см. KeyMap.isShift, GameScene)
 *   0..9           — ввод кода сейфа (вне сейфа '1' — инвентарь, как раньше)
 *
 * F1 (левый софт) оставлен как ОТЛАДОЧНАЯ клавиша: она нужна скриптам
 * автотеста (-Dhouse13.script, токен Ls) и дублирует FIRE в диалогах.
 * WASD и F2/Backspace убраны (этап 3) — раскладка сознательно минимальна.
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
                return J2.UP;
            case Input.Keys.DOWN:
                return J2.DOWN;
            case Input.Keys.LEFT:
                return J2.LEFT;
            case Input.Keys.RIGHT:
                return J2.RIGHT;
            // OK: центр джойстика (-5) + Enter/Space.
            case Input.Keys.CENTER:
            case Input.Keys.ENTER:
            case Input.Keys.SPACE:
                return J2.FIRE;
            // Z = инвентарь: порт '1' (карусель предметов, Inventory/byte 1067).
            case Input.Keys.Z:
                return J2.NUM_1;
            // Отмена / меню игры: X и Esc = правый софт (-7).
            case Input.Keys.X:
            case Input.Keys.ESCAPE:
                return J2.SOFT_RIGHT;
            // Отладочная клавиша левого софта (-6): токен автотеста Ls.
            case Input.Keys.F1:
                return J2.SOFT_LEFT;
            // Цифры: ВВОД КОДА СЕЙФА (0–9; '5' — подтверждение, как в оригинале).
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

    /**
     * Модификатор ускорения (дополнение порта, в оригинале нет):
     * удержание Shift — рука движется 10 px/тик вместо 5 (два шага
     * по 5 px с проверкой коллизий на каждом — туннелирование исключено).
     * Обрабатывается вне J2ME-модели: GameScene.tick по «сырым» gdx-кодам.
     */
    public static boolean isShift(int gdxKey) {
        return gdxKey == Input.Keys.SHIFT_LEFT || gdxKey == Input.Keys.SHIFT_RIGHT;
    }
}
