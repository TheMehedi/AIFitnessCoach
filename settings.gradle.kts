pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        // You might need to add other repositories like jitpack if your dependencies require it
        // maven("https://jitpack.io")
        // maven("https://maven.localazy.com/repository/release/")
    }
}

rootProject.name = "AIFitnessCoach"
include(":app")
    