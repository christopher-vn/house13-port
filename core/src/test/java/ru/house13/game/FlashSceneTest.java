package ru.house13.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Флеш-экран: 15 тиков показа, 16-й -> лого (порт o()). */
class FlashSceneTest {

    @Test
    void transitionToLogoOnTick16() {
        FakeHost host = new FakeHost();
        FlashScene flash = new FlashScene(host);

        for (int i = 0; i < 15; i++) {
            flash.tick(new int[0], new int[0]);
            assertNull(host.scene, "тик " + (i + 1) + " — ещё флеш");
        }
        flash.tick(new int[0], new int[0]); // 16-й
        assertNotNull(host.scene, "16-й тик -> смена сцены");
        assertTrue(host.scene instanceof LogoScene, "флеш -> лого, а не " + host.scene);
    }

    @Test
    void inputIgnoredDuringFlash() {
        // в оригинале keyPressed case -1/default пуст — ввод игнорируется
        FakeHost host = new FakeHost();
        FlashScene flash = new FlashScene(host);
        flash.tick(new int[]{66}, new int[0]); // ENTER
        assertNull(host.scene);
    }
}
