package ru.house13.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.house13.engine.Assets;
import ru.house13.engine.Fonts;
import ru.house13.engine.KeyMap;
import ru.house13.engine.KeyMap.J2;
import ru.house13.engine.Settings;
import ru.house13.engine.SoundManager;

/**
 * Игровой экран (drawStatus_ = 2). Порт RU else()/byte()/w()/try(int)/A()/e().
 *
 * СТРУКТУРА ОРИГИНАЛА (b.java), перенесённая дословно:
 *
 *  else() = два режима по preDisplayed (goto):
 *   - ИНТРО: m() [чёрный экран; полосы-«призраки» недостижимы — cR не растёт]
 *     -> h() прокрутка истории (z += 2/тик, текст + картинка LOGO/menuback,
 *     подсказка fanhui; z>=280 -> skipState) -> do() [автопропуск RU: bE=41,
 *     тексты «Ах/Господи/Больно» недостижимы] + v() «загрузочная» полоса
 *     (jindu/jindu1, чёрные шторки dd/c3 56->115) -> игра.
 *     СКИП: FIRE/левый софт/'5' в интро = ветка [B] byte() (z=500, bE=41).
 *   - ИГРА: w() [фон a() + объекты u() + монолог A() + стрелки q() + сейф s()
 *     + титры e()] -> void() [long(): движение + жалюзи + полосы null() +
 *     карта] -> инвентарь j() -> пауза i() -> окна сообщений bf/m.
 *
 *  ПРОБУЖДЕНИЕ (A()/жалюзи/стрелки): dp (bianl) 0 -> "-Где я?" ('5') ->
 *   1 -> "-Где все?" ('5') -> 2 [d2 0..9 (c6=true), dH 0..3 (c6=false),
 *   финальная реплика; '5' -> dX; '5' -> c8] -> ('5') 3 = ЖАЛЮЗИ (8 полос
 *   a4..aV по 40px открываются каскадом +3/тик, рука прижата к центру) ->
 *   cl=true -> стрелки обучения q() (bU 0..20, мигание) -> c6=true ->
 *   управление разблокировано. Синтетика paint(): при dp==2 каждый тик
 *   вводится нажатие «вниз» (keyPressed(56)) — рука «оседает» вниз.
 *
 *  Каскад byte(int) — по одному событию: [A] goto||«не FIRE/LS/5» ->
 *   [A2] концовка (любая клавиша -> меню) -> [A3] игра:
 *   1) FIRE -> '5' (без оверлеев);
 *   2) [W] (не LS/FIRE || нет оверлеев):
 *      a) правый софт закрывает сейф; б) dp-advance при !c6;
 *      в) [P] пауза (правый софт/FIRE при c6) ИЛИ:
 *         диалог k закрытие RS, меню пауза, оверлей звука,
 *         гашение wrong + очистка bf/m + гашение get/correct,
 *         инвентарь if(int) ИЛИ { wrong-использование, '1' инвентарь,
 *         try(int) хот-зоны + case(int) движение };
 *      г) LS/FIRE в оверлее инфо/звука -> назад в паузу.
 *   [B] иначе (FIRE/LS/'5' в интро) — скип интро.
 *
 * ЗВУК (этап 4, реставрация): get.mid при CreateTool, wrong.mid при
 * «Неправильное использование!», correct.mid при успехах пазлов и коде
 * сейфа. Каскад гасит каналы 1-3 при каждом нажатии — как задумано
 * в оригинале (вызовы changeClosedMid(1..3) там есть, файлов не было).
 *
 * Мёртвые поля RU, НЕ переносимые (только записи, чтений нет): S, ed
 * (анимации дверей), ck (toolNums — switch при открытии инвентаря всегда
 * default), dn, a6, am, cQ-инкремент, cp, ds, at/ct/cs/q (флаги стрелок).
 * Кросс-сценовый пейджер dA (help/автор/интро/титры делили один индекс в
 * оригинале) в архитектуре сцен порта не переносится — dA обнуляется.
 */
public final class GameScene implements Scene {

    private final GameHost host;
    final RoomScene room;    // package-private: доступ из тестов
    final Inventory inv;
    final Safe safe;
    final SafeKeypad keypad = new SafeKeypad(); // экранная клавиатура сейфа (этап 5b)
    boolean fast;            // Shift удержан — рука x2 (дополнение порта)

    // ---- сцена/оверлеи (этап 3)
    boolean pause;           // aR (canExit)
    boolean info;            // du (isHelp)
    boolean musicOverlay;    // H  (isMusic)
    int pauseItem;           // o  (GAME_CURRENT)

    // ---- интро
    boolean preDisplayed;    // goto: false = интро
    boolean skipState;       // byte: история прокручена/скипнута
    int z = 1;               // счётчик прокрутки истории (h)
    int dd = 56, c3 = 56;    // счётчики шторок полосы (v)

    // ---- пробуждение
    int dp;                  // bianl 0..3 (3 = жалюзи)
    int d2, dH;              // таймеры фазы 2 (A)
    boolean dX;              // wzdh: «Что происходит?» показано
    boolean c8;              // монолог можно завершить
    final int[] blinds = new int[8]; // a4,a3,a2,a1,a0,aX,aW,aV
    boolean cK;              // жалюзи открыты
    boolean cl;              // kaishigd: пробуждение окончено
    boolean c6;              // zong: движение/пауза/инвентарь разрешены
    int bU;                  // счётчик мигания стрелок (q)

    // ---- сообщения
    String bf = "";          // stringForShow
    String m = "";           // toolStringToshow
    boolean kFlag;           // k (guanzi): диалог перекрывает хот-зоны
    int n;                   // shuodi: строка многострочного диалога
    final DialogBox dialog = new DialogBox(); // бокс порта (этап 5c)

    // ---- флаги комнат (try(int)/u()); имена — из RU b.java
    boolean l;               // дверь спальни отперта
    boolean b1, b2;          // ком.1 посещена; тумба открыта (u)
    boolean ea;              // большой ключ взят (ком.1)
    boolean w;               // ключ из сейфа взят
    boolean dB;              // сейф открыт (код 9819)
    boolean d4, cE;          // ключ из-под телефона взят; тумба нажата
    int E;                   // счётчик нажатий тумбы (ком.0)
    boolean bd;              // дверь спальни отперта ключом
    boolean v;               // телефонный ключ взят (ховер-гейт)
    boolean dj;              // дверь ком.3 отперта (большой ключ)
    boolean aG;              // стена ком.3 пробита молотком
    boolean dF;              // дверь ком.5 отперта (зелёный ключ)
    boolean ei;              // дверь ком.6 отперта (маленький ключ)
    boolean aS;              // ручка приделана (ком.7)
    boolean ba;              // SEclub (монета в ПК) — дверь ком.16
    boolean Y;               // книги взяты (ком.9)
    boolean a5;              // зелёный ключ взят (ком.10)
    boolean I, cd, R;        // ящик открыт; хреновина взята; крышка на ящике
    boolean P, dC;           // машина нажата; монета взята (ком.12)
    int dR;                  // счётчик нажатий (ком.12)
    boolean wardrobe;        // case: шкаф открыт (ком.13)
    boolean bg, c7;          // ящик ком.13 открыт; крышка взята
    boolean dl, d3;          // книги поставлены; секрет замечен
    boolean cj, cg;          // «злой голос»; верёвка на балке (ком.16)
    boolean ch, cf;          // полка нажата; верёвка взята
    int dr;                  // счётчик нажатий (ком.16)
    boolean W;               // золотой ключик взят (ком.17)
    boolean cr, aL;          // аккумулятор взят; стена ком.14 пробита
    boolean b5, pcOn;        // тумблер ПК; ПК запитан (do)
    boolean by, ai;          // стол карты нажат; карта показана
    int bw;                  // тумблер карты 0/1
    boolean c2;              // молоток заменён хреновиной (ком.8)

    // ---- титры
    int A;                   // счётчик прокрутки титров (e)
    private String[] introLines;   // j (format(INTRO_STORY))
    private String introPackStamp;  // пак, из которого собран introLines (ЭТАП 5)
    private String[] endingLines;  // i (format(ENDING))
    private String endingPackStamp; // пак, из которого собран endingLines (ЭТАП 5)
    private int dA;          // пейджер строк (общий для интро/титров — как b.dA)
    private boolean syntheticDown; // отложенная синтетическая «8» (paint, dp==2)

    public GameScene(GameHost host) {
        this.host = host;
        this.room = new RoomScene(host.assets());
        this.inv = new Inventory(this);
        this.safe = new Safe(this, inv);
        // c() (gameIni): goto=false (интро), счётчики, массивы уже нулевые
        // Отладка -Dhouse13.room=N: старт сразу в комнате (пост-скип состояние)
        if (System.getProperty("house13.room") != null) {
            skipState = true;
            preDisplayed = true;
            dp = 3;
            cl = true;
            c6 = true;
            cK = true;
            z = 500;
            dd = 113;
            c3 = 113;
        }
        // Отладка -Dhouse13.ending=true: сразу титры (aQ[15]=10)
        if ("true".equalsIgnoreCase(System.getProperty("house13.ending"))) {
            skipState = true;
            preDisplayed = true;
            dp = 3;
            cl = true;
            c6 = true;
            cK = true;
            z = 500;
            dd = 113;
            c3 = 113;
            inv.aQ[15] = 10;
        }
    }

