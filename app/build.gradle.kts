plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }



    namespace = "com.example.mariejosekhalil_pet_adoption"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mariejosekhalil_pet_adoption"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {
    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.databinding.runtime)
    implementation(libs.androidx.recyclerview)
        implementation(libs.androidx.lifecycle.viewmodel.ktx)
        implementation(libs.androidx.lifecycle.livedata.ktx)
        implementation(libs.androidx.room.runtime)
        kapt(libs.androidx.room.compiler)
    implementation (libs.firebase.bom.v3222)

    implementation (libs.firebase.bom.v3200)

    implementation (libs.google.firebase.database.ktx)
    implementation(libs.firebase.database.ktx)
        implementation(libs.picasso) // For loading images
    implementation(libs.firebase.analytics)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}