package ru.house13.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Загрузчик и хранилище текстур. Nearest-фильтрация обязательна:
 * дробная билинейная фильтрация размывает пиксель-арт.
 * Ассеты ищутся через Gdx.files.internal (classpath / assets).
 *
 * Конвенция этапа 2 (выверена дампами): все регионы создаются с
 * flip(false,true) — SpriteBatch с Y-down камерой иначе рисует вверх ногами.
 */
public final class Assets implements Disposable {

    private final ObjectMap<String, Texture> textures = new ObjectMap<>();
    private final ObjectMap<String, TextureRegion> regions = new ObjectMap<>();
    private Texture white;

    /** Текстура по имени файла в assets (кэшируется; первая загрузка — из internal). */
    public Texture tex(String name) {
        Texture t = textures.get(name);
        if (t == null) {
            t = new Texture(Gdx.files.internal(name));
            t.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
            textures.put(name, t);
        }
        return t;
    }

    /** Весь файл как регион, перевёрнутый по вертикали (конвенция Y-down). */
    public TextureRegion region(String file) {
        Texture t = tex(file);
        return region(file, 0, 0, t.getWidth(), t.getHeight(), false, true);
    }

    /** Часть файла БЕЗ переворота (для особых случаев). */
    public TextureRegion region(String file, int sx, int sy, int w, int h) {
        return region(file, sx, sy, w, h, false, false);
    }

    /** Часть файла с полным контролем переворота (кэшируется по всем параметрам). */
    public TextureRegion region(String file, int sx, int sy, int w, int h, boolean fx, boolean fy) {
        String key = file + ':' + sx + ',' + sy + ',' + w + ',' + h + (fx ? 'F' : '-') + (fy ? 'F' : '-');
        TextureRegion r = regions.get(key);
        if (r == null) {
            r = new TextureRegion(tex(file), sx, sy, w, h);
            r.flip(fx, fy);
            regions.put(key, r);
        }
        return r;
    }

    /** Белая текстура 1x1 для заливок (setColor + draw с размером). */
    public Texture white() {
        if (white == null) {
            Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pm.setColor(1, 1, 1, 1);
            pm.fill();
            white = new Texture(pm);
            pm.dispose();
        }
        return white;
    }

    /** Заливка прямоугольника цветом (аналог Graphics.setColor+fillRect). */
    public void fill(SpriteBatch batch, int x, int y, int w, int h,
                     float r, float g, float b, float a) {
        // ВАЖНО: batch.getColor() возвращает ЖИВУЮ ссылку на внутренний Color;
        // без cpy() восстановление после setColor(...) тонировало бы всё
        // последующее в цвет заливки (поймано дампами этапа 3).
        Color save = batch.getColor().cpy();
        batch.setColor(r, g, b, a);
        batch.draw(white(), x, y, w, h);
        batch.setColor(save);
    }

    @Override
    public void dispose() {
        for (Texture t : textures.values()) {
            t.dispose();
        }
        textures.clear();
        regions.clear();
        if (white != null) {
            white.dispose();
            white = null;
        }
    }
}
