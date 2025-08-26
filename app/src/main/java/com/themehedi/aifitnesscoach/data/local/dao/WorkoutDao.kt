package com.themehedi.aifitnesscoach.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.themehedi.aifitnesscoach.data.local.relationship.WorkoutWithSessions
import com.themehedi.aifitnesscoach.domain.model.Workout
import com.themehedi.aifitnesscoach.domain.model.WorkoutWithDetails
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Dao
interface WorkoutDao {
  @Query("SELECT * FROM workouts ORDER BY date DESC")
  fun getAllWorkouts(): Flow<List<Workout>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertWorkout(workout: Workout): Long

  @Query("SELECT * FROM workouts WHERE id = :workoutId")
  fun getWorkoutById(workoutId: Int): Workout?

  @Transaction
  @Query("SELECT * FROM workouts ORDER BY date DESC")
  fun getWorkoutsWithSessions(): Flow<List<WorkoutWithSessions>>

  @Query("DELETE FROM workouts WHERE id = :workoutId")
  fun deleteWorkout(workoutId: Int)
}