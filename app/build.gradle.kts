import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("org.jetbrains.kotlin.kapt")
//    alias(libs.plugins.kotlinKapt)
}

android {

    buildFeatures {
        viewBinding = true
    }

//    viewBinding {
//        enabled true
//    }

    namespace = "com.william.desafio_tupi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.william.desafio_tupi"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core.ktx)//
    implementation(libs.androidx.appcompat)//
    implementation(libs.material)//
    implementation(libs.androidx.constraintlayout)//

    // Room components
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.activity.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

//    implementation(libs.androidx.core.ktx)//
//    implementation(libs.androidx.appcompat)//
//    implementation(libs.material)//
    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)//

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Koin
    // Dependências do Koin
    implementation (libs.koin.android) // Versão mais recente do Koin
//    implementation (libs.koin.androidx.viewmodel) // Para suporte a ViewModel

    //Usar máscaras no cardNumber e expiryDate
    implementation(libs.androidmask){
        exclude(group = "com.android.support", module = "support-v4")
    }
}