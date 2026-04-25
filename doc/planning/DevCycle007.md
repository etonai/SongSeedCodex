# DevCycle 007: Loose Rhymes Hard Ending Shift

**Status:** Planning
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

**Status:** Planning

- [ ] Decide whether Loose Rhymes replaces the current Slant Rhyme entry point or appears beside it
- [ ] Add or update route constants for Loose Rhymes category selection, Hard Ending Shift description, and Hard Ending Shift drill
- [ ] Add a Loose Rhymes entry point from the home screen
- [ ] Create a subcategory selection screen with Default, All, and Hard Ending Shift options
- [ ] Wire Default to the existing Slant Rhyme behavior
- [ ] Wire All to a combined loose-rhyme drill source that can include current and future subcategories

**Technical Notes:**
Likely touch points include [Routes.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/navigation/Routes.kt), [SongSeedCodexApp.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/SongSeedCodexApp.kt), [HomeScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/screens/HomeScreen.kt), and the existing Slant Rhyme files. Keep the top-level concept named `Loose Rhymes`; treat Slant Rhyme as the default Loose Rhymes experience unless a different product decision is made.

### Phase 2: Hard Ending Shift Data Model

**Status:** Planning

- [ ] Choose a domain model for loose-rhyme subcategories and one-example prompt pairs
- [ ] Add a Hard Ending Shift dataset where each prompt maps to exactly one example
- [ ] Allow either member of a Hard Ending Shift pair to appear as the drill word
- [ ] Keep the existing Slant Rhyme dataset available for Default behavior
- [ ] Make All pull from the current Slant Rhyme dataset plus Hard Ending Shift data
- [ ] Avoid duplicate or confusing entries where a pair is effectively too close, a perfect rhyme, or starts with the same initial letter

**Technical Notes:**
Likely touch points include [Models.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/domain/Models.kt), [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt), [SlantRhymeRepository.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/SlantRhymeRepository.kt), and [SlantRhymeViewModel.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/viewmodel/SlantRhymeViewModel.kt). Prefer a small reusable model such as a prompt/example pair with optional subcategory metadata, rather than duplicating drill logic for every future loose-rhyme type.

Either member of a Hard Ending Shift pair can be used as the drill word. For example, `bad -> cat` can appear as `bad` with example `cat`, or as `cat` with example `bad`.

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

**Status:** Planning

- [ ] Create a Hard Ending Shift description screen
- [ ] Show the title "Hard Ending Shift"
- [ ] Include the short explanation from the design note
- [ ] Show example pairs that define the quality bar: cod -> pot, bad -> cat, bed -> step
- [ ] Add a Start Drill call to action
- [ ] Navigate from Start Drill to the Hard Ending Shift drill

**Technical Notes:**
The screen copy should explain that these rhymes keep a similar vowel sound while changing the ending, avoid too-close pairs such as cod -> cot, and avoid perfect rhymes such as find -> kind. Keep the screen instructional but brief, since the drill itself should stay fast.

### Phase 4: Drill Interaction

**Status:** Planning

- [ ] Show one main prompt word centered on the drill screen
- [ ] Provide only two visible actions: Show Example and Next Word
- [ ] Make Show Example reveal one predefined example for the current prompt
- [ ] Make Next Word advance to a new prompt and hide the previous example
- [ ] Avoid correctness checking, answer submission, scorekeeping, and response-count requirements
- [ ] Ensure the drill supports mental or vocal participation without forcing typed input

**Technical Notes:**
The existing Slant Rhyme drill is the closest behavioral reference. If the current UI has extra controls that are not appropriate for Hard Ending Shift, either parameterize the shared drill UI or create a small dedicated screen while keeping repository and view-model logic reusable.

### Phase 5: Verification

**Status:** Planning

- [ ] Confirm Loose Rhymes navigation works from the home screen
- [ ] Confirm Default opens the existing Slant Rhyme behavior
- [ ] Confirm All can serve prompts from all configured loose-rhyme sources
- [ ] Confirm Hard Ending Shift opens the description screen before the drill
- [ ] Confirm Show Example reveals exactly one example and does not rotate multiple examples for the same prompt
- [ ] Confirm Next Word resets example visibility
- [ ] Run the project build

**Technical Notes:**
Use `.\gradlew.bat assembleDebug` as the baseline build verification. If UI-level tests already exist or are introduced during implementation, add focused coverage for route wiring and drill state transitions.

### Phase 6: Documentation and Cleanup

**Status:** Planning

- [ ] Update this DevCycle document as implementation decisions are made
- [ ] Record any data entries removed or adjusted during curation
- [ ] Note follow-up subcategory ideas for a future cycle
- [ ] Update the completion summary when the cycle closes

**Technical Notes:**
The source design note says that once this feels good in use, future work should remove weaker pairs, add additional Loose Rhymes subcategories, expand dataset variety, and refine based on real usage feel. Treat those as follow-up work unless the implementation reveals an obvious data-quality issue that should be fixed in this cycle.

---

## Open Questions

1. **Should Loose Rhymes replace the existing Slant Rhyme home-screen action?**
   Recommendation: Replace or relabel the entry point so Loose Rhymes becomes the umbrella category and Default preserves the current Slant Rhyme behavior.

2. **Should All include Default Slant Rhyme data immediately?**
   Recommendation: Yes. The design note defines All as current plus future Loose Rhymes subcategories, so it should include the existing Slant Rhyme behavior and Hard Ending Shift.

3. **Should Hard Ending Shift reuse the Slant Rhyme screen or have a dedicated screen?**
   Recommendation: Reuse the underlying drill mechanics, but only if the UI can be configured down to the required two-button interaction. If not, create a dedicated screen for this narrower drill.

---

## Notes and Risks

- The main product risk is terminology: Loose Rhymes, Slant Rhyme, Default, All, and Hard Ending Shift need to feel understandable without extra explanation.
- Some starter pairs may be weaker than others. Keep the dataset easy to prune after hands-on use.
- All should be designed for future subcategories, but this cycle should not overbuild a large taxonomy system before there is more than one new subcategory.
- Hard Ending Shift should remain practice-oriented and lightweight. Adding input fields, correctness checking, or scoring would conflict with the source plan.

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
- Build verification: [Result]

**Lessons / Notes:**
[Anything worth remembering for future cycles: surprises, decisions made, things that worked well or didn't.]
