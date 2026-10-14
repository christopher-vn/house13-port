package ru.house13.engine;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.IntArray;

/**
 * Очередь событий ввода с J2ME-семантикой.
 *
 * В оригинале логика работает в keyPressed/keyReleased (событийно, без autorepeat),
 * а геймплейный цикл — 10 Гц. В libGDX события ввода приходят между кадрами,
 * поэтому: события накапливаются здесь, а каждый логический тик забирает
 * накопленное (drain*). Порядок событий внутри тика сохраняется.
 */
public final class InputQueue implements InputProcessor {

    private final IntArray downs = new IntArray(true, 16);
    private final IntArray ups = new IntArray(true, 16);

    /** Нажатия, накопленные с последнего тика (порядок прихода сохранён). */
    public int[] drainKeyDowns() {
        if (downs.isEmpty()) return EMPTY;
        int[] out = new int[downs.size];
        for (int i = 0; i < downs.size; i++) out[i] = downs.get(i);
        downs.clear();
        return out;
    }

    /** Отпускания, накопленные с последнего тика. */
    public int[] drainKeyUps() {
        if (ups.isEmpty()) return EMPTY;
        int[] out = new int[ups.size];
        for (int i = 0; i < ups.size; i++) out[i] = ups.get(i);
        ups.clear();
        return out;
    }

    private static final int[] EMPTY = new int[0];

    /** Синтетическое нажатие (скрипт автотеста -Dhouse13.script): до drain текущего тика. */
    public void injectDown(int keycode) {
        downs.add(keycode);
    }

    /** Синтетическое отпускание (скрипт автотеста). */
    public void injectUp(int keycode) {
        ups.add(keycode);
    }

    @Override
    public boolean keyDown(int keycode) {
        downs.add(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        ups.add(keycode);
        return false;
    }

    // Ниже — не используется (тач — этап 6, мышь — не нужна).

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
