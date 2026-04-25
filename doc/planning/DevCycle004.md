# DevCycle 004: Rhyme Word Update Script

**Status:** Work Complete
**Start Date:** 2026-04-25
**Target Completion:** 2026-04-25
**Focus:** Add a support script that appends new easy rhyme words to `SeedData.kt` from command-line input.

---

## Goal

This cycle adds a small developer utility for maintaining the app's built-in rhyme word data. Today, adding easy rhyme words requires manually editing [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt), which is easy to do but also easy to make repetitive or inconsistent. The new script should make simple additions fast while preserving the current Kotlin seed-data structure.

## Desired Outcome

At the end of this cycle, the project should include a Python script in [support](C:/dev/songseed/SongSeedCodex/support) that accepts words from the command line, lowercases them, skips words already present in the rhyme word file, appends new words to the easy rhyme word list, and reports only how many words were added.

---

## Tasks

### Phase 1: Script Design

**Status:** Work Complete

- [x] Confirm the exact target file path for `SeedData.kt`
- [x] Identify the `easyRhymeWords` list boundaries in the Kotlin source
- [x] Define command-line behavior for zero words, duplicate arguments, and existing words
- [x] Choose output wording for the final added-word count

**Technical Notes:**
The script lives at [add_words.py](C:/dev/songseed/SongSeedCodex/support/add_words.py) and targets [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt). It operates on the existing `private val easyRhymeWords: List<Word> = listOf(...)` block and adds new entries as `Word("word", 1)`. The final script prints output in the form `N words added`.

### Phase 2: Implementation

**Status:** Work Complete

- [x] Create `support/add_words.py`
- [x] Parse command-line words from `sys.argv`
- [x] Normalize all provided words to lowercase
- [x] Detect existing rhyme words before adding new entries
- [x] Append only new words to `easyRhymeWords`
- [x] Print the number of words added without listing the words

**Technical Notes:**
The script does not validate syllable count. Every added word is categorized as an easy word by writing it with syllable count `1`. Duplicate checks are performed against all existing `Word("...", n)` entries in `SeedData.kt`, so a word already present in either the easy or normal list is not added again. Repeated command-line arguments are collapsed before insertion.

### Phase 3: Verification

**Status:** Work Complete

- [x] Run the script with words already present in `SeedData.kt` and confirm it adds `0`
- [x] Run the script with one or more new test words and confirm they are appended as lowercase easy words
- [x] Confirm repeated command-line arguments do not create duplicate entries
- [x] Run a Gradle build or focused compile check after the script modifies `SeedData.kt`
- [x] Revert or avoid committing any temporary test words used only for verification

**Technical Notes:**
Verification included `python support\add_words.py light rain blue`, which returned `0 words added`, and `python support\add_words.py`, which also returned `0 words added`. Insertion behavior was tested with `python support\add_words.py CodexProbe CodexProbe LIGHT`; it returned `1 words added`, appended `Word("codexprobe", 1)`, skipped the repeated argument, and skipped existing `light`. The temporary test word was removed after verification. The Android debug build completed successfully with `.\gradlew.bat assembleDebug`.

### Phase 4: Documentation

**Status:** Work Complete

- [x] Add a brief usage note to the script header or project documentation
- [x] Record implementation details and verification results in this DevCycle document
- [x] Update the completion summary when the cycle closes

**Technical Notes:**
The usage example from the source idea is preserved in the script header: `python support/add_words.py light rain blue`.

---

## Open Questions

1. **Should the script reject punctuation, spaces, or non-word characters?**
   Decision: No additional validation was added in this cycle. The source plan explicitly says there should be no syllable validation, and this utility is intended for developer use.

2. **Should existing-word checks include both easy and normal rhyme words?**
   Decision: Yes. The full rhyme word file is treated as the duplicate source so the script never adds a word that already exists elsewhere in `SeedData.kt`.

---

## Notes and Risks

- The script will edit Kotlin source directly, so the insertion logic should be narrow and easy to inspect.
- Formatting changes should be limited to newly inserted `Word("...", 1)` lines.
- Since there is no syllable validation, correctness depends on the developer passing appropriate one-syllable words.
- The script should avoid reporting the specific words added, matching the source plan.

---

## Completion Summary

*Fill in when the cycle closes. Move this document to `doc/planning/completed/` afterward.*

**Completion Date:** 2026-04-25
**Phases Completed:** All
**Work Deferred:** None

**Accomplishments:**
- Added `support/add_words.py` for command-line rhyme word maintenance.
- Implemented lowercase normalization, duplicate detection across all `SeedData.kt` rhyme words, easy-list insertion, and count-only output.
- Verified no-op behavior, insertion behavior, duplicate argument handling, and build compatibility.

**Metrics:**
- Files modified: 2
- Script verification: Existing words returned `0 words added`; throwaway insertion returned `1 words added` and was cleaned up.
- Build verification: `.\gradlew.bat assembleDebug` completed successfully

**Lessons / Notes:**
Direct source editing is workable for this small maintenance utility, but the script keeps the search and insertion boundaries narrow so future structure changes fail loudly instead of editing the wrong section.
