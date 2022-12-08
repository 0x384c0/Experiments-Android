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

include(":common:utils")
include(":features:reddit_posts:presentation")
include(":features:reddit_posts:domain")
include(":features:reddit_posts:data")
