package com.themehedi.aifitnesscoach.ml.pose

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions

class PoseDetector {
    private val options = AccuratePoseDetectorOptions.Builder()
        .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
        .build()
    
    private val detector = PoseDetection.getClient(options)
    
    fun processImage(image: InputImage): Task<Pose> {
        return try {
            detector.process(image)
        } catch (e: Exception) {
            Log.e("PoseDetector", "Error processing image", e)
            // Return a failed task
            com.google.android.gms.tasks.Tasks.forException(e)
        }
    }
    
    fun close() {
        try {
            detector.close()
        } catch (e: Exception) {
            Log.e("PoseDetector", "Error closing detector", e)
        }
    }
}