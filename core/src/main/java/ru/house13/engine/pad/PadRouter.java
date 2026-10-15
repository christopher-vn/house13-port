package ru.house13.engine.pad;

import com.badlogic.gdx.Input;
import ru.house13.engine.InputQueue;

import java.util.function.Consumer;

/**
 * Маршрутизатор геймпадного ввода: события моста -> {@link InputQueue}
 * (тот же путь, что клавиатура и автотесты). Вызывается на GL-потоке
 * между тиками; накапливающая семантика InputQueue сама выравнивает
 * события с логическим шагом 10 Гц.
 *
 * <p>Ключевые гарантии:
 * <ul>
 *   <li><b>Счётчик ссылок на целевую клавишу.</b> D-pad и левый стик
 *       дают одно нажатие UP, а не два; L2 и R2 делят один SHIFT.
 *       injectUp уходит только когда отвалился ПОСЛЕДНИЙ источник.</li>
 *   <li><b>Гистерезис стика</b> (вкл при |v| &ge; 0.50, выкл при
 *       |v| &lt; 0.35): нет дребезга на границе; диагонали — независимо
 *       по осям; при развороте стика сначала release старого направления,
 *       затем press нового (в игре не бывает LEFT+RIGHT одновременно).</li>
 *   <li><b>releaseAll()</b> при отключении пада / смене раскладки —
 *       ни одна клавиша не «залипает» (GameScene.fast, защёлки
 *       направлений).</li>
 * </ul>
 *
 * <p>Контракт leftStick: ось Y как в GLFW (вверх = -1, вниз = +1);
 * мост передаёт сырые значения нормализованного состояния.
 */
public final class PadRouter {

    /** Порог включения направления стика (|v| >= PRESS). */
    public static final float STICK_PRESS = 0.50f;
    /** Порог выключения (|v| < RELEASE): зона нечувствительности против дребезга. */
    public static final float STICK_RELEASE = 0.35f;

    private final InputQueue input;
    private PadMap map;

    /** Счётчики источников по gdx-коду (0 = не нажата). */
    private final int[] ref = new int[Input.Keys.MAX_KEYCODE + 1];

    /** Физическое состояние кнопок моста (для дебага/оверлея). */
    private final boolean[] btnHeld = new boolean[PadBtn.COUNT];

    /** Направления левого стика (фиксируются при PRESS, снимаются при RELEASE). */
    private boolean lsUp, lsDown, lsLeft, lsRight;

    private String padName = "";
    private Consumer<String> logger; // nullable, только для отладки

    public PadRouter(InputQueue input) {
        this(input, PadMap.DEFAULT);
    }

    public PadRouter(InputQueue input, PadMap map) {
        this.input = input;
        this.map = map;
    }

    /** Отладочный лог (например, Gdx.app::log); null выключает. */
    public void setLogger(Consumer<String> logger) {
        this.logger = logger;
    }

    /** Смена раскладки на лету (будущий экран настроек): отпускает всё зажатое. */
    public void setMap(PadMap map) {
        releaseAll();
        this.map = map;
        log("layout -> " + describeMap());
    }

    /** Нажатие/отпускание кнопки (событие моста; повторные press игнорируются). */
    public void button(PadBtn b, boolean pressed) {
        if (btnHeld[b.idx()] == pressed) {
            return; // дребезг/дубль состояния
        }
        btnHeld[b.idx()] = pressed;
        int code = map.gdxCode(b);
        if (code == PadMap.UNMAPPED) {
            log(b + (pressed ? " down (unmapped, ignored)" : " up (unmapped)"));
            return;
        }
        if (pressed) {
            press(code, b.toString());
        } else {
            release(code, b.toString());
        }
    }

    /** Левый стик: x/y в -1..1 (GLFW: y+ вниз). Гистерезис по каждой оси. */
    public void leftStick(float x, float y) {
        // Сначала release покинутых направлений (порядок: up старого до down нового).
        if (lsRight && x < STICK_RELEASE)  { lsRight = false; release(Input.Keys.RIGHT, "LS"); }
        if (lsLeft  && x > -STICK_RELEASE) { lsLeft  = false; release(Input.Keys.LEFT,  "LS"); }
        if (lsDown  && y < STICK_RELEASE)  { lsDown  = false; release(Input.Keys.DOWN,  "LS"); }
        if (lsUp    && y > -STICK_RELEASE) { lsUp    = false; release(Input.Keys.UP,    "LS"); }
        // Затем press новых.
        if (!lsRight && x >= STICK_PRESS)  { lsRight = true;  press(Input.Keys.RIGHT, "LS"); }
        if (!lsLeft  && x <= -STICK_PRESS) { lsLeft  = true;  press(Input.Keys.LEFT,  "LS"); }
        if (!lsDown  && y >= STICK_PRESS)  { lsDown  = true;  press(Input.Keys.DOWN,  "LS"); }
        if (!lsUp    && y <= -STICK_PRESS) { lsUp    = true;  press(Input.Keys.UP,    "LS"); }
    }

