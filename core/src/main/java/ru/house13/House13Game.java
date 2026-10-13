package ru.house13;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.house13.engine.Assets;
import ru.house13.engine.InputQueue;
import ru.house13.engine.IntegerScaleViewport;
import ru.house13.game.RoomScene;

/**
 * Корневой класс порта. Фиксирует архитектуру цикла:
 *
 *  - ЛОГИКА: фиксированный шаг 100 мс (10 Гц) — как run() оригинального мидлета
 *    (repaint + serviceRepaints + sleep(100)); сохраняет скорость руки (5 px/тик
 *    = 50 px/с) и все внутриигровые тайминги.
 *  - РЕНДЕР: каждый кадр (vsync), использует состояние после отработки тиков.
 *
 * События ввода (J2ME keyPressed/keyReleased) копятся в InputQueue между тиками.
 */
public class House13Game implements ApplicationListener {

    /** Логическое разрешение RU-версии. */
    public static final int SCREEN_W = 240;
    public static final int SCREEN_H = 320;

    /** Шаг логики, секунд. 1/10 Гц. */
    public static final float STEP = 0.1f;

    /** Защита от лагспайков (alt-tab, точка останова): не копим долг "прошлого". */
    private static final float MAX_FRAME_DELTA = 0.25f;

    private final InputQueue input = new InputQueue();

    private OrthographicCamera camera;
    private IntegerScaleViewport viewport;
    private SpriteBatch batch;
    private Assets assets;

    private RoomScene scene;

    private float accumulator;
    private long tickCount;
    private int renderCount;
    private boolean dumped;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, SCREEN_W, SCREEN_H); // Y вниз, как в J2ME
        viewport = new IntegerScaleViewport(SCREEN_W, SCREEN_H, camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        batch = new SpriteBatch();
        assets = new Assets();

        scene = new RoomScene(assets);

        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        // 1) Накопление времени и логические тики
        float delta = Math.min(Gdx.graphics.getDeltaTime(), MAX_FRAME_DELTA);
        accumulator += delta;
        while (accumulator >= STEP) {
            accumulator -= STEP;
            tickCount++;
            scene.tick(input.drainKeyDowns(), input.drainKeyUps());
        }

        // 2) Рендер текущего состояния (vsync)
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        scene.render(batch);
        batch.end();

        // 3) Отладочный пульс раз в ~2 с (на этапе 3 заменим на заголовок окна/оверлей)
        if (++renderCount % 120 == 0) {
            Gdx.app.log("House13",
                "fps=" + Gdx.graphics.getFramesPerSecond()
                + " scale=x" + viewport.getScale()
                + " ticks=" + tickCount
                + " window=" + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight()
                + " scene=" + scene);
        }

        // 4) Опциональный дамп framebuffer (CI/песочница): -Dhouse13.dump=<имя.png>
        //    («true» -> frame_dump.png). Сохраняет первый отрисованный кадр.
        if (!dumped && System.getProperty("house13.dump") != null) {
            dumped = true;
            String name = System.getProperty("house13.dump");
            if ("true".equals(name)) {
                name = "frame_dump.png";
            }
            try {
                Pixmap pm = ScreenUtils.getFrameBufferPixmap(0, 0,
                    Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                Pixmap fixed = flipRows(pm); // glReadPixels: снизу-вверх -> PNG как на экране
                pm.dispose();
                FileHandle out = Gdx.files.local(name);
                PixmapIO.writePNG(out, fixed);
                fixed.dispose();
                Gdx.app.log("House13", "dump -> " + out.file().getAbsolutePath());
            } catch (Exception e) {
                Gdx.app.error("House13", "dump failed", e);
            }
        }
    }

    @Override
    public void pause() {
        // На этапе 3: сюда ляжет pauseApp() мидлета (сохранение состояния)
    }

    @Override
    public void resume() {
        // На этапе 3: startApp()
    }

    /** Отразить Pixmap по строкам (низ<->верх): glReadPixels отдаёт кадр снизу-вверх. */
    private static Pixmap flipRows(Pixmap src) {
        int w = src.getWidth(), h = src.getHeight();
        int stride = w * 4;
        Pixmap dst = new Pixmap(w, h, Pixmap.Format.RGBA8888);
        java.nio.ByteBuffer s = src.getPixels();
        java.nio.ByteBuffer d = dst.getPixels();
        for (int y = 0; y < h; y++) {
            s.position((h - 1 - y) * stride);
            s.limit(s.position() + stride);
            d.position(y * stride);
            d.put(s);
        }
        s.limit(s.capacity());
        return dst;
    }

    @Override
    public void dispose() {
        scene.dispose();
        batch.dispose();
        assets.dispose();
    }
}
