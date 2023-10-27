plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
}

android {
	namespace = "com.sprintsync"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.sprintsync"
		minSdk = 26
		targetSdk = 33
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
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.4.3"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation("androidx.navigation:navigation-compose:2.7.4")
	implementation("androidx.compose.material:material:1.5.4")
	implementation("androidx.core:core-ktx:1.9.0")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
	implementation("androidx.activity:activity-compose:1.7.2")
	implementation(platform("androidx.compose:compose-bom:2023.03.00"))
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("com.google.android.gms:play-services-tagmanager-v4-impl:18.0.3")
    testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
	androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
	androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")
	implementation ("androidx.compose.ui:ui-util:1.3.3")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.0.1")

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

	implementation ("com.github.TuleSimon:xMaterialccp:1.22")

	val nav_version = "2.7.4"

	implementation("androidx.navigation:navigation-compose:$nav_version")
}