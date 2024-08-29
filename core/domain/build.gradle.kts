plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    api(libs.coroutines)
    implementation(libs.inject)
}
