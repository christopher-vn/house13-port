#!/usr/bin/env python3
"""Проверка ширины текстов паков (ЭТАП 5).

Бокс сообщений (drawStringToScreen) показывает РОВНО 2 строки: лимит
240 - 2*stringWidth("В"), сплиттер — жадный по символам, «переполняющий
символ остаётся в строке» (семантика do(String) из b.java). Строка длиннее
двух строк будет ОБРЕЗАНА — этот скрипт ловит такие случаи до дампов.

Ширины меряются тем же шрифтом, что и игра (assets/fonts/FusionPixel12.otf,
12px — этап 5b; до него был DejaVu Sans 15px) через PIL; запас 3%
на отличия хинтинга FreeType/PIL.

Запуск: python3 scripts/check_text_widths.py
"""
import json
import sys

from PIL import ImageFont

ROOT = "/home/z/my-project/house13-port"
FONT = ImageFont.truetype(f"{ROOT}/assets/fonts/FusionPixel12.otf", 12)

# Строки бокса сообщений (bf): максимум 2 строки
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
# Строки выбора пака: текст + shou(~30px) справа => w <= 164
# (лимит РАСКЛАДКИ экрана, не шрифта; при Fusion 12px запас только вырос)
TR_MAX = 164
TR_KEYS = ["TR_AUTH", "TR_VPK"]


def width(s: str) -> float:
    return FONT.getlength(s)


def greedy_lines(s: str, limit: float):
    """Сколько строк займёт s при сплиттере do(String) (2 видимые)."""
    lines = []
    r = s
    while r:
        k = len(r)
        for i in range(len(r)):
            if width(r[: i + 1]) > limit or i >= len(r) - 1 or r[i] == "\n":
                k = i + 1
                break
        lines.append(r[:k])
        r = r[k:]
    return lines


def main() -> int:
    limit = 240 - 2 * width("В")
    safe_limit = limit * 0.97  # 3% запас хинтинга
    print(f"лимит бокса: {limit:.0f}px (с запасом {safe_limit:.0f}px), "
          f"шрифт Fusion Pixel 12px, В={width('В'):.1f}px")
    failures = []
    for pack in ("ru-vpk", "ru-auth"):
        js = json.load(open(f"{ROOT}/assets/text/{pack}.json", encoding="utf-8"))
        print(f"\n=== {pack} ===")
        for key in BF_KEYS:
            s = js.get(key)
            if not isinstance(s, str):
                continue
            lines = greedy_lines(s, safe_limit)
            mark = "" if len(lines) <= 2 else "  <-- ОБРЕЗАНИЕ!"
            print(f"  {key:22s} {len(lines)} строк ({width(s):5.0f}px){mark}")
            if len(lines) > 2:
                failures.append((pack, key, len(lines)))
        for key in ONE_LINE_234:
            s = js.get(key)
            if isinstance(s, str) and width(s) > 234:
                failures.append((pack, key, 1))
                print(f"  {key:22s} ШИРЕ 234px ({width(s):.0f}px) <--")
        for key in TR_KEYS:
            s = js.get(key)
            if isinstance(s, str) and width(s) > TR_MAX:
                failures.append((pack, key, 0))
                print(f"  {key:22s} ШИРЕ {TR_MAX}px ({width(s):.0f}px) <-- (не влезет shou)")
    print()
    if failures:
        print("ПРОВАЛ:", failures)
        return 1
    print("OK: все строки паков укладываются в свои лимиты")
    return 0


if __name__ == "__main__":
    sys.exit(main())
