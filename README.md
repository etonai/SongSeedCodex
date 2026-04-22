# SongSeedCodex

SongSeedCodex is an Android app for practicing improvised songs and rhyme drills.

The current project is a Jetpack Compose MVP based on the planning documents in [doc/planning](C:/dev/songseed/SongSeedCodex/doc/planning). It includes:

- Improv Song mode with Normal and Difficult prompt generation
- Rhyme Drill mode with Easy and Normal difficulty
- Settings for repeat avoidance, timer duration, and enabled improv categories
- Built-in starter prompt and rhyme seed data

This project was written using Codex.

Another version of this project was also written using Claude, and that Claude version was determined to better meet the project needs.

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM
- Android DataStore

## Project Structure

- [app](C:/dev/songseed/SongSeedCodex/app): Android application module
- [doc/planning](C:/dev/songseed/SongSeedCodex/doc/planning): development process and DevCycle planning docs
- [SeedData.kt](C:/dev/songseed/SongSeedCodex/app/src/main/java/com/songseed/songseed/data/local/SeedData.kt): built-in improv topics and rhyme word lists

## Running The App

1. Open the project root in Android Studio.
2. Let Gradle sync finish.
3. Select the `app` run configuration.
4. Choose an emulator or connected Android device.
5. Run the app.

If Android Studio does not immediately show run controls, use `File > Sync Project with Gradle Files`.

## Current Status

This repository currently contains a first functional pass of the SongSeedCodex Android app. The MVP is intended as a foundation for future refinement, verification, UI polish, and expanded content.

## License

This project is licensed under the MIT License. See [LICENSE](C:/dev/songseed/SongSeedCodex/LICENSE).

## Author

Edward T. Tonai
