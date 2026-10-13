package ru.house13.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Целостность сгенерированных данных RoomData (источник: b.java f(), экстрактор). */
class RoomDataTest {

    @Test
    void комнат_18() {
        assertEquals(18, RoomData.WALLS.length);
        assertEquals(18, RoomData.BACKGROUNDS.length);
    }

    @Test
    void всего_136_прямоугольников() {
        int total = 0;
        for (int[][] room : RoomData.WALLS) {
            total += room.length;
        }
        assertEquals(136, total);
    }

    @Test
    void распределение_стен_по_комнатам() {
        int[] expected = {7, 11, 4, 3, 4, 3, 3, 3, 11, 9, 9, 8, 9, 11, 9, 9, 13, 10};
        assertArrayEquals(expected, counts());
    }

    private int[] counts() {
        int[] c = new int[RoomData.WALLS.length];
        for (int r = 0; r < c.length; r++) {
            c[r] = RoomData.WALLS[r].length;
        }
        return c;
    }

    @Test
    void контрольные_точки_комнаты_0() {
        // cI: левая/правая стены, потолок, кровать, нижние плиты с проходом 133..188
        assertArrayEquals(new int[]{0, 0, 8, 320}, RoomData.WALLS[0][0]);
        assertArrayEquals(new int[]{232, 0, 8, 320}, RoomData.WALLS[0][1]);
        assertArrayEquals(new int[]{0, 0, 240, 190}, RoomData.WALLS[0][2]);
        assertArrayEquals(new int[]{31, 120, 67, 82}, RoomData.WALLS[0][3]); // кровать
        assertArrayEquals(new int[]{0, 300, 133, 20}, RoomData.WALLS[0][4]);
        assertArrayEquals(new int[]{188, 300, 52, 20}, RoomData.WALLS[0][5]);
        assertArrayEquals(new int[]{0, 315, 240, 5}, RoomData.WALLS[0][6]);
    }

    @Test
    void карта_фонов() {
        assertEquals("back1", RoomData.BACKGROUNDS[0]);
        assertEquals("back2", RoomData.BACKGROUNDS[1]);
        for (int r = 2; r <= 7; r++) {
            assertEquals("back3", RoomData.BACKGROUNDS[r], "комната " + r); // коридор
        }
        assertEquals("back8", RoomData.BACKGROUNDS[8]);
        assertEquals("back15", RoomData.BACKGROUNDS[15]);
        assertEquals("back4", RoomData.BACKGROUNDS[16]);
        assertEquals("back5", RoomData.BACKGROUNDS[17]);
    }

    @Test
    void каждый_прямоугольник_корректен() {
        for (int r = 0; r < 18; r++) {
            for (int[] w : RoomData.WALLS[r]) {
                assertTrue(w[0] >= -1 && w[0] <= 240, "x комнаты " + r);
                assertTrue(w[1] >= 0 && w[1] <= 320, "y комнаты " + r);
                assertTrue(w[2] > 0 && w[3] > 0, "размеры комнаты " + r);
                assertTrue(w[0] + w[2] <= 241, "x+w комнаты " + r);
                assertTrue(w[1] + w[3] <= 321, "y+h комнаты " + r);
            }
        }
    }
}
