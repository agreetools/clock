plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.agreetools.digitalnightclockred"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.agreetools.digitalnightclockred"
        minSdk = 34
        targetSdk = 35
        versionCode = 3
        versionName = "1.$versionCode"


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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
        freeCompilerArgs += "-opt-in=com.google.android.horologist.annotations.ExperimentalHorologistApi"
    }

    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)



        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.compose.material)
        implementation(libs.androidx.compose.foundation)
        implementation(libs.androidx.wear.tooling.preview)
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.core.splashscreen)
        implementation(libs.androidx.tiles)
        implementation(libs.androidx.tiles.material)
        implementation(libs.androidx.tiles.tooling)

        debugImplementation(libs.androidx.tiles.tooling.preview)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.tiles.tooling.preview)
        debugImplementation(libs.androidx.tiles.tooling)


    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.play.services.wearable)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)


//    wearApp(project(":wear"))
    implementation(project(":myAndroid"))
}