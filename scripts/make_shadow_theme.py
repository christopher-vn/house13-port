#!/usr/bin/env python3
"""ЭТАП 5c: вторая фоновая мелодия «Тени» (mapbg2) — тема тёмного крыла дома.

mapbg оригинала — спа́рсный колокольный дрон на квинте C5/G4 с жутким
сдвигом на тритон F# к середине (FFT-анализ: 523/392 Гц, t=40s 740 Гц).
«Тени» написана в том же языке, но для страшных комнат (5-8 «там кто-то
есть / холод / нет ручки / наблюдают» и 16-17 «голоса»):

  - тон: ре-минор, квинта D-A (как C-G у mapbg, на тон ниже), регистр ниже;
  - колокола: те же негармонические частичные (bell-патч), фразы реже;
  - «холод»: сдвиг в Bb в середине (субдоминанта) — вместо тритона mapbg;
  - «взгляд»: единственный тритон Ab4 (раз в луп);
  - дрон: два расстроенных синуса (биение 1.25 Гц — «дыхание» дома) +
    суб-октава; фазы кратны длине лупа T=72 c — дрон математически
    бесшовен на стыке (k1=10548, k2=10638 периодов);
  - низкие удары D2 — «шаги в подвале» (медленная атака, длинный хвост).

Выход: assets/audio/midi/mapbg2.mid (исходник-документация),
assets/audio/mapbg2.ogg (44100 stereo, q5) — как у mapbg.ogg.
"""
import struct
import subprocess
import numpy as np

ROOT = "/home/z/my-project/house13-port"
SR = 44100
T = 72.0                      # длина лупа, сек
N = int(SR * T)

# --------------------------------------------------------------- ноты ----
# (сек, MIDI-нота, длительность сек) — колокольная тема
BELLS = [
    (2.0, 62, 3.5),           # D4
    (4.5, 69, 3.5),           # A4
    (7.5, 65, 3.0),           # F4
    (10.5, 69, 3.0),          # A4
    (13.5, 74, 5.0),          # D5
    (19.0, 62, 3.0),          # D4
    (22.5, 69, 3.0),          # A4
    (26.5, 65, 3.0),          # F4
    (30.0, 74, 4.0),          # D5
    (34.0, 58, 4.0),          # Bb3 — «холод»
    (38.0, 65, 4.0),          # F4
    (42.0, 70, 5.0),          # Bb4
    (47.0, 62, 3.0),          # D4
    (50.5, 69, 4.0),          # A4
    (54.0, 68, 7.0),          # Ab4 — тритон, «взгляд»
    (58.0, 74, 4.0),          # D5
    (61.0, 69, 4.0),          # A4
    (64.0, 62, 6.0),          # D4 — финал, хвост затихает к стыку
]
# (сек, MIDI, длительность) — низкие удары «шаги в подвале»
THUMPS = [(0.5, 38, 6.0), (24.0, 38, 6.0), (48.0, 38, 6.0)]
DRONE_MIDI = 50               # D3 — непрерывный дрон на весь луп


def hz(midi: float) -> float:
    return 440.0 * 2 ** ((midi - 69) / 12)


def bell(f0: float, dur: float, sr: int = SR) -> np.ndarray:
    """Колокол: негармонические частичные с разными постоянными затухания."""
    n = int(dur * sr)
    t = np.arange(n) / sr
    out = np.zeros(n)
    # (множитель частоты, амплитуда, тау затухания)
    for mult, amp, tau in [(1.0, 1.0, 3.0), (2.0, 0.55, 2.2),
                           (2.99, 0.30, 1.6), (4.19, 0.18, 1.1),
                           (5.43, 0.10, 0.8)]:
        out += amp * np.sin(2 * np.pi * f0 * mult * t) * np.exp(-t / tau)
    # атака 4 мс (лёгкий «щелчок» молоточка)
    atk = int(0.004 * sr)
    out[:atk] *= np.linspace(0, 1, atk)
    return out * 0.16


def thump(f0: float, dur: float, sr: int = SR) -> np.ndarray:
    """Низкий удар: синус + октава, медленная атака, длинный хвост."""
    n = int(dur * sr)
    t = np.arange(n) / sr
    out = (np.sin(2 * np.pi * f0 * t) + 0.4 * np.sin(2 * np.pi * 2 * f0 * t))
    out *= np.exp(-t / 2.5)
    atk = int(0.02 * sr)      # мягкий вход
    out[:atk] *= np.linspace(0, 1, atk)
    return out * 0.22


