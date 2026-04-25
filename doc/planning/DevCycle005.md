# DevCycle 005: Slant Rhyme Drill

**Status:** Planning
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

**Status:** Planning

- [ ] Add a domain model for a slant rhyme seed/example pair
- [ ] Add a separate slant rhyme list to `SeedData.kt`
- [ ] Initialize the list with 20 slant rhyme pairs
- [ ] Keep slant rhyme data separate from existing rhyme words

**Technical Notes:**
The source idea refers to "Slide Rhyme Seeds," but this plan treats that as a typo for "Slant Rhyme Seeds." A likely model is `data class SlantRhymePair(val seed: String, val example: String)` in [Models.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/domain/Models.kt), with seed data exposed from [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt). The example from the source plan should be included: `wake` -> `wait`.

### Phase 2: Repository and ViewModel

**Status:** Planning

- [ ] Add a repository for selecting slant rhyme seeds
- [ ] Support recent-repeat avoidance using the existing settings behavior where practical
- [ ] Add a Slant Rhyme view model that tracks the current seed and whether the example is visible
- [ ] Reset the revealed example state when the user moves to the next seed

**Technical Notes:**
The existing [RhymeRepository.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/RhymeRepository.kt) and [RhymeViewModel.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/viewmodel/RhymeViewModel.kt) provide the closest implementation pattern. The new slant rhyme flow likely needs its own repository and view model because it uses pairs and reveal state rather than a difficulty-filtered word list.

### Phase 3: Navigation and Home Entry

**Status:** Planning

- [ ] Add a new route for Slant Rhyme Drill
- [ ] Add a new Home screen button at the same level as Improv Song and Rhyme Drill
- [ ] Wire the route into `SongSeedCodexApp`
- [ ] Add the repository to the application dependency container

**Technical Notes:**
Relevant files include [Routes.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/navigation/Routes.kt), [HomeScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/screens/HomeScreen.kt), [SongSeedCodexApp.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/SongSeedCodexApp.kt), and [SongSeedCodexApplication.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/SongSeedCodexApplication.kt).

### Phase 4: Slant Rhyme Screen

**Status:** Planning

- [ ] Create a new Compose screen for Slant Rhyme Drill
- [ ] Show the current seed word prominently
- [ ] Add a `Next Word` action that advances to another seed
- [ ] Add a reveal action that shows the example slant rhyme
- [ ] Add a Back action consistent with the existing drill screens

**Technical Notes:**
The screen should reuse the existing app components from [Shared.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/screens/Shared.kt), especially `SongSeedCodexScaffold`, `ScrollColumn`, and `HeroCard`, so the new mode feels native to the app. The reveal button can be labeled along the lines of `Show Example`, with the example hidden until requested.

### Phase 5: Verification and Documentation

**Status:** Planning

- [ ] Build the app with `.\gradlew.bat assembleDebug`
- [ ] Verify Home exposes all three top-level practice modes
- [ ] Verify the Slant Rhyme screen starts with a seed word
- [ ] Verify `Show Example` reveals only the current seed's example
- [ ] Verify `Next Word` advances the seed and hides the previous example
- [ ] Update this DevCycle document with implementation notes and verification results

**Technical Notes:**
Manual verification should focus on the interaction contract from the source idea: users can choose between moving to the next seed word and revealing an example slant rhyme for the current word.

---

## Open Questions

1. **Should the reveal button stay available after the example is shown, or become disabled?**
   Recommendation: Keep it visible but harmless after reveal, matching a simple drill-style interaction.

2. **Should Slant Rhyme Drill use the global avoid-recent-repeats setting?**
   Recommendation: Yes, if it can reuse the existing recent-word pattern cleanly. It keeps drill behavior consistent across modes.

3. **Should slant rhyme examples be editable with the existing rhyme update support script?**
   Recommendation: No for this cycle. Slant rhymes are seed/example pairs, so they need a separate maintenance path if automation becomes useful later.

---

## Notes and Risks

- Slant rhyme pairs need enough musical usefulness to avoid feeling arbitrary; the initial 20 should be curated rather than generated mechanically.
- The feature should not reuse `Word` or `RhymeDifficulty` in a way that confuses perfect rhymes with slant rhymes.
- Adding a top-level mode touches navigation, app dependency wiring, UI, data, and view model layers, so verification should include a full debug build.
- If the Home screen begins to feel crowded, future cycles may need a more structured practice-mode layout.

---

## Completion Summary

*Fill in when the cycle closes. Move this document to `doc/planning/completed/` afterward.*

**Completion Date:** [YYYY-MM-DD]
**Phases Completed:** [List or "All"]
**Work Deferred:** [What was not done and why, or "None"]

**Accomplishments:**
- [What was built or changed]
- [What was built or changed]

**Metrics:**
- Files modified: [N]
- Slant rhyme pairs: [N]
- Build verification: [Result]

**Lessons / Notes:**
[Anything worth remembering for future cycles.]
