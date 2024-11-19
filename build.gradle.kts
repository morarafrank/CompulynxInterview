// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id("com.android.application") version "8.1.0" apply false
//    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
//}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.52")
    }
}
plugins {
    id ("com.android.application") version "8.7.2" apply false
    id ("com.android.library") version "8.7.2" apply false
    id ("org.jetbrains.kotlin.android") version "2.0.20" apply false
    id ("org.jetbrains.kotlin.plugin.compose") version "2.0.20" apply false
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.20"

}