package ru.house13.game;

import ru.house13.engine.Settings;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Все тексты игры.
 *
 * ЭТАП 5: тексты хранятся в JSON-паках (assets/text/*.json):
 *   ru-vpk  — издание V.P.K «Дом №13» (русская локализация 2006, вольное
 *             повествование локализатора: Черкизовский рынок, пятница 13-е);
 *   ru-auth — авторский перевод с китайского оригинала «三更灵异事件»
 *             (北京无限乐游, 2005): Сидзуко и сестра, овраг, шрам у озера.
 *
 * Поля НИЖЕ — текущие значения (не final): Texts.reload(packId) сбрасывает их
 * к вшитым значениям VPK и накладывает поверх значения из JSON-пака. Вшитые
 * значения = последний fallback, если JSON не найден: игра остаётся играбельной.
 * Числовые константы (переносы, позиции, строки) тоже перекрываются из пака —
 * издания различаются раскладкой экранов (см. ru-auth.json: HELP_ROWS, ABOUT_TOP).
 *
 * Все 83 прежних места обращения (Texts.X) не менялись: ключи = имена полей.
 */
public final class Texts {

    private Texts() {
    }

    // ------------------------------------------------------------------
    // Идентификаторы паков
    // ------------------------------------------------------------------
    public static final String PACK_VPK = "ru-vpk";
    public static final String PACK_AUTH = "ru-auth";

    // ==================================================================
    // ВШИТЫЕ ЗНАЧЕНИЯ = издание V.P.K «Дом №13» (дословно из RU b.java).
    // ЭТАП 5: эти же значения продублированы в assets/text/ru-vpk.json
    // (пак можно править без пересборки), поля перекрываются reload().
    // ==================================================================

    /** b.case(): экран «ПОМОЩЬ» главного меню (menuStatus_ = 3). */
    public static String HELP =
        "          ПОМОЩЬ \n Перед  вами  классичес  кая игра/квест о загадочном приключении девуш ки Шизуко в чужом  странном доме.Помогите бедняжке с честью избавить  ся от кошмаров и ужасов злоключений."
        + "\n        УПРАВЛЕНИЕ \n"
        + "\"2\",\"4\",\"6\",\"8\"- перемещение по дому \n"
        + "\"5\"- выбор действия \n "
        + "\"1\"- перечень предметов для использования \n"
        + "Правый \"soft\"-  вызов     меню игры \n"
        + "Левый \"soft\"- отмена действий \n"
        + "В   ИГРЕ  ОТСУТСТВУЕТ        СОХРАНЕНИЕ \n";

    /** Ширина переноса помощи: b.case() — a(var1, d1 - 20, dD). CN: WIDTH-20. */
    public static int HELP_WRAP = 240 - 20;

    /** b.case(): пейджер for(eh, 10, 30, dA, 11, dD). CN: drawString(...,10,30,...,7). */
    public static int HELP_LEFT = 10, HELP_TOP = 30, HELP_ROWS = 11;

    /** b.goto(): экран «АВТОР» (menuStatus_ = 1). */
    public static String ABOUT =
        "  Название игры: \n     \"Дом №13\" \n"
        + "         v1.0 \n   Перевод V.P.K \n        E-mail: \n vova2const@mail.ru \n "
        + "    Разработчик: \n    Vendor, Китай \n    E-mail: game \n  0311kf@126.com";

    /** b.goto(): a(var1, d1 - 50, dD); пейджер for(eh, 28, 80, 0, V=8, dD). CN: (28,40), SHOW_ROW=8. */
    public static int ABOUT_WRAP = 240 - 50;
    public static int ABOUT_LEFT = 28, ABOUT_TOP = 80, ABOUT_ROWS = 8;

    /** b()/g(): строки экрана звука. Двойной пробел после двоеточия — как в оригинале. */
    public static String SOUND_ON = "Звук:  ДА";
    public static String SOUND_OFF = "Звук:  НЕТ";

    /** b(): позиции строк экрана звука: cm/2-18 и cm/2+8. */
    public static int SOUND_ON_Y = 320 / 2 - 18;
    public static int SOUND_OFF_Y = 320 / 2 + 8;
    /** b()/g(): указатель shou на 68+32 правее текста. */
    public static int SOUND_SHOU_DX = 68 + 32;

    /** b.g(): оверлей «Настройка звука» в игре (isMusic). CN: 声音设置. */
    public static String MUSIC_TITLE = "Настройка звука";
    public static String MUSIC_UNDERLINE = "____________";

    /** b.int(): оверлей «ИНФОРМАЦИЯ» в игре (isHelp). CN: 帮 助 信 息 + 提示. di = высота шрифта. */
    public static String INFO_TITLE = " ИНФОРМАЦИЯ";
    public static String[] INFO_LINES = {
        "В игре НЕТ сохранения!",
        "2,4,6,8- перемещение",
        "\"5\"- выбор действий ",
        "\"1\"- список предметов"
    };

    // ==================================================================
    // ЭТАП 4. Строки интро, пробуждения, хот-зонов, сейфа и титров —
    // дословно из RU b.java (m/h/A/try(int)/u()/e()/s()).
    // ==================================================================

    /** b.h(): история (интро-прокрутка). Формат: a(var1, d1-16, dD). */
    public static String INTRO_STORY =
        "Меня зовут Шизуко.4 года назад я закончила школу благородных девиц и устроилась работать на черкизовский рынок.Вчера утром мне позвонили друзья и пригласили на отдых.Я нехотя согласилась,и вечером,в пятницу 13-го,мы   выехали.По дороге у нас сломалась машина и мы  заблудились в лесу.В полночь мы вышли к одному странному    неприветливому дому...";
    public static int INTRO_WRAP = 240 - 16;

    /**
     * b.m(): «строки-призраки» старой кодовой базы (Эмма/меч/скала/дворец —
     * остатки 古宝魅影, машинный перевод). В RU НЕДОСТИЖИМЫ: счётчик cR
     * нигде не инкрементируется — сохранены как документация артефакта.
     */
    public static String[] GHOST_STRINGS = {
        "Эмма камня, как в передней части, выше, крэк,",
        "Достаточно быть мечом в трещины, как меч? Трудные",
        "Дорожная она имеет ничего общего с этой скалы, Эмма будет на меч",
        "Добавлена в трещины после громкий шум, открыл дверь дворца ... ..."
    };

    /** b.try() (zhongtu): подсказка первой комнаты — в RU НИКОГДА НЕ ВЫЗЫВАЕТСЯ. */
    public static String FIRST_ROOM_HINT = "Как я могу покинуть эту комнату? ";

    /** b.do() (kongzhiren): сцена пробуждения — в RU автопропуск (bE=41). CN: 啊/啊../啊...... */
    public static String[] WAKEUP_TEXTS = {"Ах...", "Господи...", "Больно..."};

    /** b.A() (kaishichu): монолог пробуждения. */
    public static String WAKEUP_0 = "-Где я?..Ох,как болит голова...Ничего не помню...";
    public static String WAKEUP_1 = "-Где все?.Почему так тихо?.Ау-у!Что делать?";
    public static String WAKEUP_2 = "Дверь закрыта.Помню,мы вошли в дом.Удар.Темнота";
    public static String WAKEUP_2_X = "Я не понимаю,что происходит? Чей это дом?!.";

    /** b.e() (jieshuh): финальные титры. Формат a(var1, d1-16, dD). */
    public static String ENDING =
        "-Наконец-то!..Свобода! Но что это?!.Я не могу выйти!...Какая-то неведомая сила держит и не отпускает меня!..Я снова услышала голос...Жуткий неприятный смех...Я боялась оглянуться,но с ужасом понимала- Он приближается!..Он тут,рядом,за моей спиной!.Я не хочу умирать!..";
    public static int ENDING_WRAP = 240 - 16;

    /** Каскад byte(): неверное использование предмета. CN: 无效使用！ */
    public static String WRONG_USE = "Неправильное использование!";

    /** Сейф (try(int)). CN: 密码错误 / 硕大的保险柜只存放一把钥匙. */
    public static String SAFE_WRONG = "PIN-код неверный";
    public static String SAFE_OPEN = "На дне пустого сейфа лежал маленький ключик";

    /** b.try(int): сообщения хот-зонов (в порядке появления в коде). */
    public static String R0_DOOR_HINT_KEY = "Чтобы открыть,нажми \"1\" и выбери ключ";
    public static String R0_DOOR_LOCKED = "-Дверь закрыта!..И как я отсюда выйду?";
    public static String MIND_CONTROL = "-У меня такое чувство,что мной кто-то управляет";
    public static String R8_HAMMER_STUCK = "Ура!..Молоток! Я бы взяла,но он подпирает тумбу";
    public static String R1_KEY_TAKEN = "Я взяла большой ключ.";
    public static String R1_KEY_GONE = "-Ого!..Сколько тараканов?!И ни одного сухарика";
    public static String R10_NOTHING = "Ничего интересного";
    public static String R10_TOILET = "Вот люди!..Посрать - посрали,а смывать кто будет?";
    public static String R11_BOXES = "Что за старые ящики?Всюду пыль,один без крышки";
    public static String R9_LIBRARY = "Странная библиотека...  Одна медицина...";
    public static String R13_BOOKS_0 = "-Книги...\"Большая медицинская энциклопедия\".";
    public static String R13_BOOKS_1 = "Тут не хватает несколько     томов...";
    public static String R13_BOOKS_2 = "На книгах нет пыли...Ими  иногда пользуются";
    public static String R13_BOOKS_3 = "Интересно,каких книг не хватает?..";
    public static String R0_PHONE_HOVER = "-Интересно,что в шкафу?..Чтобы открыть,нажми\"5\"";
    public static String R0_PHONE_KEY_HOVER = "-Ух,ты!Ключ!.. Чтобы         получить,нажми \"5\"";
    public static String R0_BOTTOM_DOOR = "Дверь закрыта... ";
    public static String R1_SAFE_KEY = "-Хм...Еще один ключ...Пожалуй возьму, пригодится";
    public static String R5_DOOR_0 = "-Закрыто...Тихо! По-моему там кто-то есть... ";
    public static String R5_DOOR_1 = "Я четко слышала шорох...Ой,   мне страшно!..";
    public static String R3_NEED_KEY = "-Упс...Дверь закрыта...Нужен ключ";
    public static String R6_DOOR_COLD = "Дверь закрыта...Из-под двери повеяло холодом";
    public static String R7_NO_HANDLE_0 = "Открыть невозможно.У двери нет ручки...";
    public static String R7_NO_HANDLE_1 = "Нужна ручка.Любая.Дверная,от крышки,коробки";
    public static String R10_GREEN_KEY = "-А вот еще один ключ...Кто их тут оставил?";
    public static String R8_WATCHED = "Дверь закрыта...-По-моему за мной наблюдают";
    public static String R17_VOICE = "Тот же голос: -Ты всеравно отсюда не выйдешь!";
    public static String R16_ANGRY = "Сердитый  голос: -Хватит ломать дом, дура!!!";
    public static String R16_ROPE = "-Господи,мыло и веревка!.Лучше возьму веревку";
    public static String R9_BOOKS_TAKEN = "-А вот и книги!..\"Анатомия человеческого тела\"...";
    public static String R11_WHAT_IS_IT = "Что за хрень?И куда её?Возьму,может пригодиться";
    public static String R11_BREAK_HANDLE = "Вы решили отломать ручку от крышки";
    public static String R12_COIN = "-Какая большая и красивая монета!..Возьму себе";
    public static String R13_WARDROBE_0 = "-Фу-у!..Ну и запах в этом шкафу!";
    public static String R13_WARDROBE_1 = "-Какие-то цифры...Что бы это значило?...";
    public static String R13_LID = "Найдена крышка от какого- то предмета...";
    public static String R14_BATTERY = "В тайнике дома вы обнаружили аккумулятор";
    public static String R14_MAP_HOVER = "-Какая-то карта... Ничего     не понимаю...";
    public static String R3_BIG_KEY_FITS = "Большой ключ подошел... Дверь открылась";
    public static String R0_KEY_OPENS = "Теперь дверь открыта. Чтобы войти,нажми \"5\"";
    public static String R16_ROPE_ON_BEAM = "Снова голос: -Будешь вешаться - возьми мыло!..";
    public static String R5_GREEN_KEY_FITS = "Так вот откуда зеленый ключ.Дверь открылась";
    public static String R8_SWAP_HAMMER = "Классно я придумала заменить молоток хренью!";
    public static String R14_HAMMER_WALL = "Ого!..Сильна малышка!Так и дом развалить можно";
    public static String R6_SMALL_KEY_FITS = "Дверь со скрипом открылась";
    public static String R7_HANDLE_FITS = "Вы приделали ручку,и потянули дверь на себя...";
    public static String R11_LID_FITS = "-Ловко я сообразила!..И снова секреты...";
    public static String R13_BOOKS_FIT = "-Упс..Поставила книги и ящик открылся...";
    public static String R13_SECRET = "А дом-то с секретом...Не нравится мне это";
    public static String R15_BATTERY_IN = "Компьютер включился,но запустить не удалось";
    public static String R15_COIN_IN = "Компьютер проглотил монету и вы вошли на SEclub";

    /** GameScene: ключ из-под телефона (2-е нажатие, d4). CN: 找到一把钥匙. */
    public static String R0_PHONE_KEY_TAKEN = "Ключ!..    Интересно,от чего он?";

    // ==================================================================
    // Имена предметов: if(int)/j() — дословно из RU b.java.
    // CN-соответствия:色子钥匙=кубик-ключ, 书房钥匙=ключ от кабинета и т.д.
    // ==================================================================
    public static String ITEM_1 = "Крюк";
    public static String ITEM_2 = "Используйте клавишу \"5\".";
    public static String ITEM_3 = "Большой ключ";
    public static String ITEM_4 = "Маленький ключ";
    public static String ITEM_5 = "Книги";
    public static String ITEM_6 = "Крышка";
    public static String ITEM_7 = "Хреновина";
    public static String ITEM_8 = "Ручка от крышки";
    public static String ITEM_9 = "Монета";
    public static String ITEM_10 = "Зеленый ключ";
    public static String ITEM_11 = "Молоток";
    public static String ITEM_12 = "Аккумулятор";
    public static String ITEM_13 = "Веревка";
    public static String ITEM_14 = "Золотой ключик";
    public static String ITEM_SELECT_PROMPT = "Пожалуйста, выберите пункты.";

    // ==================================================================
    // ЭТАП 5. UI выбора пака на экране настроек (дополнение порта —
    // в обоих оригиналах этого экрана не было). Значения одинаковы
    // для паков (chrome порта), но тоже живут в JSON.
    // ==================================================================
    /** Строка 1: авторский перевод с китайского. */
    public static String TR_AUTH = "Авторский";
    /** Строка 2: вольное повествование локализатора V.P.K. */
    public static String TR_VPK = "Локализаторский";
    /** Серая подсказка внизу экрана настроек. */
    public static String SETTINGS_HINT = "2/8: звук, 4/6: перевод";
    /** Заголовок группы перевода. */
    public static String SETTINGS_TR_TITLE = "Перевод:";

    // ------------------------------------------------------------------
    // Служебное: текущий пак + reload()
    // ------------------------------------------------------------------
    private static String pack = PACK_VPK;

    /** Вшитые значения (снимок полей на момент загрузки класса). */
    private static final Map<String, Object> DEFAULTS = new HashMap<>();

    static {
        snapshotInto(DEFAULTS);
        reload(Settings.get("textpack", PACK_VPK));
    }

    /** Идентификатор активного пака ("ru-vpk" / "ru-auth"). */
    public static String pack() {
        return pack;
    }

    /**
     * Переключить пак: сброс к вшитым значениям VPK, затем наложение
     * text/<packId>.json. Незнакомый packId = только вшитые значения.
     */
    public static void reload(String packId) {
        if (packId == null || packId.isEmpty()) {
            packId = PACK_VPK;
        }
        applyAll(DEFAULTS);
        Map<String, Object> overlay = TextDb.load("text/" + packId + ".json");
        if (overlay != null) {
            applyAll(overlay);
        }
        pack = packId;
    }

    /** Снимок ТЕКУЩИХ значений (для тестов и отладки). */
    static Map<String, Object> snapshot() {
        Map<String, Object> m = new HashMap<>();
        snapshotInto(m);
        return m;
    }

    private static void snapshotInto(Map<String, Object> dst) {
        try {
            for (Field f : textFields()) {
                dst.put(f.getName(), f.get(null));
            }
        } catch (IllegalAccessException never) {
            throw new IllegalStateException(never);
        }
    }

    private static void applyAll(Map<String, Object> src) {
        try {
            for (Field f : textFields()) {
                Object v = src.get(f.getName());
                if (v == null) {
                    continue;
                }
                Class<?> t = f.getType();
                if (t == String.class) {
                    f.set(null, v.toString());
                } else if (t == int.class) {
                    f.setInt(null, ((Number) v).intValue());
                } else if (t == String[].class) {
                    f.set(null, toStringArray(v));
                }
            }
        } catch (IllegalAccessException never) {
            throw new IllegalStateException(never);
        }
    }

    private static String[] toStringArray(Object v) {
        if (v instanceof String[]) {
            return (String[]) v;
        }
        if (v instanceof Object[]) {
            Object[] a = (Object[]) v;
            String[] r = new String[a.length];
            for (int i = 0; i < a.length; i++) {
                r[i] = String.valueOf(a[i]);
            }
            return r;
        }
        return new String[]{v.toString()};
    }

    private static Field[] cache;

    /** Текстовые поля: static, не final, не «pack»; String / int / String[]. */
    private static Field[] textFields() {
        if (cache == null) {
            Field[] all = Texts.class.getDeclaredFields();
            int n = 0;
            for (Field f : all) {
                if (isTextField(f)) {
                    n++;
                }
            }
            Field[] out = new Field[n];
            int i = 0;
            for (Field f : all) {
                if (isTextField(f)) {
                    f.setAccessible(true);
                    out[i++] = f;
                }
            }
            cache = out;
        }
        return cache;
    }

    private static boolean isTextField(Field f) {
        int m = f.getModifiers();
        if (!Modifier.isStatic(m) || Modifier.isFinal(m) || "pack".equals(f.getName())) {
            return false;
        }
        Class<?> t = f.getType();
        return t == String.class || t == int.class || t == String[].class;
    }
}
