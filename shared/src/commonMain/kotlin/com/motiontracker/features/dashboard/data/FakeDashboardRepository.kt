package com.motiontracker.features.dashboard.data

import com.motiontracker.core.util.AppResult
import com.motiontracker.features.dashboard.domain.DashboardRepository
import com.motiontracker.features.dashboard.domain.DashboardSummary
import kotlinx.coroutines.delay

class FakeDashboardRepository : DashboardRepository {
    override suspend fun getDashboardSummary(): AppResult<DashboardSummary> {
        delay(1000) // Simulate network delay
        return AppResult.Success(
            DashboardSummary(
                totalWorkouts = 12,
                totalReps = 350,
                lastWorkoutDate = "2023-10-25"
            )
        )
    }
}
