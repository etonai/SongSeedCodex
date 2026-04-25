# DevCycle 006: Slant Rhyme Update Script

**Status:** Work Complete
**Start Date:** 2026-04-25
**Target Completion:** 2026-04-25
**Focus:** Add a support script that appends a two-word slant rhyme pair to `SeedData.kt`.

---

## Goal

This cycle adds a small developer utility for maintaining the app's built-in slant rhyme data. The Slant Rhyme Drill now uses `SlantRhymePair(seed = "...", example = "...")` entries in [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt), and adding pairs by hand is repetitive. The new script should make adding one curated pair fast while preserving the current Kotlin seed-data structure.

## Desired Outcome

At the end of this cycle, the project should include a Python script in [support](C:/dev/songseed/SongSeedCodex/support) that accepts exactly two words from the command line and appends them as a new slant rhyme pair. The script should avoid adding a pair that already exists, treating `word1`/`word2` and `word2`/`word1` as the same pair because the app can prompt with either side.

---

## Tasks

### Phase 1: Script Design

**Status:** Work Complete

- [x] Confirm the exact target file path for `SeedData.kt`
- [x] Identify the `slantRhymePairs` list boundaries in the Kotlin source
- [x] Define command-line behavior for fewer or more than two words
- [x] Define duplicate behavior for same-order and reversed-order pairs
- [x] Choose output wording for added versus skipped pairs

**Technical Notes:**
The script lives at [add_slant_rhyme.py](C:/dev/songseed/SongSeedCodex/support/add_slant_rhyme.py) and targets [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt). It operates on the existing `val slantRhymePairs: List<SlantRhymePair> = listOf(...)` block and adds new entries as `SlantRhymePair(seed = "word1", example = "word2")`.

### Phase 2: Implementation

**Status:** Work Complete

- [x] Create `support/add_slant_rhyme.py`
- [x] Parse exactly two command-line values from `sys.argv`
- [x] Normalize inputs consistently with current seed data
- [x] Detect existing slant rhyme pairs before adding a new entry
- [x] Treat reversed pairs as duplicates
- [x] Append only new pairs to `slantRhymePairs`
- [x] Print a concise result without listing unnecessary details

**Technical Notes:**
The current slant rhyme data is lowercase, so the script lowercases both words before writing. The duplicate check compares normalized unordered pairs, so `python support/add_slant_rhyme.py worm swarm` and `python support/add_slant_rhyme.py swarm worm` are treated as the same pair. It does not validate rhyme quality.

### Phase 3: Verification

**Status:** Work Complete

- [x] Run the script with an existing pair and confirm it does not modify `SeedData.kt`
- [x] Run the script with an existing reversed pair and confirm it does not modify `SeedData.kt`
- [x] Run the script with a temporary new pair and confirm it appends one `SlantRhymePair`
- [x] Confirm the script rejects calls with anything other than two words
- [x] Run `.\gradlew.bat assembleDebug` after script verification
- [x] Remove any temporary verification-only pair before finishing

**Technical Notes:**
Verification included `python support\add_slant_rhyme.py worm swarm`, which returned `0 slant rhyme pairs added`, and `python support\add_slant_rhyme.py swarm worm`, which also returned `0 slant rhyme pairs added`. Invalid argument handling was verified with `python support\add_slant_rhyme.py worm`, which returned the usage message and exit code `1`. Insertion behavior was tested with `python support\add_slant_rhyme.py CodexProbe CodexTone`; it returned `1 slant rhyme pairs added`, appended a lowercase `SlantRhymePair(seed = "codexprobe", example = "codextone")`, and the temporary pair was removed after verification. The Android debug build completed successfully with `.\gradlew.bat assembleDebug`.

### Phase 4: Documentation

**Status:** Work Complete

- [x] Add a brief usage note to the script header
- [x] Record implementation details and verification results in this DevCycle document
- [x] Update the completion summary when the cycle closes

**Technical Notes:**
The intended usage is documented in the script header: `python support/add_slant_rhyme.py worm swarm`.

---

## Open Questions

1. **Should the script allow multi-word phrases as one side of a pair?**
   Decision: No for this cycle. The interface accepts exactly two command-line words, matching the requested scope.

2. **Should the script preserve typed casing?**
   Decision: No. The script lowercases both words to match the current `SeedData.kt` slant rhyme style and avoid casing-only duplicates.

3. **Should the script update DevCycle metrics or documentation automatically?**
   Decision: No. The script stays focused on seed data only.

---

## Notes and Risks

- The script will edit Kotlin source directly, so the insertion logic should fail loudly if `slantRhymePairs` cannot be found.
- Because the app can select either member of a pair, duplicate detection must be order-insensitive.
- This script should stay separate from `support/add_words.py` because slant rhymes use pair data rather than `Word("...", 1)` entries.
- Rhyme quality remains a human curation responsibility.

---

## Completion Summary

*Fill in when the cycle closes. Move this document to `doc/planning/completed/` afterward.*

**Completion Date:** 2026-04-25
**Phases Completed:** All
**Work Deferred:** None

**Accomplishments:**
- Added `support/add_slant_rhyme.py` for command-line slant rhyme pair maintenance.
- Implemented lowercase normalization, exact-two-word validation, order-insensitive duplicate detection, and `slantRhymePairs` insertion.
- Verified no-op behavior, reversed duplicate handling, invalid argument handling, temporary insertion, cleanup, and build compatibility.

**Metrics:**
- Files modified: 2
- Script verification: Existing and reversed pairs returned `0 slant rhyme pairs added`; temporary insertion returned `1 slant rhyme pairs added` and was cleaned up.
- Build verification: `.\gradlew.bat assembleDebug` completed successfully

**Lessons / Notes:**
Order-insensitive duplicate detection matters for this script because the app can prompt from either side of a slant rhyme pair. Keeping that rule inside the support script helps the data stay aligned with runtime behavior.
