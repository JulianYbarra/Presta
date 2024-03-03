buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}

plugins {
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}