plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.mercadolibremobiletest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mercadolibremobiletest"
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

val nav_version = "2.5.3"
val room_version = "2.5.1"
val coroutines_version = "1.6.4"
val lifecycle_version = "2.5.1"

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.compose.material3:material3-window-size-class:1.3.0")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.3.0")

    // Navigation
    implementation ("androidx.navigation:navigation-compose:$nav_version")
    // Room
    implementation ("androidx.room:room-runtime:$room_version")
    testImplementation ("junit:junit:4.12")
    testImplementation ("org.junit.jupiter:junit-jupiter")
    testImplementation ("junit:junit:4.12")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    // Hilt
    implementation ("com.google.dagger:hilt-android:2.47")
    implementation ("androidx.hilt:hilt-navigation-fragment:1.0.0")
    testImplementation ("com.google.dagger:hilt-android-testing:2.47")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    kapt ("com.google.dagger:hilt-compiler:2.47")
    implementation ("com.jakewharton.timber:timber:5.0.1")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
    //LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    //Coil
    implementation ("io.coil-kt:coil-compose:2.2.2")
    implementation ("com.squareup.picasso:picasso:2.5.2")

    //Test
    testImplementation ("androidx.test:core:1.5.0")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    testImplementation ("com.google.truth:truth:1.1.3")
    testImplementation ("io.mockk:mockk:1.13.4")
    testImplementation ("io.mockk:mockk-android:1.13.4")
    testImplementation ("io.mockk:mockk-agent:1.13.4")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.1.0-alpha04")
    testImplementation ("app.cash.turbine:turbine:0.7.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}