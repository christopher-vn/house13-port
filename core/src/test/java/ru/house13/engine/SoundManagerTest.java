package ru.house13.engine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Порт MySound-каналов: create/play/closed/change + canSound + громкость 0.7.
 *  Этап 4: каналы 1-3 (get/wrong/correct) — реставрированы (one-shot). */
class SoundManagerTest {

    private final List<FakeMusic> created = new ArrayList<>();

    private SoundManager newManager() {
        return new SoundManager(f -> {
            FakeMusic m = new FakeMusic();
            created.add(m);
            return m;
        });
    }

    @AfterEach
    void resetCanSound() {
        SoundManager.canSound = true;
    }

    @Test
    void menuChannelCreatesPlaysLoopedAtVolume70() {
        SoundManager sm = newManager();
        sm.createBackMid(SoundManager.CH_MENU);
        sm.playBackMid(SoundManager.CH_MENU);
        assertTrue(sm.isPlaying(SoundManager.CH_MENU));
        FakeMusic m = created.get(0);
        assertTrue(m.looping, "оригинал зовёт player.start() каждый кадр -> зацикливание");
        assertEquals(0.7f, m.volume, 1e-6, "VolumeControl.setLevel(70)");
    }

    @Test
    void sfxChannelsAreOneShotNotLooped() {
        // реставрация этапа 4: get/wrong/correct существуют и НЕ зациклены
        SoundManager sm = newManager();
        sm.createBackMid(SoundManager.CH_GET);
        sm.createBackMid(SoundManager.CH_WRONG);
        sm.createBackMid(SoundManager.CH_CORRECT);
        sm.playBackMid(SoundManager.CH_GET);
        sm.playBackMid(SoundManager.CH_WRONG);
        sm.playBackMid(SoundManager.CH_CORRECT);
        assertTrue(sm.isPlaying(SoundManager.CH_GET));
        assertTrue(sm.isPlaying(SoundManager.CH_WRONG));
        assertTrue(sm.isPlaying(SoundManager.CH_CORRECT));
        assertFalse(created.get(0).looping, "one-shot эффекты не зацикливаются");
        assertFalse(created.get(2).looping);
        assertEquals(0.7f, created.get(0).volume, 1e-6);
    }

    @Test
    void conveniencePlayGetWrongCorrect() {
        SoundManager sm = newManager();
        sm.playGet();
        sm.playWrong();
        sm.playCorrect();
        assertTrue(sm.isPlaying(SoundManager.CH_GET));
        assertTrue(sm.isPlaying(SoundManager.CH_WRONG));
        assertTrue(sm.isPlaying(SoundManager.CH_CORRECT));
        // повторный вызов при играющем — no-op (MMAPI start())
        FakeMusic get = created.get(0);
        sm.playGet();
        assertEquals(1, get.playCount);
    }

    @Test
    void oneShotRestartsFromBeginningAfterFinish() {
        // доигравший one-shot перезапускается с нуля (setPosition(0) + play)
        SoundManager sm = newManager();
        sm.playGet();
        FakeMusic get = created.get(0);
        get.playing = false;          // «дориграл» (FakeMusic не тикает сам)
        sm.playGet();
        assertEquals(2, get.playCount, "повторная находка — звук с начала");
    }

    @Test
    void cascadeCutDisposesSfxChannels() {
        // каскад byte() гасит каналы 1-3 при каждом нажатии — как в оригинале
        SoundManager sm = newManager();
        sm.playGet();
        sm.changeClosedMid(SoundManager.CH_GET);
        assertFalse(sm.isPlaying(SoundManager.CH_GET));
        assertTrue(created.get(0).disposed);
        // следующий вызов создаёт заново
        sm.playWrong();
        assertTrue(sm.isPlaying(SoundManager.CH_WRONG));
    }

    @Test
    void playDoesNothingWhenCanSoundOff() {
        SoundManager sm = newManager();
        sm.createBackMid(SoundManager.CH_MENU);
        SoundManager.canSound = false;
        sm.playBackMid(SoundManager.CH_MENU);
        assertFalse(sm.isPlaying(SoundManager.CH_MENU));
        assertEquals(0, created.get(0).playCount);
    }

    @Test
    void closedMidStopsOnlyWhenSoundOff_changeClosedMidAlways() {
        SoundManager sm = newManager();
        sm.createBackMid(SoundManager.CH_MENU);
        sm.playBackMid(SoundManager.CH_MENU);
        FakeMusic menu = created.get(0);

        // closedMid — только при выключенном звуке (как в оригинале)
        sm.closedMid(SoundManager.CH_MENU);
        assertTrue(sm.isPlaying(SoundManager.CH_MENU), "звук включён — closedMid бездействует");

        SoundManager.canSound = false;
        sm.closedMid(SoundManager.CH_MENU);
        assertFalse(sm.isPlaying(SoundManager.CH_MENU));
        assertTrue(menu.disposed);

        // changeClosedMid — безусловно (переходы меню<->игра)
        SoundManager.canSound = true;
        sm.createBackMid(SoundManager.CH_MAPBG);
        sm.playBackMid(SoundManager.CH_MAPBG);
        FakeMusic mapbg = created.get(1);
        sm.changeClosedMid(SoundManager.CH_MAPBG);
        assertFalse(sm.isPlaying(SoundManager.CH_MAPBG));
        assertTrue(mapbg.disposed);
    }

    @Test
    void stopAllDisposesEverything() {
        SoundManager sm = newManager();
        sm.createBackMid(SoundManager.CH_MENU);
        sm.createBackMid(SoundManager.CH_MAPBG);
        sm.playBackMid(SoundManager.CH_MENU);
        sm.playBackMid(SoundManager.CH_MAPBG);
        sm.stopAll();
        assertFalse(sm.isPlaying(SoundManager.CH_MENU));
        assertFalse(sm.isPlaying(SoundManager.CH_MAPBG));
        assertTrue(created.get(0).disposed);
        assertTrue(created.get(1).disposed);
    }

    @Test
    void replayIsIdempotentLikePerFrameStart() {
        // оригинал зовёт play каждый кадр; уже играющий канал не перезапускается
        SoundManager sm = newManager();
        sm.createBackMid(SoundManager.CH_MENU);
        sm.playBackMid(SoundManager.CH_MENU);
        sm.playBackMid(SoundManager.CH_MENU);
        sm.playBackMid(SoundManager.CH_MENU);
        assertEquals(1, created.get(0).playCount);
    }

    @Test
    void focusPauseResume() {
        SoundManager sm = newManager();
        sm.createBackMid(SoundManager.CH_MENU);
        sm.playBackMid(SoundManager.CH_MENU);
        sm.pauseAll();
        assertFalse(sm.isPlaying(SoundManager.CH_MENU));
        sm.resumeAll();
        assertTrue(sm.isPlaying(SoundManager.CH_MENU));
    }
}
