package com.themehedi.aifitnesscoach.presentation.utils

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

object CameraPermission {
  @Composable
  fun RequestCameraPermission(
    content: @Composable () -> Unit
  ) {
    val context = LocalContext.current
    var hasCameraPermission by remember {
      mutableStateOf(
        ContextCompat.checkSelfPermission(
          context,
          Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
      )
    }

    val launcher = rememberLauncherForActivityResult(
      ActivityResultContracts.RequestPermission()
    ) { isGranted ->
      hasCameraPermission = isGranted
      if (!isGranted) {
        Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
      }
    }

    LaunchedEffect(Unit) {
      if (!hasCameraPermission) {
        launcher.launch(Manifest.permission.CAMERA)
      }
    }

    if (hasCameraPermission) {
      content()
    } else {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.Black),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            "Camera Permission Required",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(16.dp))
          Button(
            onClick = { launcher.launch(Manifest.permission.CAMERA) }
          ) {
            Text("Grant Permission")
          }
        }
      }
    }
  }
}