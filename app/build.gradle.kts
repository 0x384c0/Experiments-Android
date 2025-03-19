plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    `maven-publish`
}

android {
    namespace = "com.example.experiments_android"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.experiments_android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //Hilt App Modules
    implementation(project(":features:reddit_posts:presentation"))
    implementation(project(":features:reddit_posts:domain"))
    implementation(project(":features:reddit_posts:data"))
    implementation(project(":features:features_host:presentation"))
    implementation(project(":features:usb_demo:presentation"))
}


publishing {
    val companyName = "example"
    val artifactName = "example"
    publications {
        // Publication for the Release variant
        create<MavenPublication>("azure-artifact-release"){
            groupId = "com.example.experiments_android"
            artifactId = artifactName
            version = "1.0.0"
            artifact("${layout.buildDirectory.get().asFile}/outputs/aar/app-release.aar")
        }
    }

    repositories {
        maven {
            url = uri("https://pkgs.dev.azure.com/$companyName/_packaging/$artifactName/maven/v1")
            name = artifactName
            credentials {
                username = companyName
                password = System.getenv("SYSTEM_ACCESSTOKEN") ?: ""
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}