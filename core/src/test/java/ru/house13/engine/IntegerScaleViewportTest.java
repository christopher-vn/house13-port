package ru.house13.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Чистая математика IntegerScaleViewport — без GL-контекста.
 */
class IntegerScaleViewportTest {

    private IntegerScaleViewport newViewport() {
        return new IntegerScaleViewport(240, 320, new OrthographicCamera());
    }

    @Test
    void точное_окно_дает_масштаб_1() {
        IntegerScaleViewport v = newViewport();
        assertEquals(1, v.scaleFor(240, 320));
        assertEquals(1, v.scaleFor(241, 321)); // +1px ещё не достаточно для x2
    }

    @Test
    void удвоенное_окно_дает_масштаб_2() {
        IntegerScaleViewport v = newViewport();
        assertEquals(2, v.scaleFor(480, 640));
        assertEquals(2, v.scaleFor(719, 959)); // максимум без перехода на x3
    }

    @Test
    void нецелое_окно_берет_меньший_целый_масштаб() {
        IntegerScaleViewport v = newViewport();
        // 1000/240=4, 700/320=2 -> N=2, т.е. 480x640, поля x=260 y=30
        assertEquals(2, v.scaleFor(1000, 700));
        // портретный монитор 1080x2340: 1080/240=4, 2340/320=7 -> N=4
        assertEquals(4, v.scaleFor(1080, 2340));
    }

    @Test
    void маленькое_окно_не_падает_ниже_масштаба_1() {
        IntegerScaleViewport v = newViewport();
        assertEquals(1, v.scaleFor(200, 200));
        assertEquals(1, v.scaleFor(10, 10));
    }

    @Test
    void update_центрирует_и_заполняет_поля() {
        IntegerScaleViewport v = newViewport();
        v.update(1000, 700, false);
        assertEquals(2, v.getScale());
        assertEquals(260, v.getScreenX());
        assertEquals(30, v.getScreenY());
        assertEquals(480, v.getScreenWidth());
        assertEquals(640, v.getScreenHeight());
    }
}
