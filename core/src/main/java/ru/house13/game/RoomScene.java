package ru.house13.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.house13.engine.Assets;
import ru.house13.engine.KeyMap.J2;

/**
 * Комнаты и рука-курсор (этап 2; этап 4 — разделение рендера). Порт связки
 * RU: case()/long()/p()/a().
 *
 * Рендер разделён на два вызова (порядок оригинала в else()/w()/void()):
 *   renderBackground — фон комнаты (a()/publicRoom1);
 *   renderHand — спрайт руки (long(): a8.a(cO)) поверх объектов/стрелок/сейфа.
 * Объекты комнат (u()), фронтальные полосы (null()), стрелки (q()), карта
 * (dmap), жалюзи и сейф рисуются GameScene между этими двумя вызовами.
 *
 * Тик: 1) edge-walk по ТЕКУЩЕЙ позиции (12 правил, гейт !usingTool);
 * 2) p()-валидация (блокировка -> скорость 0) + a() применение (5 px);
 * 3) анимация ходьбы (пинг-понг, при блокировке кадр 0).
 *
 * Ввод (J2ME-семантика): нажатие направления при !held устанавливает
 * скорость, ряд спрайта и флаг направления; ЛЮБОЕ keyReleased сбрасывает
 * движение. Возвращает true, если нажатие принято (case(int) при !d6) —
 * по этому сигналу GameScene гасит usingTool и диалог k (как оригинал).
 *
 * Отладка (только десктоп, в оригинале нет):
 *   PgUp/PgDn — переключение комнат; F10 — оверлей стен + хитбокса.
 */
public final class RoomScene {

    public int room;                 // b.ce (offsetX): 0..17
    public final Hand hand;
    private boolean held;            // b.d6

    private final Assets assets;
    private boolean showWalls;       // F10

    public RoomScene(Assets assets) {
        this.assets = assets;
        // Старт игры: комната 0 (спальня), позиция из handIni (RU k()):
        // j = (240-21)/2 + 40 = 149, i = 320-80 = 240, направление «вниз».
        // Отладка: -Dhouse13.room=N — старт в комнате N (рука — в свободной
        // точке); -Dhouse13.pos=j,i — точная позиция руки (дампы хот-зонов).
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
        String pos = System.getProperty("house13.pos");
        if (pos != null) {
            try {
                String[] xy = pos.split(",");
                start = new Hand(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
            } catch (Exception ignored) {
            }
        }
        this.hand = start;
    }

    /**
     * Нажатие клавиши (keyPressed RU case(int) при !d6). Возвращает true,
     * если нажатие направления принято. Гейты !try (сейф) и c6 (стрелки
     * обучения) проверяет GameScene ДО вызова.
     *
     * Этап 5b: цифры 2/4/6/8 больше НЕ двигают руку (раскладка порта —
     * только стрелки; цифры остались вводом кода сейфа). Синтетическая
     * «вниз» при dp==2 приходит как J2.DOWN напрямую — не затронута.
     */
    public boolean handleKey(J2 k) {
        if (held) {
            return false; // !d6
        }
        switch (k) {
            case LEFT:
                pressDirection(0);
                return true;
            case RIGHT:
                pressDirection(1);
                return true;
            case UP:
                pressDirection(2);
                return true;
            case DOWN:
                pressDirection(3);
                return true;
            default:
                return false;
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

    /** Физический тик (long()): переходы + движение + анимация.
     * fast — ускорение порта (Shift): ДВА шага по 5 px с отдельной
     * валидацией каждого — сквозь тонкие стены не проскочить. */
    public void tick(boolean usingTool, boolean fast) {
        // 1) edge-walk по текущей позиции (гейт !cP в каждом правиле)
        EdgeRules.Rule rule = EdgeRules.match(room, hand.j, hand.i);
        if (rule != null && !usingTool) {
            room = EdgeRules.apply(rule, hand);
            return;        // long(): переход отрабатывает за такт, без движения
        }
        // 2) валидация + применение (5 px; при Shift — дважды)
        step();
        if (fast) {
            step();
        }
        // 3) анимация (paint() RU)
        hand.animate();
    }

    /** Совместимость с этапами 2-4 (тесты): обычная скорость. */
    public void tick(boolean usingTool) {
        tick(usingTool, false);
    }

    /** Один шаг 5 px: p()-валидация (блокировка -> скорость 0) + a(). */
    private void step() {
        hand.validate(RoomData.WALLS[room]);
        hand.apply();
    }

    /** Нажатие направления: keyPressed RU case() при !d6. */
    private void pressDirection(int side) {
        held = true;
        hand.blocked = false;
        hand.moving = true;
        hand.frame = 0;
        // флаги направления (dQ/c/b/b3): свой true, остальные false
        hand.dirUp = side == 2;
        hand.dirDown = side == 3;
        hand.dirLeft = side == 0;
        hand.dirRight = side == 1;
        switch (side) {
            case 0: hand.setDirectionLeft();  hand.dx = -5; hand.dy = 0; break; // '4'
            case 1: hand.setDirectionRight(); hand.dx = 5;  hand.dy = 0; break; // '6'
            case 2: hand.setDirectionUp();    hand.dx = 0;  hand.dy = -5; break; // '2'
            case 3: hand.setDirectionDown();  hand.dx = 0;  hand.dy = 5;  break; // '8'
            default:
        }
        // Мёртвые записи оригинала (case()): S=2 в ком.2, ed=2 в ком.6
        // (никогда не читаются), d3=true в ком.13 при dl — читается хот-зоном,
        // переносится GameScene при принятии нажатия.
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

    /** Фон комнаты (a()/publicRoom1: drawImage(back, 0, 0)). */
    public void renderBackground(SpriteBatch batch) {
        String backFile = RoomData.BACKGROUNDS[room] + ".png";
        batch.draw(assets.region(backFile, 0, 0, 240, 320, false, true), 0, 0);
    }

    /** Рука (long(): a8.a(cO)); c.try=false титров гасит спрайт. */
    public void renderHand(SpriteBatch batch) {
        if (!hand.visible) {
            return;
        }
        batch.draw(assets.region("hand.png", hand.frame * Hand.FRAME_X_STEP, hand.rowY,
                Hand.FRAME_W, hand.rowH, false, true), hand.j, hand.i);
    }

    /** Оверлей: стены (полупрозрачный белый) + хитбокс пальца (красный). */
    public void drawDebug(SpriteBatch batch) {
        if (!showWalls) {
            return;
        }
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
