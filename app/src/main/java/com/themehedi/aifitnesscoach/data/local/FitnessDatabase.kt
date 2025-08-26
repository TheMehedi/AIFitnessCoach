package com.themehedi.aifitnesscoach.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.themehedi.aifitnesscoach.MyApplication
import com.themehedi.aifitnesscoach.data.local.converters.DateTimeConverters
import com.themehedi.aifitnesscoach.data.local.converters.ExerciseSessionListConverter
import com.themehedi.aifitnesscoach.data.local.converters.ListStringConverters
import com.themehedi.aifitnesscoach.data.local.dao.ExerciseDao
import com.themehedi.aifitnesscoach.data.local.dao.ExerciseSessionDao
import com.themehedi.aifitnesscoach.data.local.dao.WorkoutDao
import com.themehedi.aifitnesscoach.domain.model.DifficultyLevel
import com.themehedi.aifitnesscoach.domain.model.DifficultyLevelConverters
import com.themehedi.aifitnesscoach.domain.model.Exercise
import com.themehedi.aifitnesscoach.domain.model.ExerciseSession
import com.themehedi.aifitnesscoach.domain.model.Workout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Database(
    entities = [Workout::class, ExerciseSession::class, Exercise::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(DateTimeConverters::class, ListStringConverters::class, DifficultyLevelConverters::class, ExerciseSessionListConverter::class)
abstract class FitnessDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseSessionDao(): ExerciseSessionDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var Instance: FitnessDatabase? = null

        fun getDatabase(context: Context): FitnessDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    FitnessDatabase::class.java,
                    "fitness_database"
                )
                    .addCallback(databaseCallback)
                    .build()
                    .also { Instance = it }
            }
        }

        private val databaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Prepopulate with default exercises
                CoroutineScope(Dispatchers.IO).launch {
                    getDatabase(MyApplication.context).exerciseDao().insertAllExercises(getDefaultExercises())
                }
            }
        }

        private fun getDefaultExercises(): List<Exercise> {
            return listOf(
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
                Exercise(
                    id = 3,
                    name = "Lunge",
                    description = "A lower body exercise that improves balance",
                    targetMuscles = listOf("Quadriceps", "Glutes", "Hamstrings"),
                    difficulty = DifficultyLevel.BEGINNER
                ),
                Exercise(
                    id = 4,
                    name = "Plank",
                    description = "A core stability exercise",
                    targetMuscles = listOf("Core", "Shoulders", "Back"),
                    difficulty = DifficultyLevel.BEGINNER
                ),
                Exercise(
                    id = 5,
                    name = "Deadlift",
                    description = "A compound exercise for posterior chain development",
                    targetMuscles = listOf("Hamstrings", "Glutes", "Back"),
                    difficulty = DifficultyLevel.ADVANCED
                )
            )
        }
    }
}