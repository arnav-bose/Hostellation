plugins {
    alias(libs.plugins.hostellation.android.library.compose.feature)
}

android {
    namespace = "com.arnav.home.presentation"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.home.domain)

    // Image Loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}