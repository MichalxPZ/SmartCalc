package com.poznan.put.michalxpz.smartcalc

import CalculatorScreen
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poznan.put.michalxpz.smartcalc.ui.theme.SmartCalcTheme
import dagger.hilt.android.AndroidEntryPoint
import navigation.Routes
import util.UiEvent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val intent = Intent(this, SettingsActivity::class.java)
        super.onCreate(savedInstanceState)
        setContent {
            SmartCalcTheme {
                // A surface container using the 'background' color from the theme
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
                            CalculatorScreen(navigate = { uiEvent ->
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
