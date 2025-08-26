package com.themehedi.aifitnesscoach.domain.model

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

data class AnalysisResult(
    val isValidForm: Boolean,
    val feedback: String,
    val confidence: Float
)