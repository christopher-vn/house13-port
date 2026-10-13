package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.house13.engine.Assets;
import ru.house13.engine.KeyMap.J2;

/**
 * Сейф. Порт s() (shuju — отрисовка) + хвост try(int) 4558-4704 (ввод).
 *
 * try (isOutpasswordkuang) — диалог открыт; ag (keydownNum) — позиция
 * курсора 0..5; цифры пишутся в aQ[8..12] (aQ[9..12] — четыре ВИДИМЫЕ,
 * aQ[8] — невидимая первая, туда же попадает '5', открывающая сейф —
 * так написан оригинал). Код 9819 подтверждается клавишей '5' при ag==6.
 *
 * Особенности оригинала, переносимые дословно:
 *  - ag растёт на ЛЮБУЮ клавишу (в т.ч. не-цифру — позиция пропускается);
 *  - первая набранная цифра всегда попадает в aQ[8] и не отображается;
 *  - закрытие: правый софт (byte() 954-965) и левый софт (try(int) 4558)
 *    сбрасывают aQ[8..13] и ag;
 *  - при неверном коде: «PIN-код неверный», закрытие, k=true.
 */
public final class Safe {

    /** b.try (isOutpasswordkuang): диалог ввода кода открыт. */
    boolean open;

    /** b.ag (keydownNum): 0..5 — позиция курсора (6 — момент проверки). */
    int ag;

    private final GameScene game;
    private final Inventory inv;

    Safe(GameScene game, Inventory inv) {
        this.game = game;
        this.inv = inv;
    }

    /** Сброс цифр и курсора (общий хвост закрытия). */
    private void resetDigits() {
        ag = 0;
        inv.aQ[11] = 0;
        inv.aQ[9] = 0;
        inv.aQ[10] = 0;
        inv.aQ[12] = 0;
        inv.aQ[13] = 0;
    }

    /** Закрытие по ПРАВОМУ софту (byte 954-965): сброс aQ[8..14]. */
    void closeByRightSoft() {
        open = false;
        ag = 0;
        for (int idx = 8; idx <= 14; idx++) {
            inv.aQ[idx] = 0;
        }
    }

    /** Закрытие по ЛЕВОМУ софту (try(int) 4558-4566): сброс только aQ[9..13]. */
    void closeByLeftSoft() {
        open = false;
        ag = 0;
        inv.aQ[11] = 0;
        inv.aQ[9] = 0;
        inv.aQ[10] = 0;
        inv.aQ[12] = 0;
        inv.aQ[13] = 0;
    }

    /**
     * Ввод при открытом сейфе (хвост try(int) 4568-4704): вызывается из
     * hotZones на каждую клавишу ПОСЛЕ отработки хот-зон (закрытие левым
     * софтом делает сам hotZones — try(int) 4558).
     */
    boolean handleKey(J2 k) {
        if (!open) {
            return false;
        }
        // try(int) 4568-4704: ag++ на любую клавишу, цифры 48-57 ('0'-'9');
        // не-цифра позицию ПРОПУСКАЕТ (в оригинале ничего не пишется)
        ag++;
        int digit = -1;
        if (k == J2.NUM_0) digit = 0;
        else if (k == J2.NUM_1) digit = 1;
        else if (k == J2.NUM_2) digit = 2;
        else if (k == J2.NUM_3) digit = 3;
        else if (k == J2.NUM_4) digit = 4;
        else if (k == J2.NUM_5) digit = 5;
        else if (k == J2.NUM_6) digit = 6;
        else if (k == J2.NUM_7) digit = 7;
        else if (k == J2.NUM_8) digit = 8;
        else if (k == J2.NUM_9) digit = 9;

        if (digit >= 0) {
            if (ag == 1) {
                inv.aQ[8] = digit;      // невидимая первая позиция
            } else if (ag == 2) {
                inv.aQ[9] = digit;
            } else if (ag == 3) {
                inv.aQ[10] = digit;
            } else if (ag == 4) {
                inv.aQ[11] = digit;
            } else if (ag == 5) {
                inv.aQ[12] = digit;
            }
        }
        if (ag == 6) {
            if (inv.aQ[9] != 9 || inv.aQ[10] != 8 || inv.aQ[11] != 1
                    || inv.aQ[12] != 9 || k != J2.NUM_5) {
                // неверный код (или не '5'): сообщение, закрытие, k=true
                if (!game.kFlag) {
                    game.bf = Texts.SAFE_WRONG;
                }
                open = false;
                resetDigits();
                game.kFlag = true;
                return true;
            }
            // код 9819 верен
            open = false;
            game.dB = true;             // сейф открыт навсегда
            ag = 0;
            game.bf = Texts.SAFE_OPEN;
            game.sound().playCorrect(); // реставрация: correct.mid
        }
        return true;
    }

    /**
     * s() (shuju): рамка password.png, курсор по ag, галочка при ag==5,
     * цифры num.png (кадры 0-9 по 9px) при ag >= позиции+1.
     * Рисуется только когда open && aQ[15]==0 (не в концовке).
     */
    void render(SpriteBatch batch, Assets a) {
        if (!open || inv.aQ[15] != 0) {
            return;
        }
        batch.draw(a.region("password.png", 0, 0, 116, 35, false, true),
                (240 - 116) / 2, (320 - 35) / 2);
        switch (ag) {
            case 1:
                batch.draw(a.region("password.png", 129, 4, 8, 26, false, true), 74, 147);
                break;
            case 2:
                batch.draw(a.region("password.png", 129, 4, 8, 26, false, true), 95, 147);
                break;
            case 3:
                batch.draw(a.region("password.png", 129, 4, 8, 26, false, true), 116, 147);
                break;
            case 4:
                batch.draw(a.region("password.png", 129, 4, 8, 26, false, true), 137, 147);
                break;
            case 5:
                batch.draw(a.region("password.png", 129, 4, 8, 26, false, true), 159, 147);
                batch.draw(a.region("password.png", 116, 11, 13, 13, false, true), 156, 153);
                break;
            case 6:
            default:
                break;
        }
        // num.png 90x10, 10 кадров по 9px: aQ[9..12] при ag >= i+1
        if (ag >= 2) {
            batch.draw(a.region("num.png", (inv.aQ[9] % 10) * 9, 0, 9, 10, false, true), 74, 155);
        }
        if (ag >= 3) {
            batch.draw(a.region("num.png", (inv.aQ[10] % 10) * 9, 0, 9, 10, false, true), 95, 155);
        }
        if (ag >= 4) {
            batch.draw(a.region("num.png", (inv.aQ[11] % 10) * 9, 0, 9, 10, false, true), 116, 155);
        }
        if (ag >= 5) {
            batch.draw(a.region("num.png", (inv.aQ[12] % 10) * 9, 0, 9, 10, false, true), 137, 155);
        }
    }
}
