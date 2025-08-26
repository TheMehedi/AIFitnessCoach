package com.themehedi.aifitnesscoach;

import android.app.Application;
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
  * Created by Mohammad Mehedi Hasan on 26,August,2025
  * Email: themehedi.pro@gmail.com
  */

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}