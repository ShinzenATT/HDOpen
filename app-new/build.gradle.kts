import java.util.Properties

val ktor_version: String by project
val nav_version: String by project

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
    kotlin("plugin.compose")
}

android {
    namespace = "se.gorymoon.hdopen"
    compileSdk = 35

    buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = "se.gorymoon.hdopen.test"
        minSdk = 26
        targetSdk = 35
        versionCode = 11
        versionName = "3.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val environmentFile = project.rootProject.file("environment.properties")
        val properties = Properties()
        properties.load(environmentFile.inputStream())

        buildConfigField("String", "HD_API_KEY", properties.getProperty("HD_API_KEY") ?: "")
        buildConfigField("String", "HD_API_URL", properties.getProperty("HD_API_URL") ?: "https://hd.chalmers.se/api/")
        buildConfigField("String", "GORYMOON_API_URL", properties.getProperty("GORYMOON_API_URL") ?: "https://gorymoon.se/hdopen/api/")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_23
        targetCompatibility = JavaVersion.VERSION_23
    }
    kotlinOptions {
        jvmTarget = "23"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")

    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("io.coil-kt:coil-gif:2.6.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.12.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}