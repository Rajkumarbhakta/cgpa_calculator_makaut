pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            // You can find the maven URL for other artifacts (e.g. KMP, METALAVA) on their
            // build pages.
            url = uri("https://androidx.dev/snapshots/builds/13541839/artifacts/repository")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            // You can find the maven URL for other artifacts (e.g. KMP, METALAVA) on their
            // build pages.
            url = uri("https://androidx.dev/snapshots/builds/13541839/artifacts/repository")
        }
    }
}

rootProject.name = "CGPA Calculator - MAKAUT"
include(":app")
 