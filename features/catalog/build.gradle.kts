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
        }
    }
}