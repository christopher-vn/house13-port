package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.house13.engine.Assets;
import ru.house13.engine.Fonts;
import ru.house13.engine.KeyMap;
import ru.house13.engine.KeyMap.J2;
import ru.house13.engine.SoundManager;

/**
 * Игровой экран (drawStatus_ = 2, порт b.else()/byte()/i()/g()/int()).
 *
 * Этап 3: интро-прокрутка (m/h/do/v) и ending — этап 4, поэтому сцена
 * стартует сразу в пост-интро состоянии (preDisplayed=true, флаги
 * dz/g/c6 = true — как после автопропуска сцены пробуждения в RU).
 *
 * Каскад клавиш byte(int) — в точности по одному событию:
 *   1. FIRE без оверлеев ремапится в '5' (действие);
 *   2. FIRE/левый софт при открытом инфо/звуке -> возврат в меню паузы;
 *   3. правый софт -> пауза (caidan1: ПРОДОЛЖИТЬ/ИНФО/ЗВУК/ВЫХОД, o=GAME_CURRENT);
 *   4. в паузе: вверх/вниз навигация 0..3, FIRE/левый софт/'5' — выбор;
 *      выбор 3 -> выход в главное меню (FreeGameSourse);
 *   5. в оверлее ЗВУК: вверх/вниз переключают canSound;
 *   6. иначе ввод уходит в RoomScene (движение, '5' — этап 4).
 *
 * Музыка: каждый кадр changeClosedMid(0)+createBackMid(4)+playBackMid(4)
 * (else() при dc==1) — mapbg играет, канал меню остановлен.
 */
public final class GameScene implements Scene {

    private final GameHost host;
    final RoomScene room; // package-private: доступ из тестов

    // видимость package-private — прямой доступ из юнит-тестов
    boolean pause;         // b.aR (canExit): открыто меню паузы
    boolean info;          // b.du (isHelp): оверлей ИНФОРМАЦИЯ
    boolean musicOverlay;  // b.H  (isMusic): оверлей настройки звука
    int pauseItem;         // b.o (GAME_CURRENT): пункт паузы 0..3

    public GameScene(GameHost host) {
        this.host = host;
        this.room = new RoomScene(host.assets());
    }

    @Override
    public void tick(int[] gdxKeyDowns, int[] gdxKeyUps) {
        // Нажатия раньше отпусканий (press предшествует release — J2ME-порядок);
        // keyReleased case 2 сбрасывает движение безусловно, даже при паузе.
        for (int k : gdxKeyDowns) {
            if (isDebugKey(k)) {
                room.debugKeyDown(k);
            } else {
                handleKey(KeyMap.map(k));
            }
        }
        if (gdxKeyUps.length > 0) {
            room.releaseAll();
        }
        // void(): long() выполняется только при !showTool && !canExit
        if (!pause) {
            room.tick();
        }
    }

    private static boolean isDebugKey(int gdxKey) {
        return gdxKey == com.badlogic.gdx.Input.Keys.PAGE_UP
                || gdxKey == com.badlogic.gdx.Input.Keys.PAGE_DOWN
                || gdxKey == com.badlogic.gdx.Input.Keys.F10;
    }

    /** Каскад byte(int) — один вызов на одно событие клавиши. */
    void handleKey(J2 k) {
        // 1) ремап FIRE -> '5' (byte 949: только без оверлеев)
        if (k == J2.FIRE && !pause && !info && !musicOverlay) {
            k = J2.NUM_5;
        }
        // 2) FIRE/левый софт в оверлее инфо/звука -> назад в меню паузы
        //    (byte 953: else-ветка условия «ключ не софт/FIRE ИЛИ нет оверлеев»)
        if ((k == J2.SOFT_LEFT || k == J2.FIRE) && (info || musicOverlay)) {
            pause = true;
            info = false;
            musicOverlay = false;
            return;
        }
        // 3) правый софт открывает паузу (byte 985; гейты этапа 3 выполнены:
        //    aQ[6]!=7, !guanzi, dz/g/c6 = true — этап 4 уточнит)
        if (k == J2.SOFT_RIGHT && !pause && !info && !musicOverlay) {
            pause = true;
            info = false;
            musicOverlay = false;
            return;
        }
        if (pause) {
            // 4) меню паузы (byte 995-1035)
            if (k == J2.SOFT_LEFT || k == J2.FIRE || k == J2.NUM_5) {
                selectPauseItem();
            } else if (k == J2.UP) {
                pauseItem = (pauseItem + 3) % 4;
            } else if (k == J2.DOWN) {
                pauseItem = (pauseItem + 1) % 4;
            }
            return;
        }
        if (musicOverlay) {
            // 5) переключение звука (byte 1038-1046)
            if (k == J2.UP || k == J2.DOWN) {
                SoundManager.canSound = !SoundManager.canSound;
            }
            return;
        }
        if (info) {
            return; // ИНФОРМАЦИЯ: иных клавиш нет
        }
        // 6) обычный ввод игры
        room.handleKey(k);
    }

