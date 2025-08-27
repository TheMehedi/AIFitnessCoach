package com.themehedi.aifitnesscoach.presentation.screen

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.themehedi.aifitnesscoach.domain.model.ExerciseType
import com.themehedi.aifitnesscoach.ml.pose.PoseDetectionAnalyzer
import com.themehedi.aifitnesscoach.ml.pose.PoseDetector
import com.themehedi.aifitnesscoach.presentation.component.CameraPreview
import com.themehedi.aifitnesscoach.presentation.component.FeedbackOverlay
import com.themehedi.aifitnesscoach.presentation.viewmodel.WorkoutViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.themehedi.aifitnesscoach.domain.model.AnalysisResult
import com.themehedi.aifitnesscoach.ml.analysis.ExerciseAnalyzer
import com.themehedi.aifitnesscoach.presentation.utils.CameraPermission
import com.themehedi.aifitnesscoach.presentation.utils.CameraTestUtility

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Composable
fun CameraScreen(
    exerciseType: ExerciseType,
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    CameraPermission.RequestCameraPermission {
        CameraContent(
            exerciseType = exerciseType,
            viewModel = viewModel,
            context = context,
            lifecycleOwner = lifecycleOwner
        )
    }
}

@Composable
fun CameraContent(
    exerciseType: ExerciseType,
    viewModel: WorkoutViewModel,
    context: Context,
    lifecycleOwner: LifecycleOwner
) {
    var cameraInitialized by remember { mutableStateOf(false) }
    var cameraError by remember { mutableStateOf<String?>(null) }

    val cameraController = remember {
        try {
            // Test camera availability first
            val cameraTest = CameraTestUtility.testCameraAvailability(context)
            if (!cameraTest.isAvailable) {
                cameraError = "Camera not available: ${cameraTest.error ?: "No camera found"}"
                null
            } else {
                try {
                    LifecycleCameraController(context).apply {
                        setEnabledUseCases(
                            CameraController.IMAGE_ANALYSIS
                        )
                    }
                } catch (e: Exception) {
                    cameraError = "Failed to create camera controller: ${e.message}"
                    null
                }
            }
        } catch (e: Exception) {
            cameraError = "Camera test failed: ${e.message}"
            null
        }
    }

    // Initialize camera with lifecycle
    LaunchedEffect(cameraController) {
        if (cameraController != null) {
            try {
                cameraController.bindToLifecycle(lifecycleOwner)
                cameraInitialized = true
            } catch (e: Exception) {
                cameraError = "Failed to bind camera: ${e.message}"
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            cameraError != null -> {
                // Show error message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = cameraError ?: "Unknown camera error",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
            cameraController != null && cameraInitialized -> {
                // Show camera preview
                CameraPreview(
                    controller = cameraController
                )

                // Setup pose detection analyzer
                val poseDetector = remember { PoseDetector() }
                val exerciseAnalyzer = remember { ExerciseAnalyzer() }

                DisposableEffect(Unit) {
                    val analyzer = PoseDetectionAnalyzer(poseDetector) { pose ->
                        val result = when (exerciseType) {
                            ExerciseType.SQUAT -> exerciseAnalyzer.analyzeSquat(pose)
                            ExerciseType.PUSHUP -> exerciseAnalyzer.analyzePushup(pose)
                            ExerciseType.LUNGE -> exerciseAnalyzer.analyzeLunge(pose)
                            ExerciseType.PLANK -> exerciseAnalyzer.analyzePlank(pose)
                            else -> AnalysisResult(
                                isValidForm = false,
                                feedback = "Exercise not supported",
                                confidence = 0.0f
                            )
                        }
                        viewModel.updateAnalysisResult(result)
                    }

                    try {
                        cameraController.setImageAnalysisAnalyzer(
                            ContextCompat.getMainExecutor(context),
                            analyzer
                        )
                    } catch (e: Exception) {
                        // Handle analyzer setup error
                        viewModel.updateAnalysisResult(
                            AnalysisResult(
                                isValidForm = false,
                                feedback = "Camera analysis error: ${e.message}",
                                confidence = 0.0f
                            )
                        )
                    }

                    onDispose {
                        try {
                            cameraController.clearImageAnalysisAnalyzer()
                            analyzer.shutdown()
                            poseDetector.close()
                        } catch (e: Exception) {
                            // Handle cleanup error
                        }
                    }
                }

                // Feedback overlay
                FeedbackOverlay(
                    feedback = viewModel.workoutState.value.currentFeedback,
                    isCorrectForm = viewModel.workoutState.value.isCorrectForm
                )
            }
            else -> {
                // Show loading
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Initializing camera...", color = Color.White)
                }
            }
        }
    }
}