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
 * Скорость печатной машинки (этап 5d) — в ДЕСЯТЫХ ДОЛЯХ символа за тик
 * (тик игры = 0.1 c, аккумулятор сглаживает дробность):
 *   0  — мгновенно (как в оригинале);
 *   20 — 2 симв/тик = 20 симв/с (быстро);
 *   10 — 1 симв/тик = 10 симв/с (обычно; в 5c было 2 тика/символ =
 *        5 симв/с — «прям медленно», ускорено вдвое);
 *   5  — 0.5 симв/тик = 5 симв/с (медленно).
 *
 * Анимация (5d): пока страница печатается, рендер показывает каретку
 * за печатной головкой (typingRow) и подсвечивает свежий символ
 * (sinceTyped — тиков с последнего символа); когда страница допечатана,
 * вместо каретки мигает anja-самоцвет (GameScene).
 */
final class DialogBox {

    /** Текст, для которого построен кэш (перестройка при смене bf). */
    private String shown = "";
    /** Страницы по 2 строки: pages[p] = {строка1, строка2 (может быть "")}. */
    private String[][] pages = new String[][]{{"", ""}};
    private int page;
    /** Напечатано символов на текущей странице (обе строки суммарно). */
    private int typed;
    /** Аккумулятор дробной скорости: десятые долей символа. */
    private int acc10;
    /** Скорость: десятых символа за тик; 0 = мгновенно. */
    private int speed;
    /** Тиков с последнего напечатанного символа (подсветка «чернил»). */
    private int sinceTyped;
    /** Полная длина страницы (для сравнения с typed). */
    private int pageLen;

    /** Настроить скорость: десятых символа за тик; 0 = мгновенно. */
    void setSpeed(int tenthsPerTick) {
        this.speed = Math.max(0, tenthsPerTick);
        if (speed == 0) {
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
        acc10 = 0;
        sinceTyped = 0;
        pageLen = pages[0][0].length() + pages[0][1].length();
        typed = speed == 0 ? pageLen : 0;
    }

    /** Сброс (bf очищен) — бокс снова «невидимый» с пассивным поведением. */
    void clear() {
        shown = "";
        pages = new String[][]{{"", ""}};
        page = 0;
        typed = 0;
        pageLen = 0;
        acc10 = 0;
        sinceTyped = 0;
    }

    /** Печатная машинка: acc10 += speed, каждые 10 десятых — символ. */
    void tick() {
        if (typed >= pageLen || speed == 0) {
            return;
        }
        sinceTyped++;
        acc10 += speed;
        while (acc10 >= 10 && typed < pageLen) {
            acc10 -= 10;
            typed++;
            sinceTyped = 0;
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
            acc10 = 0;
            sinceTyped = 0;
            pageLen = pages[page][0].length() + pages[page][1].length();
            typed = speed == 0 ? pageLen : 0;
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

    /** Строка, на которой стоит печатная головка (0/1); -1 — не печатает.
     *  Ровно на ней каретка и «свежие чернила» рендера. */
    int typingRow() {
        if (typed >= pageLen) {
            return -1;
        }
        return typed < pages[page][0].length() ? 0 : 1;
    }

    /** Тиков с последнего напечатанного символа (для подсветки «чернил»). */
    int sinceTyped() {
        return sinceTyped;
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
