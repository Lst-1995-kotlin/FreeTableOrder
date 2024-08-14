plugins {
    id("freetableorder.android.library")
    id("freetableorder.android.hilt")
}

android {
    namespace = "com.lst_1995.core.ui"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:domain"))

    api(libs.material)

    api(libs.navigation.fragment.ktx)
    api(libs.navigation.ui.ktx)
    implementation(libs.androidx.junit.ktx)
}
