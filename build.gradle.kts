buildscript {
    repositories {
        google()
        mavenCentral()

        if (!libs.versions.compose.snapshot.get().endsWith("SNAPSHOT")) {
            maven { url = uri("https://androidx.dev/snapshots/builds/${libs.versions.compose.snapshot.get()}/artifacts/repository/") }
        }
    }
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.hilt.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}