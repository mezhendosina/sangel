plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.ktorfit) apply false
    alias(libs.plugins.room) apply false

    alias(libs.plugins.ktlint) apply true
    alias(libs.plugins.googleGmsGoogleServices) apply false
    alias(libs.plugins.googleFirebaseCrashlytics) apply false
    alias(libs.plugins.googleFirebaseFirebasePerf) apply false
}

buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }
}
