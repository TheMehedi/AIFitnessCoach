package com.themehedi.aifitnesscoach.domain.repository

import com.themehedi.aifitnesscoach.data.local.dao.ExerciseDao
import com.themehedi.aifitnesscoach.data.local.dao.ExerciseSessionDao
import com.themehedi.aifitnesscoach.data.local.dao.WorkoutDao
import com.themehedi.aifitnesscoach.data.local.relationship.WorkoutWithSessions
import com.themehedi.aifitnesscoach.domain.model.Exercise
import com.themehedi.aifitnesscoach.domain.model.ExerciseSession
import com.themehedi.aifitnesscoach.domain.model.Workout
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

class WorkoutRepositoryImpl @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val exerciseSessionDao: ExerciseSessionDao,
    private val exerciseDao: ExerciseDao
) : WorkoutRepository {

    override fun getWorkouts(): Flow<List<Workout>> {
        return workoutDao.getAllWorkouts()
    }

    override fun getWorkoutsWithSessions(): Flow<List<WorkoutWithSessions>> {
        return workoutDao.getWorkoutsWithSessions()
    }

    override fun saveWorkout(workout: Workout, sessions: List<ExerciseSession>) {
        // Use transaction for atomic operation
        // First insert the workout
        val workoutId = workoutDao.insertWorkout(workout).toInt()

        // Then insert all exercise sessions with the correct workout ID
        val sessionsWithWorkoutId = sessions.map { it.copy(workoutId = workoutId) }
        exerciseSessionDao.insertAllExerciseSessions(sessionsWithWorkoutId)

    }

    override fun getExerciseHistory(exerciseId: Int): Flow<List<ExerciseSession>> {
        return exerciseSessionDao.getSessionsForExercise(exerciseId)
    }

    override fun getExerciseById(exerciseId: Int): Exercise? {
        return exerciseDao.getExerciseById(exerciseId)
    }

    override fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises()
    }

    override fun deleteWorkout(workoutId: Int) {
        exerciseSessionDao.deleteSessionsForWorkout(workoutId)
        workoutDao.deleteWorkout(workoutId)

    }
}
