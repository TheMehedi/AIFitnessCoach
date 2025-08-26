package com.themehedi.aifitnesscoach.domain.repository

import com.themehedi.aifitnesscoach.data.local.relationship.WorkoutWithSessions
import com.themehedi.aifitnesscoach.domain.model.Exercise
import com.themehedi.aifitnesscoach.domain.model.ExerciseSession
import com.themehedi.aifitnesscoach.domain.model.Workout
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

interface WorkoutRepository {
    fun getWorkouts(): Flow<List<Workout>>
    fun getWorkoutsWithSessions(): Flow<List<WorkoutWithSessions>>
    fun saveWorkout(workout: Workout, sessions: List<ExerciseSession>)
    fun getExerciseHistory(exerciseId: Int): Flow<List<ExerciseSession>>
    fun getExerciseById(exerciseId: Int): Exercise?
    fun getAllExercises(): Flow<List<Exercise>>
    fun deleteWorkout(workoutId: Int)
}