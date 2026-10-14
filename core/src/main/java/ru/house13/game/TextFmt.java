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

    // ==================================================================
    // ЭТАП 5c. Типографика диалогов порта: нормализация + перенос по словам.
    // ==================================================================

    /**
     * Нормализация типографики текстов паков (этап 5c). Оригинальный
     * перевод V.P.K набирался на узком экране J2ME и содержит артефакты:
     * тройные-десятикратные пробелы («Ключ!..    Интересно»), слитные
     * знаки препинания («Помню,мы вошли», «Шизуко.4 года»). Нормализация
     * применяется при Texts.reload() ко всем строковым полям — JSON-паки
     * хранят дословный исторический текст, правка только косметическая:
     *
     *  - внутренние мульти-пробелы сжимаются в один (ведущие пробелы строк
     *    сохраняются — ими центруются заголовки HELP/ABOUT);
     *  - после , ; : ! ? добавляется пробел, если дальше идёт буква/цифра;
     *  - после точки пробел добавляется только перед ЗАГЛАВНОЙ кириллицей —
     *    v1.0, mail.ru, V.P.K и латиница не затрагиваются;
     *  - последовательности «!..» «?...» обрабатываются по последнему
     *    знаку («могу выйти!...Какая» -> «!... Какая»).
     */
    public static String norm(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 8);
        boolean lineStart = true;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (lineStart) {
                    sb.append(c);          // ведущие пробелы — как есть
                } else if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(' ');        // сжатие мульти-пробелов
                }
                continue;
            }
            if (c == '\n') {
                sb.append(c);
                lineStart = true;
                continue;
            }
            lineStart = false;
            sb.append(c);
            if (isPunct(c)) {
                char next = i + 1 < len ? s.charAt(i + 1) : '\0';
                // «...» и «!..» — пробел ставит последний знак цепочки
                if (next != '\0' && next != ' ' && next != '\n' && next != '.') {
                    if (c == '.') {
                        // перед ЗАГЛАВНОЙ кириллицей («Удар.Темнота») и перед
                        // цифрой после буквы («Шизуко.4 года»); v1.0 и mail.ru
                        // (цифра/латиница до точки) не затрагиваются
                        if (isUpperCyr(next)
                                || (Character.isDigit(next)
                                && sb.length() > 1
                                && Character.isLetter(sb.charAt(sb.length() - 2)))) {
                            sb.append(' ');
                        }
                    } else if (Character.isLetterOrDigit(next)) {
                        sb.append(' ');
                    }
                }
            }
        }
        return sb.toString();
    }

    private static boolean isPunct(char c) {
        return c == ',' || c == ';' || c == ':' || c == '!' || c == '?' || c == '.';
    }

    private static boolean isUpperCyr(char c) {
        return c >= 'А' && c <= 'Я' || c == 'Ё';
    }

    /**
     * Перенос ПО СЛОВАМ (этап 5c) для диалогового бокса: слово не рвётся
     * посередине, '\n' — явный перевод строки. Слово, не влезающее в
     * maxWidth целиком, начинается новой строкой; если оно шире самой
     * строки — режется побуквенно (защита, в текстах паков такого нет).
     * format() оригинала (побуквенный) остаётся для экранов-скроллов.
     */
    public static String[] wrapWords(String text, int maxWidth, Metrics m) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return new String[]{""};
        }
        for (String para : text.split("\n", -1)) {
            if (para.isEmpty()) {
                lines.add("");
                continue;
            }
            StringBuilder cur = new StringBuilder();
            for (String word : para.split(" ")) {
                if (word.isEmpty()) {
                    continue;
                }
                String cand = cur.length() == 0 ? word : cur + " " + word;
                if (m.stringWidth(cand) <= maxWidth) {
                    cur.setLength(0);
                    cur.append(cand);
                    continue;
                }
                if (cur.length() > 0) {
                    lines.add(cur.toString());
                    cur.setLength(0);
                }
                if (m.stringWidth(word) <= maxWidth) {
                    cur.append(word);
                } else {
                    // вырожденный случай: слово шире строки — режем по буквам
                    for (int i = 0; i < word.length(); i++) {
                        String ch = String.valueOf(word.charAt(i));
                        if (cur.length() == 0 || m.stringWidth(cur + ch) <= maxWidth) {
                            cur.append(ch);
                        } else {
                            lines.add(cur.toString());
                            cur.setLength(0);
                            cur.append(ch);
                        }
                    }
                }
            }
            if (cur.length() > 0) {
                lines.add(cur.toString());
            }
        }
        return lines.toArray(new String[0]);
    }
}
