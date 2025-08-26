package com.themehedi.aifitnesscoach.domain.model

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

data class WorkoutState(
    val workouts: List<Workout> = emptyList(),
    val isLoading: Boolean = true,
    val currentFeedback: String = "",
    val isCorrectForm: Boolean = false
)