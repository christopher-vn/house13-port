package ru.house13.game;

import ru.house13.engine.Assets;
import ru.house13.engine.Fonts;
import ru.house13.engine.SoundManager;

/**
 * Доступ сцен к общим подсистемам + управление потоком сцен.
 * Реализация — House13Game; в тестах подменяется фейком.
 */
public interface GameHost {

    Assets assets();

    SoundManager sound();

    Fonts fonts();

    /** Смена сцены (текущая будет disposed после завершения тика). */
    void setScene(Scene next);

    /** Выход из приложения (TheMIDlet.quitApp). */
    void requestExit();

    /** Глобальный счётчик тиков (Timer/e оригинала, 10 Гц). */
    long tick();
}
