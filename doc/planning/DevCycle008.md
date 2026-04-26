# DevCycle 008: Multi-Syllable Loose Rhymes

**Status:** Work Complete
**Start Date:** 2026-04-26
**Target Completion:** 2026-04-26
**Focus:** Add Multi-Syllable as a Loose Rhymes subcategory with a description screen, bidirectional seed data, and the existing two-button drill flow.

---

## Goal

This cycle adds the Multi-Syllable Loose Rhymes drill described in [multi-syllable.md](C:/dev/songseed/SongSeedCodex/doc/planning/Ideas/multi-syllable.md). DC 7 established Loose Rhymes as an umbrella category with subcategory selection, description screens, and a focused Show Example / Next Word drill. Multi-Syllable should extend that structure by training users to match rhythm, vowel flow, and stress pattern across multiple syllables without requiring exact rhymes.

## Desired Outcome

At the end of this cycle, users should be able to choose Multi-Syllable from Loose Rhymes, read a short description of the concept, and start a drill. The drill should show one prompt at a time and reveal one predefined example only when Show Example is tapped. Each pair should be usable bidirectionally, so either member can appear as the drill word.

---

## Tasks

### Phase 1: Loose Rhymes Category Integration

**Status:** Work Complete

- [x] Add Multi-Syllable to the Loose Rhymes subcategory selection screen
- [x] Add or update route constants for the Multi-Syllable description flow
- [x] Route Multi-Syllable through its description screen before the drill starts
- [x] Ensure All includes Multi-Syllable data along with Default Slant Rhyme and Hard Ending Shift sources
- [x] Keep Default behavior unchanged

**Technical Notes:**
Likely touch points include [Routes.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/navigation/Routes.kt), [SongSeedCodexApp.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/SongSeedCodexApp.kt), [LooseRhymesScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/screens/LooseRhymesScreen.kt), and the Loose Rhymes domain/repository files added in DC 7.

Implementation added Multi-Syllable to the Loose Rhymes selection screen and routed it through `Routes.MultiSyllableDescription`. Default still opens the existing Slant Rhyme drill, and All now combines Slant Rhyme, Hard Ending Shift, and Multi-Syllable pairs.

### Phase 2: Multi-Syllable Data Model and Seeds

**Status:** Work Complete

- [x] Add a Multi-Syllable drill source or subcategory identifier
- [x] Add a Multi-Syllable seed list where each prompt maps to exactly one example
- [x] Preserve bidirectional prompt behavior for every pair
- [x] Keep Show Example tied to the current pair only
- [x] Review the starter pairs for perfect rhymes or weak sound/rhythm matches before implementation closes

**Technical Notes:**
The current Loose Rhymes implementation already uses pair data and can reverse a selected pair into either prompt direction. Prefer extending that path rather than creating a separate drill state model.

Implementation added `LooseRhymeDrillSource.MultiSyllable` and `SeedData.multiSyllableLooseRhymePairs`. The existing Loose Rhymes repository reverses pairs randomly, so either member can become the prompt word.

Starter Multi-Syllable pairs from the source plan:

- maybe -> daydream
- taken -> paper
- open -> broken
- better -> never
- number -> thunder
- feeling -> breathing
- matter -> after
- quiet -> giant
- heavy -> steady
- early -> journey
- silver -> river
- story -> glory
- music -> lucid
- echo -> mellow

### Phase 3: Description Screen

**Status:** Work Complete

- [x] Create a Multi-Syllable Loose Rhymes description screen
- [x] Show the title "Multi-Syllable Loose Rhymes"
- [x] Explain that the drill matches shape and sound across multiple syllables
- [x] Mention rhythm, vowel flow, and stress pattern as the focus areas
- [x] Include example pairs: maybe -> daydream, taken -> paper, better -> never
- [x] Add a Start Drill action that opens the Multi-Syllable drill

**Technical Notes:**
The description should stay short and practical. It should emphasize that the goal is something that feels similar when spoken or sung, while avoiding perfect rhymes.

Implemented in [LooseRhymesScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/screens/LooseRhymesScreen.kt) as `MultiSyllableDescriptionScreen`.

