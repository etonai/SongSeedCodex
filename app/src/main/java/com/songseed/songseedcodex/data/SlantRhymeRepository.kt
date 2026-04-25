package com.songseed.songseedcodex.data

import com.songseed.songseedcodex.data.local.SeedData
import com.songseed.songseedcodex.domain.SlantRhymePair
import kotlin.random.Random

class SlantRhymeRepository(
    private val random: Random = Random.Default
) {
    fun nextPair(
        recentSeeds: List<String>,
        avoidRecentRepeats: Boolean
    ): SlantRhymePair {
        val pairs = SeedData.slantRhymePairs
        val primaryPool = if (avoidRecentRepeats) {
            pairs.filterNot {
                it.seed in recentSeeds || it.example in recentSeeds
            }.ifEmpty { pairs }
        } else {
            pairs
        }

        return primaryPool.random(random).maybeReversed()
    }

    private fun SlantRhymePair.maybeReversed(): SlantRhymePair {
        return if (random.nextBoolean()) {
            this
        } else {
            SlantRhymePair(seed = example, example = seed)
        }
    }
}
