plugins {
    alias(libs.plugins.cinekmpKmpLibrary)
    alias(libs.plugins.cinekmpKmpCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:common"))
            implementation(project(":core:network"))
            implementation(project(":core:navigation"))
            implementation(project(":core:designsystem"))
            implementation(libs.ktor.client.core)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)
            implementation(libs.jetbrains.navigation.compose)
            implementation(libs.compose.material.icons.core)
        }
    }
}