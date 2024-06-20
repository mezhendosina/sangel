plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
    id("kotlinx-serialization")

    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")

    alias(libs.plugins.room)
    alias(libs.plugins.googleGmsGoogleServices)
}
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
//
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//        }
//    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            api(libs.androidx.startup)
            implementation(libs.maps.mobile)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
            implementation(libs.firebase.analytics)
            implementation("io.insert-koin:koin-android:3.5.6")
        }
        commonMain.dependencies {
            implementation(libs.decompose)

            implementation(libs.androidx.datastore.preferences.core)

            implementation(libs.koin.core)
            implementation("io.insert-koin:koin-logger-slf4j:3.5.6")

            implementation(libs.ktorfit.lib)
            implementation("io.ktor:ktor-client-logging:2.3.10")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.10")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.10")
            implementation("io.ktor:ktor-client-auth:2.3.10")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
            implementation("ch.qos.logback:logback-core:1.5.6")
            api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

            implementation("com.juul.kable:core:0.31.1")

            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.sqlite)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
            implementation(libs.koin.test.junit5)
            implementation("io.insert-koin:koin-test:3.5.6")
            implementation("io.insert-koin:koin-test-junit4:3.5.6")
        }
        androidNativeTest.dependencies {
            implementation(libs.kotlin.test)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
            implementation(libs.koin.test.junit5)
            implementation("io.insert-koin:koin-test:3.5.6")
            implementation("io.insert-koin:koin-test-junit4:3.5.6")
        }
    }
}

android {
    namespace = "ru.sangel"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val ktorfitVersion = "1.13.0"

dependencies {
    implementation(libs.firebase.messaging)
    add("kspCommonMainMetadata", "de.jensklingenberg.ktorfit:ktorfit-ksp:1.13.0")
    add("kspAndroid", "de.jensklingenberg.ktorfit:ktorfit-ksp:1.13.0")

    add("kspAndroid", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}
