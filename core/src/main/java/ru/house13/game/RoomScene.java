package ru.house13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.house13.engine.Assets;

/**
 * Сцена этапа 2 «первый свет»: навигация руки по комнатам.
 * Порт связки RU: case()/long()/p()/a() из b.java.
 *
 * Тик (10 Гц, порядок как в long()):
 *   1. edge-walk по ТЕКУЩЕЙ позиции (12 правил, гейт !usingTool) — при попадании
 *      комната меняется, рука перепозиционируется, движение на этом тике пропускается;
 *   2. иначе p()-валидация (блокировка -> скорость 0) + a() применение (5 px);
 *   3. анимация ходьбы (пинг-понг, при блокировке кадр 0).
 *
 * Ввод (J2ME-семантика): нажатие направления (стрелки/2/4/6/8) при !d6
 * устанавливает скорость и ряд спрайта; ЛЮБОЕ keyReleased в игре сбрасывает
 * движение (так ведёт себя keyReleased RU case 2 — без фильтра клавиши).
 *
 * Отладка (только десктоп, в оригинале нет):
 *   PgUp/PgDn — переключение комнат (телепорт в свободную точку);
 *   F10       — оверлей стен (аналог мёртвого y() RU) + хитбокс пальца.
 */
public final class RoomScene {

    public int room;                 // b.ce (offsetX): 0..17
    public final Hand hand;
    private boolean held;            // b.d6
    private boolean usingTool;       // b.cP — гейт edge-walk/движения (этап 2: всегда false)

    private final Assets assets;
    private final TextureRegion handSheet;
    private TextureRegion back;      // кэш фона текущей комнаты
    private int backRoom = -1;

    private boolean showWalls;       // F10
    private Texture white;           // 1x1 для оверлея

    public RoomScene(Assets assets) {
        this.assets = assets;
        this.handSheet = new TextureRegion(assets.tex("hand.png"));
        // Старт игры: комната 0 (спальня), позиция из handIni (RU k()):
        // j = (240-21)/2 + 40 = 149, i = 320-80 = 240, направление «вниз», кадр 0.
        // Отладка: -Dhouse13.room=N — старт в комнате N (рука — в первой свободной точке).
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

    /** Один логический тик (10 Гц). */
    public void tick(int[] keyDowns, int[] keyUps) {
        // keyReleased RU (case b0==2): ЛЮБОЕ отпускание сбрасывает движение
        if (keyUps.length > 0) {
            held = false;
            hand.release();
        }

        for (int k : keyDowns) {
            switch (k) {
                case Input.Keys.LEFT:
                case Input.Keys.NUM_4:
                case Input.Keys.NUMPAD_4:
                    pressDirection(0);
                    break;
                case Input.Keys.RIGHT:
                case Input.Keys.NUM_6:
                case Input.Keys.NUMPAD_6:
                    pressDirection(1);
                    break;
                case Input.Keys.UP:
                case Input.Keys.NUM_2:
                case Input.Keys.NUMPAD_2:
                    pressDirection(2);
                    break;
                case Input.Keys.DOWN:
                case Input.Keys.NUM_8:
                case Input.Keys.NUMPAD_8:
                    pressDirection(3);
                    break;
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

        if (usingTool) {
            return; // гейт cP
        }

        // 1) edge-walk по текущей позиции
        EdgeRules.Rule rule = EdgeRules.match(room, hand.j, hand.i);
        if (rule != null) {
            room = EdgeRules.apply(rule, hand);
            backRoom = -1; // принудительная перезагрузка фона
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
        if (held) {
            return; // !d6
        }
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
        backRoom = -1;
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

    /** Рендер (vsync). Конвенция координат J2ME (Y вниз): верхний левый угол
     *  спрайта — (j, i). Все TextureRegion создаются с flip(false,true):
     *  SpriteBatch рисует квад «верх-вверх» (нижний-левый угол), и с Y-down-камерой
     *  без flip контент выходил бы вверх ногами (выявлено экспериментом этапа 2). */
    public void render(SpriteBatch batch) {
        if (backRoom != room) {
            back = new TextureRegion(assets.tex(RoomData.BACKGROUNDS[room] + ".png"));
            back.flip(false, true);
            backRoom = room;
        }
        batch.draw(back, 0, 0);

        // рука: кадр (frame*21, rowY, 21, rowH) в позиции (j, i)
        TextureRegion frame = new TextureRegion(handSheet.getTexture(),
                hand.frame * Hand.FRAME_X_STEP, hand.rowY, Hand.FRAME_W, hand.rowH);
        frame.flip(false, true);
        batch.draw(frame, hand.j, hand.i);

        if (showWalls) {
            drawDebug(batch);
        }
    }

    /** Оверлей: стены (полупрозрачный белый) + хитбокс пальца (красный). */
    private void drawDebug(SpriteBatch batch) {
        if (white == null) {
            Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pm.setColor(1, 1, 1, 1);
            pm.fill();
            white = new Texture(pm);
            pm.dispose();
        }
        Color save = batch.getColor();
        batch.setColor(1, 1, 1, 0.45f);
        for (int[] w : RoomData.WALLS[room]) {
            batch.draw(white, w[0], w[1], w[2], w[3]);
        }
        batch.setColor(1, 0, 0, 0.8f);
        batch.draw(white, hand.j + 1, hand.i + 25, 20, 5); // хитбокс (j+1..j+21)x(i+25..i+30)
        batch.setColor(save);
        if (white != null && Gdx.app != null) {
            // ничего: текстовых лейблов на этапе 2 нет (шрифты — этап 5)
        }
    }

    public void dispose() {
        if (white != null) {
            white.dispose();
        }
    }

    @Override
    public String toString() {
        return "room=" + room + " hand=(" + hand.j + "," + hand.i + ") dx=" + hand.dx
                + " dy=" + hand.dy + " frame=" + hand.frame + " blocked=" + hand.blocked;
    }
}
