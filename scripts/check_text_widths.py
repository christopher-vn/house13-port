#!/usr/bin/env python3
"""Проверка ширины текстов паков (ЭТАП 5C/5D — диалоговый бокс порта).

Бокс порта (dialogBoxRender + TextFmt.wrapWords): перенос ПО СЛОВАМ,
лимит 216px = WIDTH - 2*fW оригинала (полноширинный CJK fW=12) — этап 5d
уравнял перенос с оригиналом (было 240-2*«В»=226 при узкой «В»=7px из
Fusion Pixel). Текст начинается с x=10, бокс с рамкой 1..238.
Текст длиннее 2 строк больше НЕ обрезается — пейджер листает страницы;
ПРОВАЛОМ считается только слово шире лимита бокса.

Тексты проверяются ПОСЛЕ рантайм-нормализации TextFmt.norm: мульти-
пробелы сжаты, после знаков препинания добавлены пробелы. Python-порт
нормализации обязан совпадать с Java-реализацией (тесты TextFmtTest).

Запуск: python3 scripts/check_text_widths.py
"""
import json
import re
import sys

from PIL import ImageFont

ROOT = "/home/z/my-project/house13-port"
FONT = ImageFont.truetype(f"{ROOT}/assets/fonts/FusionPixel12.otf", 12)

# Строки диалогового бокса (bf)
BF_KEYS = [
    "R0_DOOR_HINT_KEY", "R0_DOOR_LOCKED", "MIND_CONTROL", "R8_HAMMER_STUCK",
    "R1_KEY_TAKEN", "R1_KEY_GONE", "R10_NOTHING", "R10_TOILET", "R11_BOXES",
    "R9_LIBRARY", "R13_BOOKS_0", "R13_BOOKS_1", "R13_BOOKS_2", "R13_BOOKS_3",
    "R0_PHONE_HOVER", "R0_PHONE_KEY_HOVER", "R0_BOTTOM_DOOR", "R1_SAFE_KEY",
    "R5_DOOR_0", "R5_DOOR_1", "R3_NEED_KEY", "R6_DOOR_COLD",
    "R7_NO_HANDLE_0", "R7_NO_HANDLE_1", "R10_GREEN_KEY", "R8_WATCHED",
    "R17_VOICE", "R16_ANGRY", "R16_ROPE", "R9_BOOKS_TAKEN", "R11_WHAT_IS_IT",
    "R11_BREAK_HANDLE", "R12_COIN", "R13_WARDROBE_0", "R13_WARDROBE_1",
    "R13_LID", "R14_BATTERY", "R14_MAP_HOVER", "R3_BIG_KEY_FITS",
    "R0_KEY_OPENS", "R16_ROPE_ON_BEAM", "R5_GREEN_KEY_FITS", "R8_SWAP_HAMMER",
    "R14_HAMMER_WALL", "R6_SMALL_KEY_FITS", "R7_HANDLE_FITS", "R11_LID_FITS",
    "R13_BOOKS_FIT", "R13_SECRET", "R15_BATTERY_IN", "R15_COIN_IN",
    "WRONG_USE", "SAFE_WRONG", "SAFE_OPEN", "R0_PHONE_KEY_TAKEN",
    "WAKEUP_0", "WAKEUP_1", "WAKEUP_2", "WAKEUP_2_X",
]

# Однострочные элементы UI (запас на shou/центрирование).
# ITEM_SELECT_PROMPT не проверяется: дословная строка оригинала
# (251px у V.P.K) рисуется центрированно — как в RU-издании.
ONE_LINE_234 = ["INFO_TITLE", "MUSIC_TITLE", "SOUND_ON", "SOUND_OFF",
                "SETTINGS_HINT", "ITEM_1", "ITEM_2", "ITEM_3", "ITEM_4",
                "ITEM_5", "ITEM_6", "ITEM_7", "ITEM_8", "ITEM_9", "ITEM_10",
                "ITEM_11", "ITEM_12", "ITEM_13", "ITEM_14"]


def norm(s: str) -> str:
    """Порт TextFmt.norm (Java) — должен совпадать с TextFmtTest."""
    out = []
    line_start = True
    i = 0
    n = len(s)
    while i < n:
        c = s[i]
        if c == ' ':
            if line_start:
                out.append(c)
            elif out and out[-1] != ' ':
                out.append(' ')
            i += 1
            continue
        if c == '\n':
            out.append(c)
            line_start = True
            i += 1
            continue
        line_start = False
        out.append(c)
        if c in ',;:!.?':
            nxt = s[i + 1] if i + 1 < n else '\0'
            if nxt not in ('\0', ' ', '\n', '.'):
                if c == '.':
                    if ('А' <= nxt <= 'Я' or nxt == 'Ё'
                            or (nxt.isdigit() and out and out[-2:-1]
                                and out[-2:-1][0].isalpha())):
                        out.append(' ')
                elif nxt.isalnum():
                    out.append(' ')
        i += 1
    return ''.join(out)


def width(s: str) -> float:
    return FONT.getlength(s)


def wrap_words(s: str, limit: float):
    """Порт TextFmt.wrapWords: перенос по словам, '\n' — явный перевод."""
    lines = []
    for para in s.split('\n'):
        if not para:
            lines.append('')
            continue
        cur = ''
        for word in para.split(' '):
            if not word:
                continue
            cand = word if not cur else cur + ' ' + word
            if width(cand) <= limit:
                cur = cand
                continue
            if cur:
                lines.append(cur)
                cur = ''
            if width(word) <= limit:
                cur = word
            else:  # слово шире строки — побуквенно (не встречается)
                for ch in word:
                    if not cur or width(cur + ch) <= limit:
                        cur += ch
                    else:
                        lines.append(cur)
                        cur = ch
        if cur:
            lines.append(cur)
    return lines


def main() -> int:
    # ЭТАП 5d: лимит бокса — как в оригинале (WIDTH - 2*fW, fW=12),
    # плюс страховой запас 3% на разницу хинтинга PIL/FreeType
    limit = 216
    safe_limit = limit * 0.97
    print(f"лимит бокса: {limit}px (с запасом {safe_limit:.0f}px), "
          f"шрифт Fusion Pixel 12px, x=10, word wrap")
    failures = []
    paged = []
    for pack in ("ru-vpk", "ru-auth"):
        js = json.load(open(f"{ROOT}/assets/text/{pack}.json", encoding="utf-8"))
        print(f"\n=== {pack} ===")
        for key in BF_KEYS:
            s = js.get(key)
            if not isinstance(s, str):
                continue
            lines = wrap_words(norm(s), safe_limit)
            pages = (len(lines) + 1) // 2
            mark = ""
            if pages > 1:
                mark = f"  <-- {pages} стр. (пейджер)"
                paged.append((pack, key, pages))
            print(f"  {key:22s} {len(lines)} стрк/{pages} стр. {width(s):5.0f}px{mark}")
            for ln in lines:
                if width(ln) > limit:
                    failures.append((pack, key, len(lines)))
                    print(f"      !! строка шире лимита: {ln!r} ({width(ln):.0f}px)")
        for key in ONE_LINE_234:
            s = js.get(key)
            if isinstance(s, str) and width(norm(s)) > 234:
                failures.append((pack, key, 1))
                print(f"  {key:22s} ШИРЕ 234px ({width(norm(s)):.0f}px) <--")
    print()
    if paged:
        print(f"Многостраничные (пейджер листает, НЕ ошибка): {len(paged)} текстов")
    if failures:
        print("ПРОВАЛ:", failures)
        return 1
    print("OK: все строки укладываются в бокс (word wrap + пейджер)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
