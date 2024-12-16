plugins {
    alias(libs.plugins.hostellation.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}