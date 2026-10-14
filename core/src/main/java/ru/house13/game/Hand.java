package ru.house13.game;

/**
 * Рука-курсор: позиция, скорость, направленный спрайт.
 * Порт семантики RU b.java (c.java = MySprite + p()/long()):
 *
 *  - Позиция (j, i) — верхний левый угол кадра 21x(28..30).
 *  - Скорость else/char: устанавливается в keyPressed (±5), применяется
 *    ПО ТИКУ движком a() пока клавиша удерживается; keyReleased обнуляет.
 *  - Хитбокс (палец): точные неравенства RU c.a(int[],0) при p()
 *    (int=25, long=5, c=0 -> ширина кадра 21, new=0):
 *      (w.x + w.w - 1 > j) && (j + 21 > w.x)
 *      && (w.y + w.h - 25 > i) && (i + 30 > w.y)
 *    т.е. полоска x(j+1..j+21) y(i+25..i+30) против стены (w.x..w.x+w.w)x(w.y..w.y+w.h).
 *  - Направленные кадры (RU добавил использование рядов, которые CN грузил,
 *    но не показывал): вниз y=0 h=30, вверх y=30 h=29, влево y=59 h=30,
 *    вправо y=89 h=28; кадры анимации x=0/21/42, ширина 21 (hand.png 64x118).
 */
public final class Hand {

    // Ряды в листе hand.png: смещение y и высота по направлению
    public static final int ROW_DOWN_Y = 0,  ROW_DOWN_H = 30;
    public static final int ROW_UP_Y   = 30, ROW_UP_H   = 29;
    public static final int ROW_LEFT_Y = 59, ROW_LEFT_H = 30;
    public static final int ROW_RIGHT_Y = 89, ROW_RIGHT_H = 28;
    public static final int FRAME_W = 21;
    public static final int FRAME_X_STEP = 21; // кадры 0/21/42
    public static final int FRAME_COUNT = 3;

    public int j;        // positionX (b.a8.j)
    public int i;        // positionY (b.a8.i)
    public int dx;       // скорость X (a8.else), 0 или ±5
    public int dy;       // скорость Y (a8.char)

    public int rowY;     // текущий ряд листа (по направлению)
    public int rowH;
    public int frame;    // анимационный кадр 0..2 (a8.goto)

    public boolean blocked;  // r (qingchu): движение в стену
    public boolean moving;   // флаги ac/bn/Z/c9: идёт анимация ходьбы

    // Флаги направления RU (b.dQ/c/b/b3 = pshang/pxia/pzuo/pyou):
    // устанавливаются нажатием (case(int) — свой флаг true, остальные false).
    // ЗАЩЁЛКА «куда смотрит рука»: keyReleased оригинала (b.java 1319-1338)
    // их НЕ сбрасывает — направление живёт до нажатия ДРУГОЙ стрелки.
    // Читаются хот-зонами try(int) и ховер-подсказками u() — поэтому в
    // оригинале можно отпустить джойстик и только потом жать '5'.
    public boolean dirUp, dirDown, dirLeft, dirRight;

    /** c.try (видимость спрайта): гасится титрами e() и НЕ восстанавливается
     *  — оригинальный баг (рука невидима после концовки до перезапуска). */
    public boolean visible = true;

    public Hand(int j, int i) {
        this.j = j;
        this.i = i;
        setDirectionDown();
    }

    public void setDirectionUp()    { rowY = ROW_UP_Y;    rowH = ROW_UP_H; }
    public void setDirectionDown()  { rowY = ROW_DOWN_Y;  rowH = ROW_DOWN_H; }
    public void setDirectionLeft()  { rowY = ROW_LEFT_Y;  rowH = ROW_LEFT_H; }
    public void setDirectionRight() { rowY = ROW_RIGHT_Y; rowH = ROW_RIGHT_H; }

    /** Применить скорость (c.a(): j += else; i += char). */
    public void apply() {
        j += dx;
        i += dy;
    }

    /**
     * Валидация p(): попытка шага (dx,dy) из текущей позиции.
     * Если хитбокс пальца на новой позиции пересекает любую стену комнаты —
     * скорость обнуляется, blocked=true. Позиция НЕ меняется (в RU: a()-откат).
     * Возвращает true, если шаг свободен.
     */
    public boolean validate(int[][] walls) {
        int nj = j + dx;
        int ni = i + dy;
        for (int[] w : walls) {
            if (w[0] + w[2] - 1 > nj && nj + FRAME_W > w[0]
                    && w[1] + w[3] - 25 > ni && ni + 30 > w[1]) {
                dx = 0;
                dy = 0;
                blocked = true;
                return false;
            }
        }
        return true;
    }

    /** Анимация ходьбы из paint() RU: пинг-понг 0->1->2->1->2...; при блокировке кадр 0. */
    public void animate() {
        if (moving) {
            if (frame != 2) {
                frame++;
            } else if (frame == 2) {
                frame--;
            }
            if (blocked) {
                frame = 0;
            }
        }
    }

    /** keyReleased (b.java 1319-1338, b0==2): скорость 0, кадр 0, анимация
     *  стоп. Флаги направлений dQ/c/b/b3 НЕ трогаем — это защёлка последнего
     *  направления оригинала (сбрасывается только нажатием другой стрелки
     *  в case(int)); иначе '5' после отпускания стрелки не попадает
     *  в хот-зоны (баг-репорт «шкаф не открывается»). */
    public void release() {
        dx = 0;
        dy = 0;
        frame = 0;
        moving = false;
        blocked = false;
    }
}
