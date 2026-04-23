# DevCycle 003: Reference Data Expansion

**Status:** Work Complete
**Start Date:** 2026-04-23
**Target Completion:** 2026-04-23
**Focus:** Incorporate additional improv ideas and rhyme words from the reference files into the SongSeedCodex app data.

---

## Goal

This cycle expands the app’s creative practice content by pulling in additional words and ideas from the reference materials in [doc/reference](C:/dev/songseed/SongSeedCodex/doc/reference). The app already has larger built-in pools from Dev Cycle 2, but this cycle is about folding in the external reference sources so the in-app content better reflects the available project materials. This matters now because richer source data directly improves prompt variety and reduces repetition during practice.

## Desired Outcome

At the end of this cycle, the reference content from [ImprovData.kt](C:/dev/songseed/SongSeedCodex/doc/reference/ImprovData.kt) and [WordData.kt](C:/dev/songseed/SongSeedCodex/doc/reference/WordData.kt) should be reviewed, merged into the app where appropriate, and reflected in the built-in seed data. The project should also document any filtering, deduplication, categorization, or deferred content decisions made during the import.

---

## Tasks

### Phase 1: Reference Review and Mapping

**Status:** Work Complete

- [x] Read the reference files in `doc/reference`
- [x] Identify which reference entries map cleanly to existing improv categories and rhyme difficulty buckets
- [x] Note any source data that needs cleanup, reclassification, or manual review before import
- [x] Establish an import approach that preserves data quality instead of blindly copying everything

**Technical Notes:**
The primary source files for this cycle were [ImprovData.kt](C:/dev/songseed/SongSeedCodex/doc/reference/ImprovData.kt) and [WordData.kt](C:/dev/songseed/SongSeedCodex/doc/reference/WordData.kt). Their structures mapped cleanly enough to the app’s current seed model in [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt), but the import still required curation to avoid duplicates, duplicate source entries, and structurally irrelevant implementation content from the reference files.

### Phase 2: Improv Idea Import

**Status:** Work Complete

- [x] Merge useful improv ideas from the reference source into the app’s improv category pools
- [x] Preserve the app’s category structure and prompt-generation compatibility
- [x] Remove duplicates or near-duplicates where they reduce value
- [x] Recount the improv totals after import

**Technical Notes:**
The app’s improv content in [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseedcodex/data/local/SeedData.kt) was expanded using curated entries from the reference source while preserving the existing `ImprovCategory` structure. Imported ideas were added only when they fit the current generator model and did not duplicate existing in-app content too closely. The resulting improv pool now totals 308 ideas across the 10 categories.

### Phase 3: Rhyme Word Import

**Status:** Work Complete

- [x] Merge useful rhyme words from the reference source into the app’s rhyme word pools
- [x] Classify imported words according to the app’s current easy/normal rule
- [x] Remove duplicates and malformed entries
- [x] Recount the easy and normal rhyme totals after import

**Technical Notes:**
The app continued to use the rule `syllableCount == 1 || text.length <= 5` to determine easy rhyme words. Reference words from [WordData.kt](C:/dev/songseed/SongSeedCodex/doc/reference/WordData.kt) were imported into the existing easy and normal pools only when they were not already present in the app. Duplicate source entries such as repeated words in the reference list were effectively collapsed during curation. The final rhyme totals are 234 easy words and 169 normal words, and both pools were checked against the app’s classification rule after import.

### Phase 4: Verification and Documentation

**Status:** Work Complete

- [x] Verify the final imported totals
- [x] Confirm the app still builds after the data update
- [x] Record what was imported, skipped, or normalized
- [x] Update the DevCycle document with implementation details and final counts

**Technical Notes:**
Final totals were recounted directly from the updated seed data and the app was rebuilt successfully with `gradlew.bat assembleDebug`. Import decisions were intentionally conservative: the cycle merged category-aligned reference entries and omitted code-structure strings or function-template content from the reference files. The resulting data is significantly larger while still fitting the existing app logic.

---

## Open Questions

1. **Should every reference entry be imported if it fits technically, or should weaker entries be curated out?**
   Recommendation: Curate aggressively enough to keep the in-app pools high-quality, even if that means not importing every usable item.

2. **Should imported data stay hardcoded in `SeedData.kt`, or should this cycle begin moving content into a more editable format?**
   Recommendation: Keep this cycle focused on incorporating the reference data into the current app structure first, then consider content-format refactoring in a later cycle if needed.

---

## Notes and Risks

- Reference content may not map perfectly to the current app category structure.
- Large imports can introduce duplicates, low-quality items, or inconsistent tone if not reviewed carefully.
- Rhyme difficulty classification may be noisy if the source data lacks reliable syllable metadata.
- If the reference files are much larger than expected, some content may need to be deferred rather than merged in one pass.

---

## Completion Summary

*Fill in when the cycle closes. Move this document to `doc/planning/completed/` afterward.*

**Completion Date:** 2026-04-23
**Phases Completed:** All
**Work Deferred:** None

**Accomplishments:**
- Imported additional improv ideas from the reference source into every improv category in the app.
- Imported a large set of additional rhyme words from the reference source into both the easy and normal word pools.
- Preserved the app’s existing category model and rhyme difficulty rule while deduplicating imported content.
- Verified that the expanded seed data still compiles in a debug build.

**Metrics:**
- Files modified: 2
- Improv ideas total: 308
- Easy rhyme words total: 234
- Normal rhyme words total: 169
- Build verification: `gradlew.bat assembleDebug` completed successfully

**Lessons / Notes:**
Reference data can be valuable without being copied wholesale. The most effective approach here was to merge category-aligned content into the current app model while ignoring reference implementation scaffolding and obvious duplicates. If content continues to grow, a future cycle should strongly consider moving seed data out of Kotlin source and into a more editable content format.
