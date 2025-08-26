plugins {
    alias(libs.plugins.android.application) // Version is inherited
    alias(libs.plugins.kotlin.android)     // Version is inherited
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")   // Version is inherited (make sure kotlinCompose uses the same kotlin version)
    id("kotlin-kapt")                      // Version is inherited
    // If you defined hilt in your project-level plugins with 'apply false', you can apply it here:
    // id("com.google.dagger.hilt.android") // Version is inherited
}

android {
    namespace = "com.themehedi.aifitnesscoach"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.themehedi.aifitnesscoach"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    // If you applied dagger.hilt.android in the project-level plugins block,
    // you might need to add the hilt extension here if it's not automatically configured.
    // This is usually not needed if applied at the top.
    // dagger {
    //    enableAggregatingTask = true
    // }
}

dependencies {

// ViewModel and LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")
    implementation ("androidx.navigation:navigation-compose:2.7.3")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")

// CameraX for ML Kit integration
    implementation ("androidx.camera:camera-camera2:1.4.2")
    implementation ("androidx.camera:camera-lifecycle:1.4.2")
    implementation ("androidx.camera:camera-view:1.4.2")

// ML Kit Pose Detection
    implementation ("com.google.mlkit:pose-detection:18.0.0-beta5")
    implementation ("com.google.mlkit:pose-detection-accurate:18.0.0-beta5")

// Room Database
    implementation ("androidx.room:room-runtime:2.7.2")
    implementation ("androidx.room:room-ktx:2.7.2")
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.crashlytics.buildtools)
    kapt ("androidx.room:room-compiler:2.7.2")

// Hilt for DI
    implementation ("com.google.dagger:hilt-android:2.57.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt ("com.google.dagger:hilt-compiler:2.57.1")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}