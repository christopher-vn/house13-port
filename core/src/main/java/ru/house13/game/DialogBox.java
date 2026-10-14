package ru.house13.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Состояние диалогового бокса порта (этап 5c): перенос по словам +
 * постраничный показ (2 строки/страница) + эффект печатной машинки.
 *
 * Чистая логика без Graphics — GameScene кормит его метрикой шрифта
 * в render() (шрифты недоступны в headless-тестах), а тик/подтверждение
 * работают и без показа: до первого show() бокс считает текст одно-
 * страничным и полностью допечатанным — поведение handleKey остаётся
 * ровно как в оригинале (первая же клавиша чистит bf).
 *
 * Скорость печатания: charsPerTick = 0 — мгновенно (как в оригинале),
 * 1 — 60 симв/с, 0.5 — 30 симв/с (normal), 0.25 — 15 симв/с (slow).
 * Реализовано аккумулятором: каждые T тиков +1 символ.
 */
final class DialogBox {

    /** Текст, для которого построен кэш (перестройка при смене bf). */
    private String shown = "";
    /** Страницы по 2 строки: pages[p] = {строка1, строка2 (может быть "")}. */
    private String[][] pages = new String[][]{{"", ""}};
    private int page;
    /** Напечатано символов на текущей странице (обе строки суммарно). */
    private int typed;
    /** Аккумулятор тиков typewriter. */
    private int acc;
    /** Символов за тик X/TPD: 0 = мгновенно. */
    private int ticksPerChar;
    /** Полная длина страницы (для сравнения с typed). */
    private int pageLen;

    /** Настроить скорость: 0 = мгновенно, 1/2/4 = тиков на символ. */
    void setSpeed(int ticksPerChar) {
        this.ticksPerChar = Math.max(0, ticksPerChar);
        if (this.ticksPerChar == 0) {
            typed = pageLen;
        }
    }

    /** Построить страницы по тексту (вызывается из render при смене bf). */
    void show(String text, int maxWidth, TextFmt.Metrics m) {
        if (text.equals(shown)) {
            return;
        }
        shown = text;
        String[] lines = TextFmt.wrapWords(text, maxWidth, m);
        List<String[]> p = new ArrayList<>();
        for (int i = 0; i < lines.length; i += 2) {
            String l1 = lines[i];
            String l2 = i + 1 < lines.length ? lines[i + 1] : "";
            p.add(new String[]{l1, l2});
        }
        if (p.isEmpty()) {
            p.add(new String[]{"", ""});
        }
        pages = p.toArray(new String[0][]);
        page = 0;
        acc = 0;
        pageLen = pages[0][0].length() + pages[0][1].length();
        typed = ticksPerChar == 0 ? pageLen : 0;
    }

    /** Сброс (bf очищен) — бокс снова «невидимый» с пассивным поведением. */
    void clear() {
        shown = "";
        pages = new String[][]{{"", ""}};
        page = 0;
        typed = 0;
        pageLen = 0;
        acc = 0;
    }

    /** Печатная машинка: +1 символ каждые ticksPerChar тиков. */
    void tick() {
        if (typed >= pageLen || ticksPerChar == 0) {
            return;
        }
        acc++;
        if (acc >= ticksPerChar) {
            acc = 0;
            typed++;
        }
    }

    /**
     * Подтверждение (FIRE/'5'/правый софт). true — нажатие потреблено
     * боксом (допечатал страницу или перевернул на следующую); false —
     * страница допечатана и последняя, нажатие уходит в каскад оригинала.
     */
    boolean confirm() {
        if (typed < pageLen) {
            typed = pageLen;              // домотать печатную машинку
            return true;
        }
        if (page < pages.length - 1) {
            page++;
            acc = 0;
            pageLen = pages[page][0].length() + pages[page][1].length();
            typed = ticksPerChar == 0 ? pageLen : 0;
            return true;
        }
        return false;
    }

    /** Идёт ли печать прямо сейчас (для мигающей anja). */
    boolean isTyping() {
        return typed < pageLen;
    }

    /** Допечатана ли текущая страница целиком. */
    boolean pageShown() {
        return typed >= pageLen;
    }

    /** Есть ли страницы после текущей. */
    boolean hasMorePages() {
        return page < pages.length - 1;
    }

    /** Видимая строка row (0/1) с учётом печатной машинки. */
    String line(int row) {
        String l = pages[page][row];
        if (typed >= pageLen) {
            return l;
        }
        int budget = typed - (row == 0 ? 0 : pages[page][0].length());
        if (budget <= 0) {
            return "";
        }
        return l.substring(0, Math.min(budget, l.length()));
    }

    /** Текст, для которого построен кэш (сравнение с bf в render). */
    String shownText() {
        return shown;
    }
}
