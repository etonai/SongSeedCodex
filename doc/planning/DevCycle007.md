# DevCycle 007: Loose Rhymes Hard Ending Shift

**Status:** Work Complete
**Start Date:** 2026-04-25
**Target Completion:** 2026-04-25
**Focus:** Add a Loose Rhymes flow with a first curated subcategory, Hard Ending Shift.

---

## Goal

This cycle turns the Loose Rhymes design note in [loosecategories01.md](C:/dev/songseed/SongSeedCodex/doc/planning/Ideas/loosecategories01.md) into an implementation plan. The app already has Slant Rhyme behavior, and Loose Rhymes should become a broader category that can reuse that behavior while making room for more specific loose-rhyme drills. The first new drill, Hard Ending Shift, should teach the feel of loose rhymes where the vowel is related but the ending changes.

## Desired Outcome

At the end of this cycle, users should be able to open Loose Rhymes, choose Default, All, or Hard Ending Shift, read the Hard Ending Shift description, and start a simple drill. The drill should show one prompt word at a time with only two actions: Show Example and Next Word. Each prompt should have exactly one curated example pair, with no correctness checking, scoring, timer requirement, or required number of responses.

---

## Tasks

### Phase 1: Navigation and Category Structure

**Status:** Work Complete

- [x] Decide whether Loose Rhymes replaces the current Slant Rhyme entry point or appears beside it
- [x] Add or update route constants for Loose Rhymes category selection, Hard Ending Shift description, and Hard Ending Shift drill
- [x] Add a Loose Rhymes entry point from the home screen
- [x] Create a subcategory selection screen with Default, All, and Hard Ending Shift options
- [x] Wire Default to the existing Slant Rhyme behavior
- [x] Wire All to a combined loose-rhyme drill source that can include current and future subcategories

**Technical Notes:**
Likely touch points include [Routes.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/navigation/Routes.kt), [SongSeedCodexApp.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/SongSeedCodexApp.kt), [HomeScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/screens/HomeScreen.kt), and the existing Slant Rhyme files. Keep the top-level concept named `Loose Rhymes`; treat Slant Rhyme as the default Loose Rhymes experience unless a different product decision is made.

Decision: the home-screen Slant Rhyme entry was replaced with Loose Rhymes. Default still opens the existing Slant Rhyme drill, while All and Hard Ending Shift use the new Loose Rhymes drill route.

### Phase 2: Hard Ending Shift Data Model

**Status:** Work Complete

- [x] Choose a domain model for loose-rhyme subcategories and one-example prompt pairs
- [x] Add a Hard Ending Shift dataset where each prompt maps to exactly one example
- [x] Allow either member of a Hard Ending Shift pair to appear as the drill word
- [x] Keep the existing Slant Rhyme dataset available for Default behavior
- [x] Make All pull from the current Slant Rhyme dataset plus Hard Ending Shift data
- [x] Avoid duplicate or confusing entries where a pair is effectively too close, a perfect rhyme, or starts with the same initial letter

**Technical Notes:**
Likely touch points include [Models.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/domain/Models.kt), [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt), [SlantRhymeRepository.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/SlantRhymeRepository.kt), and [SlantRhymeViewModel.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/viewmodel/SlantRhymeViewModel.kt). Prefer a small reusable model such as a prompt/example pair with optional subcategory metadata, rather than duplicating drill logic for every future loose-rhyme type.

Either member of a Hard Ending Shift pair can be used as the drill word. For example, `bad -> cat` can appear as `bad` with example `cat`, or as `cat` with example `bad`.

Implementation added `LooseRhymeDrillSource`, `LooseRhymeRepository`, and `LooseRhymeViewModel`. The repository uses the Hard Ending Shift list for that drill source and combines existing Slant Rhyme pairs with Hard Ending Shift pairs for All. Pair selection can reverse either pair member into the prompt word.

Starter Hard Ending Shift pairs from the source plan:

- bad -> cat
- cat -> map
- bed -> set
- dog -> top
- sun -> cup
- book -> put
- stone -> note
- grass -> cap
- rain -> late
- plate -> make
- road -> note
- made -> take
- side -> time
- red -> bet
- mud -> cut

### Phase 3: Hard Ending Shift Description Screen

**Status:** Work Complete

- [x] Create a Hard Ending Shift description screen
- [x] Show the title "Hard Ending Shift"
- [x] Include the short explanation from the design note
- [x] Show example pairs that define the quality bar: cod -> pot, bad -> cat, bed -> step
- [x] Add a Start Drill call to action
- [x] Navigate from Start Drill to the Hard Ending Shift drill

**Technical Notes:**
The screen copy should explain that these rhymes keep a similar vowel sound while changing the ending, avoid too-close pairs such as cod -> cot, and avoid perfect rhymes such as find -> kind. Keep the screen instructional but brief, since the drill itself should stay fast.

