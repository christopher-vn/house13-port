package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Сквозной сценарий машины состояний (как -Dhouse13.script в CI, но через
 * прямые тики сцен): флеш -> лого -> меню -> игра -> пауза -> выход в меню.
 */
class GameFlowTest {

    @Test
    void fullCycleFlashLogoMenuGamePauseBackToMenu() {
        FakeHost host = new FakeHost();

        // 1) Флеш: 16 тиков
        Scene flash = new FlashScene(host);
        for (int i = 0; i < 16; i++) {
            flash.tick(new int[0], new int[0]);
            host.advance(1);
        }
        assertTrue(host.scene instanceof LogoScene);

        // 2) Лого: любая клавиша -> меню
        Scene logo = (LogoScene) host.scene;
        logo.tick(new int[]{Input.Keys.ANY_KEY}, new int[0]);
        assertTrue(host.scene instanceof MenuScene);

        // 3) Меню: слайд 25 тиков, вниз×0, FIRE -> игра (пункт ИГРА)
        MenuScene menu = (MenuScene) host.scene;
        for (int i = 0; i < 25; i++) {
            menu.tick(new int[0], new int[0]);
        }
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(host.scene instanceof GameScene);

        // 4) Игра: движение (вверх = dy=-5), физика, стоп, пауза, ВЫХОД (пункт 3)
        GameScene game = (GameScene) host.scene;
        game.tick(new int[]{Input.Keys.UP}, new int[0]);
        assertEquals(-5, game.room.hand.dy, "press вверх -> dy=-5");
        int startY = game.room.hand.i;
        game.tick(new int[0], new int[0]); // физика: 5px/тик
        assertEquals(startY - 5, game.room.hand.i);
        game.tick(new int[0], new int[]{Input.Keys.UP});
        assertEquals(0, game.room.hand.dy, "отпускание -> стоп");

        game.tick(new int[]{Input.Keys.ESCAPE}, new int[0]); // пауза
        assertTrue(game.pause);
        game.tick(new int[]{Input.Keys.UP}, new int[0]);     // 0 -> 3 (ВЫХОД)
        assertEquals(3, game.pauseItem);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);

        // 5) Снова меню: анимация перезапускается (FreeMenuSourse: caxia=0)
        MenuScene menu2 = (MenuScene) host.scene;
        assertNotNull(menu2);
        assertFalse(menu2.cv, "после выхода из игры слайд-анимация меню начинается заново");
    }
}
