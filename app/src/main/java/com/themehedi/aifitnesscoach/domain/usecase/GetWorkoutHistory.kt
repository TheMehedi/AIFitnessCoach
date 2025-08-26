package com.themehedi.aifitnesscoach.domain.usecase

import com.themehedi.aifitnesscoach.domain.model.Workout
import com.themehedi.aifitnesscoach.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

class GetWorkoutHistory @Inject constructor(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(): Flow<List<Workout>> {
        return repository.getWorkouts()
    }
}