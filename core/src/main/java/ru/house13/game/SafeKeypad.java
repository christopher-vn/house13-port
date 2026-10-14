package ru.house13.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.house13.engine.Assets;
import ru.house13.engine.KeyMap.J2;

/**
 * Экранная клавиатура сейфа — дополнение порта (этап 5b; в оригинале ввод
 * кода 9819 — только цифровыми клавишами телефона).
 *
 * Телефонная сетка 3x4 под панелью ввода:
 *      [1] [2] [3]
 *      [4] [5] [6]
 *      [7] [8] [9]
 *      [✓] [0] [✕]     ✓ = подтверждение ('5'), ✕ = закрыть (правый софт)
 *
 * Ввод: стрелки D-PAD'а двигают курсор (пульсирующая рамка — 2 тика ярко /
 * 1 тик тускло, идиома мигания логотипа оригинала), OK (FIRE) нажимает
 * выбранную клетку. Клавиатора — ИСТОЧНИК КЛАВИШ: она доставляет
 * оригинальную J2ME-клавишу ('1'..'9', '0', '5', правый софт) в неизменный
 * каскад byte(int) — Safe (s()/try-хвост) и все его квирки (невидимая
 * первая цифра aQ[8], подтверждение '5' шестым нажатием) не тронуты.
 * Та же схема будет использована тач-вводом Android (этап 6).
 *
 * Физические цифры 0-9 остаются быстрым путём ввода (мимо курсора),
 * F1/Esc/X — закрытие софтами, как в оригинале.
 */
final class SafeKeypad {

    /** Геометрия сетки (виртуальные пиксели 240x320). */
    static final int COLS = 3, ROWS = 4;
    static final int X0 = 36, Y0 = 186, CELL_W = 52, CELL_H = 25;
    static final int GAP_X = 6, GAP_Y = 5;

    /** Индексы служебных клеток нижнего ряда. */
    static final int IDX_OK = 9, IDX_ZERO = 10, IDX_CLOSE = 11;

    /** Клавиша каскада для каждой клетки (0..8 = '1'..'9'). */
    private static final J2[] CELL_KEYS = {
        J2.NUM_1, J2.NUM_2, J2.NUM_3,
        J2.NUM_4, J2.NUM_5, J2.NUM_6,
        J2.NUM_7, J2.NUM_8, J2.NUM_9,
        J2.NUM_5,      // ✓ — подтверждение, как '5' оригинала (ag==6)
        J2.NUM_0,      // 0
        J2.SOFT_RIGHT, // ✕ — закрытие правым софтом (byte 954)
    };

    int cursor;            // 0..11; package-private для тестов
    private boolean wasOpen;

    /** Клавиша каскада для клетки idx. */
    static J2 keyAt(int idx) {
        return CELL_KEYS[idx];
    }

    /** Курсорная пульсация: 2 тика ярко / 1 тускло (как мигание LOGO). */
    static boolean pulseBright(long tick) {
        return tick % 3 != 0;
    }

    /**
     * Трансформация клавиши каскада при открытом сейфе.
     * Стрелки двигают курсор и ПОГЛОЩАЮТСЯ (null); FIRE превращается в
     * клавишу выбранной клетки; остальное проходит без изменений.
     * При (пере)открытии сейфа курсор возвращается на '1'.
     */
    J2 map(J2 k, boolean safeOpen) {
        if (safeOpen && !wasOpen) {
            cursor = 0;
        }
        wasOpen = safeOpen;
        if (!safeOpen) {
            return k;
        }
        switch (k) {
            case UP:
                if (cursor >= COLS) {
                    cursor -= COLS;
                }
                return null;
            case DOWN:
                if (cursor < COLS * (ROWS - 1)) {
                    cursor += COLS;
                }
                return null;
            case LEFT:
                if (cursor % COLS != 0) {
                    cursor--;
                }
                return null;
            case RIGHT:
                if (cursor % COLS != COLS - 1) {
                    cursor++;
                }
                return null;
            case FIRE:
                return keyAt(cursor);
            default:
                return k;
        }
    }

    /**
     * Отрисовка: подложка, 12 клеток (цифры — num.png x2, ✓ — password.png
     * x2, ✕ — две повёрнутые полосы), пульсирующая рамка курсора.
     * Вызывается из GameScene.render при открытом сейфе.
     */
    void render(SpriteBatch batch, Assets a, long tick) {
        int totalW = COLS * CELL_W + (COLS - 1) * GAP_X;
        int totalH = ROWS * CELL_H + (ROWS - 1) * GAP_Y;
        a.fill(batch, X0 - 6, Y0 - 5, totalW + 12, totalH + 10, 0, 0, 0, 0.62f);
        for (int idx = 0; idx < CELL_KEYS.length; idx++) {
            int x = X0 + (idx % COLS) * (CELL_W + GAP_X);
            int y = Y0 + (idx / COLS) * (CELL_H + GAP_Y);
            a.fill(batch, x, y, CELL_W, CELL_H, 34 / 255f, 34 / 255f, 40 / 255f, 1);
            border(a, batch, x, y, CELL_W, CELL_H, 96 / 255f, 96 / 255f, 104 / 255f, 1);
            if (idx <= 8) {
                batch.draw(a.region("num.png", (idx + 1) * 9, 0, 9, 10, false, true),
                        x + 17, y + 2, 18, 20);
            } else if (idx == IDX_OK) {
                batch.draw(a.region("password.png", 116, 11, 13, 13, false, true),
                        x + 13, y, 26, 26);
            } else if (idx == IDX_ZERO) {
                batch.draw(a.region("num.png", 0, 0, 9, 10, false, true),
                        x + 17, y + 2, 18, 20);
            } else {
                drawCross(batch, a, x + CELL_W / 2, y + CELL_H / 2);
            }
        }
        // пульсирующая рамка курсора
        int cx = X0 + (cursor % COLS) * (CELL_W + GAP_X);
        int cy = Y0 + (cursor / COLS) * (CELL_H + GAP_Y);
        if (pulseBright(tick)) {
            border(a, batch, cx - 2, cy - 2, CELL_W + 4, CELL_H + 4, 1, 1, 1, 2);
        } else {
            border(a, batch, cx - 1, cy - 1, CELL_W + 2, CELL_H + 2,
                    150 / 255f, 150 / 255f, 150 / 255f, 1);
        }
    }

    /** Прямоугольная рамка толщиной t из четырёх заливок. */
    private static void border(Assets a, SpriteBatch b,
                               int x, int y, int w, int h,
                               float r, float g, float bl, int t) {
        a.fill(b, x, y, w, t, r, g, bl, 1);
        a.fill(b, x, y + h - t, w, t, r, g, bl, 1);
        a.fill(b, x, y + t, t, h - 2 * t, r, g, bl, 1);
        a.fill(b, x + w - t, y + t, t, h - 2 * t, r, g, bl, 1);
    }

    /** Крестик из двух повёрнутых полос white-текстуры (стиль мокапа). */
    private static void drawCross(SpriteBatch batch, Assets a, int cx, int cy) {
        Color save = batch.getColor().cpy();
        batch.setColor(220 / 255f, 220 / 255f, 220 / 255f, 1f);
        TextureRegion w = new TextureRegion(a.white());
        batch.draw(w, cx - 8, cy - 2, 8, 2, 16, 4, 1, 1, 45f);
        batch.draw(w, cx - 8, cy - 2, 8, 2, 16, 4, 1, 1, -45f);
        batch.setColor(save);
    }
}
