package com.themehedi.aifitnesscoach.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themehedi.aifitnesscoach.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Dao
interface ExerciseDao {
  @Query("SELECT * FROM exercises")
  fun getAllExercises(): Flow<List<Exercise>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertExercise(exercise: Exercise)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAllExercises(exercises: List<Exercise>)

  @Query("SELECT * FROM exercises WHERE id = :exerciseId")
  fun getExerciseById(exerciseId: Int): Exercise?

  @Query("SELECT * FROM exercises WHERE name LIKE :query")
  fun searchExercises(query: String): Flow<List<Exercise>>

  @Query("DELETE FROM exercises WHERE id = :exerciseId")
  fun deleteExercise(exerciseId: Int)
}