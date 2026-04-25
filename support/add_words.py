"""Add easy rhyme words to the SongSeed seed data.

Usage:
    python support/add_words.py light rain blue
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

EASY_RHYME_HEADER = "    private val easyRhymeWords: List<Word> = listOf("
NORMAL_RHYME_HEADER = "    private val normalRhymeWords: List<Word> = listOf("
WORD_PATTERN = re.compile(r'Word\("((?:\\.|[^"\\])*)"\s*,\s*\d+\)')


def kotlin_string(value: str) -> str:
    return value.replace("\\", "\\\\").replace('"', '\\"')


def normalized_words(args: list[str]) -> list[str]:
    words: list[str] = []
    seen: set[str] = set()

    for arg in args:
        word = arg.lower()
        if word and word not in seen:
            words.append(word)
            seen.add(word)

    return words


def existing_words(seed_data: str) -> set[str]:
    return {match.group(1).lower() for match in WORD_PATTERN.finditer(seed_data)}


def add_words(seed_data: str, words: list[str]) -> tuple[str, int]:
    current_words = existing_words(seed_data)
    words_to_add = [word for word in words if word not in current_words]

    if not words_to_add:
        return seed_data, 0

    newline = "\r\n" if "\r\n" in seed_data else "\n"
    easy_start = seed_data.find(EASY_RHYME_HEADER)
    normal_start = seed_data.find(NORMAL_RHYME_HEADER)

    if easy_start == -1 or normal_start == -1 or normal_start <= easy_start:
        raise RuntimeError("Could not locate the easy rhyme word list in SeedData.kt.")

    easy_block = seed_data[easy_start:normal_start]
    closing_index = easy_block.rfind(f"{newline}    ){newline}{newline}")

    if closing_index == -1:
        raise RuntimeError("Could not locate the end of the easy rhyme word list.")

    before_closing = easy_block[:closing_index]
    closing_and_after = easy_block[closing_index:]
    lines = before_closing.split(newline)

    last_entry_index = next(
        (index for index in range(len(lines) - 1, -1, -1) if "Word(" in lines[index]),
        None,
    )

    if last_entry_index is None:
        raise RuntimeError("Could not locate the final easy rhyme word entry.")

    if not lines[last_entry_index].rstrip().endswith(","):
        lines[last_entry_index] = f"{lines[last_entry_index]},"

    for index, word in enumerate(words_to_add):
        suffix = "," if index < len(words_to_add) - 1 else ""
        lines.append(f'        Word("{kotlin_string(word)}", 1){suffix}')

    updated_easy_block = newline.join(lines) + closing_and_after
    updated_seed_data = seed_data[:easy_start] + updated_easy_block + seed_data[normal_start:]

    return updated_seed_data, len(words_to_add)


def main() -> int:
    words = normalized_words(sys.argv[1:])
    seed_data = SEED_DATA_PATH.read_text()
    updated_seed_data, added_count = add_words(seed_data, words)

    if added_count:
        SEED_DATA_PATH.write_text(updated_seed_data)

    print(f"{added_count} words added")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
