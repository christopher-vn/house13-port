package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.house13.engine.SoundManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Интро-прокрутка, «загрузочная» полоса, монолог пробуждения, жалюзи и
 * стрелки обучения (else()/m()/h()/do()+v()/A()/q() из RU b.java).
 */
class IntroWakeUpTest {

    private FakeHost host;
    private GameScene game;

    @BeforeEach
    void setUp() {
        host = new FakeHost();
        game = new GameScene(host);
    }

    @AfterEach
    void resetCanSound() {
        SoundManager.canSound = true;
    }

    @Test
    void storyScrollAdvancesAndFlipsToBar() {
        // h(): z += 2 за тик; 280/2 = 140 тиков истории
        assertFalse(game.preDisplayed);
        assertFalse(game.skipState);
        assertEquals(1, game.z);
        game.tick(new int[0], new int[0]);
        assertEquals(3, game.z, "z += 2 за тик");
        for (int t = 0; t < 140; t++) {
            game.tick(new int[0], new int[0]);
        }
        assertTrue(game.skipState, "z>=280 -> фаза полосы");
        assertFalse(game.preDisplayed);
    }

    @Test
    void fireSkipsStoryToBar() {
        // byte() [B]: FIRE/левый софт/'5' в интро -> skipState, z=500
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.skipState);
        assertEquals(500, game.z, "[B]: z=500 как do()");
    }

    @Test
    void softLeftAndFiveAlsoSkip() {
        GameScene g2 = new GameScene(host);
        g2.tick(new int[]{Input.Keys.F1}, new int[0]);
        assertTrue(g2.skipState);
        GameScene g3 = new GameScene(host);
        g3.tick(new int[]{Input.Keys.NUM_5}, new int[0]);
        assertTrue(g3.skipState);
    }

    @Test
    void loadingBarRunsThenGameStarts() {
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);   // скип истории
        assertEquals(57, game.dd, "тик нажатия уже отработал v() один раз");
        for (int t = 0; t < 57; t++) {
            game.tick(new int[0], new int[0]);
        }
        assertFalse(game.preDisplayed, "dd=114: ещё не конец");
        game.tick(new int[0], new int[0]);                    // dd==115
        assertTrue(game.preDisplayed, "dd==115 -> игра");
        assertEquals(113, game.dd);
        assertEquals(113, game.c3);
    }

    @Test
    void wakeUpMonologueAdvancesByFive() {
        GameScene g = GameScene.postSkipIntroOnly(host);
        // dp 0: «-Где я?..»
        g.tick(new int[0], new int[0]);
        assertEquals(0, g.dp);
        assertTrue(g.bf.startsWith("-Где я?.."));
        // '5' -> dp 1
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(1, g.dp);
        // '5' -> dp 2: таймеры d2 (9) и dH (3)
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(2, g.dp);
        for (int t = 0; t < 12; t++) {
            g.tick(new int[0], new int[0]);
        }
        assertEquals(9, g.d2);
        assertEquals(3, g.dH);
        assertTrue(g.bf.startsWith("Дверь закрыта"), "финальная реплика фазы 2");
        // '5' -> dX; ещё '5' -> dp 3 (жалюзи)
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(g.dX);
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(3, g.dp);
    }

    @Test
    void syntheticDownMovesHandDuringPhase2() {
        // paint(): if (dp==2) keyPressed(56) — рука «оседает» вниз,
        // пока c6=true (кадры d2 0..9)
        GameScene g = GameScene.postSkipIntroOnly(host);
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);  // dp 1
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);  // dp 2
        int startY = g.room.hand.i;
        g.tick(new int[0], new int[0]);                   // синтетика кадра 1
        assertTrue(g.room.hand.dy == 5 || g.room.hand.i > startY,
                "синтетическая «8» двигает руку вниз");
    }

    @Test
    void blindsOpenAndUnlockArrows() {
        GameScene g = GameScene.postSkipIntroOnly(host);
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);  // dp 1
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);  // dp 2
        for (int t = 0; t < 12; t++) {
            g.tick(new int[0], new int[0]);
        }
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);  // dX
        g.tick(new int[]{Input.Keys.ENTER}, new int[0]);  // dp 3 — жалюзи
        assertFalse(g.cl);
        // рука прижата к центру (152,240)
        assertEquals(152, g.room.hand.j);
        assertEquals(240, g.room.hand.i);
        for (int t = 0; t < 30; t++) {
            g.tick(new int[0], new int[0]);
        }
        assertTrue(g.cK, "жалюзи полностью открыты");
        assertTrue(g.cl, "kaishigd: пробуждение окончено");
        assertFalse(g.c6, "стрелки ещё мигают");
        // q(): bU 1..~24, при bU>20 в фазе мигания -> c6
        for (int t = 0; t < 30; t++) {
            g.tick(new int[0], new int[0]);
        }
        assertTrue(g.c6, "стрелки завершились: движение открыто");
        assertEquals(20, g.bU);
    }

    @Test
    void movementGatedUntilArrowsDone() {
        GameScene g = GameScene.postSkipIntroOnly(host);
        // во время монолога (c6=false) движение запрещено
        g.tick(new int[]{Input.Keys.UP}, new int[0]);
        assertEquals(0, g.room.hand.dy);
        assertEquals(0, g.dp, "dp не двинулся (не '5'/правый софт)");
    }

    @Test
    void creditsRunAndReturnToMenu() {
        GameScene g = GameScene.postSkip(host);
        g.inv.aQ[15] = 10;   // флаг концовки
        g.tick(new int[0], new int[0]);
        assertFalse(g.room.hand.visible, "e(): a8.try=false (баг оригинала)");
        // A растёт 1/тик + 2 каждые 20; выход при A>270
        for (int t = 0; t < 300 && !(host.scene instanceof MenuScene); t++) {
            g.tick(new int[0], new int[0]);
        }
        assertTrue(host.scene instanceof MenuScene, "A>270 -> меню");
        assertEquals(0, g.A);
    }

    @Test
    void anyKeyDuringCreditsGoesToMenu() {
        GameScene g = GameScene.postSkip(host);
        g.inv.aQ[15] = 10;
        g.tick(new int[]{Input.Keys.UP}, new int[0]);  // [A2]
        assertTrue(host.scene instanceof MenuScene);
    }
}
