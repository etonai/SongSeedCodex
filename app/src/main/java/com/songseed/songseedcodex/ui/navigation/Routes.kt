package com.songseed.songseedcodex.ui.navigation

object Routes {
    const val Home = "home"
    const val ImprovModes = "improv_modes"
    const val Improv = "improv/{mode}"
    const val Rhyme = "rhyme"
    const val Settings = "settings"

    fun improv(mode: String): String = "improv/$mode"
}
