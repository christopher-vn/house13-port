package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.house13.engine.Assets;

/**
 * Инвентарь-карусель. Порт RU: c5[14] (tool[14]) + aQ[16] (toolStates[16]) +
 * void (currentTool) + CreateTool a(Image,int,int,...) + freeToolByIndex
 * a(int,int) + отрисовка j() (drawTool) + ввод if(int) (onTool).
 *
 * Слоты c5 — «живой» список без дыр (freeToolByIndex сдвигает хвост влево);
 * aQ — состояние предмета по ИНДЕКСУ ПРЕДМЕТА (1..14); индексы 9..12
 * совместно используются сейфом как набираемые цифры (так в оригинале!),
 * 15 — флаг концовки (=10), 8 — первая (невидимая) цифра сейфа, 6 — счётчик
 * (aQ[6]<7 всегда true — мёртвое условие оригинала).
 *
 * Позиция иконки в карусели: y = 130 - h/2 - 2 - 5 + 122 (CreateTool);
 * x текущего = 120 - w/2, соседей ±85/±140 (j()). Одиночные спрайты
 * рисуются ЦЕЛИКОМ (c.a() при d==1 — drawImage без клипа; w/h из
 * framesInfo используются только для центрирования).
 *
 * cP (usingTool) и dt (showTool) — флаги «предмет в руке» и «инвентарь
 * открыт»; хранятся здесь, т.к. управляются только вводом инвентаря.
 *
 * РЕСТАВРАЦИЯ ЗВУКА (этап 4): CreateTool -> playGet (канал get.mid),
 * freeToolByIndex -> playCorrect (канал correct.mid) — в оригинале файлы
 * в JAR отсутствовали и вызовы не были подключены (см. SoundManager).
 */
public final class Inventory {

    /** Слоты карусели (b.c5[14]); null — пусто. */
    final ToolSlot[] slots = new ToolSlot[14];

    /** Состояния предметов (b.aQ[16]); ИНДЕКС = id предмета. */
    final int[] aQ = new int[16];

    /** b.void (currentTool): индекс слота в c5, -1 — ничего не выбрано. */
    int current = -1;

    /** b.cP (usingTool): предмет «в руке» (рисуется при пальце, гейт движения). */
    boolean usingTool;

    /** b.dt (showTool): инвентарь открыт. */
    boolean showTool;

    private final GameScene game; // доступ к bf/hand/sound — как в монолите b

    /** Слот предмета (порт полей c, устанавливаемых CreateTool). */
    static final class ToolSlot {
        final String file; // image
        final int id;      // c.case — id предмета
        final int w, h;    // h[0][2], h[0][3] — только для центрирования
        final int y;       // c.i = 130 - h/2 - 2 - 5 + 122
        int x;             // c.j — назначается в j() по позиции в карусели

        ToolSlot(String file, int id, int w, int h) {
            this.file = file;
            this.id = id;
            this.w = w;
            this.h = h;
            this.y = 130 - h / 2 - 2 - 5 + 122;
        }
    }

    public Inventory(GameScene game) {
        this.game = game;
    }

