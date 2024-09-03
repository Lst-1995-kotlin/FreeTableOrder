plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.gms)
    id("kotlin-kapt")
    id("freetableorder.android.hilt")
}

android {
    namespace = "com.lst_1995.freetableorder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lst_1995.freetableorder"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // Module
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":feature:login"))
    implementation(project(":feature:main"))
    implementation(project(":feature:kitchen"))
    implementation(project(":feature:manage"))
    implementation(project(":feature:table"))

    // navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
}
