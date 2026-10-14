package ru.house13;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayDeque;
import ru.house13.engine.Assets;
import ru.house13.engine.Fonts;
import ru.house13.engine.InputQueue;
import ru.house13.engine.IntegerScaleViewport;
import ru.house13.engine.SoundManager;
import ru.house13.game.FlashScene;
import ru.house13.game.GameHost;
import ru.house13.game.GameScene;
import ru.house13.game.LogoScene;
import ru.house13.game.MenuScene;
import ru.house13.game.Scene;

/**
 * Корневой класс порта: машина состояний + фиксированный цикл.
 *
 *  - ЛОГИКА: фиксированный шаг 100 мс (10 Гц) — как run() оригинала
 *    (repaint + serviceRepaints + sleep(100)); сохраняет скорость руки
 *    (5 px/тик) и все внутриигровые тайминги (слайды меню, мигание лого).
 *  - РЕНДЕР: каждый кадр (vsync).
 *  - СЦЕНЫ = drawStatus_ оригинала: FlashScene(-1) -> LogoScene(0) ->
 *    MenuScene(1) <-> GameScene(2).
 *
 * События ввода копятся в InputQueue между тиками; внутри тика нажатия
 * обрабатываются раньше отпусканий (J2ME-порядок press->release).
 *
 * Отладочные свойства (десктоп/CI):
 *   house13.start=flash|logo|menu|game — стартовая сцена (по умолчанию flash);
 *   house13.script=U,D,F,Ls,Rs,w25,... — автотест: одна клавиша на тик
 *     (U/D/L/R — направления, F — FIRE, Ls/Rs — софт-клавиши, 0-9 — цифры,
 *     wN — пауза N тиков);
 *   house13.dump=<файл.png|true> — дамп framebuffer;
 *   house13.dump.tick=N — номер тика для дампа (0 = первый кадр);
 *   house13.room=N — стартовая комната (этап 2);
 *   house13.audio=false — отключить аудио (headless-прогоны).
 */
public class House13Game implements ApplicationListener, GameHost {

    /** Логическое разрешение RU-версии. */
    public static final int SCREEN_W = 240;
    public static final int SCREEN_H = 320;

    /** Шаг логики, секунд. 1/10 Гц. */
    public static final float STEP = 0.1f;

    /** Защита от лагспайков (alt-tab, точка останова): не копим долг «прошлого». */
    private static final float MAX_FRAME_DELTA = 0.25f;

    private final InputQueue input = new InputQueue();

    private OrthographicCamera camera;
    private IntegerScaleViewport viewport;
    private SpriteBatch batch;
    private Assets assets;
    private SoundManager sound;
    private Fonts fonts;

    private Scene scene;
    private Scene pendingScene;

    private float accumulator;
    private long tickCount;
    private int renderCount;

    // дамп (CI/песочница)
    private String dumpName;
    private int dumpTick;
    private boolean dumped;

