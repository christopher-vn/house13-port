package ru.house13.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.house13.engine.Assets;
import ru.house13.engine.KeyMap.J2;

/**
 * Комнаты и рука-курсор (этап 2). Порт связки RU: case()/long()/p()/a().
 *
 * С этапа 3 сцена — часть GameScene: ввод приходит через handleKey()
 * (после каскада паузы byte()), физика — в tick() (аналог void():
 * long() выполняется только когда пауза/инструмент не открыты).
 *
 * Тик: 1) edge-walk по ТЕКУЩЕЙ позиции (12 правил, гейт !usingTool);
 * 2) p()-валидация (блокировка -> скорость 0) + a() применение (5 px);
 * 3) анимация ходьбы (пинг-понг, при блокировке кадр 0).
 *
 * Ввод (J2ME-семантика): нажатие направления при !held устанавливает
 * скорость и ряд спрайта; ЛЮБОЕ keyReleased сбрасывает движение
 * (keyReleased RU case 2 — без фильтра клавиши).
 *
 * Отладка (только десктоп, в оригинале нет):
 *   PgUp/PgDn — переключение комнат; F10 — оверлей стен + хитбокс.
 */
public final class RoomScene {

    public int room;                 // b.ce (offsetX): 0..17
    public final Hand hand;
    private boolean held;            // b.d6
    private boolean usingTool;       // b.cP — гейт edge-walk/движения (этап 4)

    private final Assets assets;
    private boolean showWalls;       // F10

    public RoomScene(Assets assets) {
        this.assets = assets;
        // Старт игры: комната 0 (спальня), позиция из handIni (RU k()):
        // j = (240-21)/2 + 40 = 149, i = 320-80 = 240, направление «вниз».
        // Отладка: -Dhouse13.room=N — старт в комнате N (рука — в свободной точке).
        this.room = 0;
        Hand start = new Hand(149, 240);
        String dbg = System.getProperty("house13.room");
        if (dbg != null) {
            try {
                int r = Integer.parseInt(dbg) % 18;
                this.room = r;
                int[] spot = findFreeSpot(RoomData.WALLS[r]);
                start = new Hand(spot[0], spot[1]);
            } catch (NumberFormatException ignored) {
            }
        }
        this.hand = start;
    }

    /** Нажатие клавиши (keyPressed RU case(int) при !d6). Вызывает GameScene. */
    public void handleKey(J2 k) {
        if (held) {
            return; // !d6
        }
        switch (k) {
            case LEFT:
            case NUM_4:
                pressDirection(0);
                break;
            case RIGHT:
            case NUM_6:
                pressDirection(1);
                break;
            case UP:
            case NUM_2:
                pressDirection(2);
                break;
            case DOWN:
            case NUM_8:
                pressDirection(3);
                break;
            default:
                break;
        }
    }

    /** Отладочные клавиши (PgUp/PgDn/F10) — вне J2ME-модели, только десктоп. */
    public void debugKeyDown(int gdxKey) {
        switch (gdxKey) {
            case Input.Keys.PAGE_UP:
                debugSwitchRoom(+1);
                break;
            case Input.Keys.PAGE_DOWN:
                debugSwitchRoom(-1);
                break;
            case Input.Keys.F10:
                showWalls = !showWalls;
                break;
            default:
                break;
        }
    }

    /** keyReleased case 2: ЛЮБОЕ отпускание сбрасывает движение. */
    public void releaseAll() {
        held = false;
        hand.release();
    }

    /** Физический тик (void()/long()): переходы + движение + анимация. */
    public void tick() {
        if (usingTool) {
            return; // гейт cP
        }
        // 1) edge-walk по текущей позиции
        EdgeRules.Rule rule = EdgeRules.match(room, hand.j, hand.i);
        if (rule != null) {
            room = EdgeRules.apply(rule, hand);
            return;        // long(): переход отрабатывает за такт, без движения
        }
        // 2) валидация + применение
        hand.validate(RoomData.WALLS[room]);
        hand.apply();
        // 3) анимация (paint() RU)
        hand.animate();
    }

    /** Нажатие направления: keyPressed RU case() при !d6. */
    private void pressDirection(int side) {
        held = true;
        hand.blocked = false;
        hand.moving = true;
        hand.frame = 0;
        switch (side) {
            case 0: hand.setDirectionLeft();  hand.dx = -5; hand.dy = 0; break; // '4'
            case 1: hand.setDirectionRight(); hand.dx = 5;  hand.dy = 0; break; // '6'
            case 2: hand.setDirectionUp();    hand.dx = 0;  hand.dy = -5; break; // '2'
            case 3: hand.setDirectionDown();  hand.dx = 0;  hand.dy = 5;  break; // '8'
            default:
        }
    }

    /** Отладочный телепорт между комнатами (PgUp/PgDn). */
    private void debugSwitchRoom(int dir) {
        int next = (room + dir + 18) % 18;
        room = next;
        int[] spot = findFreeSpot(RoomData.WALLS[next]);
        hand.j = spot[0];
        hand.i = spot[1];
        hand.release();
        held = false;
    }

    /** Первая свободная точка (скан сетки 5px от центра) — для отладочных телепортов. */
    private static int[] findFreeSpot(int[][] walls) {
        for (int y = 100; y <= 300; y += 5) {
            for (int x = 20; x <= 220; x += 5) {
                if (free(walls, x, y)) {
                    return new int[]{x, y};
                }
            }
        }
        return new int[]{109, 145}; // теоретически недостижимо
    }

    /** Хитбокс пальца свободен от стен? (предикат Hand.validate без мутаций) */
    private static boolean free(int[][] walls, int j, int i) {
        for (int[] w : walls) {
            if (w[0] + w[2] - 1 > j && j + Hand.FRAME_W > w[0]
                    && w[1] + w[3] - 25 > i && i + 30 > w[1]) {
                return false;
            }
        }
        return true;
    }

    /** Рендер комнаты: фон + рука (+ отладочный оверлей F10). */
    public void render(SpriteBatch batch) {
        String backFile = RoomData.BACKGROUNDS[room] + ".png";
        batch.draw(assets.region(backFile, 0, 0, 240, 320, false, true), 0, 0);

        // рука: кадр (frame*21, rowY, 21, rowH) в позиции (j, i)
        batch.draw(assets.region("hand.png", hand.frame * Hand.FRAME_X_STEP, hand.rowY,
                Hand.FRAME_W, hand.rowH, false, true), hand.j, hand.i);

        if (showWalls) {
            drawDebug(batch);
        }
    }

    /** Оверлей: стены (полупрозрачный белый) + хитбокс пальца (красный). */
    private void drawDebug(SpriteBatch batch) {
        for (int[] w : RoomData.WALLS[room]) {
            assets.fill(batch, w[0], w[1], w[2], w[3], 1, 1, 1, 0.45f);
        }
        assets.fill(batch, hand.j + 1, hand.i + 25, 20, 5, 1, 0, 0, 0.8f);
    }

    @Override
    public String toString() {
        return "room=" + room + " hand=(" + hand.j + "," + hand.i + ") dx=" + hand.dx
                + " dy=" + hand.dy + " frame=" + hand.frame + " blocked=" + hand.blocked;
    }
}
