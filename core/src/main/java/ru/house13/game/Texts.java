package ru.house13.game;

/**
 * Строки русской локализации для экранов этапа 3 (взяты ДОСЛОВНО из
 * декомпиляции RU b.java: case() — помощь, goto() — автор, b() — звук,
 * g() — настройка звука в игре, int() — информация).
 *
 * ЭТАП 5 (вычитанный перевод CN->RU) перепишет этот класс целиком.
 */
public final class Texts {

    private Texts() {
    }

    /** b.case(): экран «ПОМОЩЬ» главного меню (menuStatus_ = 3). */
    public static final String HELP =
        "          ПОМОЩЬ \n Перед  вами  классичес  кая игра/квест о загадочном приключении девуш ки Шизуко в чужом  странном доме.Помогите бедняжке с честью избавить  ся от кошмаров и ужасов злоключений."
        + "\n        УПРАВЛЕНИЕ \n"
        + "\"2\",\"4\",\"6\",\"8\"- перемещение по дому \n"
        + "\"5\"- выбор действия \n "
        + "\"1\"- перечень предметов для использования \n"
        + "Правый \"soft\"-  вызов     меню игры \n"
        + "Левый \"soft\"- отмена действий \n"
        + "В   ИГРЕ  ОТСУТСТВУЕТ        СОХРАНЕНИЕ \n";

    /** Ширина переноса помощи: b.case() — a(var1, d1 - 20, dD). */
    public static final int HELP_WRAP = 240 - 20;

    /** b.case(): пейджер for(eh, 10, 30, dA, 11, dD). */
    public static final int HELP_LEFT = 10, HELP_TOP = 30, HELP_ROWS = 11;

    /** b.goto(): экран «АВТОР» (menuStatus_ = 1). */
    public static final String ABOUT =
        "  Название игры: \n     \"Дом №13\" \n"
        + "         v1.0 \n   Перевод V.P.K \n        E-mail: \n vova2const@mail.ru \n "
        + "    Разработчик: \n    Vendor, Китай \n    E-mail: game \n  0311kf@126.com";

    /** b.goto(): a(var1, d1 - 50, dD); пейджер for(eh, 28, 80, 0, V=8, dD). */
    public static final int ABOUT_WRAP = 240 - 50;
    public static final int ABOUT_LEFT = 28, ABOUT_TOP = 80, ABOUT_ROWS = 8;

    /** b()/g(): строки экрана звука. Двойной пробел после двоеточия — как в оригинале. */
    public static final String SOUND_ON = "Звук:  ДА";
    public static final String SOUND_OFF = "Звук:  НЕТ";

    /** b(): позиции строк экрана звука: cm/2-18 и cm/2+8. */
    public static final int SOUND_ON_Y = 320 / 2 - 18;
    public static final int SOUND_OFF_Y = 320 / 2 + 8;
    /** b()/g(): указатель shou на 68+32 правее текста. */
    public static final int SOUND_SHOU_DX = 68 + 32;

    /** b.g(): оверлей «Настройка звука» в игре (isMusic). */
    public static final String MUSIC_TITLE = "Настройка звука";
    public static final String MUSIC_UNDERLINE = "____________";

