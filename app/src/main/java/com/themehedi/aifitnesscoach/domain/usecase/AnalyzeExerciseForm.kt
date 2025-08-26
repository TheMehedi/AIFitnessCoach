package com.themehedi.aifitnesscoach.domain.usecase

import com.google.mlkit.vision.pose.Pose
import com.themehedi.aifitnesscoach.domain.model.AnalysisResult
import com.themehedi.aifitnesscoach.domain.model.ExerciseType
import com.themehedi.aifitnesscoach.ml.analysis.ExerciseAnalyzer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

class AnalyzeExerciseForm @Inject constructor(
    private val exerciseAnalyzer: ExerciseAnalyzer
) {
    suspend operator fun invoke(pose: Pose, exerciseType: ExerciseType): AnalysisResult {
        return withContext(Dispatchers.Default) {
            when (exerciseType) {
                ExerciseType.SQUAT -> exerciseAnalyzer.analyzeSquat(pose)
                ExerciseType.PUSHUP -> TODO()
                ExerciseType.LUNGE -> TODO()
                ExerciseType.PLANK -> TODO()
                else -> AnalysisResult(
                    isValidForm = false,
                    feedback = "Exercise analysis not implemented yet",
                    confidence = 0.0f
                )
            }
        }
    }
}