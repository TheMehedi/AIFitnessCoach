package com.themehedi.aifitnesscoach.presentation.utils

import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraUnavailableException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Utility class to test camera availability and help debug camera issues
 */
object CameraTestUtility {
    private const val TAG = "CameraTestUtility"

    /**
     * Test if camera is available on the device
     */
    fun testCameraAvailability(context: Context): CameraTestResult {
        return try {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            val cameraProvider = cameraProviderFuture.get()
            
            // Test back camera
            val backCameraAvailable = cameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA)
            // Test front camera
            val frontCameraAvailable = cameraProvider.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA)
            
            Log.d(TAG, "Back camera available: $backCameraAvailable")
            Log.d(TAG, "Front camera available: $frontCameraAvailable")
            
            CameraTestResult(
                isAvailable = backCameraAvailable || frontCameraAvailable,
                backCameraAvailable = backCameraAvailable,
                frontCameraAvailable = frontCameraAvailable,
                error = null
            )
        } catch (e: Exception) {
            Log.e(TAG, "Camera test failed", e)
            CameraTestResult(
                isAvailable = false,
                backCameraAvailable = false,
                frontCameraAvailable = false,
                error = e.message ?: "Unknown error"
            )
        }
    }

    /**
     * Test camera permissions
     */
    fun testCameraPermissions(context: Context): Boolean {
        return try {
            val permission = ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            )
            val hasPermission = permission == android.content.pm.PackageManager.PERMISSION_GRANTED
            Log.d(TAG, "Camera permission granted: $hasPermission")
            hasPermission
        } catch (e: Exception) {
            Log.e(TAG, "Error checking camera permission", e)
            false
        }
    }

    /**
     * Get camera provider for testing
     */
    fun getCameraProvider(context: Context): ProcessCameraProvider? {
        return try {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.get()
        } catch (e: Exception) {
            Log.e(TAG, "Error getting camera provider", e)
            null
        }
    }
}

data class CameraTestResult(
    val isAvailable: Boolean,
    val backCameraAvailable: Boolean,
    val frontCameraAvailable: Boolean,
    val error: String?
)
