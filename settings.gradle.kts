pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Experiments-Android"
include(":app")
include("app", "core")

include(":presentation")
project(":presentation").projectDir = File(rootDir, "layers/presentation/")
