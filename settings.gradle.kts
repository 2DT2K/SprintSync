pluginManagement {
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
		maven {
			url = uri("https://my.pspdfkit.com/maven")
		}
		maven("https://jitpack.io")
	}
}

rootProject.name = "SprintSync"
include(":app")