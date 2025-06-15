plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.clovexx.shoppe"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.clovexx.shoppe"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.clovexx.shoppe.Runner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // --- Kotlin & Core ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // --- Compose ---
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.ui.tooling.preview)

    // --- Navigation & Accompanist ---
    implementation(libs.navigation.compose)
    implementation(libs.compose.pager)
    implementation(libs.compose.pager.indicators)

    // --- Network ---
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp.logging)

    // --- DI (Hilt) ---
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // --- Room (Database) ---
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // --- Supabase ---
    implementation(platform(libs.supabase))
    implementation(libs.supabase.postgres)
    implementation(libs.supabase.storage)
    implementation(libs.ktor)

    // --- Utils ---
    implementation(libs.coil.compose)
    coreLibraryDesugaring(libs.desugar)
    implementation(libs.core.splashscreen)
    implementation(libs.coil.svg)
    implementation(libs.lottie)
    implementation(libs.datastore)
    implementation(libs.datastore.preferences)
    implementation(libs.gson)
    implementation(libs.security.crypto)

    // --- Testing: Unit ---
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.mockito.core)

    // --- Testing: Android Instrumented ---
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.arch.core.testing)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)


    // --- Debug (UI tooling only in debug) ---
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}
