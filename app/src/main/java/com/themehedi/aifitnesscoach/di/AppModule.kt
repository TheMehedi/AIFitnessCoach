package com.themehedi.aifitnesscoach.di

import android.content.Context
import androidx.room.Room
import com.themehedi.aifitnesscoach.data.local.FitnessDatabase
import com.themehedi.aifitnesscoach.data.local.converters.DateTimeConverters
import com.themehedi.aifitnesscoach.data.local.converters.ListStringConverters
import com.themehedi.aifitnesscoach.data.local.dao.ExerciseDao
import com.themehedi.aifitnesscoach.data.local.dao.ExerciseSessionDao
import com.themehedi.aifitnesscoach.data.local.dao.WorkoutDao
import com.themehedi.aifitnesscoach.domain.model.DifficultyLevelConverters
import com.themehedi.aifitnesscoach.domain.repository.WorkoutRepository
import com.themehedi.aifitnesscoach.domain.repository.WorkoutRepositoryImpl
import com.themehedi.aifitnesscoach.ml.analysis.ExerciseAnalyzer
import com.themehedi.aifitnesscoach.ml.pose.PoseDetector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePoseDetector(): PoseDetector {
        return PoseDetector()
    }

    @Provides
    @Singleton
    fun provideExerciseAnalyzer(): ExerciseAnalyzer {
        return ExerciseAnalyzer()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FitnessDatabase {
        return FitnessDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideWorkoutDao(db: FitnessDatabase): WorkoutDao = db.workoutDao()

    @Provides
    @Singleton
    fun provideExerciseSessionDao(db: FitnessDatabase): ExerciseSessionDao = db.exerciseSessionDao()

    @Provides
    @Singleton
    fun provideExerciseDao(db: FitnessDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    @Singleton
    fun provideWorkoutRepository(
        workoutDao: WorkoutDao,
        exerciseSessionDao: ExerciseSessionDao,
        exerciseDao: ExerciseDao
    ): WorkoutRepository {
        return WorkoutRepositoryImpl(workoutDao, exerciseSessionDao, exerciseDao)
    }
}