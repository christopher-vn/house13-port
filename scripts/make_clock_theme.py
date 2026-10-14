#!/usr/bin/env python3
"""ЭТАП 5d: третья фоновая мелодия «Шкатулка» (mapbg3) — крыло головоломок.

Комнаты 9-15 (библиотека, туалет, кладовые, шкаф, карта, машина) — игрок
подолгу разбирается в секретах, и одна mapbg на весь дом приедается.
«Тени» (mapbg2) остаются в тёмном крыле; «Шкатулка» — самостоятельная
пьеса БЕЗ родства с mapbg (там колокольный дрон на C-G, тут — другой
лад, размер и фактура):

  - ля-минорный ВАЛЬС 3/4, 90 уд/мин, луп T=90 c (такт = 2 c, 45 тактов);
  - музыкальная шкатулка: яркие негармонические частичные, быстрый затух;
  - сквозная драматургия:
      A  (0-7)   соло шкатулки, тема (pp);
      A' (8-15)  тема октавой выше + арпеджио + вальсовый бас (p);
      B  (16-23) контрастная фраза, полный состав + педаль (mf);
      A''(24-31) возвращение темы с эхом в одну восьмую (пустой зал);
      C  (32-39) нисходящая секвенция, разрежение (tense, pp);
      кода(40-44) эхо темы, последняя высокая ната затухает, ЧАСЫ
                  ОСТАНАВЛИВАЮТСЯ в такте 43 — 3 секунды мёртвой тишины,
                  луп перезапускается внезапным тиком;
  - тик-так маятника — позвоночник пьесы (1.5 Гц), панорама вправо;
  - педаль — пары расстроенных синусов (биение ~1.5 Гц), L/R разводка;
  - все события затухают внутри лупа, стык математически чист
    (последний звук — тик на t=87.33 c, далее цифровой ноль).

Выход: assets/audio/midi/mapbg3.mid (исходник-документация, type-1,
4 дорожки), assets/audio/mapbg3.ogg (44100 stereo, q5).
"""
import struct
import subprocess
import numpy as np

ROOT = "/home/z/my-project/house13-port"
SR = 44100
T = 90.0                       # длина лупа, сек
N = int(SR * T)
BAR = 2.0                      # такт 3/4 при 90 уд/мин
BEAT = BAR / 3.0               # четверть = 2/3 c

# --------------------------------------------------------------- ноты ----
def hz(midi: float) -> float:
    return 440.0 * 2 ** ((midi - 69) / 12)

# Аккорды: корни (бас) и тоны (арпеджио/педаль), ля-минорная гармония
CH = {
    "Am": (45, [57, 60, 64]),   # A2 / A3 C4 E4
    "Em": (40, [52, 55, 59]),   # E2 / E3 G3 B3
    "F":  (41, [53, 57, 60]),   # F2 / F3 A3 C4
    "E7": (40, [52, 56, 59]),   # E2 / E3 G#3 B3
    "C":  (48, [55, 59, 62]),   # C3 / G3 B3 D4 (обращение, без бубна)
    "G":  (43, [55, 59, 62]),   # G2 / G3 B3 D4
    "Dm": (38, [50, 53, 57]),   # D2 / D3 F3 A3
}

# Гармония 45 тактов: A | A' | B | A'' | C | кода
PROG = (
    ["Am", "Em", "Am", "Em", "F", "E7", "Am", "Am"]        # A  0-7
    + ["Am", "Em", "Am", "Em", "F", "E7", "Am", "Am"]      # A' 8-15
    + ["C", "G", "Am", "Em", "F", "Dm", "E7", "E7"]        # B  16-23
    + ["Am", "Em", "Am", "Em", "F", "E7", "Am", "Am"]      # A'' 24-31
    + ["Am", "F", "Dm", "E7", "Am", "F", "E7", "E7"]       # C  32-39
    + ["Am", "Am", "Am", "Am", "Am"]                       # кода 40-44
)

