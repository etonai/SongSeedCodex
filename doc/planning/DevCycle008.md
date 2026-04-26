# DevCycle 008: Multi-Syllable Loose Rhymes

**Status:** Planning
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

**Status:** Planning

- [ ] Add Multi-Syllable to the Loose Rhymes subcategory selection screen
- [ ] Add or update route constants for the Multi-Syllable description flow
- [ ] Route Multi-Syllable through its description screen before the drill starts
- [ ] Ensure All includes Multi-Syllable data along with Default Slant Rhyme and Hard Ending Shift sources
- [ ] Keep Default behavior unchanged

**Technical Notes:**
Likely touch points include [Routes.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/navigation/Routes.kt), [SongSeedCodexApp.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/SongSeedCodexApp.kt), [LooseRhymesScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/ui/screens/LooseRhymesScreen.kt), and the Loose Rhymes domain/repository files added in DC 7.

### Phase 2: Multi-Syllable Data Model and Seeds

**Status:** Planning

- [ ] Add a Multi-Syllable drill source or subcategory identifier
- [ ] Add a Multi-Syllable seed list where each prompt maps to exactly one example
- [ ] Preserve bidirectional prompt behavior for every pair
- [ ] Keep Show Example tied to the current pair only
- [ ] Review the starter pairs for perfect rhymes or weak sound/rhythm matches before implementation closes

**Technical Notes:**
The current Loose Rhymes implementation already uses pair data and can reverse a selected pair into either prompt direction. Prefer extending that path rather than creating a separate drill state model.

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

**Status:** Planning

- [ ] Create a Multi-Syllable Loose Rhymes description screen
- [ ] Show the title "Multi-Syllable Loose Rhymes"
- [ ] Explain that the drill matches shape and sound across multiple syllables
- [ ] Mention rhythm, vowel flow, and stress pattern as the focus areas
- [ ] Include example pairs: maybe -> daydream, taken -> paper, better -> never
- [ ] Add a Start Drill action that opens the Multi-Syllable drill

**Technical Notes:**
The description should stay short and practical. It should emphasize that the goal is something that feels similar when spoken or sung, while avoiding perfect rhymes.

### Phase 4: Drill Behavior

**Status:** Planning

- [ ] Reuse the existing Loose Rhymes drill for Multi-Syllable prompts
- [ ] Show one main prompt word or phrase centered on the drill screen
- [ ] Provide only Show Example and Next Word actions
- [ ] Make Show Example reveal exactly one predefined example
- [ ] Make Next Word advance to a new prompt and reset example visibility
- [ ] Avoid correctness checking, answer submission, scoring, timers, and required response counts

**Technical Notes:**
Multi-Syllable should use the same lightweight drill model as Hard Ending Shift. If longer words or phrases need layout adjustments, keep those changes generic so Hard Ending Shift and future Loose Rhymes subcategories benefit too.

### Phase 5: Verification

**Status:** Planning

- [ ] Confirm Multi-Syllable appears under Loose Rhymes
- [ ] Confirm Multi-Syllable opens the description screen before the drill
- [ ] Confirm Start Drill launches the Multi-Syllable drill source
- [ ] Confirm All can serve Multi-Syllable prompts in addition to existing Loose Rhymes data
- [ ] Confirm either member of a Multi-Syllable pair can appear as the drill word
- [ ] Confirm Show Example and Next Word behavior matches DC 7
- [ ] Run the project build

**Technical Notes:**
Use `.\gradlew.bat assembleDebug` as the baseline build verification. If UI automation is added later, route coverage should include Loose Rhymes -> Multi-Syllable -> Start Drill.

### Phase 6: Documentation and Cleanup

**Status:** Planning

- [ ] Update this DevCycle document as implementation decisions are made
- [ ] Record any seed pairs adjusted or deferred during curation
- [ ] Note follow-up ideas for line-based or phrase-based drills
- [ ] Update the completion summary when the cycle closes

**Technical Notes:**
The source plan lists future refinement based on spoken or sung feel, expanded multi-syllable patterns, and possible line-based or phrase-based drills. Treat those as follow-up work unless the implementation reveals a small, obvious adjustment needed for this cycle.

---

## Open Questions

1. **Should Multi-Syllable use the same drill screen as Hard Ending Shift?**
   Recommendation: Yes. The requested interaction is the same two-button flow, so the existing Loose Rhymes drill should be reused.

2. **Should All include Multi-Syllable immediately?**
   Recommendation: Yes. All should represent every available Loose Rhymes source once Multi-Syllable is added.

3. **Should any starter pairs be removed before implementation?**
   Recommendation: Review pairs by spoken/sung feel during implementation, especially pairs that may read as closer to perfect rhymes than loose rhymes.

---

## Notes and Risks

- Multi-syllable quality is more subjective than single-syllable ending shifts, so seed curation may need real usage feedback.
- Some example pairs may depend on pronunciation or singing style.
- Longer words may need slightly more flexible display sizing, but the drill should stay visually simple.
- Avoid expanding this cycle into line-based or phrase-based drills; those are promising follow-ups but separate feature work.

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
