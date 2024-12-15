import com.android.build.api.dsl.LibraryExtension
import com.arnav.convention.ExtensionType
import com.arnav.convention.configureBuildTypes
import com.arnav.convention.configureJava
import com.arnav.convention.configureKotlin
import com.arnav.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.dependencies

class JVMLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
            }

            configureJava()
            configureKotlin()
        }
    }
}