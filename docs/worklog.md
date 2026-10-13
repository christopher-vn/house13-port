# Project Worklog — House 13 (J2ME) reverse engineering

---
Task ID: 2-code-diff
Agent: general-purpose
Task: Глубокое сравнение игровой логики двух декомпилированных J2ME-мидлетов House13 (CN MainCanvas.java 5820 строк, не обфусцирован) и House13 RU (b.java 5183 строки, обфусцирован). Сопоставление методов, состояний, комнат, инвентаря, клавиш, текстового рендера; поиск логических различий; выводы для портирования.

Work Log:
- Прочитал worklog — не существовал, пропустил.
- Прочитал полностью вспомогательные классы: MySound↔a (идентичны), MySprite↔c (RU добавил коллизию с прямоугольником a(int[]) и fast-path отрисовки однофреймовых спрайтов), TheMIDlet↔TheMIDlet (идентичны).
- Построил полные списки методов обоих файлов (CN 69 методов, RU 71) и сопоставил их по порядку/сигнатурам/содержимому; проверил call-graph через grep вызовов.
- Прочитал весь CN MainCanvas.java (по частям), в т.ч. гигантский drawHand() 1863–3157 (хардкод-коллизии по комнатам 0–17 + переходы) и onRoom1() 4454–5344 (все хот-зоны/пазлы/сейф).
- Прочитал весь RU b.java, в т.ч. f() (инициализация 18 массивов коллизий cI..cT), p() (универсальный движок коллизий), long() (замена drawHand), y() (мёртвый дебаг), try(int) (аналог onRoom1), s() (сейф).
- Сверил граф переходов комнат (все 18 комнат, edge-walk + door-hotspot переходы) — сравнил каждое присваивание offsetX/ce.
- Сверил систему предметов: toolStates[16]/tool[14] ↔ aQ[16]/c5[14], все 13 CreateTool-вызовов и все freeToolByIndex/использования; код сейфа 9819; обнаружил одинаковый out-of-bounds баг toolStates[31]/aQ[31] в обеих версиях.
- Сверил обработку клавиш (onXLogo/onMainScreen/onGameScreen/onHand/onTool ↔ for/char/byte/case/if), мёртвый getSettingInfo/new(), мёртвые константы ROOM_1..27/MAIN_KEY_*/GAME_*.
- Сверил текстовый рендер: drawString/drawStrings/drawStringPass/drawStringPre/heidrawString/format ↔ for/a/if/do/a(String)/a(String,int,Font); шрифты Font.getFont(0,0,0) CN vs (0,0,8) RU.
- Сверил музыкальную систему (createBackMid/playBackMid/closedMid/changeClosedMid ↔ a/new/do/int), главный цикл run(), paint(), keyReleased().
- Проверил манифесты: CN «三更灵异事件 / 适用于摩托_E398», RU «House N13 / Vendor / SE_K700».
- Составил таблицу соответствия методов и полей, список логических различий, выводы для портирования.

Stage Summary:

