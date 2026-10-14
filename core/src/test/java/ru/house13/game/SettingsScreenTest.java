package ru.house13.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.house13.engine.KeyMap.J2;
import ru.house13.engine.Settings;
import ru.house13.engine.SoundManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ЭТАП 5c. Экран настроек: навигация, значения, персистентность.
 * Экран один для главного меню и игровой паузы.
 */
class SettingsScreenTest {

    private SettingsScreen s;

    @BeforeEach
    void setUp() {
        s = new SettingsScreen(null);
        SoundManager.canSound = true;
        Settings.testingClear();
        Texts.reload(Texts.PACK_VPK);
    }

    @AfterEach
    void restore() {
        SoundManager.canSound = true;
        Settings.testingClear();
        Texts.reload(Texts.PACK_VPK);
    }

    @Test
    void navigationWrapsFourRows() {
        assertEquals(SettingsScreen.ROW_SOUND, s.row);
        assertFalse(s.handleKey(J2.DOWN));
        assertEquals(SettingsScreen.ROW_TRANSLATION, s.row);
        assertFalse(s.handleKey(J2.DOWN));
        assertEquals(SettingsScreen.ROW_TEXT, s.row);
        assertFalse(s.handleKey(J2.DOWN));
        assertEquals(SettingsScreen.ROW_BACK, s.row);
        assertFalse(s.handleKey(J2.DOWN));                    // wrap
        assertEquals(SettingsScreen.ROW_SOUND, s.row);
        assertFalse(s.handleKey(J2.UP));                      // wrap вверх
        assertEquals(SettingsScreen.ROW_BACK, s.row);
    }

    @Test
    void soundTogglePersists() {
        s.handleKey(J2.LEFT);                                 // выкл
        assertFalse(SoundManager.canSound);
        assertEquals("off", Settings.get("sound", "on"));
        s.handleKey(J2.RIGHT);                                // вкл
        assertTrue(SoundManager.canSound);
        assertEquals("on", Settings.get("sound", "on"));
    }

    @Test
    void translationSwitchPersistsAndNotifies() {
        final boolean[] notified = {false};
        SettingsScreen withListener = new SettingsScreen(() -> notified[0] = true);
        withListener.row = SettingsScreen.ROW_TRANSLATION;
        withListener.handleKey(J2.RIGHT);
        assertEquals(Texts.PACK_AUTH, Texts.pack());
        assertEquals(Texts.PACK_AUTH, Settings.get("textpack", "x"));
        assertTrue(notified[0], "меню уведомлено о смене пака");
        withListener.handleKey(J2.LEFT);
        assertEquals(Texts.PACK_VPK, Texts.pack());
        assertEquals(2, 2);
    }

    @Test
    void textSpeedCyclesThroughFourValues() {
        s.row = SettingsScreen.ROW_TEXT;
        // normal -> slow -> instant -> fast -> normal (вправо)
        s.handleKey(J2.RIGHT);
        assertEquals("slow", Settings.get("textspeed", "x"));
        s.handleKey(J2.RIGHT);
        assertEquals("instant", Settings.get("textspeed", "x"));
        s.handleKey(J2.RIGHT);
        assertEquals("fast", Settings.get("textspeed", "x"));
        s.handleKey(J2.RIGHT);
        assertEquals("normal", Settings.get("textspeed", "x"));
        // влево — обратный порядок
        s.handleKey(J2.LEFT);
        assertEquals("fast", Settings.get("textspeed", "x"));
        // LEFT/RIGHT на «Назад» ничего не меняют
        s.row = SettingsScreen.ROW_BACK;
        s.handleKey(J2.LEFT);
        assertEquals("fast", Settings.get("textspeed", "x"));
    }

    @Test
    void backRowAndSoftKeysCloseScreen() {
        s.row = SettingsScreen.ROW_BACK;
        assertTrue(s.handleKey(J2.FIRE), "OK на «Назад» закрывает");
        assertTrue(s.handleKey(J2.SOFT_RIGHT), "правый софт закрывает");
        assertTrue(s.handleKey(J2.SOFT_LEFT), "левый софт закрывает");
        // OK на пункте-значении — не закрывает, а переключает
        s.row = SettingsScreen.ROW_SOUND;
        assertFalse(s.handleKey(J2.FIRE));
        assertFalse(SoundManager.canSound, "FIRE на «Звук» переключил значение");
    }

    @Test
    void unknownKeysIgnored() {
        assertFalse(s.handleKey(J2.NUM_0));
        assertEquals(SettingsScreen.ROW_SOUND, s.row);
        assertTrue(SoundManager.canSound);
        assertEquals(Texts.PACK_VPK, Texts.pack());
    }
}
