package com.themehedi.aifitnesscoach

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.themehedi.aifitnesscoach.presentation.FitnessAppNavigation
import com.themehedi.aifitnesscoach.presentation.theme.FitnessAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessAppTheme {
                FitnessAppNavigation()
            }
        }
    }
}