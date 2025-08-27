package com.themehedi.aifitnesscoach

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.themehedi.aifitnesscoach.presentation.FitnessAppNavigation
import com.themehedi.aifitnesscoach.presentation.theme.FitnessAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            Log.d(TAG, "Initializing MainActivity")
            setContent {
                FitnessAppTheme {
                    FitnessAppNavigation()
                }
            }
            Log.d(TAG, "MainActivity initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing MainActivity", e)
            // Show error to user
            setContent {
                FitnessAppTheme {
                    // Simple error screen
                    androidx.compose.material3.Text("Error initializing app: ${e.message}")
                }
            }
        }
    }
}