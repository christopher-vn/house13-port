package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.house13.engine.Assets;
import ru.house13.engine.Fonts;
import ru.house13.engine.KeyMap;
import ru.house13.engine.KeyMap.J2;
import ru.house13.engine.Settings;
import ru.house13.engine.SoundManager;

/**
 * Экран настроек порта (этап 5c) — кастомный, в общей стилистике игры:
 * чёрный фон, пиксельные строки drawShadow с тенями как у экранов
 * ПОМОЩЬ/АВТОР, указатель-рука shou справа от активного пункта.
 *
 * Пункты: ЗВУК (ДА/НЕТ), ПЕРЕВОД (Локализаторский V.P.K / Авторский),
 * ТЕКСТ (Мгновенно/Быстро/Обычно/Медленно — печатная машинка диалогов),
 * НАЗАД. Один экран работает и в главном меню (MenuScene, пункт «ЗВУК»),
 * и в игре (GameScene, оверлей из паузы) — оригинальный экран звука g()
 * и его расширение этапа 5 (группа «Перевод» на menuback) заменены им.
 *
 * Управление: вверх/вниз — пункт, влево/вправо и OK — значение,
 * правый софт — назад (закрыть экран). Выбор перевода и скорости
 * персистится через Settings (textpack/textspeed/sound); выключение
 * звука глушит все каналы сразу (как g(): int(0..3) оригинала).
 */
final class SettingsScreen {

    /** Пункты: 0 звук, 1 перевод, 2 скорость текста, 3 назад. */
    static final int ROW_SOUND = 0, ROW_TRANSLATION = 1, ROW_TEXT = 2, ROW_BACK = 3;
    private static final int ROWS = 4;

    /** Скорости печатной машинки в порядке переключения. */
    private static final String[] SPEEDS = {"instant", "fast", "normal", "slow"};

    int row;                                // выбранный пункт

    private final SettingsListener listener;

    /** Событие «перевод сменился» (меню чистит кэши страниц; в игре кэши
     *  и так собраны по штампу пака — слушатель опционален). */
    interface SettingsListener {
        void onPackChanged();
    }

    SettingsScreen(SettingsListener listener) {
        this.listener = listener;
    }

    /**
     * Клавиша экрана настроек. true — экран просит закрыться
     * («Назад», правый/левый софт); false — нажатие обработано (или игнор).
     */
    boolean handleKey(J2 k) {
        switch (k) {
            case UP:
                row = (row + ROWS - 1) % ROWS;
                return false;
            case DOWN:
                row = (row + 1) % ROWS;
                return false;
            case LEFT:
                if (row != ROW_BACK) {
                    change(row, -1);
                }
                return false;
            case RIGHT:
            case FIRE:
            case NUM_5:
                if (row == ROW_BACK) {
                    return true;
                }
                change(row, +1);
                return false;
            case SOFT_LEFT:
            case SOFT_RIGHT:
                return true;                // отмена — закрыть настройки
            default:
                return false;
        }
    }

    /** Переключить значение пункта в направлении dir (+1/-1). */
    private void change(int what, int dir) {
        switch (what) {
            case ROW_SOUND:
                SoundManager.canSound = !SoundManager.canSound;
                Settings.put("sound", SoundManager.canSound ? "on" : "off");
                break;
            case ROW_TRANSLATION:
                String next = Texts.pack().equals(Texts.PACK_VPK)
                        ? Texts.PACK_AUTH : Texts.PACK_VPK;
                Texts.reload(next);
                Settings.put("textpack", next);
                if (listener != null) {
                    listener.onPackChanged();
                }
                break;
            case ROW_TEXT:
                String cur = Settings.get("textspeed", "normal");
                int idx = 0;
                for (int i = 0; i < SPEEDS.length; i++) {
                    if (SPEEDS[i].equals(cur)) {
                        idx = i;
                        break;
                    }
                }
                idx = (idx + dir + SPEEDS.length) % SPEEDS.length;
                Settings.put("textspeed", SPEEDS[idx]);
                break;
            default:
        }
    }

    /** Текст пункта. */
    private static String rowLabel(int r) {
        switch (r) {
            case ROW_SOUND:
                return SoundManager.canSound ? Texts.SOUND_ON : Texts.SOUND_OFF;
            case ROW_TRANSLATION:
                return Texts.SETTINGS_TR_TITLE + " "
                        + (Texts.pack().equals(Texts.PACK_AUTH)
                        ? Texts.TR_AUTH : Texts.TR_VPK);
            case ROW_TEXT:
                return Texts.SET_TEXT + ": " + speedLabel(
                        Settings.get("textspeed", "normal"));
            default:
                return Texts.SET_BACK;
        }
    }

    private static String speedLabel(String id) {
        switch (id) {
            case "instant": return Texts.VAL_INSTANT;
            case "fast":    return Texts.VAL_FAST;
            case "slow":    return Texts.VAL_SLOW;
            default:        return Texts.VAL_NORMAL;
        }
    }

    /** Рендер: чёрный фон, заголовок с линией, 4 пункта, shou, подсказка. */
    void render(SpriteBatch batch, GameHost host) {
        Assets a = host.assets();
        Fonts f = host.fonts();
        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);

        f.drawShadowCentered(batch, Texts.SETTINGS_TITLE, 44);
        // линия под заголовком — в тон рамке диалога (250,223,134)
        a.fill(batch, 70, 62, 100, 1, 250, 223, 134, 1);

        int x = 36;
        int[] ys = {116, 148, 180, 212};
        for (int r = 0; r < ROWS; r++) {
            String label = rowLabel(r);
            boolean active = r == row;
            if (active) {
                f.drawShadow(batch, label, x, ys[r]);          // белый с тенью
            } else {
                f.drawShadow(batch, label, x, ys[r], 170, 165, 150);
            }
            if (active) {
                batch.draw(a.region("shou.png"), x + f.stringWidth(label) + 8, ys[r] + 2);
            }
        }
        f.draw(batch, Texts.SETTINGS_HINT,
                (240 - f.stringWidth(Texts.SETTINGS_HINT)) / 2, 296, 150, 150, 150);
    }
}
