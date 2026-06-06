package com.motiontracker.features.history.data

import com.motiontracker.core.util.AppResult
import com.motiontracker.features.history.domain.WorkoutHistoryRepository
import com.motiontracker.features.history.domain.WorkoutSession
import kotlinx.coroutines.delay

class FakeWorkoutHistoryRepository : WorkoutHistoryRepository {
    override suspend fun getWorkoutHistory(): AppResult<List<WorkoutSession>> {
        delay(800)
        return AppResult.Success(
            listOf(
                WorkoutSession("1", "Squats", 30, "00:45", "2023-10-25"),
                WorkoutSession("2", "Squats", 25, "00:40", "2023-10-24"),
                WorkoutSession("3", "Pushups", 20, "00:30", "2023-10-23")
            )
        )
    }
}
