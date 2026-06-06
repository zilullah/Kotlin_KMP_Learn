package com.motiontracker.core.di

import com.motiontracker.features.dashboard.data.FakeDashboardRepository
import com.motiontracker.features.dashboard.domain.DashboardRepository
import com.motiontracker.features.dashboard.domain.GetDashboardSummaryUseCase
import com.motiontracker.features.dashboard.presentation.DashboardViewModel
import com.motiontracker.features.history.data.FakeWorkoutHistoryRepository
import com.motiontracker.features.history.domain.GetWorkoutHistoryUseCase
import com.motiontracker.features.history.domain.WorkoutHistoryRepository
import com.motiontracker.features.history.presentation.HistoryViewModel
import com.motiontracker.features.settings.presentation.SettingsViewModel
import com.motiontracker.features.workout.data.FakeWorkoutRepository
import com.motiontracker.features.workout.domain.WorkoutRepository
import com.motiontracker.features.workout.presentation.WorkoutViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val repositoryModule = module {
    single<DashboardRepository> { FakeDashboardRepository() }
    single<WorkoutHistoryRepository> { FakeWorkoutHistoryRepository() }
    single<WorkoutRepository> { FakeWorkoutRepository() }
}

val useCaseModule = module {
    factoryOf(::GetDashboardSummaryUseCase)
    factoryOf(::GetWorkoutHistoryUseCase)
}

val viewModelModule = module {
    viewModelOf(::DashboardViewModel)
    viewModelOf(::HistoryViewModel)
    viewModelOf(::WorkoutViewModel)
    viewModelOf(::SettingsViewModel)
}

val networkModule = module {
}

val databaseModule = module {
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            repositoryModule,
            useCaseModule,
            viewModelModule,
            networkModule,
            databaseModule
        )
    }
