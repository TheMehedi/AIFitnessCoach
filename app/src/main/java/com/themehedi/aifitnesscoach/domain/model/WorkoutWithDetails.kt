package com.themehedi.aifitnesscoach.domain.model

import com.themehedi.aifitnesscoach.data.local.relationship.ExerciseSessionWithDetails

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

data class WorkoutWithDetails(
    val workout: Workout,
    val sessions: List<ExerciseSessionWithDetails>
)