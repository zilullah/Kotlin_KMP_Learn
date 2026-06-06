package com.motiontracker.features.workout.data

import com.motiontracker.core.util.AppResult
import com.motiontracker.features.workout.domain.WorkoutRepository

class FakeWorkoutRepository : WorkoutRepository {
    override suspend fun startWorkout(): AppResult<Unit> = AppResult.Success(Unit)
    override suspend fun stopWorkout(): AppResult<Unit> = AppResult.Success(Unit)
}
