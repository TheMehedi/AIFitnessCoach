package com.themehedi.aifitnesscoach.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.themehedi.aifitnesscoach.domain.model.WorkoutState
import com.themehedi.aifitnesscoach.domain.repository.WorkoutRepository
import com.themehedi.aifitnesscoach.domain.usecase.AnalyzeExerciseForm
import com.themehedi.aifitnesscoach.domain.usecase.GetWorkoutHistory
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.pose.Pose
import com.themehedi.aifitnesscoach.domain.model.AnalysisResult
import com.themehedi.aifitnesscoach.domain.model.ExerciseSession
import com.themehedi.aifitnesscoach.domain.model.ExerciseType
import com.themehedi.aifitnesscoach.domain.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val getWorkoutHistory: GetWorkoutHistory,
    private val analyzeExerciseForm: AnalyzeExerciseForm,
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _workouts = mutableStateOf<List<Workout>>(emptyList())
    val workouts: State<List<Workout>> = _workouts

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _currentFeedback = mutableStateOf("")
    val currentFeedback: State<String> = _currentFeedback

    private val _isCorrectForm = mutableStateOf(false)
    val isCorrectForm: State<Boolean> = _isCorrectForm

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        loadWorkouts()
    }

    private val _workoutState = mutableStateOf(WorkoutState())
    val workoutState: State<WorkoutState> = _workoutState

    // Add function to load workouts with details
    fun loadWorkoutsWithDetails() {
        viewModelScope.launch {
            // Use getWorkouts() instead of getWorkoutsWithDetails()
            repository.getWorkouts().collect { workouts ->
                _workoutState.value = _workoutState.value.copy(
                    workouts = workouts,
                    isLoading = false
                )
            }
        }
    }

    private fun loadWorkouts() {
        viewModelScope.launch {
            getWorkoutHistory().collect { workoutsList ->
                _workouts.value = workoutsList
                _isLoading.value = false
            }
        }
    }

    fun analyzePose(pose: Pose, exerciseType: ExerciseType) {
        viewModelScope.launch {
            val result = analyzeExerciseForm(pose, exerciseType)
            _workoutState.value = _workoutState.value.copy(
                currentFeedback = result.feedback,
                isCorrectForm = result.isValidForm
            )
        }
    }

    fun saveWorkout(workout: Workout, sessions: List<ExerciseSession>) {
        viewModelScope.launch {
            try {
                repository.saveWorkout(workout, sessions)
                loadWorkouts() // Refresh the list
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to save workout: ${e.message}"
            }
        }
    }
    fun updateAnalysisResult(result: AnalysisResult) {
        _workoutState.value = _workoutState.value.copy(
            currentFeedback = result.feedback,
            isCorrectForm = result.isValidForm
        )
    }

}