package ru.house13.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** 12 edge-walk правил long(): срабатывание и перепозиционирование. */
class EdgeWalkTest {

    @Test
    void правил_12() {
        assertEquals(12, EdgeRules.RULES.length);
    }

    @Test
    void комната4_влево_в_комнату5() {
        Hand h = new Hand(-10, 100);
        EdgeRules.Rule r = EdgeRules.match(4, h.j, h.i);
        assertNotNull(r);
        assertEquals(5, EdgeRules.apply(r, h));
        assertEquals(218, h.j); // -10 + 228
        assertEquals(100, h.i); // i сохраняется
    }

    @Test
    void комната4_вправо_в_комнату6() {
        Hand h = new Hand(235, 100);
        EdgeRules.Rule r = EdgeRules.match(4, h.j, h.i);
        assertNotNull(r);
        assertEquals(6, EdgeRules.apply(r, h));
        assertEquals(7, h.j); // 235 - 228
        assertEquals(100, h.i);
    }

    @Test
    void комната4_вниз_в_комнату2() {
        Hand h = new Hand(150, 310);
        EdgeRules.Rule r = EdgeRules.match(4, h.j, h.i);
        assertNotNull(r);
        assertEquals(2, EdgeRules.apply(r, h));
        assertEquals(5, h.i);   // вход сверху
        assertEquals(150, h.j); // j сохраняется
    }

    @Test
    void комната2_влево_в_комнату3() {
        Hand h = new Hand(-15, 200);
        EdgeRules.Rule r = EdgeRules.match(2, h.j, h.i);
        assertNotNull(r);
        assertEquals(3, EdgeRules.apply(r, h));
        assertEquals(213, h.j);
    }

    @Test
    void комната2_вправо_в_комнату1_фиксированная_точка() {
        Hand h = new Hand(238, 50);
        EdgeRules.Rule r = EdgeRules.match(2, h.j, h.i);
        assertNotNull(r);
        assertEquals(1, EdgeRules.apply(r, h));
        assertEquals(3, h.j);   // j=3
        assertEquals(193, h.i); // i=193
    }

    @Test
    void комната2_вверх_в_комнату4() {
        Hand h = new Hand(120, -10);
        EdgeRules.Rule r = EdgeRules.match(2, h.j, h.i);
        assertNotNull(r);
        assertEquals(4, EdgeRules.apply(r, h));
        assertEquals(280, h.i); // вход снизу
        assertEquals(120, h.j);
    }

    @Test
    void комната1_влево_в_комнату2_фиксированная_точка() {
        Hand h = new Hand(-5, 100);
        EdgeRules.Rule r = EdgeRules.match(1, h.j, h.i);
        assertNotNull(r);
        assertEquals(2, EdgeRules.apply(r, h));
        assertEquals(203, h.j);
        assertEquals(193, h.i);
    }

    @Test
    void комната3_вправо_в_комнату2() {
        Hand h = new Hand(235, 100);
        assertEquals(2, EdgeRules.apply(EdgeRules.match(3, h.j, h.i), h));
        assertEquals(7, h.j);
    }

    @Test
    void комната5_вправо_в_комнату4() {
        Hand h = new Hand(236, 150);
        assertEquals(4, EdgeRules.apply(EdgeRules.match(5, h.j, h.i), h));
        assertEquals(8, h.j);
    }

    @Test
    void комната6_влево_в_комнату4_вправо_в_комнату7() {
        Hand h = new Hand(-10, 100);
        assertEquals(4, EdgeRules.apply(EdgeRules.match(6, h.j, h.i), h));
        assertEquals(218, h.j);

        Hand h2 = new Hand(233, 100);
        assertEquals(7, EdgeRules.apply(EdgeRules.match(6, h2.j, h2.i), h2));
        assertEquals(5, h2.j);
    }

    @Test
    void комната7_влево_в_комнату6() {
        Hand h = new Hand(-19, 100);
        assertEquals(6, EdgeRules.apply(EdgeRules.match(7, h.j, h.i), h));
        assertEquals(209, h.j);
    }

    @Test
    void вне_диапазонов_перехода_нет() {
        assertNull(EdgeRules.match(4, -25, 100));  // j <= -20
        assertNull(EdgeRules.match(4, -3, 100));   // j >= -3 (граница открыта)
        assertNull(EdgeRules.match(4, 232, 100));  // j <= 232 (граница открыта)
        assertNull(EdgeRules.match(4, 245, 100));  // j >= 240
        assertNull(EdgeRules.match(4, 150, 299));  // i <= 300
        assertNull(EdgeRules.match(4, 150, 320));  // i >= 320
        assertNull(EdgeRules.match(2, 150, 1));    // вертикальные ворота i(0,320)
        assertNull(EdgeRules.match(0, -10, 100));  // у комнаты 0 edge-walk нет
        assertNull(EdgeRules.match(9, -10, 100));  // и у 8..17 нет
        assertNull(EdgeRules.match(17, -10, 100));
    }

    @Test
    void смежные_переходы_не_срабатывают_цепочкой() {
        // после влево в ком.5 (правило r5 вправо -> 4): рука на j=218 — не в воротах
        Hand h = new Hand(-10, 100);
        int room5 = EdgeRules.apply(EdgeRules.match(4, h.j, h.i), h);
        assertEquals(5, room5);
        assertNull(EdgeRules.match(room5, h.j, h.i), "j=218 вне (232,240) — обратного перехода нет");
    }
}
