package com.motiontracker.features.dashboard.domain

class GetDashboardSummaryUseCase(private val repository: DashboardRepository) {
    suspend operator fun invoke() = repository.getDashboardSummary()
}
