package com.motiontracker.features.history.presentation

import com.motiontracker.features.history.domain.WorkoutSession

data class HistoryUiState(
    val sessions: List<WorkoutSession> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
