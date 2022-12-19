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
//App
include(":app")
//Misc
include(":common:utils")
//Host
include(":features:features_host:presentation")
//Reddit
include(":features:reddit_posts:presentation")
include(":features:reddit_posts:domain")
include(":features:reddit_posts:data")
