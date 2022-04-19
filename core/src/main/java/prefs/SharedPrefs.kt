package prefs

import kotlinx.serialization.Serializable

@Serializable
data class SharedPrefs(
    val round: Int = 2
)