    // автотест-скрипт
    private final ArrayDeque<String> script = new ArrayDeque<>();
    private int scriptWait;
    private int scriptHoldKey = -1;   // hold-токен: зажатая клавиша

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, SCREEN_W, SCREEN_H); // Y вниз, как в J2ME
        viewport = new IntegerScaleViewport(SCREEN_W, SCREEN_H, camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        batch = new SpriteBatch();
        assets = new Assets();
        sound = SoundManager.createDefault();
        fonts = new Fonts();
        // ЭТАП 5c: сохранённое состояние звука (настройки персистятся)
        SoundManager.canSound = !"off".equals(
                ru.house13.engine.Settings.get("sound", "on"));

        parseDebugProps();
        scene = initialScene();

        Gdx.input.setInputProcessor(input);
    }

    private void parseDebugProps() {
        String d = System.getProperty("house13.dump");
        if (d != null) {
            dumpName = "true".equals(d) ? "frame_dump.png" : d;
        }
        try {
            dumpTick = Integer.getInteger("house13.dump.tick", 0);
        } catch (NumberFormatException ignored) {
        }
        // ЭТАП 5: стартовый пак текстов (dump-прогоны других изданий).
        // Не персистится: после перезапуска действует сохранённый выбор.
        String tp = System.getProperty("house13.textpack");
        if (tp != null && !tp.isEmpty()) {
            ru.house13.game.Texts.reload(tp);
        }
        String s = System.getProperty("house13.script");
        if (s != null && !s.isEmpty()) {
            for (String t : s.split(",")) {
                t = t.trim();
                if (!t.isEmpty()) {
                    script.add(t);
                }
            }
            if (!script.isEmpty()) {
                Gdx.app.log("House13", "script: " + s);
            }
        }
    }

    private Scene initialScene() {
        String start = System.getProperty("house13.start", "flash");
        switch (start) {
            case "logo":  return new LogoScene(this);
            case "menu":  return new MenuScene(this);
            case "game":  return new GameScene(this);
            case "flash": return new FlashScene(this);
            default:
                Gdx.app.error("House13", "неизвестная house13.start=" + start + ", стартуем flash");
                return new FlashScene(this);
        }
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
            injectScript();
            Scene s = scene;
            s.tick(input.drainKeyDowns(), input.drainKeyUps());
            if (pendingScene != null) { // сцена сменила себя в тике
                s.dispose();
                scene = pendingScene;
                pendingScene = null;
                Gdx.app.log("House13", "scene -> " + scene.getClass().getSimpleName());
            }
        }

        // 2) Рендер текущего состояния (vsync)
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        scene.render(batch);
        batch.end();

        // 3) Пульс раз в ~2 с
        if (++renderCount % 120 == 0) {
            Gdx.app.log("House13",
                "fps=" + Gdx.graphics.getFramesPerSecond()
                + " scale=x" + viewport.getScale()
                + " ticks=" + tickCount
                + " window=" + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight()
                + " scene=" + scene);
        }

        // 4) Дамп framebuffer: после рендера тика >= dumpTick
        if (!dumped && dumpName != null && tickCount >= dumpTick) {
            dumped = true;
            try {
                Pixmap pm = ScreenUtils.getFrameBufferPixmap(0, 0,
                    Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                Pixmap fixed = flipRows(pm); // glReadPixels: снизу-вверх -> PNG как на экране
                pm.dispose();
                FileHandle out = Gdx.files.local(dumpName);
                PixmapIO.writePNG(out, fixed);
                fixed.dispose();
                Gdx.app.log("House13", "dump@" + tickCount + " -> " + out.file().getAbsolutePath());
            } catch (Exception e) {
                Gdx.app.error("House13", "dump failed", e);
            }
        }
    }

    /** Одна клавиша автотеста на текущий тик (нажатие + отпускание). */
    private void injectScript() {
        if (script.isEmpty()) {
            if (scriptHoldKey >= 0) {          // страховка: отпустить зажатое
                input.injectUp(scriptHoldKey);
                scriptHoldKey = -1;
            }
            return;
        }
        if (scriptWait > 0) {
            scriptWait--;
            if (scriptWait == 0 && scriptHoldKey >= 0) {
                input.injectUp(scriptHoldKey);  // hold-токен завершён
                scriptHoldKey = -1;
            }
            return;
        }
        String t = script.poll();
        if (t.charAt(0) == 'w' || t.charAt(0) == 'W') {
            try {
                scriptWait = Integer.parseInt(t.substring(1)) - 1;
            } catch (NumberFormatException e) {
                scriptWait = 0;
            }
            return;
        }
        // Составные токены этапа 4: UF/DF/LF/RF — направление + FIRE
        // в одном тике. После фикса защёлки направлений (Hand.release не
        // сбрасывает dQ/c/b/b3 — как keyReleased оригинала) обычный F
        // тоже попадает в хот-зоны; UF оставлен для совместимости сценариев.
        if (t.length() == 2 && t.charAt(1) == 'F') {
            int dir = scriptKey(t.substring(0, 1));
            if (dir >= 0) {
                input.injectDown(dir);
                input.injectDown(com.badlogic.gdx.Input.Keys.ENTER);
                input.injectUp(dir);
                input.injectUp(com.badlogic.gdx.Input.Keys.ENTER);
                return;
            }
        }
        // Hold-токены этапа 4: u15/d3/l18/r4 — зажать направление на N тиков
        // (реальная ходьба; блокировка стены останавливает руку раньше)
        char c0 = t.charAt(0);
        if (t.length() > 1 && (c0 == 'u' || c0 == 'd' || c0 == 'l' || c0 == 'r')) {
            try {
                int hold = Integer.parseInt(t.substring(1));
                int dir = scriptKey(t.substring(0, 1).toUpperCase());
                if (dir >= 0 && hold > 0) {
                    input.injectDown(dir);
                    scriptHoldKey = dir;
                    scriptWait = hold - 1;
                    return;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        int gdx = scriptKey(t);
        if (gdx >= 0) {
            input.injectDown(gdx);
            input.injectUp(gdx);
        } else {
            Gdx.app.error("House13", "неизвестный токен скрипта: " + t);
        }
    }

    private static int scriptKey(String t) {
        switch (t) {
            case "U":  return com.badlogic.gdx.Input.Keys.UP;
            case "D":  return com.badlogic.gdx.Input.Keys.DOWN;
            case "L":  return com.badlogic.gdx.Input.Keys.LEFT;
            case "R":  return com.badlogic.gdx.Input.Keys.RIGHT;
            case "F":  return com.badlogic.gdx.Input.Keys.ENTER;
            case "Ls": return com.badlogic.gdx.Input.Keys.F1;
            case "Rs": return com.badlogic.gdx.Input.Keys.ESCAPE;
            default:
                if (t.length() == 1 && t.charAt(0) >= '0' && t.charAt(0) <= '9') {
                    return com.badlogic.gdx.Input.Keys.NUM_0 + (t.charAt(0) - '0');
                }
                return -1;
        }
    }

    // ---- GameHost ----

    @Override
    public Assets assets() {
        return assets;
    }

    @Override
    public SoundManager sound() {
        return sound;
    }

    @Override
    public Fonts fonts() {
        return fonts;
    }

    @Override
    public void setScene(Scene next) {
        pendingScene = next; // применится после завершения текущего тика
    }

    @Override
    public void requestExit() {
        Gdx.app.exit(); // TheMIDlet.quitApp() -> notifyDestroyed
    }

    @Override
    public long tick() {
        return tickCount; // Timer/e оригинала
    }

    // ---- жизненный цикл ----

    @Override
    public void pause() {
        sound.pauseAll(); // pauseApp: музыка молчит без фокуса
    }

    @Override
    public void resume() {
        sound.resumeAll();
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
        sound.dispose();
        fonts.dispose();
    }
}
