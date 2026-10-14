package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.house13.engine.SoundManager;

import static org.junit.jupiter.api.Assertions.*;

/** Игра: пауза (правый софт), навигация 0..3, оверлеи инфо/звук, выход в меню. */
class GameSceneTest {

    private FakeHost host;
    private GameScene game;

    @BeforeEach
    void setUp() {
        host = new FakeHost();
        game = GameScene.postSkip(host);
    }

    @AfterEach
    void resetCanSound() {
        SoundManager.canSound = true;
    }

    @Test
    void rightSoftOpensPause() {
        game.tick(new int[]{Input.Keys.ESCAPE}, new int[0]);
        assertTrue(game.pause);
    }

    @Test
    void fireDoesNotOpenPauseItActsAs5() {
        // byte 949: FIRE ремапится в '5' до проверки паузы — пауза только с софта
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertFalse(game.pause);
    }

    @Test
    void pauseNavigationWraps4Items() {
        openPause();
        game.tick(new int[]{Input.Keys.UP}, new int[0]);
        assertEquals(3, game.pauseItem);
        game.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        game.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        game.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        game.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        assertEquals(3, game.pauseItem, "4 нажатий вниз от 3 = круг");
    }

    @Test
    void pauseContinueClosesPause() {
        openPause();
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]); // пункт 0 — продолжить
        assertFalse(game.pause);
    }

    @Test
    void pauseItem1OpensInfoBackToPauseOnSoft() {
        openPause();
        game.tick(new int[]{Input.Keys.DOWN}, new int[0]); // пункт 1
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertFalse(game.pause);
        assertTrue(game.info);
        // из инфо левый софт/FIRE -> назад в меню паузы (else-ветка byte 953)
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.pause);
        assertFalse(game.info);
    }

    @Test
    void pauseItem2OpensSettingsScreen() {
        openPause();
        game.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        game.tick(new int[]{Input.Keys.DOWN}, new int[0]); // пункт 2
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.musicOverlay);
        assertFalse(game.pause);
        // этап 5c: настройки (SettingsScreen), пункт 0 = звук
        assertTrue(SoundManager.canSound);
        game.tick(new int[]{Input.Keys.LEFT}, new int[0]);  // влево = выкл
        assertFalse(SoundManager.canSound);
        game.tick(new int[]{Input.Keys.RIGHT}, new int[0]); // вправо = вкл
        assertTrue(SoundManager.canSound);
        // вверх/вниз — навигация по пунктам (звук больше не тумблерится)
        game.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        assertEquals(SettingsScreen.ROW_TRANSLATION, game.settingsScreen.row);
        // правый софт (Esc) — назад в паузу
        game.tick(new int[]{Input.Keys.ESCAPE}, new int[0]);
        assertTrue(game.pause);
        assertFalse(game.musicOverlay);
    }

    @Test
    void pauseItem3ExitsToMenu() {
        openPause();
        game.tick(new int[]{Input.Keys.UP}, new int[0]); // 0 -> 3
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(host.scene instanceof MenuScene, "ВЫХОД -> главное меню");
    }

    @Test
    void movementBlockedWhilePauseOpen() {
        // при паузе long() не выполняется и onHand не получает клавиши
        Hand hand = handOf(game);
        openPause();
        game.tick(new int[]{Input.Keys.LEFT}, new int[0]);
        assertEquals(0, hand.dx, "движение при паузе не устанавливается");

        // пауза закрыта — движение работает
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]); // продолжить
        game.tick(new int[]{Input.Keys.LEFT}, new int[0]);
        assertEquals(-5, hand.dx);
        game.tick(new int[0], new int[]{Input.Keys.LEFT}); // отпускание — стоп
        assertEquals(0, hand.dx);
    }

    @Test
    void handMovesFivePxPerTickThroughScene() {
        Hand hand = handOf(game);
        int startY = hand.i;
        game.tick(new int[]{Input.Keys.UP}, new int[0]);
        game.tick(new int[0], new int[0]); // физика без новых клавиш
        game.tick(new int[0], new int[0]);
        assertTrue(hand.i < startY, "рука движется вверх по 5px/тик");
        assertEquals(startY - 15, hand.i, "3 тика по 5px (1 тик занял press)");
    }

    // ==================================================================
    // ЭТАП 5c: диалоговый бокс (typewriter + страницы)
    // ==================================================================

    /** Диалог без показа (headless, render не звался) — поведение оригинала:
     *  первая же клавиша чистит bf, каскад продолжается. */
    @Test
    void dialogAnyKeyClosesItWhenNotShown() {
        game.bf = "Ключ!.. Интересно, от чего он?";
        game.kFlag = true;
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.bf.isEmpty(), "бокс закрыт — как в оригинале");
        assertFalse(game.pause);
    }

    /** Двухстраничный диалог: первый Enter переворачивает страницу
     *  (нажатие съедено), второй — закрывает бокс через каскад. */
    @Test
    void dialogConfirmPagesThroughLongText() {
        game.bf = "aaa bbb ccc ddd eee fff";
        game.kFlag = true;
        // render в headless не звался — кормим бокс, как сделал бы dialogBoxRender
        game.dialog.setSpeed(0);
        game.dialog.show(game.bf, 42, s -> s.length() * 6); // «aaa bbb»=42px
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertFalse(game.bf.isEmpty(), "первая страница — Enter листает, не закрывает");
        assertTrue(game.dialog.pageShown());
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.bf.isEmpty(), "последняя страница — Enter закрывает");
    }

    /** Печатная машинка: первое подтверждение доматывает текст,
     *  второе закрывает одностраничный бокс. */
    @Test
    void dialogConfirmFirstCompletesTyping() {
        game.bf = "aaa bbb";
        game.kFlag = true;
        game.dialog.setSpeed(10);
        game.dialog.show(game.bf, 1000, s -> s.length() * 6);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertFalse(game.bf.isEmpty(), "Enter домотал машинку, бокс жив");
        assertTrue(game.dialog.pageShown());
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.bf.isEmpty());
    }

    /** Ховер-подсказки (kFlag=false) не перехватывают ввод — как в оригинале. */
    @Test
    void hoverDialogFallsThroughLikeOriginal() {
        game.bf = "-Интересно, что в шкафу?..";
        game.kFlag = false;
        game.dialog.setSpeed(10);
        game.dialog.show(game.bf, 1000, s -> s.length() * 6);
        game.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(game.bf.isEmpty(), "ховер не блокирует клавиши");
    }

    /** Машинка продвигается в тике кадра (frame -> dialog.tick).
     *  5d: «обычно» = 10 десятых симв/тик — символ в первом же тике. */
    @Test
    void dialogTypewriterAdvancesInFrame() {
        game.bf = "aaa bbb";
        game.dialog.setSpeed(10);
        game.dialog.show(game.bf, 1000, s -> s.length() * 6);
        game.tick(new int[0], new int[0]);
        assertEquals("a", game.dialog.line(0), "1 тик — первый символ (10 симв/с)");
        game.dialog.setSpeed(5);              // «медленно»: 0.5 симв/тик
        game.tick(new int[0], new int[0]);
        assertEquals("a", game.dialog.line(0), "нечётный тик — без символа");
        game.tick(new int[0], new int[0]);
        assertEquals("aa", game.dialog.line(0), "чётный тик — символ");
    }

    @Test
    void shadowThemePlaysInDarkWingRooms() {
        // этап 5c: «Тени» в комнатах 5-8 и 16-17
        assertEquals(SoundManager.CH_MAPBG2, GameScene.roomTrack(5), "там кто-то есть");
        assertEquals(SoundManager.CH_MAPBG2, GameScene.roomTrack(6), "повеяло холодом");
        assertEquals(SoundManager.CH_MAPBG2, GameScene.roomTrack(7), "нет ручки");
        assertEquals(SoundManager.CH_MAPBG2, GameScene.roomTrack(8), "за мной наблюдают");
        assertEquals(SoundManager.CH_MAPBG2, GameScene.roomTrack(16), "сердитый голос");
        assertEquals(SoundManager.CH_MAPBG2, GameScene.roomTrack(17), "ты не выйдешь");
    }

    @Test
    void clockworkThemePlaysInPuzzleWingRooms() {
        // этап 5d: «Шкатулка» — крыло головоломок 9-15 (библиотека, туалет,
        // кладовые, шкаф, карта, машина), mapbg — стартовые комнаты 0-4
        assertEquals(SoundManager.CH_MAPBG, GameScene.roomTrack(0), "спальня");
        assertEquals(SoundManager.CH_MAPBG, GameScene.roomTrack(1), "сейф");
        assertEquals(SoundManager.CH_MAPBG, GameScene.roomTrack(4), "коридор");
        for (int r = 9; r <= 15; r++) {
            assertEquals(SoundManager.CH_MAPBG3, GameScene.roomTrack(r), "комната " + r);
        }
    }

    private void openPause() {
        game.tick(new int[]{Input.Keys.ESCAPE}, new int[0]);
        assertTrue(game.pause, "пауза должна открыться");
    }

    /** Рука живёт внутри RoomScene GameScene (package-private доступ). */
    private static Hand handOf(GameScene g) {
        return g.room.hand;
    }
}
