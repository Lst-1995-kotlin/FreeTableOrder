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
}
