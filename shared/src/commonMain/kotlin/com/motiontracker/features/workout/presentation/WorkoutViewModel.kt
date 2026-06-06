package com.motiontracker.features.workout.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motiontracker.core.util.AppResult
import com.motiontracker.features.workout.domain.WorkoutRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkoutViewModel(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WorkoutUiState())
    val uiState = _uiState.asStateFlow()

    private var timerJob: Job? = null

    fun toggleTracking() {
        if (_uiState.value.isTracking) {
            stopTracking()
        } else {
            startTracking()
        }
    }

    private fun startTracking() {
        viewModelScope.launch {
            when (repository.startWorkout()) {
                is AppResult.Success -> {
                    _uiState.update { it.copy(isTracking = true, repetitions = 0, duration = 0) }
                    startTimer()
                }
                else -> {}
            }
        }
    }

    private fun stopTracking() {
        viewModelScope.launch {
            repository.stopWorkout()
            _uiState.update { it.copy(isTracking = false) }
            timerJob?.cancel()
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _uiState.update { it.copy(duration = it.duration + 1) }
                // Simulate reps for now
                if (_uiState.value.duration % 5 == 0L) {
                    _uiState.update { it.copy(repetitions = it.repetitions + 1) }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
