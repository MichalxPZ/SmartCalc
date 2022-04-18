package util

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    object NavigateToSettingsActivity : UiEvent()
}
