package ru.house13.engine;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;

/**
 * Настройки порта (ЭТАП 5): libGDX Preferences на десктопе/Android,
 * in-memory карта в headless-тестах (Gdx.app == null).
 *
 * Единственный ключ сейчас — "textpack" ("ru-vpk" по умолчанию).
 * Оригинал настройки НЕ сохранял (RMS-код getSettingInfo мёртв) —
 * сохранение выбора перевода это дополнение порта.
 */
public final class Settings {

    private static final Map<String, String> MEM = new HashMap<>();
    private static final GdxPrefHolder PREFS = new GdxPrefHolder();

    private Settings() {
    }

    public static String get(String key, String def) {
        String p = PREFS.getString(key);
        if (p != null) {
            return p;
        }
        String m = MEM.get(key);
        return m != null ? m : def;
    }

    public static void put(String key, String value) {
        MEM.put(key, value);
        PREFS.putString(key, value);
    }

    /** Только для тестов: сброс in-memory состояния. */
    public static void testingClear() {
        MEM.clear();
    }

    /** Ленивая обёртка над Gdx.app.getPreferences (null в headless). */
    private static final class GdxPrefHolder {
        private final com.badlogic.gdx.Preferences prefs;

        GdxPrefHolder() {
            com.badlogic.gdx.Preferences p = null;
            try {
                if (Gdx.app != null) {
                    p = Gdx.app.getPreferences("house13");
                }
            } catch (Throwable t) {
                p = null; // headless / бэкенд без приложения
            }
            this.prefs = p;
        }

        String getString(String key) {
            return prefs != null ? prefs.getString(key, null) : null;
        }

        void putString(String key, String value) {
            if (prefs != null) {
                prefs.putString(key, value);
                prefs.flush();
            }
        }
    }
}
