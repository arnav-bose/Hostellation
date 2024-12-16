import com.arnav.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryComposeFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("hostellation.android.library.compose")
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
            }

            dependencies {
                "implementation"(project(":core:presentation:ui"))
                "implementation"(project(":core:presentation:design"))

                "implementation"(project.libs.findBundle("hilt-compose").get())
                "ksp"(project.libs.findBundle("hilt-ksp").get())
            }
        }
    }
}