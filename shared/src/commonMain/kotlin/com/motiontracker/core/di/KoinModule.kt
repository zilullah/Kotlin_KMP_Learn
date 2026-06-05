package com.motiontracker.core.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val repositoryModule = module {
    // Repositories will be added here
}

val useCaseModule = module {
    // Use cases will be added here
}

val viewModelModule = module {
    // ViewModels will be added here
}

val networkModule = module {
    // Ktor client will be added here
}

val databaseModule = module {
    // SQLDelight database will be added here
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
