package ru.house13.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Viewport с целочисленным масштабированием: мир 240x320 рендерится в окно
 * с множителем N (nearest), изображение центрируется. Дробный масштаб (как у
 * FitViewport) даёт неравномерные пиксели — для пиксель-арта недопустимо.
 *
 * Примеры (мир 240x320):
 *   окно 480x640  -> N=2, без полей
 *   окно 1000x700 -> N=2 (1000/240=4, 700/320=2), поле x=260, y=30
 *   окно 200x200  -> N=1, изображение 240x320 обрезается краями окна
 */
public final class IntegerScaleViewport extends Viewport {

    private final int worldWidth;
    private final int worldHeight;

    /** Последний вычисленный множитель (для отладки/заголовка). */
    private int lastScale = 1;

    public IntegerScaleViewport(int worldWidth, int worldHeight, OrthographicCamera camera) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        setCamera(camera);
        setWorldSize(worldWidth, worldHeight);
    }

    @Override
    public void update(int screenWidth, int screenHeight, boolean centerCamera) {
        lastScale = scaleFor(screenWidth, screenHeight);
        int vw = worldWidth * lastScale;
        int vh = worldHeight * lastScale;
        setScreenBounds((screenWidth - vw) / 2, (screenHeight - vh) / 2, vw, vh);
        // GL-контекст отсутствует в юнит-тестах; на платформах всегда есть
        if (Gdx.gl != null) {
            apply(centerCamera);
        }
    }

    /**
     * Целочисленный множитель для данного окна: >=1.
     * Отдельная чистая функция — тестируется без GL-контекста.
     */
    public int scaleFor(int screenWidth, int screenHeight) {
        int byWidth = screenWidth / worldWidth;
        int byHeight = screenHeight / worldHeight;
        return Math.max(1, Math.min(byWidth, byHeight));
    }

    public int getScale() {
        return lastScale;
    }
}
