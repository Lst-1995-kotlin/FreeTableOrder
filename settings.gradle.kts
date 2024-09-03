pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
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

rootProject.name = "FreeTableOrder"
include(":app")

include("core:domain")
include(":core:ui")
include(":core:data")

include(":feature:login")
include(":feature:main")
include(":feature:manage")
include(":feature:kitchen")
include(":feature:table")
