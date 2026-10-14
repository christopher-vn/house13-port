package ru.house13.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ЭТАП 5c/5d. Диалоговый бокс: перенос по словам (страницы по 2 строки),
 * печатная машинка (скорость — десятые доли символа за тик), анимация
 * (typingRow/sinceTyped), подтверждение (домотать/листнуть/пропустить).
 */
class DialogBoxTest {

    /** Фиксированная метрика: каждый символ 10px. */
    private static final TextFmt.Metrics M10 = s -> s.length() * 10;

    @Test
    void wrapsWordsIntoPagesOfTwoLines() {
        DialogBox d = new DialogBox();
        d.show("aaa bbb ccc ddd", 50, M10); // «aaa bbb»=70px>50 -> по слову в строке
        assertEquals("aaa", d.line(0));
        assertEquals("bbb", d.line(1));
        assertTrue(d.hasMorePages(), "строк 4 -> 2 страницы");
        assertTrue(d.confirm(), "первое подтверждение — перевернуть");
        assertEquals("ccc", d.line(0));
        assertEquals("ddd", d.line(1));
        assertFalse(d.hasMorePages());
        assertFalse(d.confirm(), "последняя страница — нажатие уходит в каскад");
    }

    @Test
    void explicitNewlineBreaksLine() {
        DialogBox d = new DialogBox();
        d.show("ab\ncd", 100, M10);
        assertEquals("ab", d.line(0));
        assertEquals("cd", d.line(1));
        assertFalse(d.hasMorePages());
    }

    @Test
    void normalSpeedTypesOneCharPerTick() {
        // 5d: «обычно» = 10 десятых = 1 симв/тик (10 симв/с) — вдвое быстрее
        // 5c (тогда 2 тика/символ = 5 симв/с, «прям медленно»)
        DialogBox d = new DialogBox();
        d.setSpeed(10);
        d.show("abcde fg", 50, M10);         // 80px>50 -> «abcde»/«fg», pageLen=7
        assertEquals("", d.line(0), "печать ещё не началась");
        d.tick();
        assertEquals("a", d.line(0));
        d.tick();
        assertEquals("ab", d.line(0));
        assertTrue(d.isTyping());
        assertTrue(d.confirm(), "недопечатано — подтверждение доматывает");
        assertTrue(d.pageShown());
        assertEquals("abcde", d.line(0));
        assertEquals("fg", d.line(1));
        assertFalse(d.confirm(), "страница последняя — нажатие проходит дальше");
    }

    @Test
    void fastSpeedTypesTwoCharsPerTick() {
        DialogBox d = new DialogBox();
        d.setSpeed(20);                       // «быстро»: 2 симв/тик
        d.show("abcde fg", 50, M10);
        d.tick();
        assertEquals("ab", d.line(0));
        d.tick();
        assertEquals("abcd", d.line(0));
        d.tick();                             // 5-й символ + переход на строку 2
        assertEquals("abcde", d.line(0));
        assertEquals("f", d.line(1));
    }

    @Test
    void slowSpeedTypesCharEveryTwoTicks() {
        DialogBox d = new DialogBox();
        d.setSpeed(5);                        // «медленно»: 0.5 симв/тик
        d.show("ab cd", 20, M10);            // «ab»/«cd»
        d.tick();
        assertEquals("", d.line(0), "аккумулятор ещё не набрал 10 десятых");
        d.tick();
        assertEquals("a", d.line(0));
        d.tick();
        assertEquals("a", d.line(0), "нечётный тик — без символа");
        d.tick();
        assertEquals("ab", d.line(0));
    }

    @Test
    void typewriterSpillsIntoSecondLine() {
        DialogBox d = new DialogBox();
        d.setSpeed(10);
        d.show("ab cd", 20, M10);            // «ab cd»=50px -> «ab»/«cd»
        d.tick();                            // typed=1
        assertEquals("a", d.line(0));
        assertEquals("", d.line(1));
        assertEquals(0, d.typingRow(), "головка в первой строке");
        d.tick();                            // typed=2 — строка 1 допечатана
        assertEquals("ab", d.line(0));
        assertEquals("", d.line(1));
        assertEquals(1, d.typingRow(), "каретка перешла на строку 2");
        d.tick();                            // typed=3
        assertEquals("ab", d.line(0));
        assertEquals("c", d.line(1));
        assertEquals(1, d.typingRow());
    }

    @Test
    void typingRowMinusOneWhenPageDoneOrPassive() {
        DialogBox d = new DialogBox();
        assertEquals(-1, d.typingRow(), "до показа — пассивен");
        d.setSpeed(10);
        d.show("ab", 100, M10);             // одна строка, pageLen=2
        assertEquals(0, d.typingRow());
        d.tick();
        d.tick();
        assertEquals(-1, d.typingRow(), "страница допечатана");
        assertFalse(d.isTyping());
    }

    @Test
    void sinceTypedCountsTicksAfterLastChar() {
        // «свежие чернила»: 0 в тик печати символа, растёт в тиках ожидания
        DialogBox d = new DialogBox();
        d.setSpeed(5);                        // символ раз в 2 тика
        d.show("abc", 100, M10);
        d.tick();
        assertEquals(1, d.sinceTyped(), "тик без символа");
        d.tick();
        assertEquals(0, d.sinceTyped(), "символ напечатан");
        d.tick();
        assertEquals(1, d.sinceTyped(), "снова тик ожидания");
    }

    @Test
    void instantSpeedShowsEverythingAtOnce() {
        DialogBox d = new DialogBox();
        d.setSpeed(0);                       // мгновенно — как в оригинале
        d.show("ab cd ef gh", 20, M10);      // 2 страницы по 1 слову в строке
        assertTrue(d.pageShown());
        assertEquals("ab", d.line(0));
        assertEquals("cd", d.line(1));
        assertTrue(d.confirm());             // только перевернуть
        assertEquals("ef", d.line(0));
    }

    @Test
    void speedChangeDuringTypingCompletesPage() {
        DialogBox d = new DialogBox();
        d.setSpeed(5);
        d.show("ab cd", 20, M10);
        d.tick();                            // ничего (acc10=5 < 10)
        assertEquals("", d.line(0));
        d.setSpeed(0);                       // игрок переключил на «мгновенно»
        assertTrue(d.pageShown());
    }

    @Test
    void clearResetsToPassiveState() {
        DialogBox d = new DialogBox();
        d.show("ab cd ef", 20, M10);
        d.confirm();                         // страница 2
        d.clear();
        assertEquals("", d.shownText());
        assertFalse(d.hasMorePages());
        assertFalse(d.isTyping());
        assertEquals("", d.line(0));
    }

    @Test
    void sameTextKeepsPageState() {
        DialogBox d = new DialogBox();
        d.setSpeed(0);
        d.show("ab cd ef", 20, M10);         // 2 страницы
        assertTrue(d.confirm());
        d.show("ab cd ef", 20, M10);         // тот же текст — не перестраивать
        assertEquals("ef", d.line(0), "страница сохранена");
    }

    @Test
    void passiveBeforeFirstShow() {
        // headless-тесты без render: бокс не показывал текст — подтверждение
        // не потребляется, поведение handleKey остаётся оригинальным.
        DialogBox d = new DialogBox();
        assertFalse(d.confirm());
        assertFalse(d.isTyping());
        assertFalse(d.hasMorePages());
    }
}
