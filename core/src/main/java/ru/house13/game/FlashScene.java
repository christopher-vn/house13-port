package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.house13.engine.Assets;

/**
 * Сплеш-экран (drawStatus_ = -1, порт b.o() / MainCanvas.drawFlashScreen).
 *
 * 16 тиков по 100 мс: тики 1-9 — белый фон + flash.png (128x128, центр),
 * тики 10-15 — белый фон + sp.png (176x208, центр). На 16-м тике — лого.
 * Ввод игнорируется (в оригинале case -1 в keyPressed пуст).
 */
public final class FlashScene implements Scene {

    private final GameHost host;
    private int bz;      // b.bz (drawCount_)

    public FlashScene(GameHost host) {
        this.host = host;
    }

    @Override
    public void tick(int[] gdxKeyDowns, int[] gdxKeyUps) {
        if (++bz >= 16) {
            host.setScene(new LogoScene(host)); // ++drawStatus_ -> 0 (лого)
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Assets a = host.assets();
        a.fill(batch, 0, 0, 240, 320, 1, 1, 1, 1); // белый, как в o()
        if (bz <= 9) {
            // av = flash.png (полный файл, flipY — конвенция Y-down)
            TextureRegion r = a.region("flash.png");
            batch.draw(r, (240 - r.getRegionWidth()) / 2, (320 - r.getRegionHeight()) / 2);
        } else {
            // d8 = sp.png (сплеш 176x208 — CN-размер, центрируется)
            TextureRegion r = a.region("sp.png");
            batch.draw(r, (240 - r.getRegionWidth()) / 2, (320 - r.getRegionHeight()) / 2);
        }
    }

    @Override
    public void dispose() {
        // текстуры в общем кэше Assets — освобождаются вместе с игрой
    }

    @Override
    public String toString() {
        return "flash bz=" + bz;
    }
}