ТАБЛИЦА СООТВЕТСТВИЯ МЕТОДОВ CN MainCanvas ↔ RU b:
- MainCanvas()→b(); getSettingInfo()→new() (мёртвый в обеих); inIt()→t(); jiantoud()→q(); handIni()→k(); gameIni()→c(); start()→x()
- createBackMid(int)→a(int); playBackMid(int)→new(int); closedMid(int)→do(int); changeClosedMid(int)→int(int); FreeGameSourse()→if()
- paint()→paint(); keyPressed()→keyPressed(); onXLogo()→for(int); onMainScreen()→char(int); onGameScreen()→byte(int); keyReleased()→keyReleased(); run()→run() (идентичен, 100 мс)
- drawExitMusic()→g(); drawExitHelp()→int(); drawPublicRoom()→void(); onHand()→case(int) (+вызов p())
- loadImage()→if(String); drawFlashScreen()→o(); drawXLogo()→d(); drawMainScreen()→l(); drawGamePre()→m(); drawPreStr()→v(); drawStringPre()→do(String[])
- MainScreenDrawHelp()→case(); MainScreenDrawSetting()→b(); vConvert()→a(Graphics,Image,…); beijings()→z(); MainScreenDrawAbout()→goto(); heidrawString()→a(String,int,int,int)
- drawString()→for(String[]); drawStrings()→a(String[]); drawStringPass()→if(String[]); FreeMenuSourse()→byte()
- drawHand() [1296 строк] → long() [178 строк] + p() [418 строк] + f() [19 строк] — ПОЛНАЯ ПЕРЕПИСАННАЯ КОЛЛИЗИОННАЯ СИСТЕМА
- (нет в CN)→p() — универсальный движок движения/коллизий; (нет в CN)→f() — инициализация 18 int[][]-массивов стен; (нет в CN)→y() — мёртвый дебаг-рендер рамок коллизий
- drawQuit()→i(); drawGameScreen()→else(); beijig()→null() (+впитал оверлеи jiaju); publicRoom1()→a(); jiantou()→char() (В RU НИКОГДА НЕ ВЫЗЫВАЕТСЯ — фича стрелок выходов удалена); fangjainw()→n(); fangjian()→u(); drawRoom1()→w(); jieshuh()→e(); kaishi()→h(); kongzhiren()→do(); kaishichu()→A(); zhongtu()→try(); shuju()→s(); onRoom1()→try(int); freeRoom1()→for()
- drawToolstring()→a(String); drawstringToScreen()→do(String); format()→a(String,int,Font); CreateTool×2→a(Image,…)×2; freeToolByIndex()→a(int,int); drawTool()→j(); daojuziti()→if(String,int,int,int); onTool()→if(int); jbInit()→r() (пустой в обеих, артефакт JBuilder)
Ключевые поля: offsetX→ce, drawStatus_→b0, menuStatus_→bO, GAME_CURRENT→o, toolStates→aQ, tool→c5, currentTool→void, hand→a8, usingTool→cP, showTool→dt, canExit→aR, isHelp→du, isMusic→H, isOutpasswordkuang→try, keydownNum→ag, guanzi→k, shuodi→n, Timer→e, qingchu→r, zong→c6, jianpan→g, zimua→dz, pshang/pxia/pzuo/pyou→dQ/c/b/b3, bianl→dp, wzdh→dX, kaishigd→cl, preDisplayed→goto, preCount→if, fsi→aZ, jish→bE, juli/juli11→dd/c3.

КЛЮЧЕВЫЕ РЕЗУЛЬТАТЫ:
1. RU — НЕ простой порт: платформа сменена на com.nokia.mid.ui.FullCanvas (Nokia UI API, хардкод клавиш -1..-7), и главное — движение руки-курсора ПЕРЕПИСАНО с нуля: вместо ~1300 строк хардкода в drawHand() теперь 18 массивов прямоугольников-стен (cI..cT, 138 прямоугольников на 18 комнат, стены кодируют и проёмы дверей) + универсальный цикл p() (шаг→тест a(int[])→откат) + коллизия-прямоугольник в спрайте. Хитбокс руки — полоска 21×5 на y+25..y+30 (кончик пальца), магические -1/-25 в c.java.
2. Граф комнат, все пазлы, сейф 9819, инвентарь (14 слотов, индексы 2–14, крюк-слот 1 недостижим), ending-флаг toolStates[15]=10 — ПОЛНОСТЬЮ ИДЕНТИЧНЫ. Обе версии содержат одинаковый баг выхода за границы массива toolStates[31]/aQ[31]=1 (перехватывается try/catch keyPressed) — прямое доказательство происхождения RU от CN-кода.
3. Логические различия (кроме текстов/графики/разрешения): удалены 7 дублирующих press-5 переходов (1→2, 2→3, 4→5, 4→6, 5→4, 6→4, 6→7) — оставлены edge-walk переходы; хот-зоны стали площадными (у CN точечные равенства вида y==118); сцена пробуждения в RU автопропускается (bE=41 зашит, русские тексты «Ах…/Господи…/Больно…» недостижимы); порог интро-скролла 260→280; конечные титры RU крутятся быстрее (++st vs A+=2); jiantou() (стрелки выходов) больше не вызывается; kongzhiren→do() вобрал код skip-состояния; MySprite получил fast-path для 1-фреймовых спрайтов; System.gc() в paint() в обеих.
4. Сохранений НЕТ ни в одной версии (нет RMS); RU честно предупреждает «В ИГРЕ ОТСУТСТВУЕТ СОХРАНЕНИЕ». Музыка: 5 каналов MIDI (menu/get/wrong/correct/mapbg), реально в JAR только menu+mapbg — SFX отсутствуют и молча не играют в обеих.
5. Кириллица решена системным шрифтом Font.getFont(0,0,8) (SMALL) + стандартным drawString; никаких битмап-шрифтов и кастомных кодировок; перенос строк format()/drawstringToScreen() идентичны CN (по stringWidth).
6. Мёртвый код в обеих: getSettingInfo/new() (JAD-профили), константы ROOM_1..27/MAIN_KEY_*/GAME_*, пустые if-тела (артефакты декомпилятора), jbInit (JBuilder), toolStates[6]!=7/<7 всегда true.
7. Для портирования: состояния drawStatus_ {-1 flash,0 logo,1 меню,2 игра} × menuStatus_ {0..3} × GAME_CURRENT {0..3 пауза-меню}; комнаты 0–17 = фон(publicRoom1)+кэш объектов(fangjainw/fangjian)+коллизии+хот-зоны(onRoom1); цикл 10 FPS (run: repaint+serviceRepaints+sleep(100)), вся логика в paint(), ввод гейтится canChange; рука = спрайт 4 фрейма (21×30 из листа 21×118, y-офсеты 0/30/59/89), скорость 5px/кадр; инвентарь-карусель (текущий ±1, ±2 видны); ввод: 1 инвентарь, 2/4/6/8+стрелки движение, 5/FIRE действие, левый софт подтверждение, правый софт отмена/меню, 0–9 ввод кода сейфа.

