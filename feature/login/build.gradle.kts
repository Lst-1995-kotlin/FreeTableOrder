import java.io.FileInputStream
import java.util.Properties

plugins {
    id("freetableorder.android.feature")
}

val localPropertiesFile = rootProject.file("local.properties")
val localProperties = Properties()
localProperties.load(FileInputStream(localPropertiesFile))

android {
    namespace = "com.lst_1995.login"

    defaultConfig {
        buildConfigField("String", "GOOGLE_CLIENT_ID", localProperties["google.client.id"] as String)
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.ui.auth)
    implementation(libs.google.services)
}