    /** Пост-скип состояние (для тестов паузы/оверлеев; = состоянию после
     *  интро, пробуждения и стрелок — как считал этап 3). */
    static GameScene postSkip(GameHost host) {
        GameScene g = new GameScene(host);
        g.skipState = true;
        g.preDisplayed = true;
        g.dp = 3;
        g.cl = true;
        g.c6 = true;
        g.cK = true;   // жалюзи завершены — рука не прижата к центру
        g.z = 500;
        g.dd = 113;
        g.c3 = 113;
        return g;
    }

    /** Интро пройдено, монолог пробуждения НЕ начат (для тестов A()/жалюзи). */
    static GameScene postSkipIntroOnly(GameHost host) {
        GameScene g = new GameScene(host);
        g.skipState = true;
        g.preDisplayed = true;
        g.z = 500;
        g.dd = 113;
        g.c3 = 113;
        return g;
    }

    // ==================================================================
    // ТИК
    // ==================================================================

    @Override
    public void tick(int[] gdxKeyDowns, int[] gdxKeyUps) {
        // Синтетическая клавиша кадра N (paint(): if (dp==2) keyPressed(56)) —
        // срабатывает ПОСЛЕ отрисовки кадра N, т.е. в начале следующего тика
        // (иначе монолог A() стирался бы до рендера и был бы невидим).
        if (syntheticDown) {
            syntheticDown = false;
            handleKey(J2.DOWN);
        }
        // Нажатия раньше отпусканий (press предшествует release — J2ME-порядок).
        // Shift — модификатор порта (ускорение x2): не доходит до каскада,
        // и его отпускание НЕ сбрасывает движение (в отличие от J2ME-клавиш).
        boolean nonShiftUp = false;
        for (int key : gdxKeyDowns) {
            if (KeyMap.isShift(key)) {
                fast = true;
                continue;
            }
            if (isDebugKey(key)) {
                room.debugKeyDown(key);
            } else {
                handleKey(KeyMap.map(key));
            }
        }
        for (int key : gdxKeyUps) {
            if (KeyMap.isShift(key)) {
                fast = false;
            } else {
                nonShiftUp = true;
            }
        }
        if (nonShiftUp) {
            room.releaseAll();
        }
        frame();                       // else() при paint 10 Гц
        syntheticDown = dp == 2;       // хвост paint()
    }

    private static boolean isDebugKey(int gdxKey) {
        return gdxKey == com.badlogic.gdx.Input.Keys.PAGE_UP
                || gdxKey == com.badlogic.gdx.Input.Keys.PAGE_DOWN
                || gdxKey == com.badlogic.gdx.Input.Keys.F10;
    }

    /** Логика кадра paint() -> else(). */
    private void frame() {
        if (!preDisplayed) {
            introFrame();
            return;
        }
        // ---- w(): g=true, dz=true (гейты, всегда true в игре)
        if (room.room == 1) {
            b1 = true;               // u(): ком.1 посещена
        }
        if (room.room == 0 && cE) {
            b2 = true;               // u(): тумба открыта
        }
        if (!cl) {
            wakeUpFrame();             // A()
        }
        if (cl && !c6) {
            arrowsFrame();             // q(): счётчик bU
        }
        hoverHints();                  // u(): ховер-подсказки (ком.0/14)
        if (!bf.isEmpty()) {
            dialog.tick();             // этап 5c: печатная машинка
        }
        if (inv.aQ[15] == 10) {
            creditsFrame();            // e()
        }
        // ---- void(): long() только при закрытом инвентаре и без паузы.
        // Порядок long(): шаг движения -> рисование -> жалюзи (пин руки
        // ПОСЛЕ шага — как в оригинале, pin побеждает)
        if (!inv.showTool && !pause) {
            room.tick(inv.usingTool, fast);
            if (dp == 3) {
                blindsFrame();         // жалюзи в long() при bianl==3
            }
        }
    }

    /** Интро-кадр: m() + h()/do()+v() (else/!goto). */
    private void introFrame() {
        // m(): чёрная заливка (рендер); полосы-призраки cR недостижимы
        if (!skipState) {
            // h(): прокрутка истории
            if (z < 800) {
                z += 2;
            }
            if (z >= 280) {
                skipState = true;      // else(): порог 280 (RU), не 800
            }
        } else {
            // do(): автопропуск RU (bE=41 — тексты не показываются)
            // v(): шторки «загрузочной» полосы
            if (dd < 115) {
                dd++;
                c3++;
            }
            if (dd == 115) {
                preDisplayed = true;
                dd = 113;
                c3 = 113;
            }
        }
    }

