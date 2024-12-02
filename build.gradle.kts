// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    dependencies {
        // Gradle and Google Services classpaths
        classpath(libs.gradle.v802) // Adjust version as required
        classpath(libs.google.services) // Google Services plugin for Firebase
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}
