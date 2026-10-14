package ru.house13.game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.house13.engine.Settings;
import ru.house13.engine.SoundManager;

import static org.junit.jupiter.api.Assertions.*;

/** Главное меню: слайд-гейт ввода, навигация 0..4, подэкраны, старт, выход. */
class MenuSceneTest {

    private FakeHost host;
    private MenuScene menu;

    @BeforeEach
    void setUp() {
        host = new FakeHost();
        menu = new MenuScene(host);
    }

    @AfterEach
    void resetCanSound() {
        SoundManager.canSound = true;
    }

    /** Прокрутить анимацию входа до готовности ввода (23 тика, как в l()). */
    private void slideIn() {
        for (int i = 0; i < 25; i++) {
            menu.tick(new int[0], new int[0]);
        }
        assertTrue(menu.cv, "слайд-анимация должна завершиться (dO=208, cv=true)");
    }

    @Test
    void inputGatedUntilShouSlidesIn() {
        // char() работает только при cv (shoud) — до того нажатия игнорируются
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]); // 1-й тик — анимация
        assertEquals(0, menu.dV, "ввод до завершения слайда игнорируется");
        slideIn();
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        assertEquals(1, menu.dV);
    }

    @Test
    void navigationWraps5Items() {
        slideIn();
        for (int i = 0; i < 5; i++) {
            menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        }
        assertEquals(0, menu.dV, "5 нажатий вниз = полный круг");
        menu.tick(new int[]{Input.Keys.UP}, new int[0]);
        assertEquals(4, menu.dV, "вверх от 0 -> 4");
    }

    @Test
    void selectStartsGame() {
        slideIn();
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]); // FIRE = выбор
        assertTrue(host.scene instanceof GameScene, "ИГРА -> GameScene");
    }

    @Test
    void selectFiveOpensSettingsScreen() {
        slideIn();
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]); // dV=1
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]); // dV=2 (ЗВУК)
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(2, menu.bO, "ЗВУК -> настройки (menuStatus_ 2)");

        // этап 5c: пункт 0 = звук; влево/вправо тумблер, вверх/вниз — навигация
        assertTrue(SoundManager.canSound);
        menu.settings.row = 0;
        menu.tick(new int[]{Input.Keys.LEFT}, new int[0]);
        assertFalse(SoundManager.canSound);
        menu.tick(new int[]{Input.Keys.RIGHT}, new int[0]);
        assertTrue(SoundManager.canSound);

        // 3x вниз -> «Назад», FIRE закрывает в главное меню
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        assertEquals(SettingsScreen.ROW_BACK, menu.settings.row);
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(0, menu.bO);
    }

    @Test
    void selectAuthorAndHelpSubscreensAndBack() {
        slideIn();
        // АВТОР (dV=1)
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(1, menu.bO);
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]); // FIRE = назад
        assertEquals(0, menu.bO);

        // ИНФО/ПОМОЩЬ (dV=3; после АВТОРА dV=1 — навигация не сбрасывается,
        // currentKeyCode сохраняется между подэкранами, как в оригинале)
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]); // dV=2
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]); // dV=3
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(3, menu.bO);
        // в помощи вверх/вниз скроллят (dA), пункт не меняется
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        assertEquals(3, menu.bO);
        assertEquals(2, menu.dA);
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(0, menu.bO);
        assertEquals(0, menu.dA, "выход из помощи сбрасывает скролл");
    }

    @Test
    void rightSoftKeyExitsSubscreens() {
        slideIn();
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]); // dV=1 АВТОР
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(1, menu.bO);
        menu.tick(new int[]{Input.Keys.ESCAPE}, new int[0]); // правый софт = назад
        assertEquals(0, menu.bO);
    }

    @Test
    void selectExitQuitsApp() {
        slideIn();
        for (int i = 0; i < 4; i++) {
            menu.tick(new int[]{Input.Keys.DOWN}, new int[0]); // dV=4 ВЫХОД
        }
        assertEquals(4, menu.dV);
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertTrue(host.exit, "ВЫХОД -> quitApp");
        assertNull(host.scene, "сцена не меняется");
    }

    @Test
    void digit5SelectsLikeFire() {
        // '5' = 53 входит в оба условия выбора (bc||cJ||53)
        slideIn();
        menu.tick(new int[]{Input.Keys.NUM_5}, new int[0]);
        assertTrue(host.scene instanceof GameScene, "'5' запускает игру как FIRE");
    }

    @Test
    void leftRightSwitchesTextPackOnSettingsScreen() {
        slideIn();
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);   // dV=1
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);   // dV=2 ЗВУК
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]); // bO=2 настройки
        assertEquals(2, menu.bO);
        assertEquals(Texts.PACK_VPK, Texts.pack());

        // пункт 1 = перевод
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        assertEquals(SettingsScreen.ROW_TRANSLATION, menu.settings.row);

        menu.tick(new int[]{Input.Keys.LEFT}, new int[0]);  // <- авторский
        assertEquals(Texts.PACK_AUTH, Texts.pack(), "LEFT переключает пак");
        assertEquals(Texts.PACK_AUTH, Settings.get("textpack", "x"), "выбор сохранён");

        menu.tick(new int[]{Input.Keys.RIGHT}, new int[0]); // -> обратно V.P.K
        assertEquals(Texts.PACK_VPK, Texts.pack());
        assertEquals(Texts.PACK_VPK, Settings.get("textpack", "x"));
    }

    @Test
    void leftRightIgnoredOutsideSettingsScreen() {
        slideIn();
        menu.tick(new int[]{Input.Keys.LEFT}, new int[0]);  // главное меню
        assertEquals(Texts.PACK_VPK, Texts.pack(), "в главном меню 4/6 не меняют пак");
        menu.tick(new int[]{Input.Keys.DOWN}, new int[0]);
        menu.tick(new int[]{Input.Keys.ENTER}, new int[0]);
        assertEquals(1, menu.bO);                            // АВТОР
        menu.tick(new int[]{Input.Keys.LEFT}, new int[0]);
        assertEquals(Texts.PACK_VPK, Texts.pack(), "на подэкране АВТОР 4/6 не меняют пак");
    }
}
