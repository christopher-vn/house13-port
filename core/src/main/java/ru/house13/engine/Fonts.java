package ru.house13.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;

/**
 * Шрифт порта. В оригинале — системный шрифт телефона Font.getFont(0,0,8)
 * (SMALL, пропорциональный): кириллица «бесплатно» из прошивки K700.
 * В порте системного шрифта нет, поэтому FreeType + DejaVu Sans (свободная
 * лицензия, полный набор кириллицы, см. fonts/DejaVuSans-LICENSE.txt).
 *
 * ЭТАП 5 (перевод CN->RU) заменит этот шрифт на финальный; сейчас 15px —
 * приблизительный аналог SMALL у Sony Ericsson.
 *
 * Конвенция координат — как J2ME Graphics.drawString: (x, y) = ЛЕВЫЙ ВЕРХНИЙ
 * угол строки. libGDX BitmapFont.draw считает y базовой линией, поэтому
 * внутри добавляется ascent. Сгенерированный шрифт flip=true (камера Y-вниз).
 */
public final class Fonts implements Disposable {

    /** Размер SMALL-аналога. */
    public static final int SMALL_SIZE = 15;

    /** Кириллица + типографские знаки сверх ASCII (DEFAULT_CHARS). */
    private static final String CYRILLIC =
        "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
        + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
        + "№«»—…„“";

    private final BitmapFont font;
    private final float ascent;
    private final GlyphLayout layout = new GlyphLayout();

    public Fonts() {
        FreeTypeFontGenerator gen =
            new FreeTypeFontGenerator(Gdx.files.internal("fonts/DejaVuSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p =
            new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = SMALL_SIZE;
        p.characters = FreeTypeFontGenerator.DEFAULT_CHARS + CYRILLIC;
        p.flip = true; // камера Y-вниз, как в J2ME
        p.magFilter = Texture.TextureFilter.Nearest;
        p.minFilter = Texture.TextureFilter.Nearest;
        font = gen.generateFont(p);
        gen.dispose();
        ascent = font.getAscent();
    }

    /** J2ME Font.stringWidth(). */
    public int stringWidth(String s) {
        layout.setText(font, s);
        return (int) layout.width;
    }

    /** J2ME Font.getHeight(). */
    public int height() {
        return (int) font.getLineHeight();
    }

    /** Обычный drawString (якорь — левый верхний угол, цвет 0..255 как в J2ME). */
    public void draw(SpriteBatch batch, String s, int x, int y, int r, int g, int b) {
        font.setColor(r / 255f, g / 255f, b / 255f, 1f);
        font.draw(batch, s, x, y + ascent);
    }

    /**
     * heidrawString/a(String,int,int,int) оригинала: тень (149,147,144)
     * в четырёх смещениях ±1px, затем белый текст по центру.
     */
    public void drawShadow(SpriteBatch batch, String s, int x, int y) {
        font.setColor(149 / 255f, 147 / 255f, 144 / 255f, 1f);
        font.draw(batch, s, x + 1, y + ascent);
        font.draw(batch, s, x - 1, y + ascent);
        font.draw(batch, s, x, y + ascent + 1);
        font.draw(batch, s, x, y + ascent - 1);
        font.setColor(1f, 1f, 1f, 1f);
        font.draw(batch, s, x, y + ascent);
    }

    /** ЭТАП 5: как drawShadow, но основной текст заданного цвета 0..255
     *  (активная/неактивная строка выбора перевода). */
    public void drawShadow(SpriteBatch batch, String s, int x, int y, int r, int g, int b) {
        font.setColor(149 / 255f, 147 / 255f, 144 / 255f, 1f);
        font.draw(batch, s, x + 1, y + ascent);
        font.draw(batch, s, x - 1, y + ascent);
        font.draw(batch, s, x, y + ascent + 1);
        font.draw(batch, s, x, y + ascent - 1);
        font.setColor(r / 255f, g / 255f, b / 255f, 1f);
        font.draw(batch, s, x, y + ascent);
    }

    /** Строка, центрированная по ширине (аналог (W-stringWidth)/2 в оригинале). */
    public void drawCentered(SpriteBatch batch, String s, int y, int r, int g, int b) {
        draw(batch, s, (240 - stringWidth(s)) / 2, y, r, g, b);
    }

    public void drawShadowCentered(SpriteBatch batch, String s, int y) {
        drawShadow(batch, s, (240 - stringWidth(s)) / 2, y);
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
