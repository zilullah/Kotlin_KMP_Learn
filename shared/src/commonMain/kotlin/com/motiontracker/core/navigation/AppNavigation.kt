package com.motiontracker.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.motiontracker.features.dashboard.presentation.DashboardScreen
import com.motiontracker.features.history.presentation.HistoryScreen
import com.motiontracker.features.settings.presentation.SettingsScreen
import com.motiontracker.features.workout.presentation.WorkoutScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable data object Dashboard : Screen
    @Serializable data object Workout : Screen
    @Serializable data object History : Screen
    @Serializable data object Settings : Screen
}

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    
    val topLevelRoutes = listOf(
        TopLevelRoute("Dashboard", Screen.Dashboard),
        TopLevelRoute("Workout", Screen.Workout),
        TopLevelRoute("History", Screen.History),
        TopLevelRoute("Settings", Screen.Settings)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                topLevelRoutes.forEach { topLevelRoute ->
                    NavigationBarItem(
                        icon = { Text(topLevelRoute.name.take(1)) },
                        label = { Text(topLevelRoute.name) },
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(Screen.Dashboard) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Screen.Dashboard> {
                DashboardScreen()
            }
            composable<Screen.Workout> {
                WorkoutScreen()
            }
            composable<Screen.History> {
                HistoryScreen()
            }
            composable<Screen.Settings> {
                SettingsScreen()
            }
        }
    }
}
