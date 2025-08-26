package com.themehedi.aifitnesscoach.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.themehedi.aifitnesscoach.domain.model.Workout
import com.themehedi.aifitnesscoach.presentation.viewmodel.WorkoutViewModel


/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutHistoryScreen(
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    val workoutState = viewModel.workoutState.value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Workout History") })
        }
    ) { padding ->
        if (workoutState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(workoutState.workouts) { workout ->
                    WorkoutHistoryItem(workout = workout)
                }
            }
        }
    }
}

@Composable
fun WorkoutHistoryItem(workout: Workout) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Workout on ${workout.date}",
                fontWeight = FontWeight.Bold
            )
            Text(text = "Duration: ${workout.duration} minutes")
            Text(text = "Calories: ${workout.caloriesBurned}")
            Text(text = "Exercises: ${workout.exercises.size}")
        }
    }
}