package ru.house13.game;

import ru.house13.engine.Assets;
import ru.house13.engine.FakeMusic;
import ru.house13.engine.Fonts;
import ru.house13.engine.SoundManager;

/** Фейковый GameHost для тестов сцен: без GL/аудио, фиксирует переходы. */
class FakeHost implements GameHost {

    Scene scene;          // последняя setScene
    boolean exit;         // requestExit
    long tick;            // глобальный счётчик тиков (Timer)
    final SoundManager sound;

    FakeHost() {
        sound = new SoundManager(f -> new FakeMusic());
    }

    /** Сдвинуть глобальный таймер на N тиков. */
    FakeHost advance(long n) {
        tick += n;
        return this;
    }

    @Override
    public Assets assets() {
        return null; // render в тестах не вызывается
    }

    @Override
    public SoundManager sound() {
        return sound;
    }

    @Override
    public Fonts fonts() {
        return null; // render в тестах не вызывается
    }

    @Override
    public void setScene(Scene next) {
        scene = next;
    }

    @Override
    public void requestExit() {
        exit = true;
    }

    @Override
    public long tick() {
        return tick;
    }
}
