package ru.house13.lwjgl3;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWGamepadState;
import ru.house13.engine.pad.PadBtn;
import ru.house13.engine.pad.PadPoller;
import ru.house13.engine.pad.PadRouter;

/**
 * Десктопный мост геймпада: GLFW Gamepad API + SDL-нормализация
 * (glfwGetGamepadState отдаёт единый позиционный лейаут A/B/X/Y,
 * бамперы, D-pad и оси; базы маппингов — внутри GLFW, внешних
 * зависимостей и колбэков нет).
 *
 * <p>Модель — опрос с дифом: poll() каждый кадр сравнивает срез
 * состояния с прошлым и транслирует ИЗМЕНЕНИЯ в router.button /
 * router.leftStick. 60 Гц сэмплирования против минимального тапа
 * ~50 мс — потери нет (см. PadPoller).
 *
 * <p>Фундамент (этап 5e), сознательные упрощения для полной реализации:
 * <ul>
 *   <li>активен ПЕРВЫЙ найденный геймпад (jid с наименьшим номером);
 *       несколько падов / переключение — потом;</li>
 *   <li>триггеры L2/R2 — кнопки с порогом 0.5 (гистерезис аналоговых
 *       осей — потом);</li>
 *   <li>правый стик, вибрация, GUIDE — не читаются;</li>
 *   <li>GLFWGamepadState.malloc() один раз на процесс (native-буфер
 *       живёт столько же, сколько poller).</li>
 * </ul>
 *
 * <p>Отключение: house13.pad=false (см. Lwjgl3Launcher) — мост не
 * устанавливается вовсе.
 */
public final class GlfwPadPoller implements PadPoller {

    /** GLFW-индексы кнопок (SDL Game Controller layout). */
    private static final int BTN_A = 0, BTN_B = 1, BTN_X = 2, BTN_Y = 3,
            BTN_L1 = 4, BTN_R1 = 5, BTN_BACK = 6, BTN_GUIDE = 7, BTN_START = 8,
            BTN_L3 = 9, BTN_R3 = 10,
            BTN_DPAD_UP = 11, BTN_DPAD_DOWN = 12,
            BTN_DPAD_LEFT = 13, BTN_DPAD_RIGHT = 14, BTN_COUNT = 15;

    /** GLFW-индексы осей. */
    private static final int AXIS_LX = 0, AXIS_LY = 1, AXIS_RX = 2, AXIS_RY = 3,
            AXIS_L2 = 4, AXIS_R2 = 5;

    /** Порог срабатывания триггера (SDL-нормализация: 0..1). */
    private static final float TRIGGER_PRESS = 0.5f;

    /** Предел jid GLFW (джойстики 0..15). */
    private static final int MAX_JIDS = 16;

    private final GLFWGamepadState state = GLFWGamepadState.malloc();

    /** Активный геймпад или -1. */
    private int jid = -1;
    private String name = "";

    /** Прошлый срез кнопок (для дифа press/release). */
    private final boolean[] prev = new boolean[BTN_COUNT];
    private boolean prevL2, prevR2;

    @Override
    public void poll(PadRouter router) {
        if (jid < 0) {
            scanForGamepad(router);
            if (jid < 0) {
                return;
            }
        }
        if (!GLFW.glfwJoystickPresent(jid) || !GLFW.glfwJoystickIsGamepad(jid)) {
            router.padDisconnected(); // хотплаг: выдёргивание/сон USB
            jid = -1;
            name = "";
            resetPrev();
            return;
        }
        if (!GLFW.glfwGetGamepadState(jid, state)) {
            return; // гонка отключения: следующий кадр увидит absent
        }

        // 1) Кнопки: диф с прошлым кадром.
        for (int i = 0; i < BTN_COUNT; i++) {
            boolean pressed = state.buttons().get(i) != 0;
            if (pressed != prev[i]) {
                prev[i] = pressed;
                PadBtn b = toPadBtn(i);
                if (b != null) {
                    router.button(b, pressed);
                }
            }
        }
        // 2) Триггеры: ось -> виртуальная кнопка (порог).
        boolean l2 = state.axes().get(AXIS_L2) >= TRIGGER_PRESS;
        boolean r2 = state.axes().get(AXIS_R2) >= TRIGGER_PRESS;
        if (l2 != prevL2) {
            prevL2 = l2;
            router.button(PadBtn.L2, l2);
        }
        if (r2 != prevR2) {
            prevR2 = r2;
            router.button(PadBtn.R2, r2);
        }
        // 3) Левый стик: сырые нормализованные оси (гистерезис — в router).
        router.leftStick(state.axes().get(AXIS_LX), state.axes().get(AXIS_LY));
    }

    private void scanForGamepad(PadRouter router) {
        for (int j = 0; j < MAX_JIDS; j++) {
            if (GLFW.glfwJoystickPresent(j) && GLFW.glfwJoystickIsGamepad(j)) {
                jid = j;
                name = safe(GLFW.glfwGetJoystickName(j));
                resetPrev();
                router.padConnected(name);
                return;
            }
        }
    }

    private void resetPrev() {
        java.util.Arrays.fill(prev, false);
        prevL2 = false;
        prevR2 = false;
    }

    /** GLFW-индекс -> абстрактная кнопка. */
    private static PadBtn toPadBtn(int glfwButton) {
        switch (glfwButton) {
            case BTN_A:          return PadBtn.A;
            case BTN_B:          return PadBtn.B;
            case BTN_X:          return PadBtn.X;
            case BTN_Y:          return PadBtn.Y;
            case BTN_L1:         return PadBtn.L1;
            case BTN_R1:         return PadBtn.R1;
            case BTN_BACK:       return PadBtn.SELECT;
            case BTN_GUIDE:      return PadBtn.GUIDE;
            case BTN_START:      return PadBtn.START;
            case BTN_L3:         return PadBtn.L3;
            case BTN_R3:         return PadBtn.R3;
            case BTN_DPAD_UP:    return PadBtn.DPAD_UP;
            case BTN_DPAD_DOWN:  return PadBtn.DPAD_DOWN;
            case BTN_DPAD_LEFT:  return PadBtn.DPAD_LEFT;
            case BTN_DPAD_RIGHT: return PadBtn.DPAD_RIGHT;
            default:             return null; // не бывает: BTN_COUNT = 15
        }
    }

    private static String safe(String s) {
        return s == null ? "gamepad" : s;
    }
}
