package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.house13.engine.Assets;
import ru.house13.engine.Fonts;
import ru.house13.engine.KeyMap;
import ru.house13.engine.KeyMap.J2;
import ru.house13.engine.SoundManager;

/**
 * Главное меню (drawStatus_ = 1, порт b.l()/char()/b()/case()/goto()/z()).
 *
 * Подсостояния menuStatus_ (bO): 0 — главное меню, 1 — АВТОР, 2 — ЗВУК,
 * 3 — ПОМОЩЬ. Пять пунктов (dV 0..4): ИГРА, АВТОР, ЗВУК, ИНФО, ВЫХОД —
 * надписи нарисованные в caidan.png (230x186): панель 136x186 + полосы
 * подсветки 94x26 при x=136.
 *
 * Анимация входа (как в l()): панель съезжает сверху (dO += 6/тик,
 * y = -40+dO, с перескоком 32 -> 38 -> 33), затем указатель-рука shou
 * въезжает справа (cn += 4/тик). Ввод блокируется до завершения (cv).
 *
 * Управление: вверх/вниз — навигация (в АВТОР/ПОМОЩЬ — скролл dA, в ЗВУК —
 * переключатель), FIRE/левый софт/'5' — выбор, FIRE/правый софт/'5' — назад.
 * Ввод активен только после cv (shoud) — как char().
 */
public final class MenuScene implements Scene {

    private final GameHost host;

    // видимость package-private — прямой доступ из юнит-тестов
    int bO = 0;     // menuStatus_: 0 main, 1 about, 2 sound(=настройки 5c), 3 help
    int dV = 0;     // currentKeyCode: выбранный пункт 0..4
    int dA = 0;     // menuCount_: скролл текста помощи

    /** Экран настроек (этап 5c) — общий с игровой паузой; кэши меню
     *  (help/about) пересобираются сами по штампу пака при смене перевода. */
    final SettingsScreen settings = new SettingsScreen(null);

    // анимация входа (l())
    private int dO = 0;     // caxia: слайд панели
    private int cn = 0;     // shoudw: слайд shou
    boolean cv;             // shoud: ввод разрешён

    // анимация рамки z()
    private int cy;         // isd
    private boolean cyDown; // tur

    // ленивый перенос текстов (как strsToShow == null в оригинале);
    // ЭТАП 5: штамп пака — при смене перевода кэш пересобирается
    private String[] helpLines;
    private String[] aboutLines;
    private String helpPackStamp;
    private String aboutPackStamp;

    // Геометрия пунктов меню: [пункт] = {srcY полосы, dstY, y указателя}
    private static final int[][] ITEMS = {
        {7, 40, 46}, {43, 76, 83}, {80, 113, 121}, {118, 151, 157}, {154, 187, 195}
    };

    public MenuScene(GameHost host) {
        this.host = host;
    }

    @Override
    public void tick(int[] gdxKeyDowns, int[] gdxKeyUps) {
        if (bO == 0) {
            // слайд-анимация главного меню (в l() продвигается при отрисовке)
            if (dO < 73) {
                dO += 6;
            } else {
                dO = 208;
            }
            if (dO > 73 && !cv) {
                if (cn < 36) {
                    cn += 4;
                } else {
                    cv = true;
                }
            }
        } else {
            // анимация рамки z() на подэкранах
            if (!cyDown) {
                if (cy < 126) {
                    cy += 6;
                } else {
                    cyDown = true;
                }
            } else {
                cy -= 6;
                if (cy <= 0) {
                    cyDown = false;
                }
            }
        }
        for (int k : gdxKeyDowns) {
            handleKey(KeyMap.map(k));
        }
    }

