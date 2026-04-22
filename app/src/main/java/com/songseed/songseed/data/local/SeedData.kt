package com.songseed.songseed.data.local

import com.songseed.songseed.domain.ImprovCategory
import com.songseed.songseed.domain.Word

object SeedData {
    val improvItems: Map<ImprovCategory, List<String>> = mapOf(
        ImprovCategory.Themes to listOf(
            "second chances",
            "growing older",
            "reckless hope",
            "belonging",
            "starting over"
        ),
        ImprovCategory.Images to listOf(
            "a broken window",
            "a neon motel sign",
            "an empty swing set",
            "a flooded notebook",
            "a paper crown"
        ),
        ImprovCategory.Characters to listOf(
            "a night-shift baker",
            "a runaway magician",
            "a small-town mayor",
            "an exhausted astronaut",
            "a taxi driver"
        ),
        ImprovCategory.Situations to listOf(
            "during a midnight confession",
            "after missing the last train",
            "at a wedding that should not happen",
            "while hiding from a storm",
            "right before a big apology"
        ),
        ImprovCategory.Emotions to listOf(
            "jealousy",
            "regret",
            "relief",
            "homesickness",
            "quiet joy"
        ),
        ImprovCategory.Places to listOf(
            "a grocery store parking lot",
            "a desert highway",
            "a crowded rooftop",
            "a rainy bus stop",
            "a tiny apartment kitchen"
        ),
        ImprovCategory.Goals to listOf(
            "leave town",
            "win someone back",
            "keep a promise",
            "make rent",
            "finally tell the truth"
        ),
        ImprovCategory.Obstacles to listOf(
            "your car will not start",
            "you lost the address",
            "the power just went out",
            "everyone expects you to stay",
            "you cannot stop laughing"
        ),
        ImprovCategory.Twists to listOf(
            "time is running backward",
            "the villain is right",
            "nobody remembers your name",
            "the weather responds to your mood",
            "you are your own competition"
        ),
        ImprovCategory.Elements to listOf(
            "a payphone",
            "a lucky coin",
            "a grocery receipt",
            "a red umbrella",
            "a voicemail"
        )
    )

    val rhymeWords: List<Word> = listOf(
        Word("light", 1),
        Word("car", 1),
        Word("rain", 1),
        Word("blue", 1),
        Word("time", 1),
        Word("stone", 1),
        Word("window", 2),
        Word("tomorrow", 3),
        Word("silver", 2),
        Word("memory", 3),
        Word("telephone", 3),
        Word("cinema", 3),
        Word("gravity", 3),
        Word("holiday", 3),
        Word("wondering", 3),
        Word("feather", 2),
        Word("velocity", 4),
        Word("radio", 3)
    )
}
