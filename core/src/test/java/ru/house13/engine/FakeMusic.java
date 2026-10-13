package ru.house13.engine;

import com.badlogic.gdx.audio.Music;

/** Фейковая Music для юнит-тестов SoundManager (без Gdx.audio). */
public class FakeMusic implements Music {

    public boolean playing;
    public boolean looping;
    public boolean disposed;
    public float volume = -1;
    public int playCount;
    public int stopCount;

    @Override
    public void play() {
        playing = true;
        playCount++;
    }

    @Override
    public void pause() {
        playing = false;
    }

    @Override
    public void stop() {
        playing = false;
        stopCount++;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void setLooping(boolean isLooping) {
        this.looping = isLooping;
    }

    @Override
    public boolean isLooping() {
        return looping;
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public void setPan(float pan, float volume) {
        this.volume = volume;
    }

    @Override
    public float getVolume() {
        return volume;
    }

    @Override
    public float getPosition() {
        return 0;
    }

    @Override
    public void setPosition(float position) {
    }

    @Override
    public void dispose() {
        disposed = true;
        playing = false;
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
    }
}
