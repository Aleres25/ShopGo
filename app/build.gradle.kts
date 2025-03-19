plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = "com.example.shopgo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shopgo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        viewBinding = true
    }
}

val retrofitVersion = "2.3.0"
val rxJavaVersion = "2.1.1"


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.squareup.picasso:picasso:2.8") //Retrofit eklemek için
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion") //JSON Formatındaki veriyi ayıklama işlemini otomatik yapmak için
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    implementation("io.reactivex.rxjava2:rxjava:$rxJavaVersion")

    implementation("io.reactivex.rxjava2:rxandroid:$rxJavaVersion") //ReyclerView eklemek için
    implementation("androidx.recyclerview:recyclerview:1.1.0")

    implementation("com.github.bumptech.glide:glide:4.16.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.8.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.1")

    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.17")
}