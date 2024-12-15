plugins {
    alias(libs.plugins.hostellation.android.library)
    alias(libs.plugins.jetbrainsKotlinKsp)
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.arnav.home.data"
}

dependencies {

    // Serialization
    implementation(libs.gson)

    // Network
    implementation(libs.retrofit)

    // Projects
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.home.domain)

    // Dagger-Hilt
    implementation (libs.hilt.android)
    
    implementation (libs.androidx.hilt.navigation.compose)
    ksp(libs.dagger.compiler)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}