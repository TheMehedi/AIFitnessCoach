package com.themehedi.aifitnesscoach.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.themehedi.aifitnesscoach.domain.model.ExerciseType
import com.themehedi.aifitnesscoach.presentation.screen.CameraScreen
import com.themehedi.aifitnesscoach.presentation.screen.HomeScreen
import com.themehedi.aifitnesscoach.presentation.screen.WorkoutHistoryScreen

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Composable
fun FitnessAppNavigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = "home") {
    composable("home") {
      HomeScreen(
        onStartWorkout = { exerciseType ->
          // Pass the enum name (uppercase) as string
          navController.navigate("camera/${exerciseType.name}")
        },
        onViewHistory = {
          navController.navigate("history")
        }
      )
    }

    composable("camera/{exerciseType}") { backStackEntry ->
      val exerciseTypeStr = backStackEntry.arguments?.getString("exerciseType") ?: "SQUAT"
      val exerciseType = ExerciseType.fromString(exerciseTypeStr)
      CameraScreen(exerciseType = exerciseType)
    }

    composable("history") {
      WorkoutHistoryScreen()
    }
  }
}