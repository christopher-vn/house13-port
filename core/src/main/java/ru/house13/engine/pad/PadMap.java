package ru.house13.engine.pad;

import com.badlogic.gdx.Input;
import ru.house13.engine.KeyMap;

import java.util.EnumMap;

/**
 * ЕДИНЫЙ ИСТОЧНИК ИСТИНЫ маппинга «кнопка геймпада -> клавиша порта».
 *
 * <p>Геймпадный ввод встраивается в существующую трубу КАК КЛАВИАТУРА:
 * {@code PadBtn -> gdx-код -> InputQueue.injectDown/Up -> KeyMap -> J2}.
 * Логика сцен не знает о геймпаде вообще — мост (GLFW на десктопе,
 * InputDevice на Android) лишь транслирует события в {@link PadRouter}.
 *
 * <p>Раскладка DEFAULT (позиционная, как у Xbox/PS и Steam Big Picture):
 *
 * <table border="1">
 *   <tr><th>Кнопка</th><th>gdx-код</th><th>J2 (KeyMap)</th><th>В игре</th></tr>
 *   <tr><td>D-pad / левый стик</td><td>UP/DOWN/LEFT/RIGHT</td><td>UP/DOWN/LEFT/RIGHT</td>
 *       <td>движение руки, курсоры меню и сейф-клавиатуры</td></tr>
 *   <tr><td>A (низ)</td><td>ENTER</td><td>FIRE</td>
 *       <td>OK: выбор / действие / «дальше» / клетка сейф-клавиатуры</td></tr>
 *   <tr><td>B (право)</td><td>ESCAPE</td><td>SOFT_RIGHT</td>
 *       <td>отмена / меню игры / пауза (правый софт)</td></tr>
 *   <tr><td>X (лево)</td><td>Z</td><td>NUM_1</td>
 *       <td>инвентарь (карусель предметов)</td></tr>
 *   <tr><td>START</td><td>F1</td><td>SOFT_LEFT</td>
 *       <td>меню/настройки (левый софт)</td></tr>
 *   <tr><td>L2 ИЛИ R2 (удерж.)</td><td>SHIFT_LEFT</td><td>isShift</td>
 *       <td>ускорение руки x2 (дополнение порта)</td></tr>
 * </table>
 *
 * <p>Зарезервировано (не маппится, код {@code -1}): Y (карта/заметки?),
 * SELECT (быстрое переключение перевода?), L1/R1, L3/R3, GUIDE
 * (системная, не перехватывается), правый стик (курсор тач-сцен?).
 * Значения закреплены тестом {@code PadMapTest}: каждый замапленный код
 * обязан пониматься KeyMap (J2) либо быть модификатором Shift.
 *
 * <p>{@link Layout#NINTENDO} отличается только свопом A&lt;-&gt;B и
 * X&lt;-&gt;Y (кнопка по МЕТКЕ, а не по позиции: OK на «A» Switch-пада).
 * Выбор раскладки — кандидат в экран настроек полной реализации.
 */
public final class PadMap {

    /** Раскладки маппинга лицевых кнопок. */
    public enum Layout {
        /** Позиционная (Xbox/PS/Steam): нижняя кнопка = OK. */
        DEFAULT,
        /** По меткам Nintendo: OK на правой («A»), отмена на нижней («B»). */
        NINTENDO
    }

    /** Код «не мапплена» (зарезервирована под полную реализацию). */
    public static final int UNMAPPED = -1;

    private final EnumMap<PadBtn, Integer> codes;

    private PadMap(EnumMap<PadBtn, Integer> codes) {
        this.codes = codes;
    }

    /** gdx-код клавиши порта для кнопки; {@link #UNMAPPED}, если зарезервирована. */
    public int gdxCode(PadBtn b) {
        Integer c = codes.get(b);
        return c == null ? UNMAPPED : c;
    }

    /** Кнопка участвует в игре (для проверок и будущего экрана ремапа). */
    public boolean isMapped(PadBtn b) {
        return gdxCode(b) != UNMAPPED;
    }

    /** J2ME-семантика кнопки (для подсказок UI); NONE для Shift-модификатора. */
    public KeyMap.J2 j2(PadBtn b) {
        int code = gdxCode(b);
        if (code == UNMAPPED) {
            return KeyMap.J2.NONE;
        }
        if (KeyMap.isShift(code)) {
            return KeyMap.J2.NONE; // модификатор ускорения, вне J2ME-модели
        }
        return KeyMap.map(code);
    }

    /** Стандартная позиционная раскладка (см. таблицу класса). */
    public static final PadMap DEFAULT = build(Layout.DEFAULT);

    /** Раскладка по меткам Nintendo (своп A/B и X/Y относительно DEFAULT). */
    public static final PadMap NINTENDO = build(Layout.NINTENDO);

    private static PadMap build(Layout layout) {
        EnumMap<PadBtn, Integer> m = new EnumMap<>(PadBtn.class);
        // Лицевые кнопки — по раскладке.
        int a = Input.Keys.ENTER;    // FIRE: тот же код, что у токена F автотестов
        int b = Input.Keys.ESCAPE;   // SOFT_RIGHT: тот же, что у токена Rs
        int x = Input.Keys.Z;        // NUM_1: инвентарь-карусель
        int y = UNMAPPED;            // зарезервировано
        if (layout == Layout.NINTENDO) { // своп по МЕТКАМ: A-метка = правая позиция
            int t = a; a = b; b = t;
            t = x; x = y; y = t;
        }
        m.put(PadBtn.A, a);
        m.put(PadBtn.B, b);
        m.put(PadBtn.X, x);
        m.put(PadBtn.Y, y);
        // Плечи: триггеры = ускорение (удержание), бамперы и клики — резерв.
        m.put(PadBtn.L2, Input.Keys.SHIFT_LEFT);
        m.put(PadBtn.R2, Input.Keys.SHIFT_LEFT); // оба триггера — удобно любым хватом
        m.put(PadBtn.L1, UNMAPPED);
        m.put(PadBtn.R1, UNMAPPED);
        m.put(PadBtn.L3, UNMAPPED);
        m.put(PadBtn.R3, UNMAPPED);
        // Системные: START = левый софт (меню), SELECT и GUIDE — резерв.
        m.put(PadBtn.START, Input.Keys.F1);  // тот же код, что у токена Ls
        m.put(PadBtn.SELECT, UNMAPPED);
        m.put(PadBtn.GUIDE, UNMAPPED);       // системная кнопка, не перехватываем
        // Крестовина = направления.
        m.put(PadBtn.DPAD_UP, Input.Keys.UP);
        m.put(PadBtn.DPAD_DOWN, Input.Keys.DOWN);
        m.put(PadBtn.DPAD_LEFT, Input.Keys.LEFT);
        m.put(PadBtn.DPAD_RIGHT, Input.Keys.RIGHT);
        return new PadMap(m);
    }
}
