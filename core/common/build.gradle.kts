plugins {
    alias(libs.plugins.cinekmpKmpLibrary)
    alias(libs.plugins.cinekmpKmpCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}