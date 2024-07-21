import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
    id("kotlinx-serialization")

    alias(libs.plugins.ksp)
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
            implementation(libs.jetbrains.kotlinx.coroutines.android)
            implementation(libs.firebase.analytics)
            implementation(libs.koin.android)
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.decompose)

                implementation(libs.androidx.datastore.preferences.core)

                implementation(libs.koin.core)
                implementation(libs.koin.logger.slf4j)

                implementation(libs.ktorfit.lib)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.auth)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.logback.core)
                api(libs.kotlinx.coroutines.core)

                implementation(libs.kable.core)

                implementation(libs.androidx.room.runtime)
                implementation(libs.sqlite.bundled)
                implementation(libs.sqlite)

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.koin.test.junit5)
                implementation(libs.koin.test)
                implementation(libs.koin.test.junit4)
            }
        }
        androidNativeTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.koin.test.junit5)
            implementation(libs.koin.test)
            implementation(libs.koin.test.junit4)
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


dependencies {
    implementation(libs.firebase.messaging)
    add("kspCommonMainMetadata", "de.jensklingenberg.ktorfit:ktorfit-ksp:1.13.0")
    add("kspAndroid", "de.jensklingenberg.ktorfit:ktorfit-ksp:1.13.0")

    add("kspAndroid", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}
