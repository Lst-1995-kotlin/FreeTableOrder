plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    id("freetableorder.android.library")
    id("freetableorder.android.hilt")
}

android {
    namespace = "com.lst_1995.core.data"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.datastore.preferences)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.google.services)
    implementation(libs.androidx.appcompat)
}
