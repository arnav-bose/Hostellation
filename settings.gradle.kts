pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Hostellation"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":home:presentation")
include(":home:domain")
include(":home:data")
include(":core:domain")
include(":core:data")
include(":core:presentation:design")
include(":core:presentation:ui")
