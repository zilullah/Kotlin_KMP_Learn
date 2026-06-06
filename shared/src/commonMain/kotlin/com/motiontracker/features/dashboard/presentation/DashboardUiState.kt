package com.motiontracker.features.dashboard.presentation

import com.motiontracker.features.dashboard.domain.DashboardSummary

data class DashboardUiState(
    val summary: DashboardSummary? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
