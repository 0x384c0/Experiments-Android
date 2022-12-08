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

include(":features:reddit_feature:presentation")
include(":features:reddit_feature:domain")
include(":features:reddit_feature:data")