---
Task ID: 1+3-research
Agent: main (Super Z)
Task: Скачивание архива, структурный анализ обеих версий, веб-исследование игры и компании в китайском интернете.

Work Log:
- Скачал House.zip с MediaFire (3.5 МБ): HOUSE13_DOC.txt (старая документация пользователя), House13CN_DECOMP, House13RU_DECOMP.
- Манифесты: CN = 三更灵异事件 / 无限乐游 / 适用于摩托_E398; RU = House N13 / Vendor / SE_K700.
- Сравнил все 93 PNG: RU перерисованы фоны back1-15 (176x208→240x320), LOGO, меню, toolback; спрайты предметов идентичны; в CN 2 лишних файла back1(EXTD).png (704x832) и back1(EXTD+).png (1408x1664) — независимые HD-перерисовки первой комнаты (4x и 8x), кодом не используются.
- MIDI идентичны (md5): menu.mid, mapbg.mid.
- Пароль сейфа в обеих = 9819, ввод первых 4 цифр.
- VLM-анализ графики: LOGO1.png = 三更灵异事件 (титул, в обеих версиях, включая RU!), logore.png = 按任意键继续 (тоже китайская даже в RU — локализаторы не перевели!), меню CN: 开始游戏/关于游戏/游戏设置/游戏帮助/退出游戏, меню RU: ИГРА/АВТОР/ЗВУК/ИНФО/ВЫХОД. Иконка CN = аниме-девушка, RU = кровь/луна/город.
- Веб-поиск (кит.): Baidu Baike 三更灵异事件 (ID 15428325): жанр RPG, 0.66M, полное прохождение в статье, источник — статья «三更灵异事件游戏流程全攻略» на 东北网 от 2008-12-02.
- Sina Games жёлтая страница компании (ID 1166): 北京无限乐游科技有限公司,简称 无限乐游, игры: 三更灵异事件 (kjava/rpg, 2008-12-02), 美眉扑克珍藏版 (2008-11-25), 麻将2007 (2008-04-16).
- Тот же email game0311kf@126.com у 浙江广视信息科技有限公司 (Sina ID 1750, игра 三戏杨贵妃) → общий саппорт, «0311» = код Шицзячжуана (Хэбэй) → реальная команда работала из Шицзячжуана под двумя юрлицами.
- 古宝魅影 имеет статью Baidu Baike (ID 15659299): мобильная RPG с Nokia-платформой. Неиспользуемые строки про Эмму/меч/камень/дворец в коде House13 — вероятные остатки кодбазы 古宝魅影 (кросс-промо в текстах книжного шкала).
- Найдены следы: jvgm.cc (三更之灵异事件, 240x320, 268.5KB, jar — возможно CN 240x320-билд), duote.com (三更灵异事件 S60V2, 275KB = 176x208).
- Переводчик RU: V.P.K (vova2const@mail.ru), дистрибуция через SEclub (упоминание «вошли на SEclub» в сцене с ПК).

