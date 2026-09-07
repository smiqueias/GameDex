import com.codingfeline.buildkonfig.compiler.FieldSpec
import java.util.Properties

plugins {
    alias(libs.plugins.cinekmpKmpLibrary)
    alias(libs.plugins.buildkonfig)
}

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) load(file.inputStream())
}
val rawgApiKey: String = localProperties.getProperty("RAWG_API_KEY") ?: ""

buildkonfig {
    packageName = "org.odin.gamedex.core.network"
    exposeObjectWithName = "BuildKonfig"
    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "RAWG_API_KEY", rawgApiKey)
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.serialization.kotlinxJson)
            implementation(libs.ktor.client.logging)
            implementation(libs.kotlinx.serialization.json)
            implementation(project(":core:common"))
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}