package com.themehedi.aifitnesscoach.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val targetMuscles: List<String>,
    val difficulty: DifficultyLevel
) {
    // Convert DifficultyLevel to String for storage
    @TypeConverters(DifficultyLevelConverters::class)
    fun getDifficultyValue(): String {
        return difficulty.name
    }
}

// Add converter for DifficultyLevel
class DifficultyLevelConverters {
    @TypeConverter
    fun fromDifficultyLevel(value: String): DifficultyLevel {
        return DifficultyLevel.valueOf(value)
    }

    @TypeConverter
    fun difficultyLevelToString(level: DifficultyLevel): String {
        return level.name
    }
}
enum class DifficultyLevel {
    BEGINNER, INTERMEDIATE, ADVANCED
}

enum class ExerciseType {
    SQUAT,
    PUSHUP,
    LUNGE,
    PLANK,
    DEADLIFT,
    BICEP_CURL,
    SHOULDER_PRESS;

    companion object {
        fun fromString(value: String): ExerciseType {
            return try {
                valueOf(value.uppercase())
            } catch (e: IllegalArgumentException) {
                SQUAT // Default value
            }
        }

        fun getDisplayName(type: ExerciseType): String {
            return when (type) {
                SQUAT -> "Squat"
                PUSHUP -> "Push-up"
                LUNGE -> "Lunge"
                PLANK -> "Plank"
                DEADLIFT -> "Deadlift"
                BICEP_CURL -> "Bicep Curl"
                SHOULDER_PRESS -> "Shoulder Press"
            }
        }
    }
}