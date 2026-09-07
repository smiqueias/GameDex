package org.odin.gamedex.buildlogic

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("com.android.kotlin.multiplatform.library")
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            val frameworkBaseName = path.removePrefix(":").replace(":", "-")
            val androidBaseName = path.removePrefix(":").replace(":", ".")

            extensions.configure<KotlinMultiplatformExtension>() {
                listOf(
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = frameworkBaseName
                        isStatic = true
                    }
                }

                targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java).configureEach {
                    namespace = "org.odin.gamedex.$androidBaseName"
                    compileSdk = libs.requiredVersionAsInt("android-compileSdk")
                    minSdk = libs.requiredVersionAsInt("android-minSdk")

                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_11)
                    }
                    androidResources {
                        enable = true
                    }
                }
            }

        }
    }
}