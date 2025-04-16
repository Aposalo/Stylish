import java.io.FileInputStream
import java.util.Properties
import kotlin.toString

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
}

val externalPropertiesFile = rootProject.file("external.properties")
val externalProperties = Properties()
externalProperties.load(FileInputStream(externalPropertiesFile))

android {
    namespace = "gr.aposalo.stylish"
    compileSdk = 35

    defaultConfig {
        applicationId = "gr.aposalo.stylish"
        minSdk = 24
        targetSdk = 35
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
            buildConfigField("String", "BASE_URL", externalProperties["base_url"].toString())
        }
        debug{
            buildConfigField("String", "BASE_URL", externalProperties["base_url"].toString())
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    /*** Dagger - Hilt ***/
    implementation(libs.dagger)
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.splashScreen)
    ksp(libs.dagger.hilt.android.compiler)
    /*** Dagger - Hilt ***/

    /*** Navigation ***/
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.navigation.material)
    implementation (libs.accompanist.systemuicontroller)
    /*** Navigation ***/

    /*** Moshi ***/
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    /*** Moshi ***/

    /*** Retrofit2 ***/
    implementation(libs.com.squareup.retrofit2)
    implementation(libs.com.squareup.retrofit2.converter)
    implementation(libs.com.squareup.okhttp3.logging)
    /*** Retrofit2 ***/

    //Preferences
    implementation(libs.androidx.datastore.preferences)

    //Horizontal Pager
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    //Images
    implementation("io.coil-kt:coil-compose:2.6.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}