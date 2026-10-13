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
}