    /** CreateTool: a(img, toolId, state, 0, 0, w, h) — положить предмет. */
    void createTool(String file, int toolId, int state, int w, int h) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                aQ[toolId] = state;
                slots[i] = new ToolSlot(file, toolId, w, h);
                game.sound().playGet(); // реставрация: get.mid при находке
                break;
            }
        }
    }

    /**
     * freeToolByIndex: a(toolId, newState) — израсходовать предмет.
     * Слот удаляется, хвост c5 сдвигается влево; aQ[toolId]=newState;
     * bf=""; кадр руки 0; usingTool=false; currentTool=-1.
     * (Поля b.for/b.null — мёртвые записи оригинала, не переносим.)
     */
    void freeToolByIndex(int toolId, int newState) {
        int idx = 0;
        for (int i = 0; i < slots.length && slots[i] != null; i++) {
            if (slots[i].id == toolId) {
                idx = i;
                break;
            }
        }
        for (int i = idx + 1; i < slots.length; i++) {
            if (slots[i] == null) {
                slots[i - 1] = null;
                break;
            }
            slots[i - 1] = slots[i];
        }
        game.bf = "";
        aQ[toolId] = newState;
        game.room.hand.frame = 0;
        usingTool = false;
        current = -1;
        game.sound().playCorrect(); // реставрация: correct.mid при успехе
    }

    /** j(): нормализация currentTool (void==-1 && c5[0]!=null -> 0). */
    void fixCurrentForTest() {
        if (current != -1 && slots[current] == null) {
            current = -1;
        }
        if (current == -1 && slots[0] != null) {
            current = 0;
        }
    }

    /**
     * j() (drawTool): имя текущего предмета -> game.m, панель toolback,
     * карусель (текущий ± 2), плашка toolback1.
     */
    void render(SpriteBatch batch, Assets a) {
        fixCurrentForTest();
        if (current != -1) {
            game.m = nameOf(slots[current].id);
        }
        batch.draw(a.region("toolback.png"), 0, 218); // ab: j=0, i=218
        if (current != -1) {
            drawAt(batch, a, current, 120);                    // центр
            if (current > 0 && slots[current - 1] != null) {
                drawAt(batch, a, current - 1, 120 - 85);       // сосед слева
            }
            if (current > 1 && slots[current - 2] != null) {
                drawAt(batch, a, current - 2, 120 - 140);      // крайний слева
            }
            if (current < slots.length - 1 && slots[current + 1] != null) {
                drawAt(batch, a, current + 1, 120 + 85);       // сосед справа
            }
            if (current < slots.length - 2 && slots[current + 2] != null) {
                drawAt(batch, a, current + 2, 120 + 140);      // крайний справа
            }
            batch.draw(a.region("toolback1.png"), (240 - 76) / 2 - 3, 218 + 4);
        }
    }

    /** Иконка слота: x = centerX - w/2, y — из CreateTool; спрайт целиком. */
    private void drawAt(SpriteBatch batch, Assets a, int slot, int centerX) {
        ToolSlot t = slots[slot];
        t.x = centerX - t.w / 2;
        batch.draw(a.region(t.file), t.x, t.y);
    }

    /** Имена предметов: switch(c5[void].case) из j(). */
    static String nameOf(int id) {
        switch (id) {
            case 1:  return "Крюк";
            case 2:  return "Используйте клавишу \"5\".";
            case 3:  return "Большой ключ";
            case 4:  return "Маленький ключ";
            case 5:  return "Книги";
            case 6:  return "Крышка";
            case 7:  return "Хреновина";
            case 8:  return "Ручка от крышки";
            case 9:  return "Монета";
            case 10: return "Зеленый ключ";
            case 11: return "Молоток";
            case 12: return "Аккумулятор";
            case 13: return "Веревка";
            case 14: return "Золотой ключик";
            default: return "Пожалуйста, выберите пункты.";
        }
    }

    /**
     * if(int) (onTool) — ввод при открытом инвентаре.
     * '1' — закрыть (гейты dz/g/c6 как в оригинале); влево/вправо — навигация;
     * '5'/FIRE — взять предмет в руку (usingTool=true, инвентарь закрывается).
     */
    void handleKey(ru.house13.engine.KeyMap.J2 k) {
        if (k == ru.house13.engine.KeyMap.J2.NUM_1) {
            // dz&&g&&c6 — в игре всегда выполняется к этому моменту
            showTool = false;
            usingTool = false;
            game.room.hand.frame = 0;
        } else if (k == ru.house13.engine.KeyMap.J2.LEFT
                || k == ru.house13.engine.KeyMap.J2.NUM_4) {
            if (current > 0) {
                current--;
            }
        } else if (k == ru.house13.engine.KeyMap.J2.RIGHT
                || k == ru.house13.engine.KeyMap.J2.NUM_6) {
            if (current + 1 < slots.length && slots[current + 1] != null) {
                current++;
            }
        } else if ((k == ru.house13.engine.KeyMap.J2.NUM_5
                || k == ru.house13.engine.KeyMap.J2.FIRE) && current != -1) {
            game.room.hand.frame = 0;
            usingTool = true;
            showTool = false;
        }
    }

    /** Сброс (for()/freeRoom1): c5=null, aQ обнуляется, void=-1, cP/dt=false. */
    void reset() {
        java.util.Arrays.fill(slots, null);
        java.util.Arrays.fill(aQ, 0);
        current = -1;
        usingTool = false;
        showTool = false;
    }
}
