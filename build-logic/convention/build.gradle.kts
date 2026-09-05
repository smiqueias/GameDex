import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.kotlin)
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id = "cinekmp.kmp.library"
            implementationClass = "org.odin.cinekmp.buildlogic.KmpLibraryConventionPlugin"
        }
    }
}