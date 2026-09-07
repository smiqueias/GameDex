plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.kotlin)
    implementation(libs.kotlin.serialization.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id = "cinekmp.kmp.library"
            implementationClass = "org.odin.gamedex.buildlogic.KmpLibraryConventionPlugin"
        }

        register("kmpCompose") {
            id = "cinekmp.kmp.compose"
            implementationClass = "org.odin.gamedex.buildlogic.KmpComposeConventionPlugin"
        }
    }
}