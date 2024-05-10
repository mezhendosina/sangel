plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.sangel.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "ru.sangel.android"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.decompose)
    implementation("com.arkivanov.decompose:extensions-compose:3.0.0")
    implementation("com.airbnb.android:lottie-compose:6.4.0")

    implementation("io.insert-koin:koin-core:3.5.6")
    implementation("io.insert-koin:koin-android:3.5.6")
    implementation("io.insert-koin:koin-androidx-compose:3.5.6")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.6")
    implementation("androidx.compose.ui:ui-viewbinding:1.6.7")

    implementation("com.yandex.android:maps.mobile:4.5.1-lite")

    implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")
}
