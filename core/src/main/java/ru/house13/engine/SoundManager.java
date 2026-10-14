package ru.house13.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

/**
 * Порт музыкальной подсистемы оригинала (MySound + createBackMid/playBackMid/
 * closedMid/changeClosedMid из MainCanvas/b).
 *
 * Каналы (индексы как в оригинале):
 *   0 = menu.mid  -> audio/menu.ogg   (menu, logo_Back/bR)
 *   1 = get.mid    -> audio/get.ogg     (get_M/cC — подбор предмета)
 *   2 = wrong.mid  -> audio/wrong.ogg   (wrong_M/bx — «Неправильное использование!»)
 *   3 = correct.mid-> audio/correct.ogg (correct_M/eg — пазл решён)
 *   4 = mapbg.mid -> audio/mapbg.ogg  (jieshu_Back)
 *
 * ЭТАП 4, РЕСТАВРАЦИЯ ЗВУКА: в оригинале файлы get/wrong/correct.mid
 * отсутствовали в JAR (проверено md5-сверкой всех трёх билдов), а вызовы
 * playBackMid(1..3) не были подключены — каналы только останавливались
 * каскадом keyPressed (changeClosedMid(1),(2),(3) — эти вызовы в коде ЕСТЬ).
 * Мы сочинили три джингла в стиле игры (assets/audio/midi/*.mid —
 * музыкальная шкатулка/безладовый бас/синт-струны из её же MIDI-файлов)
 * и подключили их в семантических точках (CreateTool, «Неправильное
 * использование!», успехи пазлов). Выключается: -Dhouse13.sfx=false.
 *
 * Громкость 0.7 — порт VolumeControl.setLevel(70) из MySound.createSound().
 * Каналы 0/4 зациклены (оригинал вызывал player.start() каждый кадр =
 * перезапуск на конце дорожки); каналы 1-3 — one-shot, при повторном
 * playBackMid звук начинается с начала (MMAPI start() на доигравшем
 * плеере перезапускал бы его с нуля на свежесозданном плеере).
 *
 * Фабрика Music внедряется для юнит-тестов (без Gdx.audio).
 */
public final class SoundManager implements Disposable {

    public static final int CH_MENU = 0, CH_GET = 1, CH_WRONG = 2, CH_CORRECT = 3, CH_MAPBG = 4;

    /** MySound.canSound — общий выключатель (статический, как в оригинале). */
    public static boolean canSound = true;

    /** Реставрация SFX get/wrong/correct (этап 4); -Dhouse13.sfx=false отключает. */
    public static final boolean SFX_RESTORED =
        !"false".equalsIgnoreCase(System.getProperty("house13.sfx", "true"));

    /** Имена файлов каналов. */
    private static final String[] TRACKS = {
        "menu.ogg", "get.ogg", "wrong.ogg", "correct.ogg", "mapbg.ogg"
    };

    /** Зацикливание: фоновые каналы 0/4 — да, one-shot 1/3 — нет. */
    private static final boolean[] LOOPING = {true, false, false, false, true};

    private static final float VOLUME = 0.7f; // VolumeControl.setLevel(70)

    /** Создание Music по имени файла; в тестах подменяется фейком. */
    public interface Factory {
        Music create(String file);
    }

    private final Factory factory;
    private final Music[] channels = new Music[TRACKS.length];
    private final boolean[] dead = new boolean[TRACKS.length]; // неудачная загрузка — не спамим
    private final boolean[] wasPlaying = new boolean[TRACKS.length];

    public SoundManager(Factory factory) {
        this.factory = factory;
    }

    /** Фабрика по умолчанию: Gdx.audio из internal("audio/<file>"). */
    public static SoundManager createDefault() {
        return new SoundManager(file -> {
            if (Gdx.audio == null) {
                return null; // headless-прогон (-Dhouse13.audio=false): тишина
            }
            return Gdx.audio.newMusic(Gdx.files.internal("audio/" + file));
        });
    }

    /** createBackMid(i): создать канал, если звук включён и трек существует. */
    public void createBackMid(int i) {
        if (!canSound || channels[i] != null || TRACKS[i] == null || dead[i]) {
            return;
        }
        try {
            Music m = factory.create(TRACKS[i]);
            if (m == null) {
                dead[i] = true;
                return;
            }
            m.setLooping(LOOPING[i]);
            m.setVolume(VOLUME);
            channels[i] = m;
        } catch (Exception e) {
            dead[i] = true; // как в оригинале: отсутствие звука не роняет игру
            log("канал " + i + " недоступен: " + e);
        }
    }

    /**
     * playBackMid(i): играть, если звук включён. Повторный вызов для уже
     * играющего канала — no-op (MMAPI start() на запущенном плеере).
     * One-shot каналы (1-3) при доигранном состоянии стартуют с начала.
     */
    public void playBackMid(int i) {
        if (canSound && channels[i] != null && !channels[i].isPlaying()) {
            if (!LOOPING[i]) {
                channels[i].setPosition(0); // перезапуск one-shot с начала
            }
            channels[i].play();
        }
    }

    /** Событие «предмет получен» — канал get (реставрация этапа 4). */
    public void playGet() {
        if (SFX_RESTORED) {
            createBackMid(CH_GET);
            playBackMid(CH_GET);
        }
    }

    /** Событие «неверное использование» — канал wrong (реставрация этапа 4). */
    public void playWrong() {
        if (SFX_RESTORED) {
            createBackMid(CH_WRONG);
            playBackMid(CH_WRONG);
        }
    }

    /** Событие «пазл решён» — канал correct (реставрация этапа 4). */
    public void playCorrect() {
        if (SFX_RESTORED) {
            createBackMid(CH_CORRECT);
            playBackMid(CH_CORRECT);
        }
    }

    /** closedMid(i): остановить канал, ТОЛЬКО если звук выключен (как в оригинале). */
    public void closedMid(int i) {
        if (!canSound) {
            changeClosedMid(i);
        }
    }

    /** changeClosedMid(i): остановить и освободить канал безусловно. */
    public void changeClosedMid(int i) {
        if (channels[i] != null) {
            wasPlaying[i] = false;
            channels[i].stop();
            channels[i].dispose();
            channels[i] = null;
        }
    }

    public boolean isPlaying(int i) {
        return channels[i] != null && channels[i].isPlaying();
    }

    /** Остановить всё (выход из игры: bR.if() + runing=false + quitApp). */
    public void stopAll() {
        for (int i = 0; i < channels.length; i++) {
            changeClosedMid(i);
        }
    }

    /** Пауза при потере фокуса окна (desktop-полезность; в оригинале pauseApp). */
    public void pauseAll() {
        for (int i = 0; i < channels.length; i++) {
            if (channels[i] != null && channels[i].isPlaying()) {
                channels[i].pause();
                wasPlaying[i] = true;
            }
        }
    }

    /** Возобновление после возврата фокуса. */
    public void resumeAll() {
        for (int i = 0; i < channels.length; i++) {
            if (channels[i] != null && wasPlaying[i]) {
                channels[i].play();
                wasPlaying[i] = false;
            }
        }
    }

    @Override
    public void dispose() {
        stopAll();
    }

    private static void log(String msg) {
        if (Gdx.app != null) {
            Gdx.app.log("House13Sound", msg);
        }
    }
}
