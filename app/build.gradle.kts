plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id ("kotlin-kapt")
    id ("kotlin-android")
    id ("kotlin-parcelize")
    // Parcelable
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.morarafrank.compulynxinterview"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.morarafrank.compulynxinterview"
        minSdk = 26
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    val compose_version = "1.1.0-alpha04"
    val retrofit_version = "2.9.0"
    val okhttp_version = "4.9.2"
    val coil_version = "1.4.0"
    val room_version = "2.5.0"
    val shared_preferences_version = "1.1.1"
    val datastore_version = "1.0.0"
    val hilt_version = "2.44.2"
    val paging_version = "3.1.1"
    val workmanager_version = "2.7.0"
    val lifecycle_version = "2.5.1"
    val coroutines_version = "1.5.2"

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


        // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    //    Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Coil
    implementation("io.coil-kt:coil-compose:$coil_version")

    // Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // Shared Preferences
    implementation("androidx.preference:preference-ktx:$shared_preferences_version")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:$datastore_version")
//
    // Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
//
    // Paging
//    implementation"androidx.paging:paging-runtime-ktx: $paging_version"
    implementation("androidx.paging:paging-runtime:$paging_version")
    implementation("androidx.paging:paging-runtime-ktx:$paging_version")
    implementation("androidx.paging:paging-compose:1.0.0-alpha17")

    // Work Manager
    implementation("androidx.work:work-runtime-ktx:$workmanager_version")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")


    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

        // Timber
    implementation ("com.jakewharton.timber:timber:5.0.1")

        // JSON Parsing
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
}