package ru.house13.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.house13.House13Game;
import ru.house13.lwjgl3.GlfwPadPoller;

/**
 * Десктоп-запуск (LWJGL3). Окно стартует в x2 (480x640), масштаб
 * пересчитывается на resize. Свойства house13.* пробрасываются gradle
 * (см. lwjgl3/build.gradle): start/script/dump/dump.tick/room/audio/pad.
 */
public final class Lwjgl3Launcher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Дом №13 — 三更灵异事件 (порт)");
        cfg.setWindowedMode(480, 640);
        cfg.setResizable(true);
        cfg.useVsync(true);
        cfg.setIdleFPS(0); // с vsync рендер по кадровой частоте монитора
        // headless-прогоны (Xvfb без аудио): -Dhouse13.audio=false
        cfg.disableAudio("false".equals(System.getProperty("house13.audio", "true")));
        House13Game game = new House13Game();
        // Геймпад (этап 5e): GLFW-мост; house13.pad=false — не устанавливать.
        if (!"false".equals(System.getProperty("house13.pad", "true"))) {
            game.setPadPoller(new GlfwPadPoller());
        }
        new Lwjgl3Application(game, cfg);
    }
}
