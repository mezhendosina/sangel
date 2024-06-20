plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    alias(libs.plugins.googleGmsGoogleServices)
    alias(libs.plugins.googleFirebaseCrashlytics)
    alias(libs.plugins.googleFirebaseFirebasePerf)
}

android {
    namespace = "ru.sangel.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "ru.sangel.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 3
        versionName = "0.1.2"
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
        implementation(libs.compose.ui)
        implementation(libs.compose.ui.tooling.preview)
        implementation(libs.compose.material3)
        implementation(libs.androidx.activity.compose)
        debugImplementation(libs.compose.ui.tooling)

        implementation(libs.decompose)
        implementation("com.arkivanov.decompose:extensions-compose:3.0.0")
        implementation("com.airbnb.android:lottie-compose:6.4.0")

        implementation("io.insert-koin:koin-core:3.5.6")
        implementation("io.insert-koin:koin-androidx-compose:3.5.6")
        implementation("androidx.compose.ui:ui-viewbinding:1.6.7")

        implementation("com.yandex.android:maps.mobile:4.5.1-lite")

        implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")

        implementation("com.juul.kable:core:0.31.1")

        implementation(libs.kotlin.test)
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
        implementation(libs.koin.test.junit5)
        implementation("io.insert-koin:koin-test:3.5.6")
        implementation("io.insert-koin:koin-test-junit4:3.5.6")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    }
}
dependencies {
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.rules)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.perf)
}