def render() -> np.ndarray:
    """Стерео-микс лупа: дрон + удары + колокола с чередующейся панорамой."""
    t = np.arange(N) / SR
    # дрон: биение 1.25 Гц, k1/k2 кратны T — бесшовный стык
    k1, k2 = 10548, 10638     # 146.50 / 147.75 Гц
    drone_f = [k1 / T, k2 / T]
    drone = (np.sin(2 * np.pi * drone_f[0] * t)
             + np.sin(2 * np.pi * drone_f[1] * t)) * 0.5
    drone += 0.4 * np.sin(2 * np.pi * drone_f[0] / 2 * t)   # суб-октава
    drone *= 0.055
    left = drone.copy()
    right = drone.copy()

    def place(buf_l, buf_r, snd, start_sec, pan):
        i0 = int(start_sec * SR)
        i1 = min(N, i0 + len(snd))
        s = snd[: i1 - i0]
        buf_l[i0:i1] += s * (1.0 - pan)
        buf_r[i0:i1] += s * pan

    for i, (ts, note, dur) in enumerate(THUMPS):
        place(left, right, thump(hz(note), dur), ts, 0.5)

    for i, (ts, note, dur) in enumerate(BELLS):
        pan = 0.62 if i % 2 == 0 else 0.38     # колокола блуждают L/R
        place(left, right, bell(hz(note), dur), ts, pan)

    # финальный фейд 0.5 с — хвосты не щёлкают на стыке (дрон непрерывен)
    fade = int(0.5 * SR)
    for buf in (left, right):
        buf[-fade:] *= np.linspace(1, 0, fade)

    stereo = np.stack([left, right], axis=1)
    peak = np.abs(stereo).max()
    return (stereo / peak * 0.62).astype(np.float32)


# --------------------------------------------------------------- MIDI ----
def write_midi(path: str):
    """Type-0 MIDI: тема (канал 0, GM 14 Tubular Bells) — документация
    сочинения; воспроизводимый движком звук — в mapbg2.ogg."""
    TPQ = 480                                     # тиков на четверть
    events = []                                   # (тик, порядок, байты)

    def add(tick, payload, order=1):
        events.append((int(tick * TPQ), order, bytes(payload)))

    add(0.0, [0xC0, 14], 0)                      # program: tubular bells
    add(0.0, [0xFF, 0x51, 0x03] + list(
        (1_000_000 // 2).to_bytes(3, "big")), 0)  # tempo 120 (480 тиков = 1 c)
    notes = [(DRONE_MIDI, 0.05, T - 0.1, 64)]     # (нота, старт, конец, vel)
    for ts, note, dur in THUMPS:
        notes.append((note, ts, ts + dur, 70))
    for ts, note, dur in BELLS:
        notes.append((note, ts, ts + dur, 84))
    for note, t_on, t_off, vel in notes:
        add(t_on, [0x90, note, vel], 1)
        add(t_off, [0x80, note, 0], 2)
    events.sort()

    track = bytearray()
    last = 0
    for tick, _, payload in events:
        delta = tick - last
        last = tick
        for b in reversed(struct.pack(">I", delta).lstrip(b"\x00")):
            pass
        # variable-length quantity
        vlq = []
        v = delta
        while True:
            vlq.insert(0, v & 0x7F)
            v >>= 7
            if v == 0:
                break
        for i, b in enumerate(vlq):
            track.append(b | (0x80 if i < len(vlq) - 1 else 0))
        track += payload
    track += b"\x00\xFF\x2F\x00"                 # end of track
    with open(path, "wb") as f:
        f.write(b"MThd" + struct.pack(">IHHH", 6, 0, 1, TPQ))
        f.write(b"MTrk" + struct.pack(">I", len(track)) + bytes(track))
    print(f"MIDI: {path} ({len(track) + 22} байт, {len(notes)} нот)")


def main():
    midi_path = f"{ROOT}/assets/audio/midi/mapbg2.mid"
    wav_path = "/tmp/mapbg2.wav"
    ogg_path = f"{ROOT}/assets/audio/mapbg2.ogg"
    write_midi(midi_path)
    stereo = render()
    pcm = (stereo * 32767).astype("<i2")
    import wave
    with wave.open(wav_path, "wb") as w:
        w.setnchannels(2)
        w.setsampwidth(2)
        w.setframerate(SR)
        w.writeframes(pcm.tobytes())
    subprocess.run(["ffmpeg", "-y", "-loglevel", "error",
                    "-i", wav_path, "-c:a", "libvorbis", "-q:a", "5",
                    ogg_path], check=True)
    dur = len(stereo) / SR
    print(f"OGG:  {ogg_path} ({dur:.1f} c, пик 0.62, дрон бесшовен)")


if __name__ == "__main__":
    main()
