package com.motiontracker.features.dashboard.domain

import com.motiontracker.core.util.AppResult

data class DashboardSummary(
    val totalWorkouts: Int,
    val totalReps: Int,
    val lastWorkoutDate: String?
)

interface DashboardRepository {
    suspend fun getDashboardSummary(): AppResult<DashboardSummary>
}