### Phase 4: Drill Behavior

**Status:** Work Complete

- [x] Reuse the existing Loose Rhymes drill for Multi-Syllable prompts
- [x] Show one main prompt word or phrase centered on the drill screen
- [x] Provide only Show Example and Next Word actions
- [x] Make Show Example reveal exactly one predefined example
- [x] Make Next Word advance to a new prompt and reset example visibility
- [x] Avoid correctness checking, answer submission, scoring, timers, and required response counts

**Technical Notes:**
Multi-Syllable should use the same lightweight drill model as Hard Ending Shift. If longer words or phrases need layout adjustments, keep those changes generic so Hard Ending Shift and future Loose Rhymes subcategories benefit too.

Multi-Syllable uses the existing `LooseRhymeDrillScreen`, `LooseRhymeViewModel`, and `LooseRhymeRepository` flow.

### Phase 5: Verification

**Status:** Work Complete

- [x] Confirm Multi-Syllable appears under Loose Rhymes
- [x] Confirm Multi-Syllable opens the description screen before the drill
- [x] Confirm Start Drill launches the Multi-Syllable drill source
- [x] Confirm All can serve Multi-Syllable prompts in addition to existing Loose Rhymes data
- [x] Confirm either member of a Multi-Syllable pair can appear as the drill word
- [x] Confirm Show Example and Next Word behavior matches DC 7
- [x] Run the project build

**Technical Notes:**
Use `.\gradlew.bat assembleDebug` as the baseline build verification. If UI automation is added later, route coverage should include Loose Rhymes -> Multi-Syllable -> Start Drill.

Build verification completed successfully with `.\gradlew.bat assembleDebug`. Route and state behavior were verified by code review; no UI automation tests exist in the project yet.

### Phase 6: Documentation and Cleanup

**Status:** Work Complete

- [x] Update this DevCycle document as implementation decisions are made
- [x] Record any seed pairs adjusted or deferred during curation
- [x] Note follow-up ideas for line-based or phrase-based drills
- [x] Update the completion summary when the cycle closes

**Technical Notes:**
The source plan lists future refinement based on spoken or sung feel, expanded multi-syllable patterns, and possible line-based or phrase-based drills. Treat those as follow-up work unless the implementation reveals a small, obvious adjustment needed for this cycle.

---

## Open Questions

1. **Should Multi-Syllable use the same drill screen as Hard Ending Shift?**
   Decision: Yes. Multi-Syllable reuses the existing Loose Rhymes drill screen and state flow.

2. **Should All include Multi-Syllable immediately?**
   Decision: Yes. All now includes Slant Rhyme, Hard Ending Shift, and Multi-Syllable pairs.

3. **Should any starter pairs be removed before implementation?**
   Decision: No starter pairs were removed during implementation. Further refinement should happen after spoken or sung usage feedback.

---

## Notes and Risks

- Multi-syllable quality is more subjective than single-syllable ending shifts, so seed curation may need real usage feedback.
- Some example pairs may depend on pronunciation or singing style.
- Longer words may need slightly more flexible display sizing, but the drill should stay visually simple.
- Avoid expanding this cycle into line-based or phrase-based drills; those are promising follow-ups but separate feature work.

---

## Completion Summary

*Fill in when the cycle closes. Move this document to `doc/planning/completed/` afterward.*

**Completion Date:** 2026-04-26
**Phases Completed:** All
**Work Deferred:** UI automation tests; the project does not currently include UI tests. Spoken/sung seed refinement remains a future curation pass.

**Accomplishments:**
- Added Multi-Syllable to the Loose Rhymes selection screen.
- Added Multi-Syllable seed data and a dedicated drill source.
- Added the Multi-Syllable description screen and routed Start Drill into the shared Loose Rhymes drill.
- Updated All so it includes Multi-Syllable pairs.

**Metrics:**
- Files modified: 7
- Build verification: `.\gradlew.bat assembleDebug` completed successfully

**Lessons / Notes:**
The DC 7 Loose Rhymes structure extended cleanly. Adding a new subcategory only required a source enum value, seed data, selection/description routing, and inclusion in the All source.
