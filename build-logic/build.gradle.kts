plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "freetableorder.android.library"
            implementationClass = "com.freetableorder.app.AndroidLibraryPlugin"
        }
        register("androidFeature") {
            id = "freetableorder.android.feature"
            implementationClass = "com.freetableorder.app.AndroidFeaturePlugin"
        }
        register("androidHilt") {
            id = "freetableorder.android.hilt"
            implementationClass = "com.freetableorder.app.AndroidHiltPlugin"
        }
    }
}
