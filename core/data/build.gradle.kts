plugins {
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
