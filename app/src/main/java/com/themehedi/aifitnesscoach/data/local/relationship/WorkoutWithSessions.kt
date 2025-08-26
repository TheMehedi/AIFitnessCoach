package com.themehedi.aifitnesscoach.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.themehedi.aifitnesscoach.domain.model.ExerciseSession
import com.themehedi.aifitnesscoach.domain.model.Workout

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

data class WorkoutWithSessions(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "id",
        entityColumn = "workoutId"
    )
    val sessions: List<ExerciseSession>
)