    /** Порт char(int) — по одному событию за раз, как keyPressed оригинала. */
    void handleKey(J2 k) {
        if (!cv) {
            return; // if (this.cv) — гейт ввода
        }
        // ЭТАП 5c: экран настроек (бывший экран звука + группа «Перевод»)
        if (bO == 2) {
            if (settings.handleKey(k)) {
                bO = 0;              // «Назад»/софт — в главное меню
            }
            return;
        }
        if (k == J2.UP) {
            if (bO != 3 && bO != 1) {
                if (bO == 0) {
                    dV = (dV + 4) % 5;
                }
            } else {
                dA--; // скролл АВТОР/ПОМОЩЬ (в АВТОР рендер статичен — как в RU)
            }
        }
        if (k == J2.DOWN) {
            if (bO != 3 && bO != 1) {
                if (bO == 0) {
                    dV = (dV + 1) % 5;
                }
            } else {
                dA++;
            }
        }
        // выбор: FIRE || левый софт || '5'  (bc || cJ || 53)
        if (k == J2.FIRE || k == J2.SOFT_LEFT || k == J2.NUM_5) {
            if (dV == 0) {
                host.setScene(new GameScene(host)); // FreeMenuSourse + gameIni
                return;
            } else if (dV == 3) {
                if (bO == 0) {
                    bO = 3;
                    return;
                }
            } else if (dV == 2) {
                if (bO == 0) {
                    bO = 2;
                    return;
                }
            } else if (dV == 1) {
                if (bO == 0) {
                    bO = 1;
                    return;
                }
            } else if (dV == 4) {
                host.sound().stopAll(); // bR.if(): остановить музыку меню
                host.requestExit();     // TheMIDlet.quitApp()
            }
        }
        // назад: FIRE || правый софт || '5'  (bc || cc || 53)
        if (k == J2.FIRE || k == J2.SOFT_RIGHT || k == J2.NUM_5) {
            if (dV == 3 && bO == 3) {
                bO = 0;
                dA = 0;
            }
            if (dV == 1 && bO == 1) {
                bO = 0;
                dA = 0;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Assets a = host.assets();
        SoundManager snd = host.sound();

        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
        if (bO == 0) {
            batch.draw(a.region("menuback.png"), 0, 0);
            // музыка меню при каждом кадре (идемпотентно) — как l()
            snd.changeClosedMid(SoundManager.CH_MAPBG);
            snd.createBackMid(SoundManager.CH_MENU);
            snd.playBackMid(SoundManager.CH_MENU);

            // панель: слайд сверху, затем фикс. позиция 33
            int py = dO < 73 ? -40 + dO : 33;
            batch.draw(a.region("caidan.png", 0, 0, 136, 186, false, true), 52, py);

            if (dO > 73 && !cv) {
                batch.draw(a.region("shou.png"), 240 - cn, 46);
            }
            if (dO > 73 && cv) {
                int[] it = ITEMS[dV];
                TextureRegion shou = a.region("shou.png");
                batch.draw(shou, 200, it[2]);
                TextureRegion strip = a.region("caidan.png", 136, it[0], 94, 26, false, true);
                batch.draw(strip, 72, it[1]);
            }
        } else if (bO == 2) {
            settings.render(batch, host);   // этап 5c: чёрный экран настроек
        } else if (bO == 3) {
            renderHelp(batch, a);
        } else if (bO == 1) {
            renderAbout(batch, a);
        }
    }

    /** Экран помощи (case()): скролл dA, 11 строк (CN-издание: 7), тень+белый (пейджер for). */
    private void renderHelp(SpriteBatch batch, Assets a) {
        drawFrame(batch, a);
        Fonts f = host.fonts();
        if (helpLines == null || !helpPackStamp.equals(Texts.pack())) {
            helpPackStamp = Texts.pack();
            helpLines = TextFmt.format(Texts.HELP, Texts.HELP_WRAP, f::stringWidth);
        }
        dA = TextFmt.clampRow(dA, helpLines.length);
        drawPage(batch, f, helpLines, dA, Texts.HELP_LEFT, Texts.HELP_TOP, Texts.HELP_ROWS);
    }

    /** Экран автора (goto()): статичная страница (rowIndex 0), 8 строк. */
    private void renderAbout(SpriteBatch batch, Assets a) {
        drawFrame(batch, a);
        Fonts f = host.fonts();
        if (aboutLines == null || !aboutPackStamp.equals(Texts.pack())) {
            aboutPackStamp = Texts.pack();
            aboutLines = TextFmt.format(Texts.ABOUT, Texts.ABOUT_WRAP, f::stringWidth);
        }
        int r = TextFmt.clampRow(0, aboutLines.length);
        drawPage(batch, f, aboutLines, r, Texts.ABOUT_LEFT, Texts.ABOUT_TOP, Texts.ABOUT_ROWS);
    }

    /** Пейджер for(String[],...): rowCountToDraw строк от r, шаг getHeight()+2. */
    private void drawPage(SpriteBatch batch, Fonts f, String[] lines, int r,
                          int left, int top, int rows) {
        // 5d: LINE_STEP (сетка 12px + 2px) вместо lineHeight FreeType (~18):
        // rowair=2 оригинала считался от системного шрифта K700 (fH+2 ≈ 14-16)
        int step = ru.house13.engine.Fonts.LINE_STEP + 2;
        for (int i = 0; i < rows && i + r < lines.length; i++) {
            f.drawShadow(batch, lines[i + r], left, top + i * step);
        }
    }

    /** Рамка подэкранов (z()): menuback + орнаменты lingjian с анимацией cy. */
    private void drawFrame(SpriteBatch batch, Assets a) {
        batch.draw(a.region("menuback.png"), 0, 0);
        // углы: источник = верхний-правый орнамент (0,0,19,20)
        batch.draw(a.region("lingjian.png", 0, 0, 19, 20, true, false), 2, 2);      // MIRROR
        batch.draw(a.region("lingjian.png", 0, 0, 19, 20, false, false), 219, 2);  // как есть
        batch.draw(a.region("lingjian.png", 0, 0, 19, 20, false, true), 2, 298);   // MIRROR_ROT180
        batch.draw(a.region("lingjian.png", 0, 0, 19, 20, false, true), 219, 297); // vConvert
        // боковые столбики (19,2,5,17)
        batch.draw(a.region("lingjian.png", 19, 2, 5, 17, false, false), 4, 23);
        batch.draw(a.region("lingjian.png", 19, 2, 5, 17, false, false), 232, 23);
        batch.draw(a.region("lingjian.png", 19, 2, 5, 17, false, true), 4, 282);
        batch.draw(a.region("lingjian.png", 19, 2, 5, 17, false, true), 232, 282);
        // вертикальные полоски x28
        for (int i = 0; i < 28; i++) {
            batch.draw(a.region("lingjian.png", 20, 10, 3, 9, false, false), 5, 40 + i * 9);
            batch.draw(a.region("lingjian.png", 20, 10, 3, 9, false, false), 233, 40 + i * 9);
        }
        // «бегущие» штрихи (анимация cy 0..126)
        batch.draw(a.region("lingjian.png", 24, 17, 11, 2, false, false), 229, 56 + cy);
        batch.draw(a.region("lingjian.png", 24, 17, 11, 2, false, false), 1, 153 - cy);
        // горизонтальные полоски x40 (верх/низ)
        for (int i = 0; i < 40; i++) {
            batch.draw(a.region("lingjian.png", 0, 2, 5, 4, false, false), 21 + i * 5, 4);
            batch.draw(a.region("lingjian.png", 0, 2, 5, 4, false, false), 21 + i * 5, 312);
        }
    }

    @Override
    public void dispose() {
    }

    @Override
    public String toString() {
        return "menu bO=" + bO + " dV=" + dV + " dO=" + dO + " cv=" + cv;
    }
}
