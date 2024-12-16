plugins {
    alias(libs.plugins.hostellation.android.library.compose)
}

android {
    namespace = "com.arnav.core.presentation.design"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}