package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Сквозной сценарий машины состояний (как -Dhouse13.script в CI, но через
 * прямые тики сцен): флеш -> лого -> меню -> ИГРА -> интро (скип) ->
 * «загрузка» -> монолог пробуждения -> жалюзи -> стрелки -> движение ->
 * пауза -> выход в меню.
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

        // 3) Меню: слайд 25 тиков, FIRE -> игра (пункт ИГРА) -> ИНТРО
        MenuScene menu = (MenuScene) host.scene;
        for (int i = 0; i < 25; i++) {
            menu.tick(new int[0], new int[0]);
        }
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(host.scene instanceof GameScene);

        // 4) Интро: скип истории (FIRE), полоса 59 тиков
        GameScene game = (GameScene) host.scene;
        assertFalse(game.preDisplayed);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);   // [B] скип
        assertTrue(game.skipState);
        for (int t = 0; t < 59; t++) {
            game.tick(new int[0], new int[0]);
        }
        assertTrue(game.preDisplayed, "полоса прошла — игра");

        // 5) Монолог: '5' ×2, таймеры, '5' ×2 (dX, жалюзи)
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);   // dp 1
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);   // dp 2
        for (int t = 0; t < 12; t++) {                        // d2 0..9, dH 0..3
            game.tick(new int[0], new int[0]);
        }
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);   // dX
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);   // dp 3 — жалюзи
        for (int t = 0; t < 30; t++) {                        // жалюзи ~22 тика
            game.tick(new int[0], new int[0]);
        }
        assertTrue(game.cl);
        for (int t = 0; t < 30; t++) {                        // стрелки ~24 тика
            game.tick(new int[0], new int[0]);
        }
        assertTrue(game.c6, "движение разблокировано");

        // 6) Движение: вверх = dy=-5, физика 5px/тик, стоп по отпусканию.
        // Сначала отпускание: синтетическая «8» фазы dp==2 оставила d6=true —
        // в оригинале её сбрасывает любое реальное keyReleased
        game.tick(new int[0], new int[]{Input.Keys.ENTER});
        Hand hand = game.room.hand;
        game.tick(new int[]{Input.Keys.UP}, new int[0]);
        assertEquals(-5, hand.dy, "press вверх -> dy=-5");
        int startY = hand.i;
        game.tick(new int[0], new int[0]);
        assertEquals(startY - 5, hand.i);
        game.tick(new int[0], new int[]{Input.Keys.UP});
        assertEquals(0, hand.dy, "отпускание -> стоп");

        // 7) Пауза -> ВЫХОД (пункт 3)
        game.tick(new int[]{Input.Keys.ESCAPE}, new int[0]);
        assertTrue(game.pause);
        game.tick(new int[]{Input.Keys.UP}, new int[0]);     // 0 -> 3
        assertEquals(3, game.pauseItem);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);

        // 8) Снова меню: анимация перезапускается (FreeMenuSourse)
        MenuScene menu2 = (MenuScene) host.scene;
        assertNotNull(menu2);
        assertFalse(menu2.cv, "после выхода из игры слайд-анимация меню начинается заново");
    }

    @Test
    void fullPlaythroughToEnding() {
        // Сокращённый прогон логики до концовки: инвентарь->золотой ключик
        // на нижнюю дверь спальни (глубокая цепочка — в HotZonesTest).
        FakeHost host = new FakeHost();
        GameScene game = GameScene.postSkip(host);

        game.inv.createTool("mao1.png", 14, 2, 23, 15);   // «кот» = золотой ключик
        game.inv.current = 0;
        game.inv.usingTool = true;
        game.room.hand.frame = 0;
        game.room.room = 0;
        game.room.hand.j = 150;
        game.room.hand.i = 290;
        game.room.hand.dirUp = false;
        game.room.hand.dirDown = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);

        assertEquals(10, game.inv.aQ[15], "флаг концовки установлен");
        // Титры: A растёт, рука скрыта, любая клавиша -> меню
        game.tick(new int[0], new int[0]);
        assertFalse(game.room.hand.visible);
        game.tick(new int[]{Input.Keys.SPACE}, new int[0]);
        assertTrue(host.scene instanceof MenuScene, "[A2]: клавиша в титрах -> меню");
    }
}
