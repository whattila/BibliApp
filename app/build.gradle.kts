plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    alias(libs.plugins.googleGmsGoogleServices)
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.bibliapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bibliapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // nincs Marinak
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.analytics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.51.1")
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.compose.ui:ui:1.0.0")
    implementation("androidx.compose.material:material:1.0.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.0")
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0") // nincs Marinak
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1") // nincs Marinak
    implementation("androidx.hilt:hilt-work:1.2.0") // nincs Marinak
    kapt("androidx.hilt:hilt-compiler:1.2.0") // nincs Marinak
    implementation("androidx.work:work-runtime-ktx:2.8.1") // nincs Marinak
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.room.runtime) // nincs Marinak
    kapt(libs.androidx.room.compiler) // nincs Marinak
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.navigation.fragment.ktx) // nincs Marinak
    implementation(libs.androidx.navigation.ui.ktx) // nincs Marinak
    implementation(libs.androidx.navigation.compose)
    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom))
    // Add the dependency for the Analytics library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(libs.google.firebase.crashlytics)
    implementation(libs.google.firebase.analytics)
}

kapt {
    correctErrorTypes = true
}