# Тема шкатулки (такт, доля, MIDI, длина в долях) — интонация «колыбельной,
# которую забыли остановить»
THEME = [
    (0, 0, 69, 1), (0, 1, 72, 1), (0, 2, 76, 1),      # A4 C5 E5
    (1, 0, 76, 2), (1, 2, 74, 1),                     # E5.. D5
    (2, 0, 72, 1), (2, 1, 69, 1), (2, 2, 72, 1),
    (3, 0, 71, 2), (3, 2, 67, 1),                     # B4.. G4
    (4, 0, 69, 1), (4, 1, 72, 1), (4, 2, 77, 1),      # A4 C5 F5
    (5, 0, 68, 1), (5, 1, 71, 1), (5, 2, 76, 1),      # G#4 B4 E5
    (6, 0, 81, 2), (6, 2, 76, 1),                     # A5.. E5
    (7, 0, 72, 2), (7, 2, 69, 1),                     # C5.. A4
]
# Контрастная фраза B (такты 16-23)
PHRASE_B = [
    (16, 0, 79, 1), (16, 1, 76, 1), (16, 2, 72, 1),   # G5 E5 C5
    (17, 0, 74, 2), (17, 2, 71, 1),
    (18, 0, 72, 1), (18, 1, 76, 1), (18, 2, 81, 1),
    (19, 0, 79, 2), (19, 2, 78, 1),                   # G5.. F#5
    (20, 0, 77, 1), (20, 1, 81, 1), (20, 2, 84, 1),   # F5 A5 C6
    (21, 0, 86, 2), (21, 2, 81, 1),                   # D6.. A5
    (22, 0, 76, 1), (22, 1, 80, 1), (22, 2, 83, 1),   # E5 G#5 B5
    (23, 0, 76, 3),                                   # E5 (весь такт)
]
# Нисходящая секвенция моста C (такты 32-35) + завершение
BRIDGE_C = [
    (32, 0, 76, 1), (32, 1, 72, 1), (32, 2, 69, 1),   # E5 C5 A4
    (33, 0, 74, 1), (33, 1, 71, 1), (33, 2, 67, 1),   # D5 B4 G4
    (34, 0, 72, 1), (34, 1, 69, 1), (34, 2, 65, 1),   # C5 A4 F4
    (35, 0, 71, 1), (35, 1, 68, 1), (35, 2, 64, 1),   # B4 G#4 E4
    (36, 0, 69, 3),                                   # A4
    (37, 0, 72, 2), (37, 2, 69, 1),
    (38, 0, 71, 1), (38, 1, 68, 1), (38, 2, 76, 1),
    (39, 0, 76, 3),
]
# Кода: эхо темы (40), последняя нота (41)
CODA = [
    (40, 0, 69, 1), (40, 1, 72, 1), (40, 2, 76, 1),
    (41, 0, 81, 3),                                   # A5 — затухает
]

# Динамика по секциям (множитель голосов)
DYN = {0: 1.00, 8: 1.10, 16: 1.25, 24: 1.15, 32: 0.80, 40: 0.60}

def dyn(bar: int) -> float:
    d = 1.0
    for start, v in DYN.items():
        if bar >= start:
            d = v
    return d


# ------------------------------------------------------------- голоса ----
def musicbox(f0: float, dur: float) -> np.ndarray:
    """Шкатулка: яркие частичные, быстрый стеклянный затух, щелчок зубца."""
    n = max(1, int(dur * SR))
    t = np.arange(n) / SR
    out = np.zeros(n)
    for mult, amp, tau in [(1.0, 1.0, 1.0), (2.0, 0.5, 0.55),
                           (3.01, 0.25, 0.33), (4.16, 0.11, 0.18)]:
        out += amp * np.sin(2 * np.pi * f0 * mult * t) * np.exp(-t / tau)
    atk = int(0.004 * SR)
    out[:atk] *= np.linspace(0, 1, atk)
    return out * 0.16


def pluck(f0: float, dur: float) -> np.ndarray:
    """Мягкий щипок (арпеджио/«па» вальса): синус + октава, τ 0.25."""
    n = max(1, int(dur * SR))
    t = np.arange(n) / SR
    out = (np.sin(2 * np.pi * f0 * t) + 0.35 * np.sin(2 * np.pi * 2 * f0 * t))
    out *= np.exp(-t / 0.25)
    atk = int(0.002 * SR)
    out[:atk] *= np.linspace(0, 1, atk)
    return out * 0.075


def bassnote(f0: float, dur: float) -> np.ndarray:
    """Вальсовый бас: глубокий синус + чуть октавы, τ 0.6."""
    n = max(1, int(dur * SR))
    t = np.arange(n) / SR
    out = (np.sin(2 * np.pi * f0 * t) + 0.4 * np.sin(2 * np.pi * 2 * f0 * t))
    out *= np.exp(-t / 0.6)
    atk = int(0.008 * SR)
    out[:atk] *= np.linspace(0, 1, atk)
    return out * 0.11


def pad_chord(freqs, dur: float) -> np.ndarray:
    """Педаль: пары расстроенных синусов (биение ~1.5 Гц), мягкие склоны."""
    n = max(1, int(dur * SR))
    t = np.arange(n) / SR
    out = np.zeros(n)
    for f in freqs:
        beat = 1.5 / 2.0                     # расстройка ±0.75 Гц
        out += (np.sin(2 * np.pi * (f - beat) * t)
                + np.sin(2 * np.pi * (f + beat) * t))
    out /= 2.0 * len(freqs)
    a = int(0.4 * SR)
    r = int(0.8 * SR)
    env = np.ones(n)
    env[:min(a, n)] = np.linspace(0, 1, min(a, n))
    if n > r:
        env[-r:] *= np.linspace(1, 0, r)
    return out * env * 0.05


