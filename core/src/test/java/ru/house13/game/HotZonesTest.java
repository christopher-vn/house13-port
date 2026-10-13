package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Хот-зоны try(int): переходы, находки, цепочка пазлов, концовка.
 * Позиции руки и флаги направления выставляются напрямую (как в
 * HandMovementTest), «нажатие» — тик с ENTER (FIRE -> '5').
 */
class HotZonesTest {

    private FakeHost host;
    private GameScene game;

    @BeforeEach
    void setUp() {
        host = new FakeHost();
        game = GameScene.postSkip(host);
    }

    /** Поставить руку в (j,i) с направлением и нажать '5'. */
    private void press5(int room, int j, int i, char dir) {
        game.room.room = room;
        game.room.hand.j = j;
        game.room.hand.i = i;
        game.room.hand.frame = 0;
        game.room.hand.dirUp = dir == 'U';
        game.room.hand.dirDown = dir == 'D';
        game.room.hand.dirLeft = dir == 'L';
        game.room.hand.dirRight = dir == 'R';
        game.inv.usingTool = false;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
    }

    @Test
    void room0LockedDoorMessages() {
        game.b1 = false;   // «комната 1 ещё не посещена»
        press5(0, 140, 165, 'U');
        assertEquals(Texts.R0_DOOR_LOCKED, game.bf, "без ключа: дверь закрыта");
        assertTrue(game.kFlag);
        game.kFlag = false;
        game.d4 = true;    // ключ найден (но не использован)
        press5(0, 140, 165, 'U');
        assertEquals(Texts.R0_DOOR_HINT_KEY, game.bf, "с ключом: подсказка «нажми 1»");
    }

    @Test
    void room0PhoneTwoPressesGiveKey() {
        game.room.hand.frame = 0;
        press5(0, 57, 180, 'U');
        assertTrue(game.cE, "первое нажатие: тумба открыта");
        assertEquals(1, game.E);
        press5(0, 57, 180, 'U');
        assertTrue(game.d4, "второе нажатие: ключ взят");
        assertTrue(game.v);
        assertEquals(0, game.E);
        assertEquals(2, game.inv.slots[0].id, "предмет 2 (ключ) в слоте 0");
        assertEquals("Ключ!..    Интересно,от чего он?", game.bf);
    }

