plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "app.wallet_monitor.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "sharedKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    tasks.register<Copy>("copyResourcesForIos") {
        from("$projectDir/src/commonMain/resources")
        into("$projectDir/iosMain/resources")
    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            resources.srcDir("src/commonMain/resources")
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(compose.components.resources)

                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.lifecycle.viewmodel)
                implementation(libs.bundles.ktor)
                implementation(libs.kotlinx.datetime)
                implementation(libs.multiplatform.settings)
                implementation(libs.multiplatform.settings.no.arg)
                implementation(libs.sqldelight.coroutines)

                api(libs.koin.compose)
                api(libs.koin.core)
                api(libs.datastore.preferences)
                api(libs.datastore)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation(libs.koin.android)
                implementation(libs.sqldelight.android)
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.testExt.junit)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.sqldelight.ios)
            }
        }
    }
}

sqldelight {
    databases {
        create("WalletMonitorDB") {
            packageName ="app.wallet_monitor.db"
            version = 0
            schemaOutputDirectory = file("src/commonMain/sqldelight/app/wallet_monitor/db/schemas")
            verifyMigrations = true
        }
    }
}
