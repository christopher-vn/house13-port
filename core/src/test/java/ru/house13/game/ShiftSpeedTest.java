package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Ускорение Shift (дополнение порта, этап 5b): удержание — 10 px/тик
 * (два шага по 5 px с валидацией каждого), без Shift — оригинальные 5 px.
 * Отпускание Shift НЕ сбрасывает движение (в отличие от J2ME-клавиш);
 * у стены ускорение не туннелирует.
 */
class ShiftSpeedTest {

    private FakeHost host;
    private GameScene game;

    @BeforeEach
    void setUp() {
        host = new FakeHost();
        game = GameScene.postSkip(host);   // рука (149,240), комната 0
    }

    @Test
    void shiftDoublesSpeed() {
        game.tick(new int[]{Input.Keys.RIGHT}, new int[0]);
        assertEquals(154, game.room.hand.j, "оригинальная скорость: 5 px/тик");
        game.tick(new int[0], new int[]{Input.Keys.RIGHT});
        game.tick(new int[]{Input.Keys.SHIFT_LEFT, Input.Keys.RIGHT}, new int[0]);
        assertEquals(164, game.room.hand.j, "с Shift: два шага по 5 px за тик");
        assertTrue(game.fast);
    }

    @Test
    void rightShiftWorksToo() {
        game.tick(new int[]{Input.Keys.SHIFT_RIGHT, Input.Keys.RIGHT}, new int[0]);
        assertEquals(159, game.room.hand.j);
        assertTrue(game.fast);
    }

    @Test
    void shiftReleaseKeepsMoving() {
        game.tick(new int[]{Input.Keys.SHIFT_LEFT, Input.Keys.RIGHT}, new int[0]);
        assertEquals(159, game.room.hand.j);
        game.tick(new int[0], new int[]{Input.Keys.SHIFT_LEFT});
        assertFalse(game.fast);
        assertEquals(5, game.room.hand.dx, "движение продолжается (Shift — не J2ME-клавиша)");
        assertEquals(164, game.room.hand.j, "тик отпускания уже шагает обычные 5 px");
        game.tick(new int[0], new int[0]);
        assertEquals(169, game.room.hand.j, "обычная скорость после отпускания Shift");
    }

    @Test
    void directionReleaseStops() {
        game.tick(new int[]{Input.Keys.SHIFT_LEFT, Input.Keys.RIGHT}, new int[0]);
        game.tick(new int[0], new int[]{Input.Keys.RIGHT});
        assertEquals(0, game.room.hand.dx);
        int j = game.room.hand.j;
        game.tick(new int[0], new int[0]);
        assertEquals(j, game.room.hand.j, "без направления рука стоит");
    }

    @Test
    void shiftAloneDoesNotMove() {
        game.tick(new int[]{Input.Keys.SHIFT_LEFT}, new int[0]);
        assertEquals(149, game.room.hand.j);
        assertEquals(0, game.room.hand.dx);
        assertEquals(0, game.room.hand.dy);
    }

    @Test
    void fastDoesNotTunnelThroughWall() {
        // правая стена {232,0,8,320}: из j=207 (hitbox 208..228) шаг 212 блокируется
        game.room.hand.j = 207;
        game.tick(new int[]{Input.Keys.SHIFT_LEFT, Input.Keys.RIGHT}, new int[0]);
        assertEquals(207, game.room.hand.j, "первый же подшаг блокируется стеной");
        assertTrue(game.room.hand.blocked);
        assertEquals(0, game.room.hand.dx);
    }
}
