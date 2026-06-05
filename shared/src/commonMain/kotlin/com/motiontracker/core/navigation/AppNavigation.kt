package com.motiontracker.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.motiontracker.features.dashboard.presentation.DashboardScreen
import com.motiontracker.features.history.presentation.HistoryScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Dashboard : Screen
    @Serializable
    data object History : Screen
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard
    ) {
        composable<Screen.Dashboard> {
            DashboardScreen(
                onNavigateToHistory = {
                    navController.navigate(Screen.History)
                }
            )
        }
        composable<Screen.History> {
            HistoryScreen()
        }
    }
}
