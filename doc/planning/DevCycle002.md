# DevCycle 002: Naming Consistency and Content Expansion

**Status:** Work Complete
**Start Date:** 2026-04-22
**Target Completion:** 2026-04-22
**Focus:** Standardize the project name as SongSeedCodex everywhere and expand the built-in improv and rhyme content pools to support stronger practice variety.

---

## Goal

This cycle has two immediate priorities. First, the project should consistently use the name `SongSeedCodex` instead of `SongSeed` across the app, project configuration, and documentation so the product identity is coherent. Second, the built-in practice content should be expanded substantially so the app feels less repetitive and better supports ongoing improv and rhyme practice.

## Desired Outcome

At the end of this cycle, all remaining user-facing and project-level naming should consistently say `SongSeedCodex`. The app should also contain larger built-in seed lists with a total of 150 improv ideas, 150 easy rhyme words, and 100 normal rhyme words, giving the MVP a much more usable content base.

---

## Tasks

### Phase 1: SongSeedCodex Naming Cleanup

**Status:** Work Complete

- [x] Find all remaining uses of `SongSeed` that should be renamed to `SongSeedCodex`
- [x] Update app labels, titles, configuration names, and documentation where appropriate
- [x] Leave intentional technical identifiers unchanged unless there is a clear benefit to renaming them
- [x] Review the resulting naming for consistency across the active project

**Technical Notes:**
This phase went beyond user-facing labels and updated project-level technical identifiers as well. The Android namespace and application ID were changed to `com.songseed.songseedcodex`, the app entry classes were renamed to `SongSeedCodexApplication`, `SongSeedCodexApp`, and `SongSeedCodexTheme`, the XML theme name became `Theme.SongSeedCodex`, and the DataStore backing file name was updated to `songseedcodex_settings`. Historical planning docs were also updated where they still described the active project as `SongSeed`.

### Phase 2: Improv Idea Expansion

**Status:** Work Complete

- [x] Expand the built-in improv category lists to reach 150 total improv ideas
- [x] Preserve balanced coverage across all improv categories
- [x] Keep prompts flexible, singable, and varied enough for repeat practice
- [x] Recheck generator behavior against the larger pool

**Technical Notes:**
Improv data in [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/data/local/SeedData.kt) was expanded from 50 total ideas to 150 total ideas. Each of the 10 improv categories now contains 15 entries, which keeps the content balanced and makes the generator significantly less repetitive while preserving the existing prompt structure.

### Phase 3: Rhyme Word Expansion

**Status:** Work Complete

- [x] Expand the easy rhyme word list to 150 words
- [x] Expand the normal rhyme word list to 100 words
- [x] Validate that the easy-word rule still matches the intended pool
- [x] Review the resulting variety so the drill feels useful rather than padded

**Technical Notes:**
Rhyme data in [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/data/local/SeedData.kt) was expanded to 250 total words. The final pool contains exactly 150 easy words and 100 normal words, and the counts were verified against the app’s current easy-word rule: `syllableCount == 1 || text.length <= 5`. The rhyme list was split into explicit easy and normal pools in code so the totals are easier to maintain.

### Phase 4: Review and Closeout Preparation

**Status:** Work Complete

- [x] Recount all seed content totals after expansion
- [x] Spot-check the app flows for naming and content quality
- [x] Record any deferred cleanup or follow-up work for a future DevCycle

**Technical Notes:**
The final totals were checked directly from the Kotlin seed data and matched the requested counts exactly: 150 improv ideas, 150 easy rhyme words, and 100 normal rhyme words. A debug build was also completed successfully with `gradlew.bat assembleDebug`, confirming that the package rename and data expansion compiled together.

---

## Notes and Risks

- Renaming every technical identifier may not be worth the disruption; some internal names may remain if they do not affect the product-facing identity.
- Expanding content quickly can introduce low-quality or repetitive entries if the list is not reviewed carefully.
- The easy rhyme classification rule is broad because words with 5 or fewer characters qualify even when they are not single-syllable.
- Large hardcoded content lists are acceptable for this cycle, but future work may benefit from moving seed data into a more editable format.

---

## Completion Summary

*Fill in when the cycle closes. Move this document to `doc/planning/completed/` afterward.*

**Completion Date:** 2026-04-22
**Phases Completed:** All
**Work Deferred:** None

**Accomplishments:**
- Renamed the app and project identifiers so the active project consistently presents as SongSeedCodex.
- Updated the Android namespace, application ID, theme identifier, entry-point class names, and settings store name to align with the new project name.
- Expanded the improv seed data from 50 ideas to 150 balanced ideas across the 10 improv categories.
- Expanded the rhyme drill pool to 250 total words with exactly 150 easy words and 100 normal words.
- Verified that the debug build completed successfully after the rename and data changes.

**Metrics:**
- Files modified: 28
- Improv ideas total: 150
- Easy rhyme words total: 150
- Normal rhyme words total: 100
- Build verification: `gradlew.bat assembleDebug` completed successfully

**Lessons / Notes:**
Renaming technical identifiers late in a young Android project is still manageable, but it creates more churn once build artifacts and IDE state are involved. Keeping the content pools explicit and countable in code made it much easier to verify the requested totals exactly, and future cycles may benefit from moving these larger lists into a more editable content format.
