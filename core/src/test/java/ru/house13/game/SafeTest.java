package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.house13.engine.KeyMap.J2;

import static org.junit.jupiter.api.Assertions.*;

/** Сейф: открытие, ввод (quirk невидимой первой цифры), код 9819, закрытия. */
class SafeTest {

    private FakeHost host;
    private GameScene game;
    private Safe safe;
    private Inventory inv;

    @BeforeEach
    void setUp() {
        host = new FakeHost();
        game = GameScene.postSkip(host);
        safe = game.safe;
        inv = game.inv;
    }

    private void openSafeAtSpot() {
        game.room.room = 1;
        game.room.hand.j = 190;   // зона сейфа (173..203)
        game.room.hand.i = 100;   // (0..138)
        game.room.hand.dirUp = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);  // '5' открывает
        assertTrue(safe.open);
        // открывающая '5' попадает в невидимую первую позицию aQ[8]
        assertEquals(1, safe.ag);
        assertEquals(5, inv.aQ[8]);
    }

    @Test
    void fiveOpensSafeAndFeedsInvisibleDigit() {
        openSafeAtSpot();
    }

    @Test
    void digitsFillVisiblePositionsByCursor() {
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.NUMPAD_9}, new int[0]);   // ag 2 -> aQ[9]
        game.tick(new int[]{Input.Keys.NUMPAD_8}, new int[0]);   // ag 3 -> aQ[10]
        game.tick(new int[]{Input.Keys.NUMPAD_1}, new int[0]);   // ag 4 -> aQ[11]
        game.tick(new int[]{Input.Keys.NUMPAD_9}, new int[0]);   // ag 5 -> aQ[12]
        assertEquals(9, inv.aQ[9]);
        assertEquals(8, inv.aQ[10]);
        assertEquals(1, inv.aQ[11]);
        assertEquals(9, inv.aQ[12]);
        assertEquals(5, safe.ag);
    }

    @Test
    void correctCode9819PlusFiveOpensSafeForever() {
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.NUMPAD_9}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_8}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_1}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_9}, new int[0]);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);      // '5' — подтверждение
        assertFalse(safe.open);
        assertTrue(game.dB, "сейф открыт навсегда");
        assertEquals(Texts.SAFE_OPEN, game.bf);
        assertTrue(host.sound.isPlaying(3), "correct.mid при коде");
        // ключ теперь можно взять из открытого сейфа
        game.room.hand.j = 190;
        game.room.hand.i = 100;
        game.room.hand.dirUp = true;
        game.room.hand.frame = 0;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.w);
        assertEquals(4, inv.slots[0].id, "маленький ключ в инвентаре");
    }

    @Test
    void wrongCodeClosesAndShowsMessage() {
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.NUMPAD_1}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_2}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_3}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_4}, new int[0]);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertFalse(safe.open);
        assertEquals(Texts.SAFE_WRONG, game.bf);
        assertTrue(game.kFlag);
        assertEquals(0, inv.aQ[9]);
        assertEquals(0, inv.aQ[12]);
    }

    @Test
    void confirmMustBeFive() {
        // 6-я клавиша не '5' — код не принят, даже если цифры верны
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.NUMPAD_9}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_8}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_1}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_9}, new int[0]);
        game.tick(new int[]{Input.Keys.NUMPAD_3}, new int[0]);   // не '5'!
        assertFalse(safe.open);
        assertFalse(game.dB);
        assertEquals(Texts.SAFE_WRONG, game.bf);
    }

    @Test
    void nonDigitKeySkipsPositionButAdvancesCursor() {
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.UP}, new int[0]);   // не цифра: ag++, позиция не пишет
        assertEquals(2, safe.ag);
        assertEquals(0, inv.aQ[9], "позиция пропущена со значением 0");
    }

    @Test
    void rightSoftCloseResets8to14() {
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.NUMPAD_9}, new int[0]);
        inv.aQ[14] = 2;    // «золотой ключик»-слот (quirk оригинала)
        game.tick(new int[]{Input.Keys.ESCAPE}, new int[0]);  // правый софт
        assertFalse(safe.open);
        assertEquals(0, inv.aQ[8]);
        assertEquals(0, inv.aQ[9]);
        assertEquals(0, inv.aQ[14], "byte 954: сброс и aQ[14]");
    }

    @Test
    void leftSoftCloseKeepsAq8And14() {
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.NUMPAD_9}, new int[0]);
        inv.aQ[14] = 2;
        game.tick(new int[]{Input.Keys.F1}, new int[0]);   // левый софт
        assertFalse(safe.open);
        assertEquals(5, inv.aQ[8], "try(int) 4558: aQ[8] НЕ сбрасывается");
        assertEquals(2, inv.aQ[14], "и aQ[14] тоже");
        assertEquals(0, inv.aQ[9]);
    }

    @Test
    void movementBlockedWhileSafeOpen() {
        openSafeAtSpot();
        game.tick(new int[]{Input.Keys.UP}, new int[0]);
        assertEquals(0, game.room.hand.dy, "case() гейтится !try");
    }
}
