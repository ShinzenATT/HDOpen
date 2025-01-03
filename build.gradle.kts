// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.7.3" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
    id("com.github.ben-manes.versions") version "0.42.0" apply false
    id("com.mikepenz.aboutlibraries.plugin") version "8.9.4" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    kotlin("plugin.serialization") version "2.1.0" apply  false
    kotlin("plugin.compose") version "2.1.0" apply false
}
