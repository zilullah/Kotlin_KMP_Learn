package com.motiontracker.features.history.domain

class GetWorkoutHistoryUseCase(private val repository: WorkoutHistoryRepository) {
    suspend operator fun invoke() = repository.getWorkoutHistory()
}
