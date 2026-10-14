package ru.house13.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Порт format() (перенос по ширине) и пейджер-клампа drawString(). */
class TextFmtTest {

    /** Фиксированная метрика: каждый символ 10px. */
    private static final TextFmt.Metrics M10 = s -> s.length() * 10;

    @Test
    void wrapsByWidth() {
        // 25px вмещает 2 символа по 10px; третий не влезает -> новая строка
        String[] r = TextFmt.format("ABCDE", 25, M10);
        assertArrayEquals(new String[]{"AB", "CD", "E"}, r);
    }

    @Test
    void newlineForcesBreakAndIsExcluded() {
        String[] r = TextFmt.format("AB\nCD", 100, M10);
        assertArrayEquals(new String[]{"AB", "CD"}, r);
    }

    @Test
    void overflowingCharStartsNextLine() {
        // Символ, после которого ширина превышена, НЕ входит в строку
        String[] r = TextFmt.format("ABCD", 30, M10); // 30px: A(10) B(20) C(30) D(40>30)
        assertArrayEquals(new String[]{"ABC", "D"}, r);
    }

    @Test
    void trailingNewlineDoesNotAddEmptyLine() {
        String[] r = TextFmt.format("AB\n", 100, M10);
        assertArrayEquals(new String[]{"AB"}, r);
    }

    @Test
    void degenerateFatCharDoesNotHang() {
        // Символ шире maxWidth: оригинал зацикливался, порт форсирует включение
        TextFmt.Metrics fat = s -> 100;
        String[] r = TextFmt.format("AB", 50, fat);
        assertArrayEquals(new String[]{"A", "B"}, r);
    }

    @Test
    void clampRowBoundsLikeOriginal() {
        // [0, lines-3]
        assertEquals(0, TextFmt.clampRow(-5, 10));
        assertEquals(0, TextFmt.clampRow(0, 10));
        assertEquals(3, TextFmt.clampRow(3, 10));
        assertEquals(7, TextFmt.clampRow(8, 10));
        assertEquals(7, TextFmt.clampRow(100, 10));
        // lines < 3: отрицательный кламп — строк не выводим (как в оригинале)
        assertEquals(-1, TextFmt.clampRow(5, 2));
    }

    // ==================================================================
    // ЭТАП 5c: norm() + wrapWords()
    // ==================================================================

    @Test
    void normSqueezesMultiSpaces() {
        assertEquals("Ключ!.. Интересно, от чего он?",
                TextFmt.norm("Ключ!..    Интересно,от чего он?"));
        assertEquals("Чтобы получить",
                TextFmt.norm("Чтобы         получить"));
    }

    @Test
    void normAddsSpaceAfterPunctuation() {
        assertEquals("Помню, мы вошли", TextFmt.norm("Помню,мы вошли"));
        assertEquals("Шизуко. 4 года назад", TextFmt.norm("Шизуко.4 года назад"));
        assertEquals("могу выйти!... Какая", TextFmt.norm("могу выйти!...Какая"));
        assertEquals("все?. Почему", TextFmt.norm("все?.Почему"));
        assertEquals("Ух, ты!", TextFmt.norm("Ух,ты!"));
        assertEquals("Звук: ДА", TextFmt.norm("Звук:  ДА"));
    }

    @Test
    void normKeepsAbbreviationsAndVersionNumbers() {
        assertEquals("v1.0", TextFmt.norm("v1.0"));
        assertEquals("vova2const@mail.ru", TextFmt.norm("vova2const@mail.ru"));
        assertEquals("Перевод V.P.K", TextFmt.norm("Перевод V.P.K"));
        assertEquals("0311kf@126.com", TextFmt.norm("0311kf@126.com"));
        assertEquals("вошли на SEclub", TextFmt.norm("вошли на SEclub"));
    }

    @Test
    void normKeepsLineLeadingSpacesAndEllipses() {
        // ведущие пробелы центруют заголовки HELP/ABOUT
        assertEquals("     ПОМОЩЬ", TextFmt.norm("     ПОМОЩЬ"));
        // многоточие в середине слова и в конце строки не трогается
        assertEquals("томов...", TextFmt.norm("томов..."));
        assertEquals("шорох... Ой", TextFmt.norm("шорох...Ой"));
    }

    @Test
    void normIsIdempotent() {
        String messy = "Ключ!..    Интересно,от чего он?Шизуко.4 года";
        assertEquals(TextFmt.norm(TextFmt.norm(messy)), TextFmt.norm(messy));
    }

    @Test
    void wrapWordsNeverBreaksInsideWord() {
        // 25px = 2.5 символа: «aaa» (30px) не влезает вместе с «bbb»
        String[] r = TextFmt.wrapWords("aaa bbb ccc", 50, M10); // 50px = 5 симв.
        assertArrayEquals(new String[]{"aaa", "bbb", "ccc"}, r);
        // побуквенный format() дал бы «aaa b», «bb cc», «c» — слова рвались
    }

    @Test
    void wrapWordsFillsLineWhileItFits() {
        String[] r = TextFmt.wrapWords("ab cd ef", 50, M10); // «ab cd»=50px ровно
        assertArrayEquals(new String[]{"ab cd", "ef"}, r);
    }

    @Test
    void wrapWordsRespectsNewline() {
        String[] r = TextFmt.wrapWords("ab\ncd ef", 100, M10);
        assertArrayEquals(new String[]{"ab", "cd ef"}, r);
    }

    @Test
    void wrapWordsCutsDegenerateFatWord() {
        TextFmt.Metrics fat = s -> 100;      // любой текст шире лимита
        String[] r = TextFmt.wrapWords("abcd", 50, fat);
        assertArrayEquals(new String[]{"a", "b", "c", "d"}, r);
    }

    @Test
    void wrapWordsEmptyText() {
        assertArrayEquals(new String[]{""}, TextFmt.wrapWords("", 100, M10));
    }
}