Implemented in [LooseRhymesScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/screens/LooseRhymesScreen.kt) alongside the Loose Rhymes selection and drill screens.

### Phase 4: Drill Interaction

**Status:** Work Complete

- [x] Show one main prompt word centered on the drill screen
- [x] Provide only two visible actions: Show Example and Next Word
- [x] Make Show Example reveal one predefined example for the current prompt
- [x] Make Next Word advance to a new prompt and hide the previous example
- [x] Add a Main Page action from the Hard Ending Shift drill
- [x] Avoid correctness checking, answer submission, scorekeeping, and response-count requirements
- [x] Ensure the drill supports mental or vocal participation without forcing typed input

**Technical Notes:**
The existing Slant Rhyme drill is the closest behavioral reference. If the current UI has extra controls that are not appropriate for Hard Ending Shift, either parameterize the shared drill UI or create a small dedicated screen while keeping repository and view-model logic reusable.

Implementation uses a dedicated `LooseRhymeDrillScreen` so Hard Ending Shift keeps the required Show Example and Next Word actions, plus a Main Page action that returns directly to Home.

### Phase 5: Verification

**Status:** Work Complete

- [x] Confirm Loose Rhymes navigation works from the home screen
- [x] Confirm Default opens the existing Slant Rhyme behavior
- [x] Confirm All can serve prompts from all configured loose-rhyme sources
- [x] Confirm Hard Ending Shift opens the description screen before the drill
- [x] Confirm Show Example reveals exactly one example and does not rotate multiple examples for the same prompt
- [x] Confirm Next Word resets example visibility
- [x] Run the project build

**Technical Notes:**
Use `.\gradlew.bat assembleDebug` as the baseline build verification. If UI-level tests already exist or are introduced during implementation, add focused coverage for route wiring and drill state transitions.

Build verification completed successfully with `.\gradlew.bat assembleDebug`. Route and state behavior were verified by code review; no UI automation tests exist in the project yet.

### Phase 6: Documentation and Cleanup

**Status:** Work Complete

- [x] Update this DevCycle document as implementation decisions are made
- [x] Record any data entries removed or adjusted during curation
- [x] Note follow-up subcategory ideas for a future cycle
- [x] Update the completion summary when the cycle closes

**Technical Notes:**
The source design note says that once this feels good in use, future work should remove weaker pairs, add additional Loose Rhymes subcategories, expand dataset variety, and refine based on real usage feel. Treat those as follow-up work unless the implementation reveals an obvious data-quality issue that should be fixed in this cycle.

---

## Open Questions

1. **Should Loose Rhymes replace the existing Slant Rhyme home-screen action?**
   Decision: Yes. Loose Rhymes now appears on the home screen, and Default preserves the current Slant Rhyme behavior.

2. **Should All include Default Slant Rhyme data immediately?**
   Decision: Yes. All combines existing Slant Rhyme data with Hard Ending Shift data.

3. **Should Hard Ending Shift reuse the Slant Rhyme screen or have a dedicated screen?**
   Decision: Hard Ending Shift uses a dedicated Loose Rhymes drill screen so the visible interaction stays limited to Show Example and Next Word.

---

## Notes and Risks

- The main product risk is terminology: Loose Rhymes, Slant Rhyme, Default, All, and Hard Ending Shift need to feel understandable without extra explanation.
- Some starter pairs may be weaker than others. Keep the dataset easy to prune after hands-on use.
- All should be designed for future subcategories, but this cycle should not overbuild a large taxonomy system before there is more than one new subcategory.
- Hard Ending Shift should remain practice-oriented and lightweight. Adding input fields, correctness checking, or scoring would conflict with the source plan.

---

## Completion Summary

*Fill in when the cycle closes. Move this document to `doc/planning/completed/` afterward.*

**Completion Date:** 2026-04-25
**Phases Completed:** All
**Work Deferred:** UI automation tests; the project does not currently include UI tests.

**Accomplishments:**
- Added Loose Rhymes as the home-screen entry point, with Default, All, and Hard Ending Shift options.
- Added Hard Ending Shift seed data, bidirectional prompt behavior, and an All source that combines Slant Rhyme and Hard Ending Shift pairs.
- Added the Hard Ending Shift description screen and a focused two-button Loose Rhymes drill screen.
- Added a Main Page action to the Loose Rhymes drill screen.

**Metrics:**
- Files modified: 11
- Build verification: `.\gradlew.bat assembleDebug` completed successfully; rerun after adding Main Page also completed successfully

**Lessons / Notes:**
The existing Slant Rhyme repository pattern translated cleanly to Loose Rhymes. A dedicated drill screen was the simplest way to preserve the exact two-button interaction required for Hard Ending Shift while still reusing the pair and recent-repeat behavior.
