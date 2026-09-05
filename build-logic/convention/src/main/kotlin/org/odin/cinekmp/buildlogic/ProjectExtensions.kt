package org.odin.cinekmp.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.requiredVersion(alias: String): String =
    findVersion(alias).get().requiredVersion

fun VersionCatalog.requiredVersionAsInt(alias: String): Int =
    requiredVersion(alias).toInt()