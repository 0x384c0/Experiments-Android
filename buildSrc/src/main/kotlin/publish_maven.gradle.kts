plugins {
    `maven-publish`
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