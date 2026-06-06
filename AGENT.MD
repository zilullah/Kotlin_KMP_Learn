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

## SOLID Principles

All code in this project must follow SOLID principles.

### S — Single Responsibility Principle

Each class or function must have one reason to change.

Rules:

* ViewModels only manage UI state and delegate logic to UseCases.
* UseCases contain exactly one business operation.
* Repositories are responsible only for data access, not for business decisions.
* Composables are responsible only for rendering, not for logic.

Bad:

```kotlin
class WorkoutViewModel(private val db: WorkoutDatabase) {
    fun saveWorkout(reps: Int) {
        // ViewModel directly writing to DB and calculating calories
        val calories = reps * 0.5
        db.insert(WorkoutEntity(reps, calories))
    }
}
```

Good:

```kotlin
class WorkoutViewModel(private val saveWorkoutUseCase: SaveWorkoutUseCase) {
    fun saveWorkout(reps: Int) {
        saveWorkoutUseCase(reps)
    }
}
```

---

### O — Open/Closed Principle

Classes must be open for extension, closed for modification.

Rules:

* Add new workout types (e.g., PushupCounter, PlankTracker) by creating new classes, not by modifying existing ones.
* Use interfaces or abstract classes as extension points.

Bad:

```kotlin
class WorkoutCounter {
    fun count(type: String, angle: Double): Int {
        return if (type == "squat") countSquat(angle)
        else if (type == "pushup") countPushup(angle)
        else 0
    }
}
```

Good:

```kotlin
interface WorkoutCounter {
    fun count(landmarks: PoseLandmarks): Int
}

class SquatCounter : WorkoutCounter { ... }
class PushupCounter : WorkoutCounter { ... }
```

---

### L — Liskov Substitution Principle

Subtypes must be substitutable for their base types without breaking behavior.

Rules:

* Any `WorkoutCounter` implementation must behave correctly when used through the `WorkoutCounter` interface.
* `expect/actual` implementations (e.g., `AndroidPoseDetector`, `IOSPoseDetector`) must fulfill the full contract of `PoseDetector` without unexpected behavior or silent failures.

Bad:

```kotlin
actual class IOSPoseDetector : PoseDetector {
    override fun detect(frame: CameraFrame): PoseResult {
        throw NotImplementedError() // Breaks LSP
    }
}
```

Good:

```kotlin
actual class IOSPoseDetector : PoseDetector {
    override fun detect(frame: CameraFrame): PoseResult {
        // Proper iOS implementation
    }
}
```

---

### I — Interface Segregation Principle

Do not force classes to depend on interfaces they do not use.

Rules:

* Split large interfaces into smaller, focused ones.
* A read-only history screen should not depend on write operations.

Bad:

```kotlin
interface WorkoutRepository {
    suspend fun save(workout: Workout)
    suspend fun delete(id: String)
    suspend fun getAll(): List<Workout>
    suspend fun getById(id: String): Workout?
    suspend fun exportToCsv(): File
}
```

Good:

```kotlin
interface WorkoutReader {
    suspend fun getAll(): List<Workout>
    suspend fun getById(id: String): Workout?
}

interface WorkoutWriter {
    suspend fun save(workout: Workout)
    suspend fun delete(id: String)
}

interface WorkoutExporter {
    suspend fun exportToCsv(): File
}
```

---

### D — Dependency Inversion Principle

High-level modules must not depend on low-level modules. Both must depend on abstractions.

Rules:

* ViewModels depend on UseCase interfaces, not concrete implementations.
* UseCases depend on Repository interfaces, not `RepositoryImpl`.
* Platform-specific code (CameraX, MediaPipe) is always accessed through shared abstractions.
* Koin is responsible for wiring concrete implementations to abstractions.

Bad:

```kotlin
class WorkoutViewModel {
    private val repository = WorkoutRepositoryImpl(DatabaseDriverFactory())
}
```

Good:

```kotlin
class WorkoutViewModel(private val repository: WorkoutRepository) // injected via Koin
```

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
2. Follow SOLID Principles.
3. Follow Feature-Based Structure.
4. Use Koin for dependency injection.
5. Use SQLDelight for persistence.
6. Use Ktor for networking.
7. Keep business logic inside shared code.
8. Keep platform code isolated.
9. Prefer maintainability over clever code.
10. Do not introduce unnecessary libraries.
11. MVP first, optimization later.