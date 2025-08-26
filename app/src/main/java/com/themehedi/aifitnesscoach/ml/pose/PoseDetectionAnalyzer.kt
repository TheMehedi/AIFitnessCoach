package com.themehedi.aifitnesscoach.ml.pose

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import java.util.concurrent.Executors

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

class PoseDetectionAnalyzer(
  private val poseDetector: PoseDetector,
  private val onPoseDetected: (Pose) -> Unit
) : ImageAnalysis.Analyzer {
  private val executor = Executors.newSingleThreadExecutor()

  @SuppressLint("UnsafeOptInUsageError")
  override fun analyze(imageProxy: ImageProxy) {
    val mediaImage = imageProxy.image
    if (mediaImage != null) {
      val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

      poseDetector.processImage(image)
        .addOnSuccessListener(executor) { pose ->
          onPoseDetected(pose)
        }
        .addOnFailureListener(executor) { e ->
          Log.e("PoseDetection", "Pose detection failed", e)
        }
        .addOnCompleteListener {
          imageProxy.close()
        }
    } else {
      imageProxy.close()
    }
  }
}