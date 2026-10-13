package ru.house13.engine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** ЭТАП 5. Settings: in-memory в headless (Gdx.app == null). */
class SettingsTest {

    @AfterEach
    void clean() {
        Settings.testingClear();
    }

    @Test
    void putGetRoundtrip() {
        assertEquals("ru-vpk", Settings.get("textpack", "ru-vpk"), "нет значения -> дефолт");
        Settings.put("textpack", "ru-auth");
        assertEquals("ru-auth", Settings.get("textpack", "ru-vpk"));
        Settings.put("textpack", "ru-vpk");
        assertEquals("ru-vpk", Settings.get("textpack", "x"));
    }

    @Test
    void testingClearResets() {
        Settings.put("textpack", "ru-auth");
        Settings.testingClear();
        assertEquals("def", Settings.get("textpack", "def"), "после clear — дефолт");
    }
}
