package ru.house13.game;

/**
 * Edge-walk переходы — порт long() (b.java, замена drawHand CN).
 * Проверяются ДО движения, по текущей позиции руки, только при !usingTool.
 *
 * 12 правил; диапазоны открыты (строгие <), как в long():
 *   ком.4: влево  j(-20,-3) -> ком.5 (j+=228); вправо j(232,240) -> ком.6 (j-=228);
 *          вниз   i(300,320) -> ком.2 (i=5, j прежний)
 *   ком.2: влево  j(-20,-3) -> ком.3 (j+=228); вправо j(232,240) -> ком.1 (j=3, i=193);
 *          вверх  i(-20,0)   -> ком.4 (i=280, j прежний)
 *   ком.1: влево  j(-20,-3)  -> ком.2 (j=203, i=193)
 *   ком.3: вправо j(232,240) -> ком.2 (j-=228)
 *   ком.5: вправо j(232,240) -> ком.4 (j-=228)
 *   ком.6: влево  j(-20,-3)  -> ком.4 (j+=228); вправо j(232,240) -> ком.7 (j-=228)
 *   ком.7: влево  j(-20,-3)  -> ком.6 (j+=228)
 */
final class EdgeRules {

    private EdgeRules() {}

    // Режимы координаты при переходе
    static final int KEEP = 0; // сохранить
    static final int ADD  = 1; // прибавить дельту
    static final int SET  = 2; // присвоить

    /** Одна строка таблицы переходов. */
    static final class Rule {
        final int room;
        final int side;        // 0=влево, 1=вправо, 2=вверх, 3=вниз
        final double jMin, jMax; // ворота по j (для боковых)
        final double iMin, iMax; // ворота по i (для вертикальных)
        final int target;
        final int jMode, jVal;
        final int iMode, iVal;

        Rule(int room, int side, double jMin, double jMax, double iMin, double iMax,
             int target, int jMode, int jVal, int iMode, int iVal) {
            this.room = room; this.side = side;
            this.jMin = jMin; this.jMax = jMax; this.iMin = iMin; this.iMax = iMax;
            this.target = target;
            this.jMode = jMode; this.jVal = jVal;
            this.iMode = iMode; this.iVal = iVal;
        }
    }

    static final Rule[] RULES = {
        // комната 4 — хаб коридора
        new Rule(4, 0, -20, -3, 0, 320, 5, ADD, 228, KEEP, 0), // влево -> 5
        new Rule(4, 1, 232, 240, 0, 320, 6, ADD, -228, KEEP, 0), // вправо -> 6
        new Rule(4, 3, -1e9, 1e9, 300, 320, 2, KEEP, 0, SET, 5), // вниз -> 2
        // комната 2
        new Rule(2, 0, -20, -3, 0, 320, 3, ADD, 228, KEEP, 0),  // влево -> 3
        new Rule(2, 1, 232, 240, 0, 320, 1, SET, 3, SET, 193),  // вправо -> 1
        new Rule(2, 2, -1e9, 1e9, -20, 0, 4, KEEP, 0, SET, 280), // вверх -> 4
        // комната 1
        new Rule(1, 0, -20, -3, 0, 320, 2, SET, 203, SET, 193), // влево -> 2
        // комната 3
        new Rule(3, 1, 232, 240, 0, 320, 2, ADD, -228, KEEP, 0), // вправо -> 2
        // комната 5
        new Rule(5, 1, 232, 240, 0, 320, 4, ADD, -228, KEEP, 0), // вправо -> 4
        // комната 6
        new Rule(6, 0, -20, -3, 0, 320, 4, ADD, 228, KEEP, 0),  // влево -> 4
        new Rule(6, 1, 232, 240, 0, 320, 7, ADD, -228, KEEP, 0), // вправо -> 7
        // комната 7
        new Rule(7, 0, -20, -3, 0, 320, 6, ADD, 228, KEEP, 0),  // влево -> 6
    };

    /**
     * Проверить переход для руки (j, i) в комнате room; null, если нет.
     * Боковые: j в (jMin,jMax) И i в (0,320). Вертикальные: i в (iMin,iMax).
     * (long() для вертикальных переходов j не ограничивает.)
     */
    static Rule match(int room, int j, int i) {
        for (Rule r : RULES) {
            if (r.room != room) {
                continue;
            }
            boolean hit;
            switch (r.side) {
                case 0:
                case 1:
                    hit = j > r.jMin && j < r.jMax && i > 0 && i < 320;
                    break;
                case 2:
                case 3:
                    hit = i > r.iMin && i < r.iMax;
                    break;
                default:
                    hit = false;
            }
            if (hit) {
                return r;
            }
        }
        return null;
    }

    /** Применить правило к руке; возвращает целевую комнату (мутирует hand). */
    static int apply(Rule r, Hand hand) {
        switch (r.jMode) {
            case ADD: hand.j += r.jVal; break;
            case SET: hand.j = r.jVal; break;
            default: break;             // KEEP
        }
        switch (r.iMode) {
            case ADD: hand.i += r.iVal; break;
            case SET: hand.i = r.iVal; break;
            default: break;             // KEEP
        }
        return r.target;
    }
}
