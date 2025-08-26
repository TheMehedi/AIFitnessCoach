package com.themehedi.aifitnesscoach.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.themehedi.aifitnesscoach.domain.model.ExerciseType

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Composable
fun DebugOverlay(
  exerciseType: ExerciseType,
  isCameraInitialized: Boolean,
  error: String?
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    contentAlignment = Alignment.TopStart
  ) {
    Column {
      Text(
        text = "Exercise: ${exerciseType.name}",
        color = Color.White,
        fontSize = 16.sp,
        modifier = Modifier.background(Color.Black.copy(alpha = 0.7f))
      )
      Text(
        text = "Camera: ${if (isCameraInitialized) "Ready" else "Initializing"}",
        color = Color.White,
        fontSize = 14.sp,
        modifier = Modifier.background(Color.Black.copy(alpha = 0.7f))
      )
      if (error != null) {
        Text(
          text = "Error: $error",
          color = Color.Red,
          fontSize = 12.sp,
          modifier = Modifier.background(Color.Black.copy(alpha = 0.7f))
        )
      }
    }
  }
}