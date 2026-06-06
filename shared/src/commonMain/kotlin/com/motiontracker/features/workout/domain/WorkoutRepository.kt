package com.motiontracker.features.workout.domain

import com.motiontracker.core.util.AppResult

interface WorkoutRepository {
    suspend fun startWorkout(): AppResult<Unit>
    suspend fun stopWorkout(): AppResult<Unit>
}