def tick_click(f0: float) -> np.ndarray:
    """Тик маятника: щелчок + короткий резонанс."""
    n = int(0.05 * SR)
    t = np.arange(n) / SR
    rng = np.random.default_rng(20081013)    # дата выхода оригинала
    click = rng.standard_normal(n) * np.exp(-t / 0.004)
    ping = np.sin(2 * np.pi * f0 * t) * np.exp(-t / 0.02)
    out = 0.35 * click + ping
    atk = int(0.001 * SR)
    out[:atk] *= np.linspace(0, 1, atk)
    return out * 0.055


# --------------------------------------------------------------- микс ----
def render() -> np.ndarray:
    left = np.zeros(N)
    right = np.zeros(N)

    def place(buf_l, buf_r, snd, start_sec, pan, gain=1.0):
        i0 = int(start_sec * SR)
        if i0 >= N:
            return
        i1 = min(N, i0 + len(snd))
        s = snd[: i1 - i0] * gain
        buf_l[i0:i1] += s * (1.0 - pan)
        buf_r[i0:i1] += s * pan

    def bt(bar, beat):
        return bar * BAR + beat * BEAT

    # --- тик-так: позвоночник, последний тик — финальная доля такта 43
    clock_beats = 44 * 3                    # последний тик t=87.33 c
    for k in range(clock_beats):
        f = 2200.0 if k % 2 == 0 else 1500.0
        g = 0.07 if 32 <= k // 3 < 40 else 1.0   # мост — часы тревожнее
        place(left, right, tick_click(f), k * BEAT, 0.62, g)

    # --- голоса по тактам ---
    for bar, chname in enumerate(PROG):
        root, tones = CH[chname]
        d = dyn(bar)
        section = (bar // 8) if bar < 40 else 5

        # бас: A — только корень через такт; далее полный ум-па-па
        if bar >= 8 and bar < 42:
            place(left, right, bassnote(hz(root), BEAT * 1.6), bt(bar, 0), 0.45, d)
            if bar >= 8 and section != 4:               # мост — без «па»
                for b in (1, 2):
                    for mn in (tones[1], tones[2]):
                        place(left, right, pluck(hz(mn), BEAT * 0.9),
                              bt(bar, b), 0.35, 0.7 * d)

        # арпеджио восьмыми: A' и далее (в мосту — тише)
        if 8 <= bar < 42:
            pattern = [tones[0], tones[1], tones[2],
                       tones[0] + 12, tones[2], tones[1]]
            g = 0.4 * d if section == 4 else d
            for i, mn in enumerate(pattern):
                place(left, right, pluck(hz(mn), BEAT * 0.8),
                      bt(bar, i * 0.5), 0.30, g)

        # педаль: с B-секции; в коде — только 2 первых такта
        if 16 <= bar < 42:
            place(left, right, pad_chord([hz(m) for m in tones], BAR * 1.2),
                  bt(bar, 0), 0.5, 0.9 * d)

    # --- шкатулка (лид) ---
    def lead(events, bar_shift=0, oct_shift=0, gain=1.0, echo=False):
        for bar, beat, midi, dur_b in events:
            t0 = bt(bar + bar_shift, beat)
            note = musicbox(hz(midi + oct_shift), dur_b * BEAT + 0.6)
            place(left, right, note, t0, 0.5, gain)
            if echo:                       # эхо пустого зала, восьмая спустя
                place(left, right, note, t0 + BEAT / 2, 0.58, gain * 0.35)

    lead(THEME)                                        # A: соло, pp
    lead(THEME, 8, 12, 0.55)                           # A': блеск октавой
    lead(PHRASE_B, gain=dyn(16))                       # B: контраст, mf
    lead(THEME, 24, 0, dyn(24), echo=True)             # A'': с эхом
    lead(BRIDGE_C, gain=dyn(32))                       # C: секвенция
    lead(CODA, gain=dyn(40))                           # кода

    stereo = np.stack([left, right], axis=1)
    peak = np.abs(stereo).max()
    return (stereo / peak * 0.75).astype(np.float32)


# --------------------------------------------------------------- MIDI ----
def write_midi(path: str):
    """Type-1 MIDI, 4 дорожки-документации: шкатулка/арпеджио/бас/часы."""
    TPQ = 480

    def track_bytes(events, program, channel):
        ev = []
        ev.append((0, 0, bytes([0xC0 | channel, program])))
        ev.append((0, 0, bytes([0xFF, 0x51, 0x03])
                   + (666667).to_bytes(3, "big")))     # 90 уд/мин
        for tick, order, payload in events:
            ev.append((int(tick), order, payload))
        ev.sort()
        tr = bytearray()
        last = 0
        for tick, _, payload in ev:
            delta = tick - last
            last = tick
            vlq = []
            v = delta
            while True:
                vlq.insert(0, v & 0x7F)
                v >>= 7
                if v == 0:
                    break
            for i, b in enumerate(vlq):
                tr.append(b | (0x80 if i < len(vlq) - 1 else 0))
            tr += payload
        tr += b"\x00\xFF\x2F\x00"
        return bytes(tr)

    def note_events(notes, channel, base_tick=0):
        """notes: (такт, доля, midi, длина_в_долях, vel)."""
        ev = []
        for bar, beat, midi, dur_b, vel in notes:
            on = int((bar * 3 + beat) * TPQ) + base_tick
            off = int((bar * 3 + beat + dur_b) * TPQ) + base_tick
            ev.append((on, 1, bytes([0x90 | channel, midi, vel])))
            ev.append((off, 2, bytes([0x80 | channel, midi, 0])))
        return ev

    def bt_notes(bar, beat):
        return int((bar * 3 + beat) * TPQ)

    # дорожка 0: шкатулка (сдвиги тактов — секции A' и A'')
    lead_notes = [(b, k, m, d, 84) for b, k, m, d in THEME]
    lead_notes += [(b + 8, k, m + 12, d, 70) for b, k, m, d in THEME]
    lead_notes += [(b, k, m, d, 90) for b, k, m, d in PHRASE_B]
    lead_notes += [(b + 24, k, m, d, 84) for b, k, m, d in THEME]
    lead_notes += [(b, k, m, d, 84) for b, k, m, d in BRIDGE_C]
    lead_notes += [(b, k, m, d, 70) for b, k, m, d in CODA]
    t0 = track_bytes(note_events(lead_notes, 0), 10, 0)       # Music Box

    # дорожка 1: арпеджио + «па»
    arp_notes = []
    for bar, chname in enumerate(PROG):
        _, tones = CH[chname]
        if 8 <= bar < 42:
            pattern = [tones[0], tones[1], tones[2],
                       tones[0] + 12, tones[2], tones[1]]
            for i, mn in enumerate(pattern):
                arp_notes.append((bar, i * 0.5, mn, 0.45, 48))
            for b in (1, 2):
                for mn in (tones[1], tones[2]):
                    arp_notes.append((bar, b, mn, 0.4, 40))
    arp_notes = [(int(b), int(k) if float(k).is_integer() else k, m, d, v)
                 for b, k, m, d, v in arp_notes]
    # дробные доли -> тики вручную
    ev1 = []
    for bar, beat, midi, dur_b, vel in arp_notes:
        on = int(round((bar * 3 + beat) * TPQ))
        off = int(round((bar * 3 + beat + dur_b) * TPQ))
        ev1.append((on, 1, bytes([0x91, midi, vel])))
        ev1.append((off, 2, bytes([0x81, midi, 0])))
    t1 = track_bytes(ev1, 24, 1)                              # Nylon Guitar

    # дорожка 2: бас
    bass_notes = []
    for bar, chname in enumerate(PROG):
        root, _ = CH[chname]
        if 8 <= bar < 42:
            bass_notes.append((bar, 0, root, 1.5, 66))
    t2 = track_bytes(note_events(bass_notes, 2), 32, 2)       # Acoustic Bass

    # дорожка 3: часы (Woodblock)
    ev3 = []
    for k in range(44 * 3):
        on = int(round(k * TPQ))
        ev3.append((on, 1, bytes([0x99, 76 if k % 2 == 0 else 58, 40])))
    t3 = track_bytes(ev3, 115, 9)                             # Woodblock

    def chunk(tag, data):
        return tag + struct.pack(">I", len(data)) + data

    with open(path, "wb") as f:
        f.write(chunk(b"MThd", struct.pack(">HHH", 1, 4, TPQ)))
        f.write(chunk(b"MTrk", t0))
        f.write(chunk(b"MTrk", t1))
        f.write(chunk(b"MTrk", t2))
        f.write(chunk(b"MTrk", t3))
    print(f"MIDI: {path} ({len(t0)+len(t1)+len(t2)+len(t3)+38} байт)")


def main():
    midi_path = f"{ROOT}/assets/audio/midi/mapbg3.mid"
    wav_path = "/tmp/mapbg3.wav"
    ogg_path = f"{ROOT}/assets/audio/mapbg3.ogg"
    write_midi(midi_path)
    stereo = render()
    # контроль стыка: последние 2.5 с — цифровой ноль (кроме уже нулевых)
    tail = stereo[-int(2.5 * SR):]
    assert np.abs(tail).max() == 0.0, "хвост лупа должен быть тишиной"
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
    print(f"OGG:  {ogg_path} ({dur:.1f} c, пик 0.75, "
          f"стих: часы стоят с 87.3 c, стык чист)")


if __name__ == "__main__":
    main()
