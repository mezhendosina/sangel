plugins {
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    alias(libs.plugins.googleGmsGoogleServices)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.googleFirebaseCrashlytics)
    alias(libs.plugins.googleFirebaseFirebasePerf)
}

android {
    namespace = "ru.sangel.zaya"
    compileSdk = 34
    defaultConfig {
        applicationId = "ru.sangel.zaya"
        minSdk = 26
        targetSdk = 34
        versionCode = 4
        versionName = "0.2"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            libs.versions.compose.compiler
                .get()
    }
    packaging {
        resources {
            excludes += "/META-INF/"
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false

            isShrinkResources = false

            proguardFiles("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dependencies {
        implementation(projects.shared)
        implementation(projects.map)
        implementation(projects.common)
        implementation(platform(libs.androidx.compose.bom))

        implementation(libs.compose.ui)
        implementation(libs.compose.ui.tooling.preview)
        implementation(libs.compose.material3)
        implementation(libs.androidx.activity.compose)
        debugImplementation(libs.compose.ui.tooling)

        implementation(libs.decompose)
        implementation(libs.decompose.extensions.compose)
        implementation(libs.lottie.compose)

        implementation(libs.koin.core)
        implementation(libs.koin.androidx.compose)
        implementation(libs.androidx.ui.viewbinding)

        implementation(libs.maps.mobile)

        implementation(libs.accompanist.permissions)

        implementation(libs.kable.core)

        implementation(libs.kotlinx.coroutines.test)

        implementation(libs.koin.test.junit5)
        implementation(libs.koin.test)
        implementation(libs.koin.test.junit4)

        implementation(libs.kotlin.test)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(libs.androidx.ui.test.junit4)
    }
}
dependencies {
    implementation(libs.androidx.junit)
    implementation(libs.androidx.rules)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.perf)
    implementation(libs.firebase.auth)
}
