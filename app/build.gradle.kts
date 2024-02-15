plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "monster.moti.lsposed"
    compileSdk = 34

    defaultConfig {
        applicationId = "monster.moti.lsposed"
        minSdk = 33
        targetSdk = 33
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        if(System.getenv("KEY_FILE_PATH") != null) {
            create("release") {
                storeFile = file(System.getenv("KEY_FILE_PATH"))
                storePassword = System.getenv("KEY_FILE_PASSWORD")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD")
            }
        } else {
            create("release") {

            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = android.signingConfigs.getByName("release")
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildToolsVersion = "34.0.0"

}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    compileOnly("de.robv.android.xposed:api:82")
    compileOnly(fileTree("lib"))
}

tasks.register("appVersionName") {
    println(android.defaultConfig.versionName)
}