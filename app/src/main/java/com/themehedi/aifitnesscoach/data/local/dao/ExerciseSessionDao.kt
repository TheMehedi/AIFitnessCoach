package com.themehedi.aifitnesscoach.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themehedi.aifitnesscoach.domain.model.ExerciseSession
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Dao
interface ExerciseSessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExerciseSession(session: ExerciseSession)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllExerciseSessions(sessions: List<ExerciseSession>)

    @Query("SELECT * FROM exercise_sessions WHERE workoutId = :workoutId")
    fun getSessionsForWorkout(workoutId: Int): Flow<List<ExerciseSession>>

    @Query("SELECT * FROM exercise_sessions WHERE exerciseId = :exerciseId ORDER BY id DESC")
    fun getSessionsForExercise(exerciseId: Int): Flow<List<ExerciseSession>>

    @Query("DELETE FROM exercise_sessions WHERE workoutId = :workoutId")
    fun deleteSessionsForWorkout(workoutId: Int)

    @Query("SELECT * FROM exercise_sessions WHERE id = :sessionId")
    fun getSessionById(sessionId: Int): ExerciseSession?
}