package ru.house13.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Текстовые утилиты оригинала, вынесенные в чистую логику (без Graphics):
 *
 *  - format(text, maxWidth, metrics) — порт MainCanvas.format() /
 *    b.a(String,int,Font): побуквенный перенос по ширине, '\n' — явный
 *    перевод строки. Символ, не влезающий в строку, НАЧИНАЕТ следующую
 *    (в оригинале substring(index0, index1) его исключает).
 *    Отличие от оригинала: защита от зависания — если ОДИН символ шире
 *    maxWidth, оригинал зацикливался бы; порт принудительно включает его
 *    в строку (в игровых текстах такой ситуации нет).
 *
 *  - clampRow(rowIndex, lines) — кламп скролла пейджера drawString()/
 *    b.for(String[],...): [0, lines-3].
 */
public final class TextFmt {

    /** Аналог javax.microedition.lcdui.Font для замены в тестах. */
    public interface Metrics {
        int stringWidth(String s);
    }

    private TextFmt() {
    }

    /** Порт format(): разбиение текста на строки по ширине. */
    public static String[] format(String text, int maxWidth, Metrics m) {
        List<String> lines = new ArrayList<>();
        int len = text.length();
        int index1 = 0;
        do {
            int start = index1;
            int widthes = 0;
            boolean wrap = false;
            int i = index1;
            while (i < len) {
                char c = text.charAt(i);
                if (c == '\n') {
                    i++;
                    wrap = true;
                    break;
                }
                widthes += m.stringWidth(String.valueOf(c));
                if (widthes > maxWidth) {
                    break;
                }
                i++;
            }
            // Защита от вырожденного случая (символ шире строки) — оригинал
            // зацикливался; порт включает символ в текущую строку.
            if (i == start && !wrap) {
                i = start + 1;
            }
            lines.add(text.substring(start, wrap ? i - 1 : i));
            index1 = i;
        } while (index1 < len);
        return lines.toArray(new String[0]);
    }

    /** Кламп начала вывода пейджера: отрицательные -> 0, большие -> lines-3. */
    public static int clampRow(int rowIndex, int lines) {
        int r = rowIndex;
        if (r <= 0) {
            r = 0;
        }
        if (r >= lines - 1 - 2) {
            r = lines - 1 - 2;
        }
        return r;
    }
}
