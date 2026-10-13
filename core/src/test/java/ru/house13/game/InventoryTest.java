package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.house13.engine.KeyMap.J2;

import static org.junit.jupiter.api.Assertions.*;

/** Инвентарь-карусель: CreateTool / freeToolByIndex / if(int) (j() — рендер). */
class InventoryTest {

    private FakeHost host;
    private GameScene game;
    private Inventory inv;

    @BeforeEach
    void setUp() {
        host = new FakeHost();
        game = GameScene.postSkip(host);
        inv = game.inv;
    }

    @Test
    void createToolFillsSlotsAndSetsState() {
        inv.createTool("shaizi1.png", 3, 2, 25, 13);
        assertEquals(3, inv.slots[0].id);
        assertEquals(2, inv.aQ[3]);
        inv.createTool("shu.png", 4, 2, 23, 11);
        assertEquals(4, inv.slots[1].id);
        assertNull(inv.slots[2]);
    }

    @Test
    void createToolPlaysGetSound() {
        // реставрация: канал get при находке
        inv.createTool("qq.png", 9, 2, 9, 10);
        assertTrue(host.sound.isPlaying(1), "get.mid (канал 1) играет");
    }

    @Test
    void freeToolRemovesSlotShiftsTail() {
        inv.createTool("shaizi1.png", 3, 2, 25, 13);
        inv.createTool("shu.png", 4, 2, 23, 11);
        inv.createTool("ys.png", 10, 2, 23, 11);
        inv.current = 0;
        inv.usingTool = true;
        inv.freeToolByIndex(3, 5);
        assertNull(inv.slots[2], "хвост сдвинулся влево");
        assertEquals(4, inv.slots[0].id);
        assertEquals(10, inv.slots[1].id);
        assertEquals(5, inv.aQ[3], "aQ[3] = новое состояние");
        assertEquals(-1, inv.current);
        assertFalse(inv.usingTool);
        assertEquals("", game.bf, "freeToolByIndex очищает сообщение");
        assertTrue(host.sound.isPlaying(3), "correct.mid (канал 3) при успехе");
    }

    @Test
    void carouselNavigationBounds() {
        inv.createTool("shaizi1.png", 3, 2, 25, 13);
        inv.createTool("shu.png", 4, 2, 23, 11);
        assertEquals(-1, inv.current);
        inv.fixCurrentForTest();
        assertEquals(0, inv.current, "current=-1 && c5[0]!=null -> 0");
        inv.handleKey(J2.LEFT);
        assertEquals(0, inv.current, "влево за пределами — стоп");
        inv.handleKey(J2.RIGHT);
        assertEquals(1, inv.current);
        inv.handleKey(J2.RIGHT);
        assertEquals(1, inv.current, "вправо за хвостом — стоп");
    }

    @Test
    void fiveSelectsItemIntoHandAndCloses() {
        inv.createTool("shuji1.png", 5, 2, 33, 31);
        inv.showTool = true;
        inv.fixCurrentForTest();   // j(): current=-1 && c5[0]!=null -> 0
        assertEquals(0, inv.current);
        inv.handleKey(J2.NUM_5);
        assertTrue(inv.usingTool, "предмет «в руке» (cP)");
        assertFalse(inv.showTool, "инвентарь закрылся (dt=false)");
        assertEquals(0, inv.current);
        assertEquals(0, game.room.hand.frame);
    }

    @Test
    void oneClosesInventoryAndDropsItem() {
        inv.createTool("shuji1.png", 5, 2, 33, 31);
        inv.showTool = true;
        inv.usingTool = true;
        inv.handleKey(J2.NUM_1);
        assertFalse(inv.showTool);
        assertFalse(inv.usingTool);
    }

    @Test
    void namesMatchOriginalSwitch() {
        assertEquals("Большой ключ", Inventory.nameOf(3));
        assertEquals("Маленький ключ", Inventory.nameOf(4));
        assertEquals("Золотой ключик", Inventory.nameOf(14));
        assertEquals("Хреновина", Inventory.nameOf(7));
        assertEquals("Пожалуйста, выберите пункты.", Inventory.nameOf(0));
        assertEquals("Используйте клавишу \"5\".", Inventory.nameOf(2));
    }

    @Test
    void itemVerticalPositionFormula() {
        // CreateTool: i = 130 - h/2 - 2 - 5 + 122
        inv.createTool("shaizi1.png", 3, 2, 25, 13);
        assertEquals(130 - 13 / 2 - 2 - 5 + 122, inv.slots[0].y);
        inv.createTool("chuizi.png", 11, 2, 28, 28);
        assertEquals(130 - 28 / 2 - 2 - 5 + 122, inv.slots[1].y);
    }

    @Test
    void oneOpensInventoryThroughCascade() {
        // byte 1067: '1' при !safe && c6 (postSkip: c6=true)
        game.tick(new int[]{Input.Keys.NUM_1}, new int[0]);
        assertTrue(inv.showTool);
        // и закрывается '1' через if(int)
        game.tick(new int[]{Input.Keys.NUM_1}, new int[0]);
        assertFalse(inv.showTool);
    }

    @Test
    void fiveWithItemOutsideHotzoneIsWrongUse() {
        // byte 1061 + реставрация wrong.mid
        inv.createTool("shu.png", 4, 2, 23, 11);
        inv.usingTool = true;
        inv.current = 0;
        game.room.hand.frame = 0;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);  // FIRE -> '5'
        assertEquals(Texts.WRONG_USE, game.bf);
        assertFalse(inv.usingTool);
        assertTrue(host.sound.isPlaying(2), "wrong.mid (канал 2)");
    }

    @Test
    void itemNamesFollowTextPack() {
        inv.createTool("shaizi1.png", 3, 2, 25, 13);   // кубик-ключ/большой ключ
        inv.createTool("mao1.png", 14, 2, 23, 15);     // ключ с бубенчиком/золотой ключик
        assertEquals("Большой ключ", Inventory.nameOf(3));
        assertEquals("Золотой ключик", Inventory.nameOf(14));

        Texts.reload(Texts.PACK_AUTH);
        assertEquals("Кубик-ключ", Inventory.nameOf(3), "CN: 色子钥匙");
        assertEquals("Ключ с бубенчиком", Inventory.nameOf(14), "CN: 带有铃铛的钥匙");

        Texts.reload(Texts.PACK_VPK);
        assertEquals("Большой ключ", Inventory.nameOf(3));
        assertEquals("Золотой ключик", Inventory.nameOf(14));
    }
}
