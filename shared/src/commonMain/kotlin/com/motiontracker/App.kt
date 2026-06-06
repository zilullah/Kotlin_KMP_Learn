package com.motiontracker

import androidx.compose.runtime.Composable
import com.motiontracker.core.navigation.MainScreen
import com.motiontracker.core.ui.MotionTrackerTheme

@Composable
fun App() {
    MotionTrackerTheme {
        MainScreen()
    }
}
