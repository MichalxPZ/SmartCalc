package com.android.michalxpz.settings_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import prefs.SharedPrefs
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor() : ViewModel() {

    var dataStore: DataStore<SharedPrefs>? = null

    var state by mutableStateOf(SettingsState(2))
        private set

    fun setState(value: Int) {
        viewModelScope.launch {
            state = state.copy(value)
            dataStore?.let {
                it.updateData {
                    it.copy(round = value)
                }
            }
        }
    }

}