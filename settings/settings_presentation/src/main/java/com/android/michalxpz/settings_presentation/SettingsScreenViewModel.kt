package com.android.michalxpz.settings_presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import util.SharedPrefs
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(SettingsState(SharedPrefs.round))
        private set

    fun setState(value: Int) {
        viewModelScope.launch {
            state = state.copy(value)
            SharedPrefs.round = value
            Log.i("SHAREDPREFS", "round: ${SharedPrefs.round}")
        }
    }

}