package ru.house13.engine.pad;

/**
 * Абстрактные кнопки геймпада — нормализованные имена в духе SDL
 * Game Controller API (glfwGetGamepadState). Позиционная семантика:
 * «A» — НИЖНЯЯ лицевая кнопка, «B» — правая и т.д., независимо от марки пада.
 *
 * <p>Нюанс Nintendo: у Switch-пада физическая нижняя кнопка помечена «B»,
 * а правая — «A». SDL/GLFW нормализуют ПОЗИЦИОННО (как Xbox/PS), поэтому
 * нижняя кнопка приходит как {@link #A}. Раскладка
 * {@link PadMap.Layout#NINTENDO} меняет местами A/B и X/Y для тех, кто
 * привык к «OK на кнопке с меткой A».
 *
 * <p>Стик — не кнопка: направления левого стика порождает
 * {@link PadRouter#leftStick} синтетически (гистерезис + общий счётчик
 * ссылок с D-pad). Правый стик и вибрация — этап полной реализации.
 */
public enum PadBtn {
    /** Лицевые кнопки (позиции: низ / право / лево / верх). */
    A, B, X, Y,
    /** Бамперы. */
    L1, R1,
    /** Триггеры (мост нормализует ось/кнопку в press/release). */
    L2, R2,
    /** Select/View/Share, Start/Menu, центральная системная (guide). */
    SELECT, START, GUIDE,
    /** Клики стиков. */
    L3, R3,
    /** Крестовина. */
    DPAD_UP, DPAD_DOWN, DPAD_LEFT, DPAD_RIGHT;

    /** Количество значений (для массивов состояния). */
    public static final int COUNT = values().length;

    /** Индекс в массивах состояния (порядок = ordinal). */
    public int idx() {
        return ordinal();
    }
}
