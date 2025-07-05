plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    //id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.inventpanroll"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.inventpanroll"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    //Iconos
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

   /* // Navigation Component
    implementation ("androidx.navigation:navigation-fragment-ktx:2.9.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.9.1")*/
    implementation(libs.androidx.navigation.compose)

    // Hilt Dependency Injection
    implementation ("com.google.dagger:hilt-android:2.56.2")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation(libs.androidx.runtime.livedata)
    kapt ("com.google.dagger:hilt-compiler:2.56.2")

    // Retrofit + OkHttp
    implementation ("com.squareup.retrofit2:retrofit:3.0.0")
    implementation ("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Lifecycle Components
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.9.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Camera
    implementation ("androidx.camera:camera-core:1.4.2")
    implementation ("androidx.camera:camera-camera2:1.4.2")
    implementation ("androidx.camera:camera-lifecycle:1.4.2")
    implementation ("androidx.camera:camera-view:1.4.2")
    implementation ("androidx.camera:camera-extensions:1.4.2")

    // QR Scanner
    implementation ("com.google.mlkit:barcode-scanning:17.3.0")
    // Alternativa para QR más completa:
    // implementation 'com.journeyapps:zxing-android-embedded:4.3.0'

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}