Stage Summary:
- Игра = 三更灵异事件 (Sāngēng Língyì Shìjiàn), «Паранормальные явления третьей стражи» (三更 = третья ночная стража, ~23:00–01:00). «Дом №13» — выдумка русских локализаторов (из их же выдуманной предыстории про пятницу 13-е).
- Разработчик = 北京无限乐游科技有限公司 (Beijing Wuxian Leyou Technology), мелкая студия ~2007-2008, публиковалась через мобильный портал Sina; фактически работала из Шицзячжуана (общий саппорт-email с Zhejiang Guangshi). Родственная игра 古宝魅影 (вероятно общая кодовая база).
- RU-версия построена из CN-кода (идентичные баги, мёртвый код), но существенно доработана: 240x320, Nokia FullCanvas, переписанная коллизионная система, вольный перевод с изменённым сюжетом.
- Все материалы сохранены в /home/z/my-project/house13/, итоговый отчёт — /home/z/my-project/download/.

---
Task ID: 4-report
Agent: main (Super Z)
Task: Генерация итогового отчёта DOCX по результатам исследования.

Work Log:
- Загружен навык docx, изучены create.md, design-system.md (рецепт R1), common-rules.md, docx-js-core.md, toc.md, report.md.
- Написаны скрипты: scripts/house13_report.js (обложка R1, стили, хелперы), scripts/house13_report_content.js (10 разделов, 5 таблиц), scripts/postprocess_docx.py (патч ROMAN/arabic футеров, удаление pgNumType).
- Прогенерирован документ, добавлены TOC-заполнители (23 заголовка), выполнена пост-обработка.
- postcheck.py: 8/9, 0 ошибок (1 предупреждение о межстрочных интервалах — особенность таблиц, приемлемо).
- Визуальная проверка через конвертацию в PDF (14 страниц) и VLM-анализ обложки, оглавления, таблиц: дефектов нет, иероглифы отображаются корректно.

Stage Summary:
- Итоговый отчёт: /home/z/my-project/download/Дом_№13_отчёт_об_исследовании.docx (14 стр.: резюме, идентификация игры, разработчик, сравнение версий, архитектура, логика, словарь перевода 3 таблицы, заметки для портирования, рекомендация движка, источники).

---
Task ID: 5-repo
Agent: main (Super Z)
Task: Создание приватного GitHub-репозитория под наработки и worklog; фиксация архитектурных решений по ассетам и движку; ответы на вопросы пользователя.

Work Log:
- Уточнение от пользователя: back1(EXTD).png (704×832, ×4 от CN 176×208) и back1(EXTD+).png (1408×1664, ×8) — его собственные эксперименты с AI-апскейлом при планировании GameMaker-переноса; не игровые ассеты. Зафиксировано в README.
- GameMaker отклонён пользователем (незнаком инструмент) — выбран путь мини-движка.
- Решение по ассетам: НЕ апскейлить сейчас. 800×600 отвергнуто (ландшафт 4:3 vs портрет 3:4; нецелые множители 3.33×/1.875×). Схема: рендер в логическом 240×320 + целочисленный блит (×2/×3/×4); HD-пак опционально позже, базой RU 240×320, множитель ×4.
- Решение по движку: C++17 + SDL2 (+SDL_image/SDL_mixer), CMake; слой engine/ (тонкий) + game/ (порт логики); цикл: логика 10 Гц фикс., рендер vsync; Android через SDL2 android-project (gradle+NDK). MIDI→OGG — в дорожную карту (fluidsynth/timidity в песочнице отсутствуют).
- Проверен токен GitHub (аккаунт christopher-vn, ID 50106757), через API создан приватный репозиторий house13-port (private: true, ветка main).
- Структура: README (идентификация, выводы, решения, дорожная карта 0–6), docs/ (worklog + отчёт DOCX), reference/ (CN/RU декомп, HOUSE13_DOC.txt, House.zip как provenance), .gitignore под будущую сборку.
- Коммит 3a30646 (193 файла, 7.7 МБ), push в origin/main, проверен ls-remote.

Stage Summary:
- Репозиторий: https://github.com/christopher-vn/house13-port (приватный), main = 3a30646.
- Зафиксировано в README: цель C++17+SDL2, логическое разрешение 240×320 с целочисленным апскейлом, цикл 10 Гц/ vsync-рендер, дорожная карта 0–6 (0 = MIDI→OGG, 1 = скелет движка, 6 = Android).
- Пользователю рекомендовано перевыпустить токен (засвечен в чате).
- Локальное зеркало репо: /home/z/my-project/house13-port/ (токен только в .git/config, в файлы репо не попадает).
- Следующие шаги предложены: скелет движка («первый свет»: окно 240×320 + целочисленный блит + цикл 10 Гц + back1.png) и параллельно вычитанный перевод CN→RU по комнатам.
