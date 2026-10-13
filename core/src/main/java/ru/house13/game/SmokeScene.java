package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.house13.engine.Assets;

/**
 * ВРЕМЕННАЯ smoke-сцена этапа 1 (каркас): проверяет конвейер
 * ассеты -> IntegerScaleViewport -> 10 Гц -> ввод. Заменяется машиной
 * состояний на этапе 3; коллизии не реализованы (этап 2 «первый свет»).
 *
 * Данные из декомпиляции RU b.java, handIni/k():
 *   спрайт руки: лист hand.png 64x118, рабочие кадры 21x30 при (x=0/21/42, y=0)
 *   (4-й слот framesInfo[3]={63,0,21,30} за краем листа и никогда не показывается);
 *   скорость руки 5 px за нажатие (тик), т.е. 50 px/с.
 */
public final class SmokeScene {

    private static final int FRAME_W = 21;
    private static final int FRAME_H = 30;
    private static final int FRAME_COUNT = 3; // анимация idle: 0 -> 1 -> 2 -> 0
    private static final int SPEED = 5;       // px/тик, как onHand() оригинала

    private final TextureRegion[] handFrames = new TextureRegion[FRAME_COUNT];
    private final TextureRegion back;

    private float handX = (240 - FRAME_W) / 2f;
    private float handY = (320 - FRAME_H) / 2f;
    private int frame = 0;

    public SmokeScene(Assets assets) {
        back = new TextureRegion(assets.tex("back1.png"));
        for (int i = 0; i < FRAME_COUNT; i++) {
            handFrames[i] = new TextureRegion(assets.tex("hand.png"), i * FRAME_W, 0, FRAME_W, FRAME_H);
        }
    }

    /** Один логический тик (10 Гц). */
    public void tick(int[] keyDowns, int[] keyUps) {
        for (int k : keyDowns) {
            switch (k) {
                // Стрелки — Nokia FullCanvas (-1..-4) в RU-версии; libGDX-коды.
                case com.badlogic.gdx.Input.Keys.LEFT:
                case com.badlogic.gdx.Input.Keys.NUMPAD_4:
                case com.badlogic.gdx.Input.Keys.NUM_4:
                    handX = Math.max(0, handX - SPEED);
                    break;
                case com.badlogic.gdx.Input.Keys.RIGHT:
                case com.badlogic.gdx.Input.Keys.NUMPAD_6:
                case com.badlogic.gdx.Input.Keys.NUM_6:
                    handX = Math.min(240 - FRAME_W, handX + SPEED);
                    break;
                case com.badlogic.gdx.Input.Keys.UP:
                case com.badlogic.gdx.Input.Keys.NUMPAD_2:
                case com.badlogic.gdx.Input.Keys.NUM_2:
                    handY = Math.max(0, handY - SPEED);
                    break;
                case com.badlogic.gdx.Input.Keys.DOWN:
                case com.badlogic.gdx.Input.Keys.NUMPAD_8:
                case com.badlogic.gdx.Input.Keys.NUM_8:
                    handY = Math.min(320 - FRAME_H, handY + SPEED);
                    break;
                default:
                    break;
            }
        }
    }

    /** Рендер (vsync). */
    public void render(SpriteBatch batch) {
        batch.draw(back, 0, 0);
        batch.draw(handFrames[frame], (int) handX, (int) handY);
    }
}
