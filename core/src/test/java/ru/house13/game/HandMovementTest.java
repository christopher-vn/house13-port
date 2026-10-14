package ru.house13.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Движок движения: семантика p()/a() из b.java. Комната 0 (cI, спальня). */
class HandMovementTest {

    private static final int[][] C0 = RoomData.WALLS[0];

    @Test
    void старт_игры_блокирован_сверху() {
        // Старт (149,240); «вверх» — hitbox (174..179)x(265..270) не задевает
        // потолок {0,0,240,190} (190-25=165 > 240? нет) — двигаемся до i=165.
        Hand h = new Hand(149, 240);
        h.setDirectionUp();
        h.dy = -5;
        int guard = 0;
        while (h.dy != 0 && guard++ < 100) {
            h.validate(C0);
            h.apply();
        }
        assertEquals(165, h.i, "палец упирается в потолок y=190 (hitbox i+25=190)");
        assertEquals(149, h.j);
        assertTrue(h.blocked);
    }

    @Test
    void вниз_через_проход_останавливается_на_270() {
        Hand h = new Hand(109, 240);
        h.setDirectionDown();
        h.dy = 5;
        for (int t = 0; t < 40; t++) {
            h.validate(C0);
            h.apply();
        }
        assertEquals(270, h.i);
        assertTrue(h.blocked);
    }

    @Test
    void блок_у_стены_не_меняет_позицию_и_обнуляет_скорость() {
        // (109,270): вниз — нижняя плита {0,300,133,20} накрывает hitbox x(110..130).
        Hand h = new Hand(109, 270);
        h.setDirectionDown();
        h.dy = 5;
        h.validate(C0);
        assertEquals(0, h.dy, "скорость обнулена при блоке");
        h.apply();
        assertEquals(270, h.i, "позиция не изменилась");
        assertTrue(h.blocked);
    }

    @Test
    void проход_между_плитами_пропускает_руку_до_i_285() {
        // Hitbox x(151..171) в створе 133..188: вниз до i=285, где hitbox (310..315)
        // упирается в {0,315,240,5} (блок при ni=290: 295>290 && 320>315).
        Hand h = new Hand(150, 240);
        h.setDirectionDown();
        h.dy = 5;
        int guard = 0;
        while (h.dy != 0 && guard++ < 100) {
            h.validate(C0);
            h.apply();
        }
        assertEquals(285, h.i, "палец доходит до нижней стены y=315");
        assertTrue(h.blocked);
    }

    @Test
    void кровать_блокирует_движение() {
        // Над кроватью {31,120,67,82}: рука x=40 (hitbox 41..61 < 98) идёт вверх.
        // Блок, когда 177 > ni (bed.y+bed.h-25): свободно до ni=180 включительно,
        // блок при ni=175 -> рука останавливается на i=180 (палец y 205..210
        // чуть ниже нижнего края кровати 202).
        Hand h = new Hand(40, 240);
        h.setDirectionUp();
        h.dy = -5;
        int guard = 0;
        while (h.dy != 0 && guard++ < 100) {
            h.validate(C0);
            h.apply();
        }
        assertEquals(180, h.i);
        assertTrue(h.blocked);
    }

    @Test
    void свободное_движение_применяет_5px() {
        Hand h = new Hand(149, 240);
        h.setDirectionLeft();
        h.dx = -5;
        assertTrue(h.validate(C0), "влево от (149,240) свободно");
        h.apply();
        assertEquals(144, h.j);
        assertEquals(240, h.i);
        assertFalse(h.blocked);
    }

    @Test
    void анимация_пинг_понг() {
        Hand h = new Hand(149, 240);
        h.moving = true;
        h.animate(); // 0->1
        assertEquals(1, h.frame);
        h.animate(); // 1->2
        assertEquals(2, h.frame);
        h.animate(); // 2->1
        assertEquals(1, h.frame);
        h.animate(); // 1->2
        assertEquals(2, h.frame);
        h.blocked = true;
        h.animate();
        assertEquals(0, h.frame, "блокировка -> кадр 0");
        h.release();
        assertFalse(h.moving);
        assertEquals(0, h.dx);
        assertEquals(0, h.dy);
    }
}