    /** A() (kaishichu): монолог пробуждения, вызывается каждый кадр при !cl. */
    private void wakeUpFrame() {
        switch (dp) {
            case 0:
                bf = Texts.WAKEUP_0;
                break;
            case 1:
                bf = Texts.WAKEUP_1;
                break;
            case 2:
                if (d2 != 9) {
                    d2++;
                    c6 = true;
                }
                if (d2 == 9) {
                    if (dH != 3) {
                        dH++;
                        c6 = false;
                    }
                    if (dH == 3) {
                        if (!dX) {
                            bf = Texts.WAKEUP_2;
                        } else {
                            bf = Texts.WAKEUP_2_X;
                            c8 = true;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    /** q() (jiantoud): счётчик стрелок обучения (рисование — в рендере). */
    private void arrowsFrame() {
        bU++;
        if (bU / 4 % 2 == 0 && bU > 20) {
            c6 = true;
            bU = 20;
        }
    }

    /** Жалюзи (long() при dp==3): 8 полос открываются каскадом. */
    private void blindsFrame() {
        if (blinds[0] < 40) blinds[0] += 3;                       // a4
        if (blinds[0] >= 4 && blinds[1] < 40) blinds[1] += 3;     // a3
        if (blinds[1] >= 5 && blinds[2] < 40) blinds[2] += 3;     // a2
        if (blinds[2] >= 4 && blinds[3] < 40) blinds[3] += 3;     // a1
        if (blinds[3] >= 5 && blinds[4] < 40) blinds[4] += 3;     // a0
        if (blinds[4] >= 4 && blinds[5] < 40) blinds[5] += 3;     // aX
        if (blinds[5] >= 5 && blinds[6] < 40) blinds[6] += 3;     // aW
        if (blinds[6] >= 4 && blinds[7] < 40) blinds[7] += 3;     // aV
        if (!cK) {
            // рука прижата к центру (152, 240), кадр 0
            room.hand.j = (240 - Hand.FRAME_W) / 2 + 40 + 3;
            room.hand.i = 320 - 80;
            room.hand.frame = 0;
        }
        if (blinds[7] >= 40) {
            cK = true;
            cl = true;
        }
    }

    /** e() (jieshuh): титры (логика; рисование в рендере). */
    private void creditsFrame() {
        room.hand.visible = false; // a8.try=false; оригинал НЕ восстанавливает
        A++;
        if (A % 20 == 0) {
            A += 2;    // ускорение каждые 20 тиков (RU: ++A + A+=2)
        }
        if (A > 270) {
            A = 0;
            exitToMenu();
        }
    }

    /** u(): ховер-подсказки (ком.0 тумба, ком.14 стол с картой). */
    private void hoverHints() {
        int j = room.hand.j;
        int i = room.hand.i;
        if (room.room == 0 && j > 47 && j < 67 && i > 175 && i < 185
                && !b2 && room.hand.dirUp) {
            bf = Texts.R0_PHONE_HOVER;
        }
        if (room.room == 0 && j > 47 && j < 67 && i > 175 && i < 185
                && b2 && !v && room.hand.dirUp) {
            bf = Texts.R0_PHONE_KEY_HOVER;
        }
        if (room.room == 14 && j > 98 && j < 118 && i > 208 && i < 218
                && !ai && room.hand.dirUp && !by && !inv.usingTool) {
            bf = Texts.R14_MAP_HOVER;
        }
    }

    private void exitToMenu() {
        host.setScene(new MenuScene(host)); // if() (FreeGameSourse) + b0=1, bO=0
    }

    /** Доступ подсистем (Inventory/Safe) к общим сервисам — как в монолите b. */
    SoundManager sound() {
        return host.sound();
    }

    // ==================================================================
    // КАСКАД КЛАВИШ byte(int)
    // ==================================================================

    /** keyPressed -> byte(var1) — один вызов на одно событие клавиши. */
    void handleKey(J2 k) {
        // [A] goto || ee(мёртв) || клавиша не из {FIRE, левый софт, '5'}
        boolean bKey = k == J2.FIRE || k == J2.SOFT_LEFT || k == J2.NUM_5;
        if (preDisplayed || !bKey) {
            // [A1] ветка ee недостижима (cR не растёт в RU)
            // [A2] концовка: любая клавиша -> меню
            if (inv.aQ[15] == 10) {
                exitToMenu();
                return;
            }
            cascade(k);
        } else {
            // [B] скип интро: byte=true, z=500, bE=41 (do())
            skipState = true;
            z = 500;
        }
    }

    /** [A3] основной каскад игры. */
    private void cascade(J2 k) {
        // ЭТАП 5b: экранная клавиатура сейфа. Стрелки двигают пульсирующий
        // курсор (поглощаются), OK нажимает клетку — доставляя ОРИГИНАЛЬНУЮ
        // клавишу ('1'..'9'/'0', '5'-подтверждение, правый софт-закрытие) в
        // нижележащий каскад. Safe (s()/try-хвост) не меняется; цифры и
        // софты проходят напрямую (быстрый путь с физической клавиатуры).
        k = keypad.map(k, safe.open);
        if (k == null) {
            return;                    // стрелка съедена курсором клавиатуры
        }
        // ЭТАП 5c: подтверждение в «настоящем» диалоге (kFlag, msg()) —
        // домотать печатную машинку / перевернуть страницу и СЪЕСТЬ нажатие.
        // Ховер-подсказки и монолог пробуждения (kFlag=false) сохраняют
        // семантику оригинала: любая клавиша чистит bf, каскад продолжается.
        if (kFlag && !bf.isEmpty() && !safe.open && !pause && !info
                && !musicOverlay && !inv.showTool
                && (k == J2.FIRE || k == J2.NUM_5 || k == J2.SOFT_RIGHT)
                && dialog.confirm()) {
            return;
        }
        // 1) FIRE -> '5' (без оверлеев; byte 949)
        if (k == J2.FIRE && !pause && !info && !musicOverlay) {
            k = J2.NUM_5;
        }
        // 2) [W] (не софт-лево/FIRE) ИЛИ (нет оверлеев инфо/звук)
        if (k != J2.SOFT_LEFT && k != J2.FIRE || !info && !musicOverlay) {
            // a) правый софт закрывает сейф (byte 954: сброс aQ[8..14])
            if (k == J2.SOFT_RIGHT && safe.open) {
                safe.closeByRightSoft();
            }
            // б) монолог пробуждения (byte 967-983)
            if (!c6) {
                if (dp == 0 && (k == J2.NUM_5 || k == J2.SOFT_RIGHT)) {
                    dp = 1;
                } else if (dp == 1 && (k == J2.NUM_5 || k == J2.SOFT_RIGHT)) {
                    dp = 2;
                }
                if (!c6 && dH == 3 && (k == J2.NUM_5 || k == J2.SOFT_RIGHT)) {
                    dX = true;
                }
                if (!c6 && c8 && (k == J2.NUM_5 || k == J2.SOFT_RIGHT)) {
                    dp = 3;   // жалюзи!
                }
            }
            // в) [P] открытие паузы (byte 985); при совпадении остальное
            // каскада пропускается (else-ветка [P])
            boolean openPause = (k == J2.SOFT_RIGHT && !kFlag) || k == J2.FIRE;
            if (openPause && inv.aQ[6] != 7 && !info && !musicOverlay && c6
                    && !kFlag && !pause) {
                pause = true;
                info = false;
                musicOverlay = false;
                return;
            }
            // [P]-else (byte 989+): без ранних выходов — навигация паузы
            // и оверлеев ПРОДОЛЖАЕТ каскад (очистка bf и т.д.)
            if (k == J2.SOFT_RIGHT && kFlag) {
                kFlag = false;      // правый софт закрывает диалог
                n = 0;
            }
            if (pause) {
                // меню паузы (byte 995-1035): выбор — return, навигация — дальше
                if (k == J2.SOFT_LEFT || k == J2.FIRE || k == J2.NUM_5) {
                    selectPauseItem();
                    return;
                }
                if (k == J2.UP || k == J2.NUM_2) {
                    pauseItem = (pauseItem + 3) % 4;
                }
                if (k == J2.DOWN || k == J2.NUM_8) {
                    pauseItem = (pauseItem + 1) % 4;
                }
            }
            if (musicOverlay) {
                // переключение звука (byte 1038-1046)
                if (k == J2.UP || k == J2.NUM_2 || k == J2.DOWN || k == J2.NUM_8) {
                    SoundManager.canSound = !SoundManager.canSound;
                }
            }
            // (info: иных клавиш нет — падаем в общую часть, как в оригинале)
            // гашение wrong + очистка сообщений + гашение get/correct
            // (byte 1048-1057; в оригинале каналы 1-3 всегда «молчали» —
            // теперь это реальное отсечение джинглов)
            if (Texts.WRONG_USE.equals(bf)) {
                inv.usingTool = false;
                room.hand.frame = 0;
                host.sound().changeClosedMid(SoundManager.CH_WRONG);
            }
            bf = "";
            m = "";
            if (kFlag && !dialog.shownText().isEmpty()) {
                dialog.clear();        // бокс закрыт вместе с bf
            }
            host.sound().changeClosedMid(SoundManager.CH_GET);
            host.sound().changeClosedMid(SoundManager.CH_CORRECT);
            // инвентарь ИЛИ игра (byte 1058)
            if (inv.showTool && !pause && !info && !musicOverlay) {
                inv.handleKey(k);
            } else {
                // «Неправильное использование!» (byte 1061): '5' с предметом
                // в руке мимо хот-зона (реставрация: wrong.mid)
                if (room.hand.frame == 0 && k == J2.NUM_5 && inv.usingTool) {
                    bf = Texts.WRONG_USE;
                    host.sound().playWrong();
                } else {
                    bf = "";
                }
                // '1' открывает инвентарь (byte 1067; switch(ck) мёртв)
                if (k == J2.NUM_1 && !pause && !info && !musicOverlay
                        && inv.aQ[6] < 7 && !safe.open && c6) {
                    inv.showTool = true;
                }
                // хот-зоны + движение (byte 1094)
                if (!pause && !info && !musicOverlay) {
                    hotZones(k);
                    boolean accepted = false;
                    if (!safe.open && c6) {          // case(): !try, g, c6
                        accepted = room.handleKey(k);
                    }
                    if (accepted) {
                        inv.usingTool = false;       // cP=false в case()
                        kFlag = false;
                        n = 0;
                        if (room.room == 13 && dl) {
                            d3 = true;               // case(): ком.13
                        }
                    }
                }
                if (Texts.WRONG_USE.equals(bf)) {
                    inv.usingTool = false;
                    room.hand.frame = 0;
                }
            }
        } else {
            // [W]-else: левый софт/FIRE в оверлее инфо/звука -> назад в паузу
            pause = true;
            info = false;
            musicOverlay = false;
        }
    }

    /** Выбор пункта паузы (byte 996-1020): 0 продолжить, 1 инфо, 2 звук, 3 выход. */
    private void selectPauseItem() {
        switch (pauseItem) {
            case 1:
                info = true;
                break;
            case 2:
                musicOverlay = true;
                break;
            case 3:
                exitToMenu();
                return;
            default:
                break; // 0: продолжить
        }
        pause = false;
    }

    // ==================================================================
    // ХОТ-ЗОНЫ try(int) — порт b.java 3892-4711 дословно.
    // j/i — позиция руки на момент вызова; msg() = { bf = s; kFlag = true; }
    // ==================================================================

    private void msg(String s) {
        bf = s;
        kFlag = true;
    }

    /** Многострочный диалог по счётчику n (паттерн b: n==len -> сброс и k). */
    private void dialog(String[] lines) {
        for (int idx = 0; idx < lines.length; idx++) {
            if (n == idx) {
                bf = lines[idx];
            }
        }
        if (n == lines.length) {
            n = 0;
            kFlag = true;
        }
        if (n != lines.length) {
            n++;
        } else {
            n = 0;
        }
    }

    /** Переход в комнату (ce=X; рука в (j,i)). */
    private void goTo(int roomIdx, int hj, int hi) {
        room.room = roomIdx;
        room.hand.j = hj;
        room.hand.i = hi;
    }

    @SuppressWarnings("Duplicates")
    void hotZones(J2 k) {
        Hand h = room.hand;
        int j = h.j;
        int i = h.i;
        int r = room.room;
        boolean five = k == J2.NUM_5;               // var1 == 53
        boolean f0 = h.frame == 0;                  // a8.goto == 0
        boolean up = h.dirUp, down = h.dirDown, left = h.dirLeft, right = h.dirRight;

        // --- подсказки двери спальни (ком.0; y==165 ТОЧНО, b.a8.i)
        if (r == 0 && five && f0) {
            if (!inv.usingTool && h.j > 122 && h.j < 162 && h.i == 165
                    && !l && !b1 && up && d4 && !bd && !kFlag) {
                msg(Texts.R0_DOOR_HINT_KEY);
                return;
            }
            if (!inv.usingTool && h.j > 122 && h.j < 162 && h.i == 165
                    && !b1 && up && !d4 && !bd && !kFlag) {
                msg(Texts.R0_DOOR_LOCKED);
                return;
            }
        }

        // --- «кем-то управляют» (ком.12/15/17) + молоток (ком.8)
        if (r == 12 && five && f0 && j > 171 && j < 206 && i > 102 && i < 154
                && !inv.usingTool && up && !kFlag) {
            msg(Texts.MIND_CONTROL);
        } else if (r == 15 && five && f0 && j > 98 && j < 193 && i > 98 && i < 108
                && !inv.usingTool && up && !kFlag) {
            msg(Texts.MIND_CONTROL);
        } else if (r == 17 && five && f0 && j > 136 && j < 166 && i > 53 && i < 83
                && !inv.usingTool && up && !kFlag) {
            msg(Texts.MIND_CONTROL);
        } else if (r == 8 && five && f0 && j == 143 && i == 123
                && !inv.usingTool && up && !c2 && !kFlag) {
            msg(Texts.R8_HAMMER_STUCK);
        } else {
            // --- ком.1: ящик с большим ключом
            if (r == 1 && five && f0) {
                if (j > 128 && j < 168 && i > 198 && i < 213
                        && !inv.usingTool && up && !kFlag && !ea) {
                    ea = true;
                    inv.createTool("shaizi1.png", 3, 2, 25, 13);
                    msg(Texts.R1_KEY_TAKEN);
                    return;
                }
                if (j > 128 && j < 168 && i > 198 && i < 213
                        && !inv.usingTool && up && !kFlag && ea) {
                    msg(Texts.R1_KEY_GONE);
                    return;
                }
            }

            // --- ком.10 (туалет)
            if (r == 10 && five && f0) {
                if (j > 8 && j < 41 && i > 127 && i < 200
                        && !inv.usingTool && !kFlag && left) {
                    msg(Texts.R10_NOTHING);
                    return;
                }
                if (j > 103 && j < 159 && i > 130 && i < 167
                        && !inv.usingTool && !kFlag) {
                    msg(Texts.R10_NOTHING);
                    return;
                }
                if (j > 201 && j < 232 && i > 151 && i < 181
                        && !inv.usingTool && !kFlag) {
                    msg(Texts.R10_TOILET);
                    return;
                }
            }

            // --- ком.11: ящики
            if (r == 11 && five && f0 && j > 88 && j < 168 && i > 132 && i < 175
                    && !inv.usingTool && up && !kFlag) {
                msg(Texts.R11_BOXES);
            } else {
                // --- ком.9: полки библиотеки
                if (r == 9 && five && f0) {
                    if (j > 8 && j < 64 && i > 98 && i < 151
                            && !inv.usingTool && up && !kFlag) {
                        msg(Texts.R9_LIBRARY);
                        return;
                    }
                    if (j > 116 && j < 173 && i > 98 && i < 151
                            && !inv.usingTool && up && !kFlag) {
                        msg(Texts.R9_LIBRARY);
                        return;
                    }
                    if (j > 25 && j < 87 && i > 195 && i < 242
                            && !inv.usingTool && up && !kFlag) {
                        msg(Texts.R9_LIBRARY);
                        return;
                    }
                }

                // --- ком.13: книжный шкаф (многострочный диалог)
                if (r == 13 && five && f0 && j > 36 && j < 100 && i > 78 && i < 146
                        && !inv.usingTool && up && !dl && !kFlag) {
                    dialog(new String[]{Texts.R13_BOOKS_0, Texts.R13_BOOKS_1,
                            Texts.R13_BOOKS_2, Texts.R13_BOOKS_3});
                } else {
                    if (r == 0) {
                        if (five && f0) {
                            // дверь в коридор (bd = отперта ключом)
                            if (j > 122 && j < 162 && i > 0 && i < 170
                                    && !inv.usingTool && up && bd) {
                                goTo(1, 148, 278);
                            }
                            // тумба с телефоном: нажатие 1
                            if (j > 47 && j < 67 && i > 175 && i < 185 && up) {
                                cE = true;
                                E++;
                            }
                            // нажатие 2: ключ из-под телефона
                            if (j > 47 && j < 67 && i > 175 && i < 185
                                    && cE && E == 2 && !d4 && up && !kFlag) {
                                d4 = true;
                                v = true;
                                inv.createTool("shouji1.png", 2, 2, 15, 27);
                                bf = Texts.R0_PHONE_KEY_TAKEN;
                                kFlag = true;
                                E = 0;
                                return;
                            }
                            // нижняя дверь (никогда не открывается)
                            if (j > 127 && j < 172 && i > 270 && i < 320
                                    && !W && !inv.usingTool && down && !kFlag) {
                                msg(Texts.R0_BOTTOM_DOOR);
                                return;
                            }
                        }
                    }

                    if (r == 1 && five && f0 && !inv.usingTool) {
                        // дверь в туалет
                        if (j > 13 && j < 43 && i > 0 && i < 113 && up) {
                            goTo(10, 18, 278);
                        }
                        // сейф: открытие
                        if (j > 173 && j < 203 && i > 0 && i < 138 && !dB && up) {
                            safe.open = true;
                        }
                        // ключ на дне открытого сейфа
                        if (j > 173 && j < 203 && i > 0 && i < 138
                                && dB && !w && up && !kFlag) {
                            w = true;
                            inv.createTool("shu.png", 4, 2, 23, 11);
                            msg(Texts.R1_SAFE_KEY);
                            return;
                        }
                    }

                    if (r == 2 && five && f0 && j > 33 && j < 78 && i > 0 && i < 143
                            && !inv.usingTool && up) {
                        goTo(14, 193, 278);
                    }

                    if (r == 4 && five && f0 && j > 33 && j < 78 && i > 0 && i < 143
                            && !inv.usingTool && up) {
                        goTo(13, 93, 278);
                    }

                    if (r == 5 && five && f0) {
                        if (j > 33 && j < 78 && i > 0 && i < 143
                                && dF && !inv.usingTool && up) {
                            goTo(8, 58, 278);
                        }
                        if (j > 33 && j < 78 && i > 0 && i < 143
                                && !dF && !inv.usingTool && up && !kFlag) {
                            dialog(new String[]{Texts.R5_DOOR_0, Texts.R5_DOOR_1});
                            return;
                        }
                    }

                    if (r == 3 && five && f0) {
                        if (j > 33 && j < 78 && i > 0 && i < 143
                                && dj && !inv.usingTool && up) {
                            goTo(11, 133, 278);
                        } else if (j > 33 && j < 78 && i > 0 && i < 143
                                && !dj && !inv.usingTool && up && !kFlag) {
                            msg(Texts.R3_NEED_KEY);
                            return;
                        }
                        if (j > 133 && j < 168 && i > 133 && i < 143
                                && aG && !inv.usingTool && up) {
                            goTo(15, 98, 283);
                        }
                    }

                    if (r == 15 && five && f0 && j > 48 && j < 73
                            && i > 123 && i < 133 && up) {
                        b5 = true;
                    }

                    if (r == 6 && five && f0) {
                        if (j > 100 && j < 135 && i > 0 && i < 143
                                && ei && !inv.usingTool && up) {
                            goTo(9, 193, 278);
                        }
                        if (j > 100 && j < 135 && i > 0 && i < 143
                                && !ei && !inv.usingTool && up && !kFlag) {
                            msg(Texts.R6_DOOR_COLD);
                            return;
                        }
                    }

                    if (r == 7 && five && f0) {
                        if (j > 100 && j < 135 && i > 0 && i < 143
                                && aS && !inv.usingTool && up) {
                            goTo(12, 108, 278);
                            return;
                        }
                        if (j > 100 && j < 135 && i > 0 && i < 143
                                && !aS && !inv.usingTool && up && !kFlag) {
                            dialog(new String[]{Texts.R7_NO_HANDLE_0, Texts.R7_NO_HANDLE_1});
                            return;
                        }
                    }

                    if (r == 1 && five && f0 && j > 127 && j < 172 && i > 270
                            && i < 320 && !inv.usingTool && down) {
                        goTo(0, 142, 170);
                    }

                    if (r == 2 && five && f0 && j > 138 && j < 176 && i > 103
                            && i < 208 && !inv.usingTool && right) {
                        goTo(1, 3, 98);
                    }

                    if (r == 3 && five && f0 && j > 138 && j < 176 && i > 95
                            && i < 208 && !inv.usingTool && right) {
                        goTo(2, 3, 123);
                    }

                    if (r == 10 && five && f0) {
                        if (j > 0 && j < 38 && i > 270 && i < 320
                                && !inv.usingTool && down) {
                            goTo(1, 28, 113);
                        }
                        if ((j > 88 && j < 118 && i > 130 && i < 168
                                && !a5 && up && !kFlag)
                                || (j > 73 && j < 111 && i > 130 && i < 164
                                && !a5 && right && !kFlag)) {
                            a5 = true;
                            inv.createTool("ys.png", 10, 2, 23, 11);
                            msg(Texts.R10_GREEN_KEY);
                            return;
                        }
                    }

                    if (r == 8 && five && f0) {
                        if (j > 43 && j < 83 && i > 270 && i < 320
                                && !inv.usingTool && down) {
                            goTo(5, 53, 143);
                        }
                        if (j > 18 && j < 48 && i > 143 && i < 453
                                && ba && up) {
                            goTo(16, 108, 278);
                            return;
                        }
                        if (j > 18 && j < 48 && i > 143 && i < 453
                                && !ba && up && !kFlag) {
                            msg(Texts.R8_WATCHED);
                            return;
                        }
                    }

                    if (r == 15 && five && f0 && j > 73 && j < 123 && i > 270
                            && i < 320 && aG && !inv.usingTool && down) {
                        goTo(3, 143, 143);
                    }

                    if (r == 17 && five && f0) {
                        if (j > 114 && j < 173 && i > 66 && i < 140
                                && !inv.usingTool) {
                            goTo(16, 123, 108);
                            return;
                        }
                        if (j > 72 && j < 112 && i > 98 && i < 138
                                && !W && up && !kFlag) {
                            W = true;
                            inv.createTool("mao1.png", 14, 2, 23, 15);
                            msg(Texts.R17_VOICE);
                            return;
                        }
                    }

                    if (r == 16 && five && f0) {
                        if (j > 88 && j < 128 && i > 270 && i < 320
                                && !inv.usingTool && down) {
                            goTo(8, 33, 153);
                        }
                        if (j > 108 && j < 143 && i > 103 && i < 113
                                && !cj && up && !inv.usingTool && !kFlag) {
                            cj = true;
                            msg(Texts.R16_ANGRY);
                            return;
                        }
                        if (j > 153 && j < 173 && i > 183 && i < 193 && up) {
                            ch = true;
                            dr++;
                        }
                        if (j > 153 && j < 173 && i > 183 && i < 193
                                && ch && !cf && dr == 2 && up && !kFlag) {
                            cf = true;
                            dr = 0;
                            inv.createTool("shengzi.png", 13, 2, 34, 20);
                            msg(Texts.R16_ROPE);
                            return;
                        }
                        if (j > 108 && j < 143 && i > 103 && i < 113
                                && cj && cg && !inv.usingTool && up) {
                            goTo(17, 123, 97);
                        }
                    }

                    if (r == 9 && five && f0) {
                        if (j > 173 && j < 240 && i > 270 && i < 320
                                && !inv.usingTool && down) {
                            goTo(6, 115, 145);
                        }
                        if (j > 198 && j < 239 && i > 116 && i < 150
                                && !Y && up && !kFlag) {
                            inv.createTool("shuji1.png", 5, 2, 33, 31);
                            msg(Texts.R9_BOOKS_TAKEN);
                            Y = true;
                            return;
                        }
                    }

                    if (r == 11 && five && f0) {
                        if (j > 108 && j < 153 && i > 270 && i < 320
                                && !inv.usingTool && down) {
                            goTo(3, 53, 143);
                        }
                        if (j > 22 && j < 54 && i > 132 && i < 181
                                && I && !cd && up && !kFlag) {
                            cd = true;
                            inv.createTool("q.png", 7, 2, 40, 9);
                            msg(Texts.R11_WHAT_IS_IT);
                            return;
                        }
                        if (j > 182 && j < 208 && i > 132 && i < 175
                                && R && I && cd && up && !kFlag) {
                            R = false;
                            inv.createTool("hse.png", 8, 2, 12, 19);
                            msg(Texts.R11_BREAK_HANDLE);
                            I = false;
                            return;
                        }
                    }

                    if (r == 12 && five && f0) {
                        if (j > 88 && j < 128 && i > 270 && i < 320
                                && !inv.usingTool && down) {
                            goTo(7, 115, 145);
                        }
                        if (j > 102 && j < 140 && i > 90 && i < 156 && up) {
                            P = true;
                            if (!dC) {
                                dR++;
                            }
                        }
                        if (j > 102 && j < 140 && i > 90 && i < 156
                                && P && !dC && dR == 2 && up && !kFlag) {
                            dC = true;
                            dR = 0;
                            inv.createTool("qq.png", 9, 2, 9, 10);
                            msg(Texts.R12_COIN);
                            return;
                        }
                    }

                    if (r == 13 && five && f0) {
                        if (j > 68 && j < 118 && i > 270 && i < 320
                                && !inv.usingTool && down) {
                            goTo(4, 53, 145);
                        }
                        if (j > 190 && j < 223 && i > 82 && i < 140
                                && wardrobe && !kFlag) {
                            dialog(new String[]{Texts.R13_WARDROBE_0, Texts.R13_WARDROBE_1});
                            return;
                        }
                        if (j > 190 && j < 223 && i > 82 && i < 140
                                && !wardrobe && up) {
                            wardrobe = true;
                        } else if (j > 190 && j < 223 && i > 82 && i < 140
                                && wardrobe && up) {
                            wardrobe = false;
                        }
                        if (j > 130 && j < 176 && i > 102 && i < 153
                                && bg && !c7 && up && !kFlag) {
                            c7 = true;
                            inv.createTool("sez.png", 6, 2, 22, 16);
                            msg(Texts.R13_LID);
                            return;
                        }
                    }

                    if (r == 14 && five && f0) {
                        if (j > 178 && j < 240 && i > 270 && i < 320
                                && !inv.usingTool && down) {
                            goTo(2, 53, 143);
                        }
                        if (j > 133 && j < 153 && i > 113 && i < 123
                                && aL && !cr && up && !kFlag) {
                            cr = true;
                            inv.createTool("dianchi.png", 12, 2, 8, 16);
                            msg(Texts.R14_BATTERY);
                            return;
                        }
                    }

                    // стол с картой (ком.14); else = «обычное '5'»
                    if (r == 14 && five && f0 && j > 98 && j < 118
                            && i > 203 && i < 218 && up && !inv.usingTool) {
                        by = true;
                        if (bw == 0) {
                            ai = true;
                        }
                        if (bw == 1) {
                            ai = false;
                        }
                        if (bw != 1) {
                            bw++;
                        } else {
                            bw = 0;
                        }
                    } else {
                        // '5' с пустой рукой: закрыть диалог (byte 4447)
                        if (five && f0 && !inv.usingTool) {
                            kFlag = false;
                            bf = "";
                            n = 0;
                        }
                        // использование предметов (byte 4455-4556)
                        if (five && inv.usingTool) {
                            int id = inv.slots[inv.current].id;
                            if (r == 3 && f0 && j > 33 && j < 78 && i > 0 && i < 143
                                    && up && id == 3) {
                                inv.freeToolByIndex(3, 2);
                                bf = Texts.R3_BIG_KEY_FITS;
                                dj = true;
                            }
                            if (r == 0 && f0) {
                                if (j > 127 && j < 172 && i > 270 && i < 320
                                        && down && !kFlag && id == 14) {
                                    inv.freeToolByIndex(14, 2);
                                    inv.aQ[15] = 10;   // КОНЦОВКА
                                }
                                if (j > 127 && j < 162 && i > 0 && i < 170 && id == 2) {
                                    inv.freeToolByIndex(2, 2);
                                    bd = true;
                                    l = true;
                                    bf = Texts.R0_KEY_OPENS;
                                }
                            }
                            if (r == 16 && f0 && j > 108 && j < 143 && i > 103
                                    && i < 113 && cj && up && id == 13) {
                                inv.freeToolByIndex(13, 2);
                                bf = Texts.R16_ROPE_ON_BEAM;
                                cg = true;
                            }
                            if (r == 5 && f0 && j > 33 && j < 78 && i > 0 && i < 143
                                    && up && id == 10) {
                                inv.freeToolByIndex(10, 2);
                                bf = Texts.R5_GREEN_KEY_FITS;
                                dF = true;
                            }
                            if (r == 8 && f0 && j == 143 && i == 123
                                    && up && id == 7) {
                                inv.freeToolByIndex(7, 2);
                                bf = Texts.R8_SWAP_HAMMER;
                                inv.createTool("chuizi.png", 11, 2, 28, 28);
                                c2 = true;
                            }
                            if (r == 14 && f0 && j > 133 && j < 153 && i > 113
                                    && i < 123 && up && !aL && id == 11) {
                                bf = Texts.R14_HAMMER_WALL;
                                aL = true;
                                inv.usingTool = false;
                                host.sound().playCorrect(); // реставрация
                            }
                            if (r == 3 && f0 && j > 133 && j < 168 && i > 133
                                    && i < 143 && !aG && up && id == 11) {
                                bf = Texts.R14_HAMMER_WALL;
                                aG = true;
                                inv.usingTool = false;
                                host.sound().playCorrect(); // реставрация
                            }
                            if (r == 6 && f0 && j > 100 && j < 135 && i > 0
                                    && i < 143 && up && id == 4) {
                                inv.freeToolByIndex(4, 2);
                                bf = Texts.R6_SMALL_KEY_FITS;
                                ei = true;
                            }
                            if (r == 7 && f0 && j > 100 && j < 135 && i > 0
                                    && i < 143 && up && id == 8) {
                                inv.freeToolByIndex(8, 2);
                                bf = Texts.R7_HANDLE_FITS;
                                aS = true;
                            }
                            if (r == 11 && f0 && j > 182 && j < 208 && i > 132
                                    && i < 175 && up && id == 6) {
                                inv.freeToolByIndex(6, 2);
                                bf = Texts.R11_LID_FITS;
                                R = true;
                                I = true;
                            }
                            if (r == 13) {
                                if (f0 && j > 36 && j < 100 && i > 78 && i < 146
                                        && up && id == 5) {
                                    dl = true;
                                    inv.freeToolByIndex(5, 2);
                                    bf = Texts.R13_BOOKS_FIT;
                                    bg = true;
                                    return;
                                }
                            }
                            if (r == 15 && f0) {
                                if (j > 48 && j < 73 && i > 123 && i < 133
                                        && b5 && up && id == 12) {
                                    inv.freeToolByIndex(12, 2);
                                    bf = Texts.R15_BATTERY_IN;
                                    pcOn = true;
                                }
                                if (j > 48 && j < 73 && i > 123 && i < 133
                                        && b5 && up && pcOn && id == 9) {
                                    inv.freeToolByIndex(9, 2);
                                    bf = Texts.R15_COIN_IN;
                                    ba = true;
                                }
                            }
                        }
                        // секрет ком.13 — с ПУСТОЙ рукой (byte 4536: !cP)
                        if (five && r == 13 && f0 && !inv.usingTool
                                && j > 36 && j < 100 && i > 78 && i < 146
                                && up && dl && !d3) {
                            bf = Texts.R13_SECRET;
                            d3 = true;
                            return;
                        }
                        // сейф: закрытие левым софтом + ввод (byte 4558-4704)
                        if (k == J2.SOFT_LEFT && safe.open) {
                            safe.closeByLeftSoft();
                            return;
                        }
                        if (safe.open) {
                            safe.handleKey(k);
                        }
                    }
                }
            }
        }
    }

    // ==================================================================
    // РЕНДЕР (else() + m()/h()/do()/v() + u()/null()/q()/s()/e())
    // ==================================================================

    @Override
    public void render(SpriteBatch batch) {
        Assets a = host.assets();
        if (!preDisplayed) {
            introRender(batch, a);
        } else {
            // else(): чёрная заливка + музыка (int(0); a(4); new(4))
            a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
            host.sound().changeClosedMid(SoundManager.CH_MENU);
            host.sound().createBackMid(SoundManager.CH_MAPBG);
            host.sound().playBackMid(SoundManager.CH_MAPBG);
            // w(): фон -> объекты -> стрелки -> сейф -> титры
            room.renderBackground(batch);
            renderRoomObjects(batch, a);
            if (cl && !c6) {
                renderArrows(batch, a);
            }
            safe.render(batch, a);
            if (safe.open && inv.aQ[15] != 10) {
                keypad.render(batch, a, host.tick()); // клавиатура 3x4 + пульс
            }
            if (inv.aQ[15] == 10) {
                renderCredits(batch, a);
            }
            // void(): long() — рука + фронт + карта + жалюзи
            if (!inv.showTool && !pause) {
                room.renderHand(batch);
                if (inv.aQ[15] != 10) {
                    renderForeground(batch, a);
                }
                renderMapOverlay(batch, a);
                renderBlinds(batch, a);
            }
            if (inv.showTool) {
                inv.render(batch, a);        // j()
            }
            if (pause) {
                drawPause(batch, a);         // i()
            }
            room.drawDebug(batch);
        }
        // окна сообщений (else() 2647-2672; гейт !aR)
        if (!bf.isEmpty() && !pause) {
            dialogBoxRender(batch, a);
        }
        if (!m.isEmpty() && !pause) {
            a.fill(batch, 0, 270, 240, 51, 0, 0, 0, 1);
            host.fonts().drawCentered(batch, m, 280, 250, 223, 134);
        }
        if (musicOverlay) {
            drawMusicOverlay(batch, a);
        }
        if (info) {
            drawInfoOverlay(batch, a);
        }
    }

    /** Интро: m() + h() / do()+v(). */
    private void introRender(SpriteBatch batch, Assets a) {
        // m(): чёрный экран (полосы-«призраки» недостижимы: cR не растёт)
        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
        if (!skipState) {
            // h(): прокрутка истории
            if (introLines == null || !introPackStamp.equals(Texts.pack())) {
                introPackStamp = Texts.pack();
                introLines = TextFmt.format(Texts.INTRO_STORY, Texts.INTRO_WRAP,
                        host.fonts()::stringWidth);
            }
            drawLadder(batch, introLines, 10, 272 - z, dA, 50);
            a.fill(batch, 0, 0, 240, 115, 0, 0, 0, 1);       // верхняя маска
            if (z < 99) {
                batch.draw(a.region("LOGO.png", 51, 135, 156, 90, false, true),
                        (240 - 156) / 2, 12);
            } else {
                batch.draw(a.region("menuback.png", 84, 230, 156, 90, false, true),
                        (240 - 156) / 2, 12);
            }
            a.fill(batch, 0, 300, 240, 20, 0, 0, 0, 1);       // нижняя маска
            batch.draw(a.region("fanhui.png"), 0, 310);       // подсказка скипа
        } else {
            // do(): пусто (bE=41); v(): «загрузочная» полоса.
            // dd/c3 уже инкрементированы в тике — рисуем с предыдущим
            // значением (в оригинале постинкремент внутри fillRect)
            batch.draw(a.region("jindu.png"), (240 - 176) / 2, 0);
            batch.draw(a.region("jindu1.png"), (240 - 113) / 2, (320 - 13) / 2 + 70);
            a.fill(batch, (240 - 113) / 2 - (dd - 1), 223, 113, 15, 0, 0, 0, 1);
            a.fill(batch, (240 - 113) / 2 + (c3 - 1), 223, 113, 15, 0, 0, 0, 1);
        }
    }

    /** do(String[],int,int,int,int,Font): пейджер с «лестницей» цветов. */
    private int drawLadder(SpriteBatch batch, String[] lines, int x, int y,
                           int start, int count) {
        Fonts f = host.fonts();
        int di = f.height();
        int s = start;
        if (s <= 0) {
            s = 0;
        }
        if (s >= lines.length - 1) {
            s = lines.length - 1;
        }
        int cr = 0, cg = 0, cb = 0;   // цвет тянется, если полоса не нашлась
        for (int idx = 0; idx < count && idx + s < lines.length; idx++) {
            int ly = y + idx * (di + 1);
            int[] c = ladderColor(ly, di);
            if (c != null) {
                cr = c[0];
                cg = c[1];
                cb = c[2];
            }
            f.draw(batch, lines[idx + s], x, ly, cr, cg, cb);
        }
        return s;
    }

    /** Цветовая «лестница» do(String[],...) — градиент факела над текстом. */
    private static int[] ladderColor(int y, int di) {
        // @formatter:off
        if (y < 124 - di)  return new int[]{0, 0, 0};
        if (y < 125 - di)  return new int[]{67, 67, 66};
        if (y < 127 - di)  return new int[]{131, 131, 129};
        if (y < 129 - di)  return new int[]{148, 147, 146};
        if (y < 131 - di)  return new int[]{163, 162, 160};
        if (y < 133 - di)  return new int[]{179, 179, 177};
        if (y < 135 - di)  return new int[]{197, 196, 192};
        if (y < 320 - 12 - 9 - di)  return new int[]{246, 245, 242};
        if (y < 320 - 12 - 7 - di)  return new int[]{183, 183, 179};
        if (y < 320 - 12 - 5 - di)  return new int[]{152, 152, 150};
        if (y < 320 - 12 - 3 - di)  return new int[]{152, 152, 150};
        if (y < 320 - 12 - 1 - di)  return new int[]{130, 130, 127};
        if (y < 320 - 12 + 1 - di)  return new int[]{130, 130, 127};
        if (y < 320 - 12 + 3 - di)  return new int[]{90, 90, 89};
        if (y < 320 - 12 + 5 - di)  return new int[]{80, 80, 78};
        if (y < 320 - 12 + 7 - di)  return new int[]{80, 80, 78};
        if (y < 320 - 12 + 9 - di)  return new int[]{66, 66, 65};
        if (y < 320 - 12 + 11 - di) return new int[]{66, 66, 65};
        return null;               // прежний цвет (quirk оригинала)
        // @formatter:on
    }

    /** do(String) (drawstringToScreen) — ЭТАП 5c: бокс порта с рамкой,
     *  переносом по словам, страницами по 2 строки и печатной машинкой.
     *  Геометрия строк и цвет (250,223,134) — как в оригинале. */
    private void dialogBoxRender(SpriteBatch batch, Assets a) {
        Fonts f = host.fonts();
        if (!bf.equals(dialog.shownText())) {
            dialog.setSpeed(textSpeedTicks());
            dialog.show(bf, 240 - 2 * f.stringWidth("В"), f::stringWidth);
        }
        a.fill(batch, 0, 268, 240, 52, 0, 0, 0, 1);
        drawBoxFrame(batch, a, 1, 269, 238, 50);
        int x = f.stringWidth("В") - 4 - 3;
        f.draw(batch, dialog.line(0), x, 275, 250, 223, 134);
        f.draw(batch, dialog.line(1), x, 320 - f.height() - 4, 250, 223, 134);
        int y = host.tick() % 2 == 0 ? 309 : 308;   // Timer e % 2
        batch.draw(a.region("anja.png"), 228, y);
    }

    /** Двойная пиксельная рамка бокса (этап 5c): верх/лево — светлый
     *  песочный в цвет текста, низ/право — тёмный, углы срезаны на 2px. */
    private static void drawBoxFrame(SpriteBatch batch, Assets a,
                                     int x, int y, int w, int h) {
        a.fill(batch, x + 2, y, w - 4, 1, 250, 223, 134, 1);
        a.fill(batch, x, y + 2, 1, h - 4, 250, 223, 134, 1);
        a.fill(batch, x + 2, y + h - 1, w - 4, 1, 166, 140, 84, 1);
        a.fill(batch, x + w - 1, y + 2, 1, h - 4, 166, 140, 84, 1);
        a.fill(batch, x + 1, y + 1, 1, 1, 208, 181, 109, 1);
        a.fill(batch, x + w - 2, y + 1, 1, 1, 208, 181, 109, 1);
        a.fill(batch, x + 1, y + h - 2, 1, 1, 208, 181, 109, 1);
        a.fill(batch, x + w - 2, y + h - 2, 1, 1, 208, 181, 109, 1);
    }

    /** Скорость печатной машинки из настроек: тиков на символ (0 = мгновенно). */
    private static int textSpeedTicks() {
        String v = Settings.get("textspeed", "normal");
        switch (v) {
            case "instant":
                return 0;
            case "fast":
                return 1;
            case "slow":
                return 4;
            default:
                return 2;
        }
    }

    /** u() (fangjian): объекты комнат. */
    private void renderRoomObjects(SpriteBatch batch, Assets a) {
        int r = room.room;
        switch (r) {
            case 0:
                if (cE) {
                    batch.draw(a.region("kaigui.png"), 57, 138);
                    if (!d4) {
                        batch.draw(a.region("shouji.png"), 65, 185);
                    }
                }
                break;
            case 1:
                if (!ea) {
                    batch.draw(a.region("shaizi.png"), 136, 212);
                }
                if (dB) {
                    batch.draw(a.region("gui2.png"), 175, 129);
                    if (!w) {
                        batch.draw(a.region("shuyao.png"), 193, 140);
                    }
                }
                break;
            case 2:
                batch.draw(a.region("men.png", 0, 0, 51, 97, false, true), 42, 65);
                batch.draw(a.region("menkuang.png"), 0, 58);
                batch.draw(a.region("lou.png", 0, 0, 66, 128, false, true), 174, 51);
                batch.draw(a.region("lou.png", 0, 0, 66, 51, false, true), 174, 0);
                batch.draw(a.region("paaizi.png"), 122, 81);
                batch.draw(a.region("loub.png", 0, 0, 7, 16, false, true), 130, 86);
                batch.draw(a.region("loub.png", 17, 0, 11, 16, false, true), 138, 86);
                break;
            case 3:
                batch.draw(a.region("men.png", 0, 0, 51, 97, false, true), 42, 65);
                batch.draw(a.region("menkuang.png"), 0, 58);
                batch.draw(a.region("menkuang.png", 101, 0, 75, 7, false, true), 176, 58);
                batch.draw(a.region(aG ? "dong.png" : "kuangtpong.png"), 121, 106);
                break;
            case 4:
                batch.draw(a.region("men.png", 0, 0, 51, 97, false, true), 42, 65);
                batch.draw(a.region("menkuang.png"), 0, 58);
                batch.draw(a.region("menkuang.png", 101, 0, 75, 7, false, true), 176, 58);
                batch.draw(a.region("lou.png", 66, 93, 66, 35, false, true), 174, 285);
                batch.draw(a.region("lou.png", 66, 93, 66, 6, false, true), 174, 279);
                batch.draw(a.region("lou.png", 66, 93, 66, 6, false, true), 174, 277);
                batch.draw(a.region("paaizi.png"), 122, 81);
                batch.draw(a.region("loub.png", 7, 0, 10, 16, false, true), 130, 86);
                batch.draw(a.region("loub.png", 17, 0, 11, 16, false, true), 138, 86);
                break;
            case 5:
                batch.draw(a.region("men.png", 0, 0, 51, 97, false, true), 42, 65);
                batch.draw(a.region("menkuang.png"), 0, 58);
                batch.draw(a.region("menkuang.png", 101, 0, 75, 7, false, true), 176, 58);
                break;
            case 6:
            case 7:
                batch.draw(a.region("men.png", 0, 0, 51, 97, false, true), 106, 65);
                batch.draw(a.region("menkuang.png"), 64, 58);
                batch.draw(a.region("menkuang.png", 101, 0, 75, 7, false, true), 0, 58);
                break;
            case 8:
                if (ba) {
                    batch.draw(a.region("kamen.png"), 22, 102);
                }
                batch.draw(a.region("gz.png"), 143, 125);
                break;
            case 9:
                if (!Y) {
                    batch.draw(a.region("shuji.png"), 214, 128);
                }
                break;
            case 10:
                if (!a5) {
                    batch.draw(a.region("weiyao.png"), 105, 139);
                }
                break;
            case 11:
                batch.draw(a.region("wei.png"), 88, 132);
                batch.draw(a.region("shise.png", 0, 0, 14, 12, false, true), 189, 133);
                if (R) {
                    batch.draw(a.region("shise.png", 14, 0, 12, 12, false, true), 190, 134);
                }
                if (I) {
                    batch.draw(a.region("guis.png"), 27, 150);
                    if (!cd) {
                        batch.draw(a.region("bang.png"), 30, 154);
                    }
                }
                break;
            case 12:
                if (P) {
                    batch.draw(a.region("guika.png"), 106, 119);
                    if (!dC) {
                        batch.draw(a.region("qian.png"), 118, 121);
                    }
                }
                break;
            case 13:
                if (wardrobe) {
                    batch.draw(a.region("kguizi.png"), 191, 97);
                    batch.draw(a.region("shuzi.png"), 192, 105);
                }
                if (!bg) {
                    batch.draw(a.region("dang.png"), 44, 111);
                } else {
                    batch.draw(a.region("chouti.png"), 135, 131);
                    if (!c7) {
                        batch.draw(a.region("sez.png"), 145, 133);
                    }
                }
                break;
            case 14:
                batch.draw(a.region("xmap.png"), 112, 207);
                if (aL) {
                    batch.draw(a.region("diban.png", 0, 0, 19, 15, false, true), 145, 125);
                    if (!cr) {
                        batch.draw(a.region("diban.png", 19, 0, 5, 7, false, true), 152, 128);
                    }
                }
                break;
            case 15:
                if (b5) {
                    batch.draw(a.region("nkai.png"), 58, 130);
                }
                break;
            case 16:
                if (cj) {
                    batch.draw(a.region("k.png"), 114, 80);
                }
                if (ch) {
                    batch.draw(a.region("kc.png"), 161, 182);
                    if (!cf) {
                        batch.draw(a.region("sk.png"), 170, 187);
                    }
                }
                if (cg) {
                    batch.draw(a.region("sk.png"), 133, 72);
                    batch.draw(a.region("s.png"), 133, 72);
                }
                break;
            case 17:
                if (!W) {
                    batch.draw(a.region("mao.png"), 92, 108);
                }
                break;
            default:
                break;
        }
    }

    /** null() (beijig): фронтальные полосы поверх руки. */
    private void renderForeground(SpriteBatch batch, Assets a) {
        int r = room.room;
        String back = RoomData.BACKGROUNDS[r] + ".png";
        switch (r) {
            case 0:
                batch.draw(a.region(back, 0, 292, 134, 28, false, true), 0, 292);
                batch.draw(a.region(back, 186, 292, 54, 28, false, true), 186, 292);
                break;
            case 1:
                batch.draw(a.region(back, 120, 184, 5, 14, false, true), 120, 184);
                batch.draw(a.region(back, 134, 188, 48, 10, false, true), 134, 188);
                batch.draw(a.region(back, 193, 184, 5, 14, false, true), 193, 184);
                batch.draw(a.region(back, 0, 292, 134, 28, false, true), 0, 292);
                batch.draw(a.region(back, 187, 292, 53, 28, false, true), 187, 292);
                if (safe.open && inv.aQ[15] == 0) {
                    batch.draw(a.region("fanhui.png"), 0, 310);
                }
                break;
            case 2:
            case 3:
                batch.draw(a.region(back, 0, 277, 240, 43, false, true), 0, 277);
                break;
            case 4:
                batch.draw(a.region(back, 0, 277, 174, 43, false, true), 0, 277);
                break;
            case 5:
            case 6:
            case 7:
                batch.draw(a.region(back, 0, 277, 240, 43, false, true), 0, 277);
                break;
            case 8:
                batch.draw(a.region(back, 0, 288, 46, 32, false, true), 0, 288);
                batch.draw(a.region(back, 100, 288, 140, 32, false, true), 100, 288);
                batch.draw(a.region(back, 141, 264, 74, 24, false, true), 141, 264);
                batch.draw(a.region(back, 163, 259, 32, 12, false, true), 163, 259);
                break;
            case 9:
                batch.draw(a.region(back, 25, 179, 62, 23, false, true), 25, 179);
                batch.draw(a.region(back, 0, 289, 176, 31, false, true), 0, 289);
                break;
            case 10:
                batch.draw(a.region(back, 52, 289, 188, 31, false, true), 52, 289);
                batch.draw(a.region(back, 182, 268, 58, 21, false, true), 182, 268);
                break;
            case 11:
                batch.draw(a.region(back, 0, 288, 115, 32, false, true), 0, 288);
                batch.draw(a.region(back, 168, 288, 72, 32, false, true), 168, 288);
                break;
            case 12:
                batch.draw(a.region(back, 0, 288, 93, 32, false, true), 0, 288);
                batch.draw(a.region(back, 146, 288, 94, 32, false, true), 146, 288);
                break;
            case 13:
                batch.draw(a.region(back, 0, 288, 71, 32, false, true), 0, 288);
                batch.draw(a.region(back, 136, 288, 104, 32, false, true), 136, 288);
                batch.draw(a.region(back, 0, 192, 26, 15, false, true), 0, 192);
                batch.draw(a.region(back, 180, 227, 42, 21, false, true), 180, 227);
                break;
            case 14:
                batch.draw(a.region(back, 0, 288, 180, 32, false, true), 0, 288);
                batch.draw(a.region(back, 43, 166, 29, 22, false, true), 43, 166);
                batch.draw(a.region(back, 94, 166, 46, 22, false, true), 94, 166);
                batch.draw(a.region(back, 170, 166, 29, 22, false, true), 170, 166);
                break;
            case 15:
                batch.draw(a.region(back, 0, 287, 79, 33, false, true), 0, 287);
                batch.draw(a.region(back, 142, 287, 98, 33, false, true), 142, 287);
                batch.draw(a.region(back, 175, 211, 41, 21, false, true), 175, 211);
                break;
            case 16:
                batch.draw(a.region(back, 0, 286, 91, 34, false, true), 0, 286);
                batch.draw(a.region(back, 144, 286, 96, 34, false, true), 144, 286);
                batch.draw(a.region(back, 0, 225, 13, 9, false, true), 0, 225);
                batch.draw(a.region(back, 0, 234, 25, 16, false, true), 0, 234);
                batch.draw(a.region(back, 227, 225, 13, 8, false, true), 227, 225);
                batch.draw(a.region(back, 217, 233, 23, 8, false, true), 217, 233);
                batch.draw(a.region(back, 158, 163, 64, 17, false, true), 158, 163);
                break;
            case 17:
                batch.draw(a.region(back, 0, 287, 240, 33, false, true), 0, 287);
                batch.draw(a.region(back, 0, 240, 41, 22, false, true), 0, 240);
                batch.draw(a.region(back, 198, 248, 31, 13, false, true), 198, 248);
                break;
            default:
                break;
        }
    }

    /** q() (jiantoud): стрелки обучения (b8=52, b7=108). */
    private void renderArrows(SpriteBatch batch, Assets a) {
        arrow(batch, a, 12, 0, 15, 12, 157, 218);
        arrow(batch, a, 39, 4, 24, 15, 152, 228);
        arrow(batch, a, 63, 19, 24, 15, 152, 276);
        arrow(batch, a, 12, 27, 15, 12, 157, 291);
        arrow(batch, a, 63, 4, 24, 15, 122, 258);
        arrow(batch, a, 0, 12, 12, 15, 110, 258);
        arrow(batch, a, 39, 19, 24, 15, 182, 258);
        arrow(batch, a, 27, 12, 12, 15, 205, 258);
        if (bU / 4 % 2 == 0) {
            arrow(batch, a, 12, 0, 15, 12, 157, 218);
            arrow(batch, a, 87, 4, 24, 15, 152, 228);
            arrow(batch, a, 12, 27, 15, 12, 157, 291);
            arrow(batch, a, 111, 19, 24, 15, 152, 276);
            arrow(batch, a, 0, 12, 12, 15, 110, 258);
            arrow(batch, a, 111, 4, 24, 15, 122, 258);
            arrow(batch, a, 27, 12, 12, 15, 205, 258);
            arrow(batch, a, 87, 19, 24, 15, 182, 258);
        }
    }

    private void arrow(SpriteBatch batch, Assets a, int sx, int sy, int w, int h,
                       int x, int y) {
        batch.draw(a.region("jiantou1.png", sx, sy, w, h, false, true), x, y);
    }

    /** long() ком.14: карта дома (ai) с мигающей точкой. */
    private void renderMapOverlay(SpriteBatch batch, Assets a) {
        if (room.room != 14 || !ai) {
            return;
        }
        batch.draw(a.region("dmap.png"), (240 - 81) / 2, (320 - 71) / 2 + 13);
        batch.draw(a.region("fanhui.png"), 0, 310);
        if (host.tick() / 6 % 2 == 0) {
            // drawArc 4x4 + белая точка (в 4px квадрате — заливка)
            a.fill(batch, 112, 177, 4, 4, 1, 0, 0, 1);
            a.fill(batch, 114, 179, 1, 1, 1, 1, 1, 1);
        }
    }

    /** Жалюзи (long() при dp==3): 8 полос по 40px. */
    private void renderBlinds(SpriteBatch batch, Assets a) {
        if (dp != 3 || cl) {
            return;   // рисуются только пока открываются (до cK/cl)
        }
        for (int idx = 0; idx < 8; idx++) {
            int hgt = 40 - blinds[idx];
            if (hgt > 0) {
                a.fill(batch, 0, idx * 40 + blinds[idx], 240, hgt, 0, 0, 0, 1);
            }
        }
    }

    /** e() (jieshuh): титры — чёрный экран, текст, js.png. */
    private void renderCredits(SpriteBatch batch, Assets a) {
        if (endingLines == null || !endingPackStamp.equals(Texts.pack())) {
            endingPackStamp = Texts.pack();
            endingLines = TextFmt.format(Texts.ENDING, Texts.ENDING_WRAP,
                    host.fonts()::stringWidth);
        }
        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
        // (амплитуда am мертва: 0 < 290 всегда)
        drawLadder(batch, endingLines, 10, 272 - A, dA, 14);
        batch.draw(a.region("js.png"), (240 - 176) / 2, 20);
    }

    /** Меню паузы i(): чёрный фон + caidan1 (230x149: панель 136x149 + 4 полосы). */
    private void drawPause(SpriteBatch batch, Assets a) {
        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
        batch.draw(a.region("caidan1.png", 0, 0, 136, 149, false, true),
                (240 - 136) / 2, (320 - 149) / 2);
        // [пункт] = {srcY полосы 94x26, dstY, x полосы, y указателя, x указателя}
        switch (pauseItem) {
            case 0:
                batch.draw(a.region("caidan1.png", 136, 6, 94, 26, false, true), 74, 92);
                batch.draw(a.region("shou.png"), 193, 97);
                break;
            case 1:
                batch.draw(a.region("caidan1.png", 136, 44, 94, 26, false, true), 74, 130);
                batch.draw(a.region("shou.png"), 193, 136);
                break;
            case 2:
                batch.draw(a.region("caidan1.png", 136, 80, 94, 26, false, true), 74, 166);
                batch.draw(a.region("shou.png"), 193, 172);
                break;
            case 3:
                batch.draw(a.region("caidan1.png", 136, 116, 94, 26, false, true), 74, 202);
                batch.draw(a.region("shou.png"), 193, 208);
                break;
            default:
        }
    }

    /** Оверлей настройки звука g(): заливка (1,1,1), заголовок, ДА/НЕТ + shou. */
    private void drawMusicOverlay(SpriteBatch batch, Assets a) {
        a.fill(batch, 0, 0, 240, 320, 1f / 255f, 1f / 255f, 1f / 255f, 1);
        Fonts f = host.fonts();
        f.drawCentered(batch, Texts.MUSIC_TITLE, 8, 100, 100, 255);
        f.drawCentered(batch, Texts.MUSIC_UNDERLINE, 16, 100, 100, 255);

        if (!SoundManager.canSound) {
            // g(): int(4),int(0),int(1),int(2),int(3) — остановить ВСЕ каналы
            host.sound().changeClosedMid(SoundManager.CH_MAPBG);
            host.sound().changeClosedMid(SoundManager.CH_MENU);
            host.sound().changeClosedMid(SoundManager.CH_GET);
            host.sound().changeClosedMid(SoundManager.CH_WRONG);
            host.sound().changeClosedMid(SoundManager.CH_CORRECT);
        }

        int x = (240 - f.stringWidth(Texts.SOUND_ON)) / 2;
        if (SoundManager.canSound) {
            f.draw(batch, Texts.SOUND_ON, x, Texts.SOUND_ON_Y, 255, 255, 255);
            f.draw(batch, Texts.SOUND_OFF, x, Texts.SOUND_OFF_Y, 200, 200, 200);
            batch.draw(a.region("shou.png"), x + Texts.SOUND_SHOU_DX, Texts.SOUND_ON_Y + 2);
        } else {
            f.draw(batch, Texts.SOUND_ON, x, Texts.SOUND_ON_Y, 200, 200, 200);
            f.draw(batch, Texts.SOUND_OFF, x, Texts.SOUND_OFF_Y, 255, 255, 255);
            batch.draw(a.region("shou.png"), x + Texts.SOUND_SHOU_DX, Texts.SOUND_OFF_Y);
        }
    }

    /** Оверлей ИНФОРМАЦИЯ int(): чёрный фон, заголовок, 4 строки (di = высота шрифта). */
    private void drawInfoOverlay(SpriteBatch batch, Assets a) {
        a.fill(batch, 0, 0, 240, 320, 0, 0, 0, 1);
        Fonts f = host.fonts();
        f.drawCentered(batch, Texts.INFO_TITLE, 5, 100, 100, 255);
        int di = f.height();
        String[] lines = Texts.INFO_LINES;
        int[] ys = {15 + di, 20 + 2 * di, 22 + 3 * di, 24 + 4 * di};
        for (int idx = 0; idx < lines.length; idx++) {
            f.draw(batch, lines[idx], 3, ys[idx], 255, 255, 255);
        }
    }

    @Override
    public void dispose() {
        //-room: текстуры в общем кэше Assets
    }

    @Override
    public String toString() {
        return "game " + room + (pause ? " PAUSE=" + pauseItem : "")
                + (info ? " INFO" : "") + (musicOverlay ? " MUSIC" : "")
                + (inv.showTool ? " TOOL" : "") + (safe.open ? " SAFE" : "");
    }
}
