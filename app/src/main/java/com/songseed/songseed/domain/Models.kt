package com.songseed.songseed.domain

enum class ImprovMode(val label: String) {
    Normal("Normal"),
    Difficult("Difficult");

    companion object {
        fun fromRouteValue(value: String?): ImprovMode {
            return values().firstOrNull { it.name.equals(value, ignoreCase = true) } ?: Normal
        }
    }
}

enum class RhymeDifficulty(val label: String) {
    Easy("Easy"),
    Normal("Normal")
}

enum class ImprovCategory(val displayName: String) {
    Themes("Themes"),
    Images("Images"),
    Characters("Characters"),
    Situations("Situations"),
    Emotions("Emotions"),
    Places("Places"),
    Goals("Goals"),
    Obstacles("Obstacles"),
    Twists("Twists"),
    Elements("Elements")
}

data class PromptPiece(
    val category: ImprovCategory,
    val value: String
)

data class ImprovPrompt(
    val text: String,
    val pieces: List<PromptPiece>
)

data class Word(
    val text: String,
    val syllableCount: Int
)

data class SettingsState(
    val avoidRecentRepeats: Boolean = true,
    val rhymeTimerSeconds: Int = 60,
    val enabledCategories: Set<ImprovCategory> = ImprovCategory.values().toSet()
)

data class SessionState(
    val recentPrompts: List<String> = emptyList(),
    val recentWords: List<String> = emptyList()
)
