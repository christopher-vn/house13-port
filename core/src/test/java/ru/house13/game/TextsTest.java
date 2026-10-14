package ru.house13.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.house13.engine.Settings;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ЭТАП 5. Паки текстов: загрузка JSON, переключение, fallback,
 * отличия авторского перевода (CN) от локализаторского (V.P.K).
 */
class TextsTest {

    @AfterEach
    void restore() {
        Texts.reload(Texts.PACK_VPK);
        Settings.testingClear();
    }

    /** JSON ru-vpk.json должен в точности совпадать со вшитыми значениями. */
    @Test
    void vpkPackMatchesCompiledDefaults() {
        Map<String, Object> before = Texts.snapshot();
        Texts.reload(Texts.PACK_VPK);
        Map<String, Object> after = Texts.snapshot();
        assertSnapshotsEqual(before, after, "ru-vpk.json обязан зеркалить вшитые значения Texts");
        assertEquals(Texts.PACK_VPK, Texts.pack());
    }

    /** Авторский перевод: сюжет с сестрой и оврагом, предметы как в CN. */
    @Test
    void authPackTranslatesChineseOriginal() {
        Texts.reload(Texts.PACK_AUTH);
        assertEquals(Texts.PACK_AUTH, Texts.pack());

        // история: Сидзуко и сестра падают в овраг (не рынок и не машина)
        assertTrue(Texts.INTRO_STORY.contains("сестра"), "в CN-сюжете есть сестра");
        assertTrue(Texts.INTRO_STORY.contains("ущель"), "в CN-сюжете есть овраг");
        assertFalse(Texts.INTRO_STORY.contains("Черкизовск"), "Черкизон — вольность V.P.K");
        assertTrue(Texts.INTRO_STORY.contains("Сидзуко"), "Поливановская транслитерация");

        // монолог: клац вместо «удар/темнота»
        assertEquals("Клац...клац...", Texts.WAKEUP_2);
        assertTrue(Texts.WAKEUP_2_X.contains("заперта снаружи"));

        // концовка: эпилог у озера вместо хоррора
        assertTrue(Texts.ENDING.contains("озера"));
        assertFalse(Texts.ENDING.contains("не хочу умирать"));

        // экраны: раскладка CN-издания
        assertEquals(7, Texts.HELP_ROWS, "CN: drawString(...,7)");
        assertEquals(40, Texts.ABOUT_TOP, "CN: (28,40), у V.P.K — 80");
        assertEquals("Включить звук", Texts.SOUND_ON);
        assertEquals("С П Р А В К А", Texts.INFO_TITLE);
        assertTrue(Texts.HELP.contains("третьей стражи"));

        // предметы CN: кубик-ключ вместо большого ключа
        assertEquals("Кубик-ключ", Texts.ITEM_3);
        assertEquals("Ключ с бубенчиком", Texts.ITEM_14);
        assertEquals("Батарейка", Texts.ITEM_12);

        // хот-зоны CN: шкаф показывает сам код сейфа
        assertEquals("9819?", Texts.R13_WARDROBE_0);
        assertEquals("Нашла ключ.", Texts.R0_PHONE_KEY_TAKEN);
        assertEquals("Клац.... Эта дверь уже заперта.", Texts.R0_DOOR_LOCKED);
    }

    /** V.P.K-пак: контрольные значения локализатора. */
    @Test
    void vpkPackKeepsLocalizerTexts() {
        Texts.reload(Texts.PACK_VPK);
        assertTrue(Texts.INTRO_STORY.contains("черкизовский рынок"));
        assertEquals("Хреновина", Texts.ITEM_7);
        assertEquals(11, Texts.HELP_ROWS);
        assertEquals(80, Texts.ABOUT_TOP);
        assertTrue(Texts.R13_WARDROBE_0.contains("запах"));
    }

    /** Незнакомый пак = вшитые значения, без падения. */
    @Test
    void unknownPackFallsBackToDefaults() {
        Texts.reload(Texts.PACK_VPK); // чистая база: вшитые значения
        Map<String, Object> defaults = Texts.snapshot();
        Texts.reload("no-such-pack");
        assertEquals("no-such-pack", Texts.pack());
        assertSnapshotsEqual(defaults, Texts.snapshot(),
                "неизвестный пак оставляет вшитые значения VPK");
    }

    /** Битый путь не должен ронять загрузчика. */
    @Test
    void textDbMissingFileReturnsNull() {
        assertNull(TextDb.load("text/missing.json"));
        assertNull(TextDb.load("no/ such :path?.json"));
    }

    /** Сохранённый выбор применяется при следующем запуске (эмуляция). */
    @Test
    void savedChoiceIsAppliedOnStartup() {
        Settings.put("textpack", Texts.PACK_AUTH);
        assertEquals(Texts.PACK_AUTH, Settings.get("textpack", Texts.PACK_VPK));
        // Texts.<clinit> уже отработал; новая загрузка читает ту же настройку:
        Texts.reload(Settings.get("textpack", Texts.PACK_VPK));
        assertEquals(Texts.PACK_AUTH, Texts.pack());
        assertTrue(Texts.INTRO_STORY.contains("сестра"));
    }

    /** ЭТАП 5c: рантайм-нормализация типографики V.P.K (JSON хранит сырьё). */
    @Test
    void reloadAppliesTypographyNormalization() {
        Texts.reload(Texts.PACK_VPK);
        // мульти-пробелы сжаты («Ключ!..    Интересно» со скриншота бага)
        assertEquals("Ключ!.. Интересно, от чего он?", Texts.R0_PHONE_KEY_TAKEN);
        assertTrue(Texts.R0_PHONE_KEY_HOVER.startsWith("-Ух, ты! Ключ!.. Чтобы получить"));
        // слитная пунктуация разделена
        assertTrue(Texts.INTRO_STORY.contains("рынок. Вчера"));
        assertTrue(Texts.WAKEUP_0.contains("-Где я?.. Ох, как"));
        // ведущие пробелы центровки HELP сохранены
        assertTrue(Texts.HELP.startsWith("          ПОМОЩЬ"));
        // ни одного поля с внутренними мульти-пробелами
        for (Map.Entry<String, Object> e : Texts.snapshot().entrySet()) {
            if (e.getValue() instanceof String) {
                String s = (String) e.getValue();
                assertFalse(s.matches("(?s).*\\S  +\\S.*"),
                        e.getKey() + " содержит мульти-пробел: " + s);
            }
        }
    }

    /** ЭТАП 5c: нормализация не ломает авторский пак (он уже аккуратен). */
    @Test
    void authPackSurvivesNormalization() {
        Texts.reload(Texts.PACK_AUTH);
        assertEquals("Нашла ключ.", Texts.R0_PHONE_KEY_TAKEN);
        assertEquals("9819?", Texts.R13_WARDROBE_0);
        assertTrue(Texts.INTRO_STORY.contains("сестра"));
    }

    /** Map.equals не видит содержимое массивов — сравниваем поля вручную. */
    private static void assertSnapshotsEqual(Map<String, Object> expected,
                                             Map<String, Object> actual, String msg) {
        assertEquals(expected.keySet(), actual.keySet(), msg + " (набор ключей)");
        for (String k : expected.keySet()) {
            Object e = expected.get(k);
            Object a = actual.get(k);
            if (e instanceof String[]) {
                assertArrayEquals((String[]) e, (String[]) a, msg + " (" + k + ")");
            } else {
                assertEquals(e, a, msg + " (" + k + ")");
            }
        }
    }
}
