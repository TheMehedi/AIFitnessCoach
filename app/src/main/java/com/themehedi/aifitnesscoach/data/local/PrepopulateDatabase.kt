package com.themehedi.aifitnesscoach.data.local

import com.themehedi.aifitnesscoach.data.local.dao.ExerciseDao
import com.themehedi.aifitnesscoach.domain.model.DifficultyLevel
import com.themehedi.aifitnesscoach.domain.model.Exercise
import androidx.lifecycle.viewModelScope

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

fun prepopulateExercises(exerciseDao: ExerciseDao) {
    val defaultExercises = listOf(
        Exercise(
            id = 1,
            name = "Squat",
            description = "A compound exercise that trains the muscles of the legs",
            targetMuscles = listOf("Quadriceps", "Glutes", "Hamstrings"),
            difficulty = DifficultyLevel.BEGINNER
        ),
        Exercise(
            id = 2,
            name = "Push-up",
            description = "A classic upper body exercise",
            targetMuscles = listOf("Chest", "Triceps", "Shoulders"),
            difficulty = DifficultyLevel.BEGINNER
        ),
        // Add more exercises...
    )
    defaultExercises.forEach { exerciseDao.insertExercise(it) }

}