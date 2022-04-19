package com.android.michalxpz.settings_presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.hilt.navigation.compose.hiltViewModel
import com.poznan.put.michalxpz.core_ui.LocalSpacing
import prefs.SharedPrefs

@Composable
fun SettingsScreen(
    popBack: () -> Unit,
    dataStore: DataStore<SharedPrefs>,
    viewModel: SettingsScreenViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    LaunchedEffect(key1 = viewModel) {
        viewModel.dataStore = dataStore
    }

    Column(
        modifier = Modifier
            .padding(spacing.extraLarge)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Default.Person, contentDescription = "", modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.height(spacing.mediumLarge))
        Text(text = "Rounded to : ${state.round}")
        Spacer(modifier = Modifier.height(spacing.mediumLarge))
        Slider(
            value = state.round.toFloat(),
            onValueChange = { viewModel.setState(it.toInt()) },
            valueRange = 0f..5f
        )
        Spacer(modifier = Modifier.height(spacing.mediumLarge))
        IconButton(
            onClick = { popBack() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "",
                modifier = Modifier.size(100.dp))
        }

    }
}