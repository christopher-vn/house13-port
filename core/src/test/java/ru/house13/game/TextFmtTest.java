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
}
