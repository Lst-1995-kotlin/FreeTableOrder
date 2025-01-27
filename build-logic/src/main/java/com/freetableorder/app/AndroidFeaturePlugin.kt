package com.freetableorder.app

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("freetableorder.android.library")
                apply("freetableorder.android.hilt")
                apply("androidx.navigation.safeargs.kotlin")
            }

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    dataBinding = true
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:data"))
                add("implementation", libs.findLibrary("material").get())
            }
        }
    }
}
