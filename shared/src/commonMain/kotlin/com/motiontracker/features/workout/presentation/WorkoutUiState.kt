package com.motiontracker.features.workout.presentation

data class WorkoutUiState(
    val repetitions: Int = 0,
    val isTracking: Boolean = false,
    val duration: Long = 0,
    val isLoading: Boolean = false
)
