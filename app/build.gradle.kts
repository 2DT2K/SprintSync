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
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
	implementation("androidx.navigation:navigation-compose:2.7.4")

	implementation(platform("androidx.compose:compose-bom:2023.10.01"))
	implementation("androidx.compose.material:material")
	implementation("androidx.compose.material3:material3")
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("androidx.compose.ui:ui-util")

	// Retrofit
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))
	implementation("com.squareup.okhttp3:okhttp")
	implementation("com.squareup.okhttp3:logging-interceptor")

	//	VICO Chart
	dependencies {
		// For Jetpack Compose.
		implementation("com.patrykandpatrick.vico:compose:1.12.0")

		// For `compose`. Creates a `ChartStyle` based on an M2 Material Theme.
		implementation("com.patrykandpatrick.vico:compose-m2:1.12.0")

		// For `compose`. Creates a `ChartStyle` based on an M3 Material Theme.
		implementation("com.patrykandpatrick.vico:compose-m3:1.12.0")

		// Houses the core logic for charts and other elements. Included in all other modules.
		implementation("com.patrykandpatrick.vico:core:1.12.0")

		// For the view system.
		implementation("com.patrykandpatrick.vico:views:1.12.0")
	}

	// Firebase
	implementation(platform("com.google.firebase:firebase-bom:32.4.0"))
	implementation("com.google.firebase:firebase-auth-ktx")

	// Google Auth
	implementation("com.google.android.gms:play-services-auth:20.7.0")

	// Calendar
	implementation("io.github.boguszpawlowski.composecalendar:composecalendar:1.1.1")
	implementation("io.github.boguszpawlowski.composecalendar:kotlinx-datetime:1.1.1")
	implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")

	implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

	implementation("com.github.TuleSimon:xMaterialccp:1.22")

//	testing dependencies
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.compose.ui:ui-test-junit4")
	androidTestImplementation("androidx.navigation:navigation-testing:2.7.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
	androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))

//	debugging dependencies
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")
}