package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Сцена = состояние drawStatus_ оригинала (-1 flash, 0 logo, 1 menu, 2 game).
 *
 * Семантика тика: все события ввода, накопленные с прошлого тика (keyDown
 * до keyUp, порядок сохранён), плюс чистая логика 10 Гц. Рендер отдельно
 * (vsync) и в тестах не вызывается.
 */
public interface Scene {

    /** Один логический тик (10 Гц). Массивы — keycode libGDX. */
    void tick(int[] gdxKeyDowns, int[] gdxKeyUps);

    /** Рендер текущего состояния. Координаты J2ME: (0,0) левый верх, Y вниз. */
    void render(SpriteBatch batch);

    /** Освобождение ресурсов сцены (FreeMenuSourse/FreeGameSourse-аналог). */
    void dispose();
}
