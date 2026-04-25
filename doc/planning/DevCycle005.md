# DevCycle 005: Slant Rhyme Drill

**Status:** Work Complete
**Start Date:** 2026-04-25
**Target Completion:** 2026-04-25
**Focus:** Add a new Slant Rhyme Drill practice mode alongside Improv Song and Rhyme Drill.

---

## Goal

This cycle adds a third top-level practice mode for slant rhymes. The new mode should feel familiar to users who already understand Rhyme Drill, but it has a different interaction: the user sees a seed word and can either move to the next seed or reveal an example slant rhyme for the current seed. This matters because slant rhymes are a distinct songwriting skill and should not be mixed into the existing perfect-rhyme word pools.

## Desired Outcome

At the end of this cycle, the Home screen should offer a new `Slant Rhyme Drill` option at the same level as `Improv Song` and `Rhyme Drill`. The app should include a separate slant rhyme seed data set initialized with 20 seed/example pairs, and the drill screen should let the user advance to a new seed word or reveal the example for the current seed.

---

## Tasks

### Phase 1: Data Model and Seed Data

**Status:** Work Complete

- [x] Add a domain model for a slant rhyme seed/example pair
- [x] Add a separate slant rhyme list to `SeedData.kt`
- [x] Initialize the list with 20 slant rhyme pairs
- [x] Keep slant rhyme data separate from existing rhyme words

**Technical Notes:**
The source idea refers to "Slide Rhyme Seeds," but this cycle treated that as a typo for "Slant Rhyme Seeds." Added `data class SlantRhymePair(val seed: String, val example: String)` in [Models.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/domain/Models.kt), with 20 seed/example pairs exposed from [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt). The source-plan example `wake` -> `wait` is included.

### Phase 2: Repository and ViewModel

**Status:** Work Complete

- [x] Add a repository for selecting slant rhyme seeds
- [x] Support recent-repeat avoidance using the existing settings behavior where practical
- [x] Add a Slant Rhyme view model that tracks the current seed and whether the example is visible
- [x] Reset the revealed example state when the user moves to the next seed

**Technical Notes:**
Added [SlantRhymeRepository.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/SlantRhymeRepository.kt) and [SlantRhymeViewModel.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/viewmodel/SlantRhymeViewModel.kt). The repository selects from `SeedData.slantRhymePairs` and honors the existing avoid-recent-repeats setting. The view model tracks the current pair, reveal state, and recent seed history; `Next Word` resets the example visibility.

### Phase 3: Navigation and Home Entry

**Status:** Work Complete

- [x] Add a new route for Slant Rhyme Drill
- [x] Add a new Home screen button at the same level as Improv Song and Rhyme Drill
- [x] Wire the route into `SongSeedCodexApp`
- [x] Add the repository to the application dependency container

**Technical Notes:**
Added `Routes.SlantRhyme`, a `Slant Rhyme Drill` Home button, a new `NavHost` destination in [SongSeedCodexApp.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/SongSeedCodexApp.kt), and application-level repository wiring through [SongSeedCodexApplication.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/SongSeedCodexApplication.kt) and [MainActivity.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/MainActivity.kt).

### Phase 4: Slant Rhyme Screen

**Status:** Work Complete

- [x] Create a new Compose screen for Slant Rhyme Drill
- [x] Show the current seed word prominently
- [x] Add a `Next Word` action that advances to another seed
- [x] Add a reveal action that shows the example slant rhyme
- [x] Add a Back action consistent with the existing drill screens

**Technical Notes:**
Added [SlantRhymeScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/screens/SlantRhymeScreen.kt). It reuses `SongSeedCodexScaffold`, `ScrollColumn`, and `HeroCard`, shows the current seed word, exposes `Next Word`, reveals the example via `Show Example`, and includes the same Back pattern as the existing Rhyme Drill screen.

### Phase 5: Verification and Documentation

**Status:** Work Complete

- [x] Build the app with `.\gradlew.bat assembleDebug`
- [x] Verify Home exposes all three top-level practice modes
- [x] Verify the Slant Rhyme screen starts with a seed word
- [x] Verify `Show Example` reveals only the current seed's example
- [x] Verify `Next Word` advances the seed and hides the previous example
- [x] Update this DevCycle document with implementation notes and verification results

**Technical Notes:**
The debug build completed successfully with `.\gradlew.bat assembleDebug`. Static implementation review confirms Home now exposes `Improv Song`, `Rhyme Drill`, and `Slant Rhyme Drill`; the Slant Rhyme route initializes a view model that generates a seed immediately; `Show Example` reveals only the current pair's example; and `generateNextPair()` sets `isExampleVisible = false` for the next seed.

---

## Open Questions

1. **Should the reveal button stay available after the example is shown, or become disabled?**
   Decision: Keep it visible and harmless after reveal, matching a simple drill-style interaction.

2. **Should Slant Rhyme Drill use the global avoid-recent-repeats setting?**
   Decision: Yes. The slant rhyme view model reuses the existing avoid-recent-repeats setting with a recent seed history.

3. **Should slant rhyme examples be editable with the existing rhyme update support script?**
   Decision: No for this cycle. Slant rhymes are seed/example pairs, so they need a separate maintenance path if automation becomes useful later.

---

## Notes and Risks

- Slant rhyme pairs need enough musical usefulness to avoid feeling arbitrary; the initial 20 should be curated rather than generated mechanically.
- The feature should not reuse `Word` or `RhymeDifficulty` in a way that confuses perfect rhymes with slant rhymes.
- Adding a top-level mode touches navigation, app dependency wiring, UI, data, and view model layers, so verification should include a full debug build.
- If the Home screen begins to feel crowded, future cycles may need a more structured practice-mode layout.

---

## Completion Summary

*Fill in when the cycle closes. Move this document to `doc/planning/completed/` afterward.*

**Completion Date:** 2026-04-25
**Phases Completed:** All
**Work Deferred:** None

**Accomplishments:**
- Added Slant Rhyme Drill as a third top-level practice mode from Home.
- Added a separate 20-pair slant rhyme data set and dedicated domain model.
- Added repository, view model, screen, navigation, and application wiring for the new drill.
- Preserved the current Rhyme Drill data and behavior separately from slant rhymes.

**Metrics:**
- Files modified: 11
- Slant rhyme pairs: 20
- Build verification: `.\gradlew.bat assembleDebug` completed successfully

**Lessons / Notes:**
The existing Rhyme Drill architecture was a good template, but keeping slant rhymes as their own pair-based flow avoided blurring perfect-rhyme and slant-rhyme concepts in the domain model.
