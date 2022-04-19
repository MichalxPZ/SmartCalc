package com.poznan.put.michalxpz.smartcalc

import CalculatorScreen
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poznan.put.michalxpz.smartcalc.ui.theme.SmartCalcTheme
import dagger.hilt.android.AndroidEntryPoint
import navigation.Routes
import prefs.SharedPrefs
import prefs.SharedPrefsSerializer
import util.UiEvent
import java.io.File

const val sharedPrefsFileName = "sharedPrefs.json"

var dataStore : DataStore<SharedPrefs>? = null

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        dataStore = DataStoreFactory.create(
            serializer = SharedPrefsSerializer,
            produceFile = {
                this.cacheDir.absoluteFile
            }
        )

        val intent = Intent(this, SettingsActivity::class.java).apply {
            putExtra("sharedPrefsFileName", sharedPrefsFileName)
        }

        super.onCreate(savedInstanceState)
        setContent {
            SmartCalcTheme {
                val sharedPrefs = dataStore!!.data.collectAsState(
                    initial = SharedPrefs()
                ).value

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.CALCULATOR
                    ) {
                        composable(Routes.CALCULATOR) {
                            CalculatorScreen(
                                round = sharedPrefs.round,
                                startSettingsActivity = { startActivity(intent) },
                                navigate = { uiEvent ->
                                when (uiEvent) {
                                    is UiEvent.Navigate -> { navController.navigate(uiEvent.route) }
                                    is UiEvent.NavigateToSettingsActivity -> {
                                        startActivity(intent)
                                    }
                                }
                            }
                            )
                        }
                    }
                }
            }
        }
    }
}
