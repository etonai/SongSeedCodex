"""Add a slant rhyme pair to the SongSeed seed data.

Usage:
    python support/add_slant_rhyme.py worm swarm
"""

from pathlib import Path
import re
import sys


REPO_ROOT = Path(__file__).resolve().parents[1]
SEED_DATA_PATH = (
    REPO_ROOT
    / "app"
    / "src"
    / "main"
    / "java"
    / "com"
    / "songseed"
    / "songseedcodex"
    / "data"
    / "local"
    / "SeedData.kt"
)

SLANT_RHYME_HEADER = "    val slantRhymePairs: List<SlantRhymePair> = listOf("
PAIR_PATTERN = re.compile(
    r'SlantRhymePair\(\s*seed\s*=\s*"((?:\\.|[^"\\])*)"\s*,\s*example\s*=\s*"((?:\\.|[^"\\])*)"\s*\)'
)


def kotlin_string(value: str) -> str:
    return value.replace("\\", "\\\\").replace('"', '\\"')


def normalized_pair(args: list[str]) -> tuple[str, str]:
    if len(args) != 2:
        raise ValueError("Usage: python support/add_slant_rhyme.py word1 word2")

    first = args[0].lower()
    second = args[1].lower()

    if not first or not second:
        raise ValueError("Both slant rhyme words must be non-empty.")

    return first, second


def pair_key(first: str, second: str) -> frozenset[str]:
    return frozenset((first, second))


def existing_pair_keys(seed_data: str) -> set[frozenset[str]]:
    return {
        pair_key(match.group(1).lower(), match.group(2).lower())
        for match in PAIR_PATTERN.finditer(seed_data)
    }


def add_slant_rhyme(seed_data: str, first: str, second: str) -> tuple[str, int]:
    if pair_key(first, second) in existing_pair_keys(seed_data):
        return seed_data, 0

    newline = "\r\n" if "\r\n" in seed_data else "\n"
    start = seed_data.find(SLANT_RHYME_HEADER)

    if start == -1:
        raise RuntimeError("Could not locate the slant rhyme pair list in SeedData.kt.")

    list_block = seed_data[start:]
    closing_index = list_block.find(f"{newline}    ){newline}")

    if closing_index == -1:
        raise RuntimeError("Could not locate the end of the slant rhyme pair list.")

    before_closing = list_block[:closing_index]
    closing_and_after = list_block[closing_index:]
    lines = before_closing.split(newline)

    last_entry_index = next(
        (index for index in range(len(lines) - 1, -1, -1) if "SlantRhymePair(" in lines[index]),
        None,
    )

    if last_entry_index is None:
        raise RuntimeError("Could not locate the final slant rhyme pair entry.")

    if not lines[last_entry_index].rstrip().endswith(","):
        lines[last_entry_index] = f"{lines[last_entry_index]},"

    lines.append(
        f'        SlantRhymePair(seed = "{kotlin_string(first)}", example = "{kotlin_string(second)}")'
    )

    updated_list_block = newline.join(lines) + closing_and_after
    updated_seed_data = seed_data[:start] + updated_list_block

    return updated_seed_data, 1


def main() -> int:
    try:
        first, second = normalized_pair(sys.argv[1:])
        seed_data = SEED_DATA_PATH.read_text()
        updated_seed_data, added_count = add_slant_rhyme(seed_data, first, second)
    except (RuntimeError, ValueError) as error:
        print(error, file=sys.stderr)
        return 1

    if added_count:
        SEED_DATA_PATH.write_text(updated_seed_data)

    print(f"{added_count} slant rhyme pairs added")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
