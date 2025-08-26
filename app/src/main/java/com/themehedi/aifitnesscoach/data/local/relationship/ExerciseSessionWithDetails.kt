package com.themehedi.aifitnesscoach.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.themehedi.aifitnesscoach.domain.model.Exercise
import com.themehedi.aifitnesscoach.domain.model.ExerciseSession

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

data class ExerciseSessionWithDetails(
    @Embedded val session: ExerciseSession,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "id"
    )
    val exercise: Exercise
)