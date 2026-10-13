package ru.house13.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.house13.House13Game;

/** Десктоп-запуск (LWJGL3). Окно стартует в x2 (480x640), масштаб пересчитывается на resize. */
public final class Lwjgl3Launcher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Дом №13 — 三更灵异事件 (port, stage 1)");
        cfg.setWindowedMode(480, 640);
        cfg.setResizable(true);
        cfg.useVsync(true);
        cfg.setIdleFPS(0); // с vsync рендер по кадровой частоте монитора
        new Lwjgl3Application(new House13Game(), cfg);
    }
}