    /** Подключение пада (горячее). */
    public void padConnected(String name) {
        this.padName = name == null ? "" : name;
        log("connected: " + padName);
    }

    /** Отключение пада: отпустить всё зажатое им. */
    public void padDisconnected() {
        releaseAll();
        log("disconnected: " + padName);
        padName = "";
    }

    /** Аварийный сброс: инъекция «отпусканий» для всех активных источников. */
    public void releaseAll() {
        for (PadBtn b : PadBtn.values()) {
            if (btnHeld[b.idx()]) {
                btnHeld[b.idx()] = false;
                int code = map.gdxCode(b);
                if (code != PadMap.UNMAPPED) {
                    release(code, b.toString() + " (releaseAll)");
                }
            }
        }
        if (lsUp)    { lsUp = false;    release(Input.Keys.UP,    "LS (releaseAll)"); }
        if (lsDown)  { lsDown = false;  release(Input.Keys.DOWN,  "LS (releaseAll)"); }
        if (lsLeft)  { lsLeft = false;  release(Input.Keys.LEFT,  "LS (releaseAll)"); }
        if (lsRight) { lsRight = false; release(Input.Keys.RIGHT, "LS (releaseAll)"); }
        // Страховка от рассинхрона счётчиков (не должно случаться).
        java.util.Arrays.fill(ref, 0);
    }

    /** Физическое состояние кнопки (дебаг/оверлей; не зависит от раскладки). */
    public boolean isHeld(PadBtn b) {
        return btnHeld[b.idx()];
    }

    /** Есть ли активные источники (для оверлея «геймпад подключён»). */
    public boolean padActive() {
        return !padName.isEmpty();
    }

    /** Имя подключённого пада (пусто, если нет). */
    public String padName() {
        return padName;
    }

    /** Строка состояния для лога/оверлея: имя + зажатое + направления стика. */
    public String describe() {
        StringBuilder sb = new StringBuilder(padName.isEmpty() ? "-" : padName);
        sb.append(" [");
        boolean first = true;
        for (PadBtn b : PadBtn.values()) {
            if (btnHeld[b.idx()]) {
                if (!first) sb.append(' ');
                sb.append(b);
                first = false;
            }
        }
        sb.append("] LS:");
        if (lsUp) sb.append('↑');
        if (lsDown) sb.append('↓');
        if (lsLeft) sb.append('←');
        if (lsRight) sb.append('→');
        if (!lsUp && !lsDown && !lsLeft && !lsRight) sb.append('-');
        return sb.toString();
    }

    private String describeMap() {
        return map == PadMap.NINTENDO ? "NINTENDO" : "DEFAULT";
    }

    private void press(int code, String source) {
        if (ref[code]++ == 0) {
            input.injectDown(code);
            log(source + " down -> " + keyName(code) + " (ref=" + ref[code] + ")");
        } else {
            log(source + " down -> " + keyName(code) + " (merged, ref=" + ref[code] + ")");
        }
    }

    private void release(int code, String source) {
        if (ref[code] <= 0) {
            return; // up без down (страховка)
        }
        if (--ref[code] == 0) {
            input.injectUp(code);
            log(source + " up -> " + keyName(code) + " (ref=0)");
        } else {
            log(source + " up -> " + keyName(code) + " (held, ref=" + ref[code] + ")");
        }
    }

    private void log(String msg) {
        if (logger != null) {
            logger.accept("[pad] " + msg);
        }
    }

    /** Человекочитаемое имя клавиши (ENTER/FIRE, ESCAPE/софт-право и т.п.). */
    static String keyName(int code) {
        switch (code) {
            case Input.Keys.UP:    return "UP";
            case Input.Keys.DOWN:  return "DOWN";
            case Input.Keys.LEFT:  return "LEFT";
            case Input.Keys.RIGHT: return "RIGHT";
            case Input.Keys.ENTER: return "ENTER(FIRE)";
            case Input.Keys.ESCAPE: return "ESCAPE(SOFT_RIGHT)";
            case Input.Keys.F1:    return "F1(SOFT_LEFT)";
            case Input.Keys.Z:     return "Z(NUM_1:инвентарь)";
            case Input.Keys.SHIFT_LEFT: return "SHIFT(ускорение)";
            default: return "key#" + code;
        }
    }
}
