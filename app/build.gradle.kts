plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.rzjaffery.sociallearningapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rzjaffery.sociallearningapplication"
        minSdk = 23
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.inappmessaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.viewpager2:viewpager2:1.1.0")
    implementation ("androidx.navigation:navigation-fragment:2.7.7")
    implementation ("androidx.navigation:navigation-ui:2.7.7")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.8.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.8.4")
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:34.1.0"))
    implementation ("com.google.firebase:firebase-auth")
    implementation ("com.google.firebase:firebase-database")
    implementation ("com.google.firebase:firebase-storage")

    // Ads
    implementation ("com.google.android.gms:play-services-ads:23.2.0")

}