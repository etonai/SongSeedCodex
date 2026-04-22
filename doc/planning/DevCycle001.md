# DevCycle 001: SongSeed Android MVP First Pass

**Status:** Work Complete
**Start Date:** 2026-04-22
**Target Completion:** 2026-04-22
**Focus:** Convert the initial SongSeed concept into a first working Android app structure with the core MVP flows implemented.

---

## Goal

This cycle turns the initial SongSeed app plan into a concrete first Android implementation. The immediate objective was to create a usable Jetpack Compose MVP that reflects the product structure in the planning document rather than leaving the app at the idea stage. This matters now because it creates a real codebase we can iterate on, test, refine, and extend in future DevCycles.

## Desired Outcome

At the end of this cycle, the project should contain a structured Android app with the main SongSeed practice flows implemented: Home, Improv Song mode selection, Improv prompt generation, Rhyme Drill generation, and Settings. The app should also include starter data, app architecture, and persistence for basic settings so future cycles can focus on refinement instead of initial scaffolding.

---

## Tasks

### Phase 1: Project Scaffolding and Architecture

**Status:** Work Complete

- [x] Create a fresh Android project structure for SongSeed
- [x] Add Gradle configuration for a Kotlin + Jetpack Compose app
- [x] Establish package structure for data, domain, UI, navigation, theme, and view models
- [x] Add Android manifest, application class, and main activity

**Technical Notes:**
Created a greenfield Android project because the workspace only contained planning documents and no existing app scaffold. Added root project files at [build.gradle.kts](C:/dev/songseed/SongSeedCodex/build.gradle.kts), [settings.gradle.kts](C:/dev/songseed/SongSeedCodex/settings.gradle.kts), and [gradle.properties](C:/dev/songseed/SongSeedCodex/gradle.properties), plus the app module configuration in [app/build.gradle.kts](C:/dev/songseed/SongSeedCodex/app/build.gradle.kts). Established the base Android entry points in [MainActivity.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/MainActivity.kt), [SongSeedApplication.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/SongSeedApplication.kt), and [AndroidManifest.xml](C:/dev/songseed/SongSeedCodex/app/src/main/AndroidManifest.xml).

### Phase 2: Domain, Data, and Generation Logic

**Status:** Work Complete

- [x] Define shared domain models for improv prompts, rhyme words, settings, and modes
- [x] Add starter seed data for improv categories and rhyme drill words
- [x] Implement improv prompt generation for Normal and Difficult modes
- [x] Implement rhyme drill word generation with Easy and Normal filtering
- [x] Add settings persistence for repeat avoidance, timer seconds, and enabled categories

**Technical Notes:**
Added core models in [Models.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/domain/Models.kt). Added built-in prompt and word pools in [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/data/local/SeedData.kt). Implemented generation behavior in [ImprovRepository.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/data/ImprovRepository.kt) and [RhymeRepository.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/data/RhymeRepository.kt). Added DataStore-backed settings management in [SettingsRepository.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/data/SettingsRepository.kt). Difficult improv mode was adjusted to always combine two or more ideas, including the case where only one category is enabled.

### Phase 3: UI, Navigation, and ViewModels

**Status:** Work Complete

- [x] Build the Home screen
- [x] Build the Improv mode selection screen
- [x] Build the Improv prompt screen with immediate generation and regenerate behavior
- [x] Build the Rhyme Drill screen with difficulty toggles and next-word behavior
- [x] Build the Settings screen for user-configurable options
- [x] Connect screens through Compose navigation
- [x] Add ViewModels for improv, rhyme, and settings flows

**Technical Notes:**
Navigation and app wiring were implemented in [SongSeedApp.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/SongSeedApp.kt) and [Routes.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/navigation/Routes.kt). Screen implementations live in [HomeScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/screens/HomeScreen.kt), [ImprovModeSelectionScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/screens/ImprovModeSelectionScreen.kt), [ImprovScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/screens/ImprovScreen.kt), [RhymeScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/screens/RhymeScreen.kt), [SettingsScreen.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/screens/SettingsScreen.kt), and shared layout helpers in [Shared.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/screens/Shared.kt). State handling was implemented in [ImprovViewModel.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/viewmodel/ImprovViewModel.kt), [RhymeViewModel.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/viewmodel/RhymeViewModel.kt), [SettingsViewModel.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/viewmodel/SettingsViewModel.kt), and [Factories.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/viewmodel/Factories.kt).

### Phase 4: Theme, Resources, and Handoff Readiness

**Status:** Work Complete

- [x] Add base theme and typography files for Compose
- [x] Add string and theme resources
- [x] Review the first-pass structure for handoff
- [x] Record limitations discovered during implementation

**Technical Notes:**
Added base Compose theming in [Theme.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/theme/Theme.kt) and [Type.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/ui/theme/Type.kt), along with resources in [strings.xml](C:/dev/songseed/SongSeedCodex/app/src/main/res/values/strings.xml) and [themes.xml](C:/dev/songseed/SongSeedCodex/app/src/main/res/values/themes.xml). The cycle was left at `Work Complete` rather than `Verified` because the environment did not have a Gradle wrapper in the repo and did not have a local `gradle` command available for build verification.

---

## Notes and Risks

- The project is a fresh first pass, so visual polish, testing, and verification are still pending.
- The app was implemented against the written plan, but it has not yet been compiled in this environment.
- There is no Gradle wrapper in the repository yet, which blocks a standard `./gradlew` or `gradlew.bat` verification flow.
- The current theme is intentionally minimal and should be refined in a future DevCycle once the MVP behavior is confirmed.
- Seed data is built in for now; future cycles may want richer content, externalized data, or editor tooling for prompt pools.

---

## Completion Summary

**Completion Date:** 2026-04-22
**Phases Completed:** All
**Work Deferred:** Build verification, Gradle wrapper setup, testing, and UI polish

**Accomplishments:**
- Built the first SongSeed Android MVP structure from the initial planning document.
- Implemented the core app flows for Home, Improv Song, Rhyme Drill, and Settings.
- Added prompt and word generation logic aligned to the initial plan.
- Added persistence for core settings using DataStore.
- Left the project in a state that is ready for verification and iterative refinement in the next DevCycle.

**Metrics:**
- Files modified: 30
- Features implemented: 5 core screens plus navigation, generation logic, and settings persistence
- Verification status: Not verified in this environment due to missing Gradle tooling

**Lessons / Notes:**
Starting from a planning-only workspace made it reasonable to build a clean greenfield MVP rather than over-optimize around nonexistent structure. The most important follow-up is to add a Gradle wrapper and run a real build so the next cycle can shift from implementation setup to verification, bug fixing, and refinement.
