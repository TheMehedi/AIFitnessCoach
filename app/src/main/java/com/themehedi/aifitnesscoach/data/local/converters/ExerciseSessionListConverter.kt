package com.themehedi.aifitnesscoach.data.local.converters

import androidx.room.TypeConverter
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.themehedi.aifitnesscoach.domain.model.ExerciseSession

/**
 * Created by Mohammad Mehedi Hasan on 26,August,2025
 * Email: themehedi.pro@gmail.com
 */

class ExerciseSessionListConverter {

  @TypeConverter
  fun fromExerciseSessionList(value: List<ExerciseSession>?): String? {
    if (value == null) {
      return null
    }
    val gson = Gson()
    val type = object : TypeToken<List<ExerciseSession>>() {}.type
    return gson.toJson(value, type)
  }

  @TypeConverter
  fun toExerciseSessionList(value: String?): List<ExerciseSession>? {
    if (value == null) {
      return null
    }
    val gson = Gson()
    val type = object : TypeToken<List<ExerciseSession>>() {}.type
    return gson.fromJson(value, type)
  }
}