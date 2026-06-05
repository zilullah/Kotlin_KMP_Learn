# AGENT.md

## Project Overview

Project Name: MotionTracker

MotionTracker is a Kotlin Multiplatform (KMP) application for workout and motion tracking using camera-based pose estimation.

Current MVP Scope:

* Dashboard
* Workout Session
* Squat Counter
* Workout History
* Local Storage
* Android First
* Future iOS Support

Authentication is intentionally excluded from the MVP.

---

## Tech Stack

### Shared

* Kotlin Multiplatform
* Compose Multiplatform
* Koin
* Ktor
* SQLDelight
* Kotlin Coroutines
* Kotlin Serialization

### Android

* CameraX
* MediaPipe Pose Detection

### iOS (Future)

* AVFoundation
* MediaPipe iOS

---

## Architecture

Follow Clean Architecture.

Layers:

Presentation
↓
Domain
↓
Data

Dependencies must always point inward.

Presentation must not directly access Data sources.

---

## Feature Structure

Organize code by feature.

Example:

shared/src/commonMain/kotlin/

features/
├── dashboard/
│   ├── presentation/
│   ├── domain/
│   └── data/
│
├── workout/
│   ├── presentation/
│   ├── domain/
│   └── data/
│
├── history/
│   ├── presentation/
│   ├── domain/
│   └── data/
│
core/
├── database/
├── network/
├── di/
├── navigation/
└── ui/

platform/

Do not create large global folders for repositories, use cases, or view models.

Everything should belong to a feature.

---

## State Management

Use MVVM.

Screen State:

* immutable uiState
* StateFlow

Example:

WorkoutUiState
HistoryUiState
DashboardUiState

Avoid mutable state exposed publicly.

---

## Dependency Injection

Use Koin.

Register dependencies through modules.

Example:

* repositoryModule
* databaseModule
* networkModule
* featureModule

Never instantiate repositories directly inside ViewModels.

---

## Database

Use SQLDelight.

Rules:

* Database access only through repositories.
* No SQL in UI layer.
* Use suspend functions.

---

## Networking

Use Ktor.

Requirements:

* ContentNegotiation
* Kotlin Serialization
* Logging in debug builds

Avoid Retrofit.

---

## Pose Detection

Pose detection must be abstracted.

Shared code should never directly depend on MediaPipe.

Use expect/actual.

Example:

expect interface PoseDetector

Android implementation:

actual class AndroidPoseDetector

iOS implementation:

actual class IOSPoseDetector

---

## Camera

Camera implementations are platform-specific.

Use abstractions.

Shared layer must not import CameraX.

---

## Workout Logic

Workout counting algorithms must live in shared code.

Examples:

* SquatCounter
* PushupCounter
* PlankTracker

These algorithms should be reusable across Android and iOS.

---

## Naming Conventions

Use:

WorkoutRepository
WorkoutRepositoryImpl

SaveWorkoutUseCase

WorkoutViewModel

Avoid abbreviations.

Bad:

WkRepo
DbMgr
Vm

Good:

WorkoutRepository
WorkoutHistoryRepository
WorkoutViewModel

---

## UI Guidelines

Compose Multiplatform only.

Prefer:

* Stateless composables
* Reusable components
* Material 3

Avoid business logic inside composables.

---

## Error Handling

Use Result pattern.

Example:

sealed interface AppResult

Success
Error
Loading

Do not swallow exceptions.

---

## Testing

For every new feature create:

* Unit tests for UseCases
* Unit tests for Counters
* Repository tests when possible

Priority:

1. Workout logic
2. Repository logic
3. ViewModel logic

---

## MVP Roadmap

Phase 1

* Project setup
* Navigation
* Dashboard
* History

Phase 2

* SQLDelight integration
* Workout persistence

Phase 3

* Android CameraX

Phase 4

* MediaPipe Pose Detection

Phase 5

* Squat Counter

Phase 6

* Push-up Counter

Phase 7

* iOS Support

---

## Important Rules

1. Follow Clean Architecture.
2. Follow Feature-Based Structure.
3. Use Koin for dependency injection.
4. Use SQLDelight for persistence.
5. Use Ktor for networking.
6. Keep business logic inside shared code.
7. Keep platform code isolated.
8. Prefer maintainability over clever code.
9. Do not introduce unnecessary libraries.
10. MVP first, optimization later.