    @Test
    void room0KeyOpensDoorAndTransition() {
        game.inv.createTool("shouji1.png", 2, 2, 15, 27);
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.room = 0;
        game.room.hand.j = 140;
        game.room.hand.i = 100;
        game.room.hand.dirUp = true;
        game.room.hand.dirDown = false;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.bd, "дверь отперта");
        assertTrue(game.l);
        assertEquals(Texts.R0_KEY_OPENS, game.bf);
        // теперь '5' без предмета на той же зоне — переход в ком.1
        press5(0, 140, 100, 'U');
        assertEquals(1, game.room.room);
        assertEquals(148, game.room.hand.j);
        assertEquals(278, game.room.hand.i);
        assertTrue(game.b1, "u(): ком.1 посещена");
    }

    @Test
    void room1DrawerBigKeyAndEmptyAfter() {
        press5(1, 148, 205, 'U');
        assertTrue(game.ea);
        assertEquals(3, game.inv.slots[0].id);
        assertEquals(Texts.R1_KEY_TAKEN, game.bf);
        game.kFlag = false;
        press5(1, 148, 205, 'U');
        assertEquals(Texts.R1_KEY_GONE, game.bf, "тараканы вместо ключа");
    }

    @Test
    void room3BigKeyUnlocksDoor() {
        game.inv.createTool("shaizi1.png", 3, 2, 25, 13);
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.room = 3;
        game.room.hand.j = 50;
        game.room.hand.i = 70;
        game.room.hand.dirUp = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.dj);
        assertEquals(Texts.R3_BIG_KEY_FITS, game.bf);
        assertNull(game.inv.slots[0], "ключ израсходован");
        // переход в ком.11
        press5(3, 50, 70, 'U');
        assertEquals(11, game.room.room);
    }

    @Test
    void room3LockedWithoutKey() {
        game.kFlag = false;
        press5(3, 50, 70, 'U');
        assertEquals(Texts.R3_NEED_KEY, game.bf);
        assertEquals(3, game.room.room, "перехода нет");
    }

    @Test
    void hammerChainSwapInRoom8() {
        // молоток подпирает тумбу: без хреновины только сообщение
        game.kFlag = false;
        press5(8, 143, 123, 'U');
        assertEquals(Texts.R8_HAMMER_STUCK, game.bf);
        // хреновина (7) на место молотка -> молоток (11) в руки
        game.inv.createTool("q.png", 7, 2, 40, 9);
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.room = 8;
        game.room.hand.j = 143;
        game.room.hand.i = 123;
        game.room.hand.dirUp = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.c2);
        assertEquals(Texts.R8_SWAP_HAMMER, game.bf);
        assertEquals(11, game.inv.slots[0].id, "хреновина заменена молотком");
    }

    @Test
    void hammerSmashesWallsAndKeepsHammer() {
        game.inv.createTool("chuizi.png", 11, 2, 28, 28);
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.room = 3;
        game.room.hand.j = 150;
        game.room.hand.i = 138;
        game.room.hand.dirUp = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.aG, "стена ком.3 пробита");
        assertFalse(game.inv.usingTool, "молоток «убран в руку» (cP=false)");
        assertNotNull(game.inv.slots[0], "молоток НЕ израсходован");
        assertEquals(Texts.R14_HAMMER_WALL, game.bf);
    }

    @Test
    void room13BooksOpenDrawerLidSecret() {
        // книги (5) на шкаф -> ящик открыт -> крышка (6)
        game.inv.createTool("shuji1.png", 5, 2, 33, 31);
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.room = 13;
        game.room.hand.j = 60;
        game.room.hand.i = 100;
        game.room.hand.dirUp = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.dl);
        assertTrue(game.bg);
        assertEquals(Texts.R13_BOOKS_FIT, game.bf);
        // секрет после установки книг
        game.bf = "";
        press5(13, 60, 100, 'U');
        assertEquals(Texts.R13_SECRET, game.bf);
        assertTrue(game.d3);
        // крышка из ящика
        game.kFlag = false;
        press5(13, 150, 120, 'U');
        assertTrue(game.c7);
        assertEquals(6, game.inv.slots[0].id);
    }

    @Test
    void room13WardrobeToggleAndNumbers() {
        game.kFlag = false;
        press5(13, 205, 110, 'U');
        assertTrue(game.wardrobe, "шкаф открыт");
        // открыт: двухстрочный диалог про запах и цифры
        press5(13, 205, 110, 'U');
        assertTrue(game.bf.startsWith("-Фу-у!"), "n=0: запах");
        press5(13, 205, 110, 'U');
        assertEquals(Texts.R13_WARDROBE_1, game.bf, "n=1: цифры (подсказка кода)");
    }

    @Test
    void room15PcBatteryThenCoin() {
        game.b5 = true;    // тумблер нажат
        // аккумулятор без ПК — просто сообщение? нет: батарея работает при b5
        game.inv.createTool("dianchi.png", 12, 2, 8, 16);
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.room = 15;
        game.room.hand.j = 60;
        game.room.hand.i = 128;
        game.room.hand.dirUp = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.pcOn, "ПК запитан");
        assertEquals(Texts.R15_BATTERY_IN, game.bf);
        // монета -> SEclub
        game.inv.createTool("qq.png", 9, 2, 9, 10);
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.hand.j = 60;
        game.room.hand.i = 128;
        game.room.hand.dirUp = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.ba, "вход на SEclub");
        assertEquals(Texts.R15_COIN_IN, game.bf);
    }

    @Test
    void room16RopeBeamCatAndEnding() {
        // полка: 2 нажатия -> веревка
        game.kFlag = false;
        press5(16, 163, 188, 'U');
        assertTrue(game.ch);
        assertEquals(1, game.dr);
        press5(16, 163, 188, 'U');
        assertTrue(game.cf);
        assertEquals(13, game.inv.slots[0].id);
        // «злой голос»
        game.kFlag = false;
        press5(16, 120, 108, 'U');
        assertTrue(game.cj);
        assertEquals(Texts.R16_ANGRY, game.bf);
        // веревка на балку -> cg (диалог погашен '5' — как в оригинале !k)
        game.kFlag = false;
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.room = 16;
        game.room.hand.j = 120;
        game.room.hand.i = 108;
        game.room.hand.dirUp = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.cg);
        // подъём в ком.17
        press5(16, 120, 108, 'U');
        assertEquals(17, game.room.room);
        // кот = золотой ключик (14)
        game.kFlag = false;
        press5(17, 90, 115, 'U');
        assertTrue(game.W);
        assertEquals(14, game.inv.slots[0].id);
        assertEquals(Texts.R17_VOICE, game.bf);
        // ЗОЛОТОЙ КЛЮЧИК на нижнюю дверь спальни = КОНЦОВКА
        game.kFlag = false;   // гейт !k (диалог должен быть погашен)
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.room = 0;
        game.room.hand.j = 150;
        game.room.hand.i = 290;
        game.room.hand.dirUp = false;
        game.room.hand.dirDown = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(10, game.inv.aQ[15], "флаг концовки");
        assertNull(game.inv.slots[0], "ключик израсходован");
    }

    @Test
    void room14MapTableTogglesMap() {
        game.kFlag = false;
        press5(14, 108, 210, 'U');
        assertTrue(game.ai, "карта показана");
        assertTrue(game.by);
        press5(14, 108, 210, 'U');
        assertFalse(game.ai, "второе нажатие прячет карту");
    }

    @Test
    void hoverHintsRoom0() {
        game.room.room = 0;
        game.room.hand.j = 57;
        game.room.hand.i = 180;
        game.room.hand.dirUp = true;
        game.tick(new int[0], new int[0]);
        assertEquals(Texts.R0_PHONE_HOVER, game.bf);
        game.cE = true;
        game.b2 = true;
        game.v = false;
        game.tick(new int[0], new int[0]);
        assertEquals(Texts.R0_PHONE_KEY_HOVER, game.bf);
    }

    @Test
    void dialogDismissByFive() {
        // '5' с пустой рукой гасит диалог (byte 4447)
        game.kFlag = true;
        game.bf = "Сообщение";
        game.n = 1;
        press5(5, 200, 300, 'D');   // нейтральная точка
        assertFalse(game.kFlag);
        assertEquals("", game.bf);
        assertEquals(0, game.n);
    }

    @Test
    void rightSoftClosesDialog() {
        game.kFlag = true;
        game.bf = "Сообщение";
        game.tick(new int[]{Input.Keys.ESCAPE}, new int[0]);
        assertFalse(game.kFlag, "[P]-else: правый софт гасит k");
    }

    @Test
    void directionPressClosesDialogAndDropsItem() {
        game.kFlag = true;
        game.inv.usingTool = true;
        game.tick(new int[]{Input.Keys.UP}, new int[0]);
        assertTrue(game.room.hand.dy == -5, "движение принято");
        assertFalse(game.kFlag, "case(): k=false");
        assertFalse(game.inv.usingTool, "case(): cP=false");
    }
}
