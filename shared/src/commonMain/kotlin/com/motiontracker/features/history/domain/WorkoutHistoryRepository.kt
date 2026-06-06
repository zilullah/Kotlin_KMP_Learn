package com.motiontracker.features.history.domain

import com.motiontracker.core.util.AppResult

data class WorkoutSession(
    val id: String,
    val type: String,
    val repetitions: Int,
    val duration: String,
    val date: String
)

interface WorkoutHistoryRepository {
    suspend fun getWorkoutHistory(): AppResult<List<WorkoutSession>>
}