    /** b.int(): оверлей «ИНФОРМАЦИЯ» в игре (isHelp). di = высота шрифта. */
    public static final String INFO_TITLE = " ИНФОРМАЦИЯ";
    public static final String[] INFO_LINES = {
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
    public static final String INTRO_STORY =
        "Меня зовут Шизуко.4 года назад я закончила школу благородных девиц и устроилась работать на черкизовский рынок.Вчера утром мне позвонили друзья и пригласили на отдых.Я нехотя согласилась,и вечером,в пятницу 13-го,мы   выехали.По дороге у нас сломалась машина и мы  заблудились в лесу.В полночь мы вышли к одному странному    неприветливому дому...";
    public static final int INTRO_WRAP = 240 - 16;

    /**
     * b.m(): «строки-призраки» старой кодовой базы (Эмма/меч/скала/дворец —
     * остатки 古宝魅影, машинный перевод). В RU НЕДОСТИЖИМЫ: счётчик cR
     * нигде не инкрементируется (остаётся cm-10=310 -> всегда первая,
     * пустая полоса) — сохранены как документация артефакта.
     */
    public static final String[] GHOST_STRINGS = {
        "Эмма камня, как в передней части, выше, крэк,",
        "Достаточно быть мечом в трещины, как меч? Трудные",
        "Дорожная она имеет ничего общего с этой скалы, Эмма будет на меч",
        "Добавлена в трещины после громкий шум, открыл дверь дворца ... ..."
    };

    /** b.try() (zhongtu): подсказка первой комнаты — в RU НИКОГДА НЕ ВЫЗЫВАЕТСЯ. */
    public static final String FIRST_ROOM_HINT = "Как я могу покинуть эту комнату? ";

    /** b.do() (kongzhiren): сцена пробуждения — в RU автопропуск (bE=41). */
    public static final String[] WAKEUP_TEXTS = {"Ах...", "Господи...", "Больно..."};

    /** b.A() (kaishichu): монолог пробуждения. */
    public static final String WAKEUP_0 = "-Где я?..Ох,как болит голова...Ничего не помню...";
    public static final String WAKEUP_1 = "-Где все?.Почему так тихо?.Ау-у!Что делать?";
    public static final String WAKEUP_2 = "Дверь закрыта.Помню,мы вошли в дом.Удар.Темнота";
    public static final String WAKEUP_2_X = "Я не понимаю,что происходит? Чей это дом?!.";

    /** b.e() (jieshuh): финальные титры. Формат a(var1, d1-16, dD). */
    public static final String ENDING =
        "-Наконец-то!..Свобода! Но что это?!.Я не могу выйти!...Какая-то неведомая сила держит и не отпускает меня!..Я снова услышала голос...Жуткий неприятный смех...Я боялась оглянуться,но с ужасом понимала- Он приближается!..Он тут,рядом,за моей спиной!.Я не хочу умирать!..";
    public static final int ENDING_WRAP = 240 - 16;

    /** Каскад byte(): неверное использование предмета. */
    public static final String WRONG_USE = "Неправильное использование!";

    /** Сейф (try(int)). */
    public static final String SAFE_WRONG = "PIN-код неверный";
    public static final String SAFE_OPEN = "На дне пустого сейфа лежал маленький ключик";

    /** b.try(int): сообщения хот-зонов (в порядке появления в коде). */
    public static final String R0_DOOR_HINT_KEY = "Чтобы открыть,нажми \"1\" и выбери ключ";
    public static final String R0_DOOR_LOCKED = "-Дверь закрыта!..И как я отсюда выйду?";
    public static final String MIND_CONTROL = "-У меня такое чувство,что мной кто-то управляет";
    public static final String R8_HAMMER_STUCK = "Ура!..Молоток! Я бы взяла,но он подпирает тумбу";
    public static final String R1_KEY_TAKEN = "Я взяла большой ключ.";
    public static final String R1_KEY_GONE = "-Ого!..Сколько тараканов?!И ни одного сухарика";
    public static final String R10_NOTHING = "Ничего интересного";
    public static final String R10_TOILET = "Вот люди!..Посрать - посрали,а смывать кто будет?";
    public static final String R11_BOXES = "Что за старые ящики?Всюду пыль,один без крышки";
    public static final String R9_LIBRARY = "Странная библиотека...  Одна медицина...";
    public static final String R13_BOOKS_0 = "-Книги...\"Большая медицинская энциклопедия\".";
    public static final String R13_BOOKS_1 = "Тут не хватает несколько     томов...";
    public static final String R13_BOOKS_2 = "На книгах нет пыли...Ими  иногда пользуются";
    public static final String R13_BOOKS_3 = "Интересно,каких книг не хватает?..";
    public static final String R0_PHONE_HOVER = "-Интересно,что в шкафу?..Чтобы открыть,нажми\"5\"";
    public static final String R0_PHONE_KEY_HOVER = "-Ух,ты!Ключ!.. Чтобы         получить,нажми \"5\"";
    public static final String R0_BOTTOM_DOOR = "Дверь закрыта... ";
    public static final String R1_SAFE_KEY = "-Хм...Еще один ключ...Пожалуй возьму, пригодится";
    public static final String R5_DOOR_0 = "-Закрыто...Тихо! По-моему там кто-то есть... ";
    public static final String R5_DOOR_1 = "Я четко слышала шорох...Ой,   мне страшно!..";
    public static final String R3_NEED_KEY = "-Упс...Дверь закрыта...Нужен ключ";
    public static final String R6_DOOR_COLD = "Дверь закрыта...Из-под двери повеяло холодом";
    public static final String R7_NO_HANDLE_0 = "Открыть невозможно.У двери нет ручки...";
    public static final String R7_NO_HANDLE_1 = "Нужна ручка.Любая.Дверная,от крышки,коробки";
    public static final String R10_GREEN_KEY = "-А вот еще один ключ...Кто их тут оставил?";
    public static final String R8_WATCHED = "Дверь закрыта...-По-моему за мной наблюдают";
    public static final String R17_VOICE = "Тот же голос: -Ты всеравно отсюда не выйдешь!";
    public static final String R16_ANGRY = "Сердитый  голос: -Хватит ломать дом, дура!!!";
    public static final String R16_ROPE = "-Господи,мыло и веревка!.Лучше возьму веревку";
    public static final String R9_BOOKS_TAKEN = "-А вот и книги!..\"Анатомия человеческого тела\"...";
    public static final String R11_WHAT_IS_IT = "Что за хрень?И куда её?Возьму,может пригодиться";
    public static final String R11_BREAK_HANDLE = "Вы решили отломать ручку от крышки";
    public static final String R12_COIN = "-Какая большая и красивая монета!..Возьму себе";
    public static final String R13_WARDROBE_0 = "-Фу-у!..Ну и запах в этом шкафу!";
    public static final String R13_WARDROBE_1 = "-Какие-то цифры...Что бы это значило?...";
    public static final String R13_LID = "Найдена крышка от какого- то предмета...";
    public static final String R14_BATTERY = "В тайнике дома вы обнаружили аккумулятор";
    public static final String R14_MAP_HOVER = "-Какая-то карта... Ничего     не понимаю...";
    public static final String R3_BIG_KEY_FITS = "Большой ключ подошел... Дверь открылась";
    public static final String R0_KEY_OPENS = "Теперь дверь открыта. Чтобы войти,нажми \"5\"";
    public static final String R16_ROPE_ON_BEAM = "Снова голос: -Будешь вешаться - возьми мыло!..";
    public static final String R5_GREEN_KEY_FITS = "Так вот откуда зеленый ключ.Дверь открылась";
    public static final String R8_SWAP_HAMMER = "Классно я придумала заменить молоток хренью!";
    public static final String R14_HAMMER_WALL = "Ого!..Сильна малышка!Так и дом развалить можно";
    public static final String R6_SMALL_KEY_FITS = "Дверь со скрипом открылась";
    public static final String R7_HANDLE_FITS = "Вы приделали ручку,и потянули дверь на себя...";
    public static final String R11_LID_FITS = "-Ловко я сообразила!..И снова секреты...";
    public static final String R13_BOOKS_FIT = "-Упс..Поставила книги и ящик открылся...";
    public static final String R13_SECRET = "А дом-то с секретом...Не нравится мне это";
    public static final String R15_BATTERY_IN = "Компьютер включился,но запустить не удалось";
    public static final String R15_COIN_IN = "Компьютер проглотил монету и вы вошли на SEclub";
}
