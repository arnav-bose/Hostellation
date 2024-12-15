plugins {
    alias(libs.plugins.hostellation.android.library)
}

android {
    namespace = "com.arnav.core.data"
}

dependencies {

    // Serialization
    implementation(libs.retrofit2.converter.gson)

    // Network
    implementation(libs.retrofit)
    implementation (libs.logging.interceptor)

    implementation(projects.core.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}