# DESIGN.md

## Design Philosophy

MotionTracker follows a clean, modern, fitness-focused design.

Principles:

* Simplicity first
* Large touch targets
* Consistent spacing
* Material 3
* Accessibility-friendly
* Mobile-first
* Cross-platform consistency

The UI should feel similar to modern fitness applications such as Google Fit, Nike Training Club, and Strong.

---

# UI Framework

Use:

* Compose Multiplatform
* Material 3

Avoid:

* XML layouts
* Platform-specific UI unless absolutely required

All screens must use Compose.

---

# Theme

Use Material 3 theming.

Theme Structure:

ui/
├── theme/
│   ├── Color.kt
│   ├── Typography.kt
│   ├── Shape.kt
│   └── Theme.kt

---

# Color System

Primary

* #2563EB

Primary Variant

* #1D4ED8

Secondary

* #10B981

Background

* #F8FAFC

Surface

* #FFFFFF

Error

* #DC2626

Success

* #16A34A

Warning

* #F59E0B

---

# Dark Mode

Dark Background

* #0F172A

Dark Surface

* #1E293B

Dark Primary

* #60A5FA

Dark Text

* #F8FAFC

Support automatic dark mode.

---

# Typography

Use Material Typography.

Sizes:

DisplayLarge = 32sp

HeadlineLarge = 24sp

HeadlineMedium = 20sp

TitleLarge = 18sp

BodyLarge = 16sp

BodyMedium = 14sp

LabelMedium = 12sp

Avoid custom font families initially.

Use system fonts.

---

# Spacing System

Use an 8dp grid.

Spacing:

xs = 4dp

sm = 8dp

md = 16dp

lg = 24dp

xl = 32dp

xxl = 48dp

Never hardcode random spacing values.

Create:

Spacing.kt

Example:

Spacing.Small
Spacing.Medium
Spacing.Large

---

# Corner Radius

Small = 8dp

Medium = 12dp

Large = 16dp

ExtraLarge = 24dp

Use rounded corners consistently.

---

# Elevation

Card Elevation:

2dp

Dialog Elevation:

8dp

Avoid excessive shadows.

---

# Navigation

Bottom Navigation

Items:

* Dashboard
* Workout
* History
* Settings

Maximum 4 tabs.

---

# Dashboard Screen

Purpose:

Overview of workout activity.

Components:

* Welcome Header
* Workout Summary Card
* Weekly Statistics Card
* Quick Start Workout Button

Layout:

Scrollable Column

Spacing:

16dp between sections

---

# Workout Screen

Purpose:

Real-time workout tracking.

Components:

* Camera Preview
* Repetition Counter
* Workout Status
* Start Button
* Stop Button

The counter must always remain visible.

---

# History Screen

Purpose:

Display workout sessions.

Components:

* Session List
* Empty State

Each item should display:

* Workout Type
* Repetitions
* Duration
* Date

Use LazyColumn.

---

# Settings Screen

Purpose:

Application preferences.

Components:

* Theme Selection
* About App
* Version Information

Keep settings simple.

---

# Buttons

Use Material Buttons.

Primary Action:

Filled Button

Secondary Action:

Outlined Button

Danger Action:

Text Button

Do not create custom button styles unless necessary.

---

# Cards

Use Material Card.

Card Rules:

* Rounded corners
* Padding 16dp
* Consistent elevation

All dashboard statistics should be displayed inside cards.

---

# Loading State

Use:

* CircularProgressIndicator

or

* Skeleton Placeholder

Never leave blank screens during loading.

---

# Empty State

Every screen must have an empty state.

Example:

No workouts yet.

Start your first workout session.

---

# Error State

Every screen must support:

* Error title
* Error description
* Retry button

---

# Accessibility

Requirements:

* Clickable area >= 48dp
* Text readable at large font sizes
* Color contrast must meet accessibility standards

---

# Responsive Design

Support:

* Phones
* Tablets

Avoid hardcoded screen widths.

Use:

BoxWithConstraints

or adaptive layouts when necessary.

---

# Component Reuse

Create reusable components.

ui/components/

Examples:

* PrimaryButton
* StatCard
* LoadingView
* ErrorView
* EmptyStateView

Avoid duplicating UI code.

---

# Preview Support

Every composable screen should have:

@Preview

when possible.

---

# Design Rules

1. Use Material 3.
2. Follow 8dp spacing system.
3. Use reusable components.
4. Support dark mode.
5. Keep screens minimal.
6. Avoid unnecessary animations.
7. Prioritize readability over visual complexity.
8. Maintain consistency across Android and iOS.