    /** Выбор пункта паузы (byte 996-1020): 0 продолжить, 1 инфо, 2 звук, 3 выход. */
    private void selectPauseItem() {
        switch (pauseItem) {
            case 1:
                info = true;
                break;
            case 2:
                musicOverlay = true;
                break;
            case 3:
                host.setScene(new MenuScene(host)); // FreeGameSourse + b0=1, bO=0
                return;
            default:
                break; // 0: продолжить
        }
        pause = false;
    }

    @Override
    public void render(SpriteBatch batch) {
        Assets a = host.assets();
        SoundManager snd = host.sound();

        // else(): чёрная заливка + музыка mapbg (dc==1 в игре всегда)
        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
        snd.changeClosedMid(SoundManager.CH_MENU);
        snd.createBackMid(SoundManager.CH_MAPBG);
        snd.playBackMid(SoundManager.CH_MAPBG);

        // w()/drawPublicRoom(): комнаты + рука
        room.render(batch);

        if (pause) {
            drawPause(batch, a);
        }
        if (musicOverlay) {
            drawMusicOverlay(batch, a, snd);
        }
        if (info) {
            drawInfoOverlay(batch, a);
        }
    }

    /** Меню паузы i(): чёрный фон + caidan1 (230x149: панель 136x149 + 4 полосы). */
    private void drawPause(SpriteBatch batch, Assets a) {
        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
        batch.draw(a.region("caidan1.png", 0, 0, 136, 149, false, true),
                (240 - 136) / 2, (320 - 149) / 2);
        // [пункт] = {srcY полосы 94x26, dstY, x полосы, y указателя, x указателя}
        switch (pauseItem) {
            case 0:
                batch.draw(a.region("caidan1.png", 136, 6, 94, 26, false, true), 74, 92);
                batch.draw(a.region("shou.png"), 193, 97);
                break;
            case 1:
                batch.draw(a.region("caidan1.png", 136, 44, 94, 26, false, true), 74, 130);
                batch.draw(a.region("shou.png"), 193, 136);
                break;
            case 2:
                batch.draw(a.region("caidan1.png", 136, 80, 94, 26, false, true), 74, 166);
                batch.draw(a.region("shou.png"), 193, 172);
                break;
            case 3:
                batch.draw(a.region("caidan1.png", 136, 116, 94, 26, false, true), 74, 202);
                batch.draw(a.region("shou.png"), 193, 208);
                break;
            default:
        }
    }

    /** Оверлей настройки звука g(): заливка (1,1,1), заголовок, ДА/НЕТ + shou. */
    private void drawMusicOverlay(SpriteBatch batch, Assets a, SoundManager snd) {
        a.fill(batch, 0, 0, 240, 320, 1f / 255f, 1f / 255f, 1f / 255f, 1);
        Fonts f = host.fonts();
        f.drawCentered(batch, Texts.MUSIC_TITLE, 8, 100, 100, 255);
        f.drawCentered(batch, Texts.MUSIC_UNDERLINE, 16, 100, 100, 255);

        if (!SoundManager.canSound) {
            // g(): int(4),int(0),int(1),int(2),int(3) — остановить всё
            snd.changeClosedMid(SoundManager.CH_MAPBG);
            snd.changeClosedMid(SoundManager.CH_MENU);
        }

        int x = (240 - f.stringWidth(Texts.SOUND_ON)) / 2;
        if (SoundManager.canSound) {
            f.draw(batch, Texts.SOUND_ON, x, Texts.SOUND_ON_Y, 255, 255, 255);
            f.draw(batch, Texts.SOUND_OFF, x, Texts.SOUND_OFF_Y, 200, 200, 200);
            batch.draw(a.region("shou.png"), x + Texts.SOUND_SHOU_DX, Texts.SOUND_ON_Y + 2);
        } else {
            f.draw(batch, Texts.SOUND_ON, x, Texts.SOUND_ON_Y, 200, 200, 200);
            f.draw(batch, Texts.SOUND_OFF, x, Texts.SOUND_OFF_Y, 255, 255, 255);
            batch.draw(a.region("shou.png"), x + Texts.SOUND_SHOU_DX, Texts.SOUND_OFF_Y);
        }
    }

    /** Оверлей ИНФОРМАЦИЯ int(): чёрный фон, заголовок, 4 строки (di = высота шрифта). */
    private void drawInfoOverlay(SpriteBatch batch, Assets a) {
        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
        Fonts f = host.fonts();
        f.drawCentered(batch, Texts.INFO_TITLE, 5, 100, 100, 255);
        int di = f.height();
        String[] lines = Texts.INFO_LINES;
        int[] ys = {15 + di, 20 + 2 * di, 22 + 3 * di, 24 + 4 * di};
        for (int i = 0; i < lines.length; i++) {
            f.draw(batch, lines[i], 3, ys[i], 255, 255, 255);
        }
    }

    @Override
    public void dispose() {
        //-room: текстуры в общем кэше Assets
    }

    @Override
    public String toString() {
        return "game " + room + (pause ? " PAUSE=" + pauseItem : "")
                + (info ? " INFO" : "") + (musicOverlay ? " MUSIC" : "");
    }
}
