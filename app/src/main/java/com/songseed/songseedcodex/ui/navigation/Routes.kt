package com.songseed.songseedcodex.ui.navigation

object Routes {
    const val Home = "home"
    const val ImprovModes = "improv_modes"
    const val Improv = "improv/{mode}"
    const val Rhyme = "rhyme"
    const val LooseRhymes = "loose_rhymes"
    const val HardEndingShiftDescription = "loose_rhymes/hard-ending-shift"
    const val LooseRhymeDrill = "loose_rhymes/drill/{source}"
    const val SlantRhyme = "slant_rhyme"
    const val Settings = "settings"

    fun improv(mode: String): String = "improv/$mode"
    fun looseRhymeDrill(source: String): String = "loose_rhymes/drill/$source"
}
