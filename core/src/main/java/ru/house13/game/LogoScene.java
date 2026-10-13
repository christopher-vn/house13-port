package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.house13.engine.Assets;
import ru.house13.engine.SoundManager;

/**
 * Экран логотипа (drawStatus_ = 0, порт b.d() / MainCanvas.drawXLogo).
 *
 * Чёрный фон; LOGO.png 480x320 = две половины 240x320: левая рисуется
 * всегда, правая — «мигание» (эффект проявления):
 *   if (Timer/20 % 2 == 0 && js == 1) { js = 0; показать }
 *   else if (Timer/10 % 2 == 0 && ++js1/10 % 2 == 0) показать
 * Timer — глобальный тик 10 Гц (host.tick(), аналог b.e). LOGO1 (титул)
 * вверху, logore («нажмите любую клавишу») внизу.
 *
 * Здесь стартует музыка меню: changeClosedMid(4) + createBackMid(0) +
 * playBackMid(0) — как в d(), при каждом кадре (идемпотентно).
 *
 * ЛЮБАЯ клавиша -> меню (b.for(int): без фильтра клавиш).
 */
public final class LogoScene implements Scene {

    private final GameHost host;
    private int js = 1;    // b.bZ: после первого показа правой половины = 0
    private int js1 = 0;   // b.bj: счётчик мигания (растёт по ТИКАМ, как paint 10 Гц)
    private boolean showHalf2;

    public LogoScene(GameHost host) {
        this.host = host;
    }

    @Override
    public void tick(int[] gdxKeyDowns, int[] gdxKeyUps) {
        if (gdxKeyDowns.length > 0) {
            host.setScene(new MenuScene(host)); // for(var1): bQ=null, b0=1
            return;
        }
        // мигание (в оригинале — в paint(), т.е. 10 Гц; НЕ в рендере 60+ fps)
        int timer = (int) host.tick();
        showHalf2 = false;
        if (timer / 20 % 2 == 0 && js == 1) {
            js = 0;
            showHalf2 = true;
        } else if (timer / 10 % 2 == 0 && ++js1 / 10 % 2 == 0) {
            showHalf2 = true;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Assets a = host.assets();
        SoundManager snd = host.sound();

        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
        batch.draw(a.region("LOGO.png", 0, 0, 240, 320, false, true), 0, 0);

        // музыка меню (как в d(): int(4); a(0); new(0))
        snd.changeClosedMid(SoundManager.CH_MAPBG);
        snd.createBackMid(SoundManager.CH_MENU);
        snd.playBackMid(SoundManager.CH_MENU);

        // мигание правой половиной (состояние вычислено в тике)
        if (showHalf2) {
            TextureRegion half2 = a.region("LOGO.png", 240, 0, 240, 320, false, true);
            batch.draw(half2, 0, 0);
        }

        // logore (111x27) внизу, LOGO1 (148x100) вверху — поверх
        batch.draw(a.region("logore.png"), (240 - 111) / 2, 320 - 27 - 15);
        batch.draw(a.region("LOGO1.png"), (240 - 148) / 2, 10);
    }

    @Override
    public void dispose() {
    }

    @Override
    public String toString() {
        return "logo js=" + js + " js1=" + js1;
    }
}
