plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.junka.presta.core.data"
    compileSdk = extra["compileSdk.version"]?.toString()?.toInt()

    defaultConfig {
        minSdk = extra["minSdk.version"]?.toString()?.toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    testImplementation(project(":core:testing"))

    val hilt_version = "2.50"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
    kapt("androidx.hilt:hilt-compiler:1.2.0")


    testImplementation("junit:junit:4.13.2")
    testImplementation("org.amshove.kluent:kluent:1.73")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}