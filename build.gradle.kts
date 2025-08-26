plugins {
    // Use the version from libs.versions.toml for the application plugin
    alias(libs.plugins.android.application) apply false
    // For the library plugin, if you use it, define it similarly or directly if not in TOML yet
    id("com.android.library") version "8.12.1" apply false // Or better, add to TOML and use alias
    // Use the version from libs.versions.toml for the Kotlin Android plugin
    alias(libs.plugins.kotlin.android) apply false // Ensure kotlin version in TOML matches your needs
    // Hilt plugin
    id("com.google.dagger.hilt.android") version "2.57.1" apply false // Or add to TOML
    // Kotlin Kapt plugin
    id("org.jetbrains.kotlin.kapt") version "2.0.21" apply false // Match your kotlin.android version from TOML (libs.versions.kotlin)
}

allprojects {
    repositories {
        // It's generally better to define repositories in settings.gradle.kts
        // but if you keep them here, ensure they are necessary.
        // google() and mavenCentral() are usually sufficient and should be in settings.gradle.kts
        maven("https://maven.localazy.com/repository/release/")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jitpack.io")
        // jcenter() is deprecated and should be removed if possible
        // maven("https://jcenter.bintray.com")
    }
}

tasks.register("clean") {
    delete(rootProject.buildDir)
}