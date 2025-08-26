package com.themehedi.aifitnesscoach.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.themehedi.aifitnesscoach.domain.model.ExerciseType
import com.themehedi.aifitnesscoach.presentation.viewmodel.WorkoutViewModel

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

// presentation/screen/HomeScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onStartWorkout: (ExerciseType) -> Unit, // Accept ExerciseType parameter
    onViewHistory: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AI Fitness Coach") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Example: Button for each exercise type
            ExerciseType.entries.forEach { exerciseType ->
                Button(
                    onClick = { onStartWorkout(exerciseType) },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(4.dp)
                ) {
                    Text(ExerciseType.getDisplayName(exerciseType))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onViewHistory,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("View Workout History")
            }
        }
    }
}