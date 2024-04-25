plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")
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
        androidMain.configure {
        }
        commonMain.dependencies {
            // put your multiplatform dependencies here
            implementation(libs.decompose)
            implementation(libs.ktorfit.lib)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {

    namespace = "ru.sangel"
    compileSdk = 34
    defaultConfig {
        minSdk = 29
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val ktorfitVersion = "1.13.0"

dependencies {
    add("kspCommonMainMetadata", "de.jensklingenberg.ktorfit:ktorfit-ksp:1.13.0")
    add("kspAndroid", "de.jensklingenberg.ktorfit:ktorfit-ksp:1.13.0")
}
