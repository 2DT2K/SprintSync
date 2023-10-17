plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("com.google.gms.google-services")
}

android {
	namespace = "com.sprintsync"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.sprintsync"
		minSdk = 26
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}

	kotlin {
		jvmToolchain(17)
	}

	buildFeatures {
		compose = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.2"
	}

	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.activity:activity-compose:1.8.0")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
	implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
	implementation("androidx.navigation:navigation-compose:2.7.4")
	implementation("io.coil-kt:coil-compose:2.4.0")

	implementation(platform("androidx.compose:compose-bom:2023.10.00"))
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("androidx.compose.material3:material3")

	implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
	implementation("com.google.firebase:firebase-auth-ktx")

	implementation("com.google.android.gms:play-services-auth:20.7.0")

//	testing dependencies
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.compose.ui:ui-test-junit4")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
	androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.00"))

//	debugging dependencies
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.3")
}