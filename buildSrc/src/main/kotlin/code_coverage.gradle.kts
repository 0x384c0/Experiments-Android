plugins {
    id("jacoco")
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.register<JacocoReport>("testWithCoverageReport") {
    dependsOn("test")

    reports {
        html.required.set(true)
        xml.required.set(false)
        csv.required.set(false)
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*")
    val mainJavaSrc = "${project.projectDir}/src/main/java"
    val mainKotlinSrc = "${project.projectDir}/src/main/kotlin"
    val packageFilter = project.findProperty("codeCoveragePackageFilter") as String? ?: "" // in app/build.gradle.kts add extra["codeCoveragePackageFilter"] = "com/exampl/app"

    classDirectories.setFrom(fileTree("${project.buildDir}/intermediates/javac/debug") {
        include("$packageFilter/**/*.class")
        exclude(fileFilter)
    }, fileTree("${project.buildDir}/tmp/kotlin-classes/debug") {
        include("$packageFilter/**/*.class")
        exclude(fileFilter)
    })

    sourceDirectories.setFrom(files(mainJavaSrc, mainKotlinSrc))
    executionData.setFrom(fileTree(project.buildDir) {
        include("**/*.exec")
    })
}