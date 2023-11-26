package com.sprintsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.api.client.json.gson.GsonFactory
import com.sprintsync.data.auth.Authenticator
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.views.HomePage
import com.sprintsync.ui.views.auth.PasswordResetView
import com.sprintsync.ui.views.auth.SignInView
import com.sprintsync.ui.views.auth.SignUpView
import com.sprintsync.ui.views.profile.ProfileScreen
import com.sprintsync.ui.views.project_view.DetailProject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		installSplashScreen()
		setContent { MainContent() }
	}
}

@Composable
fun MainContent() {
	val navController = rememberNavController()

	var showBottomBar by remember { mutableStateOf(false) }
	navController.addOnDestinationChangedListener { _, dest, _ ->
		showBottomBar = dest.route !in listOf("sign_in", "sign_up", "password_reset")
	}

	Scaffold(
		bottomBar = { if (showBottomBar) BottomNavigation(navController) }
	) { paddingValues ->
		Surface(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {
			NavHost(
				navController = navController,
				startDestination = if (Authenticator.signedIn) "home" else "sign_in",
//				startDestination = "calendar",
				enterTransition = { EnterTransition.None },
				exitTransition = { ExitTransition.None }
			) {
				composable("sign_in") { SignInView(navController) }
				composable("sign_up") { SignUpView(navController) }
				composable("password_reset") { PasswordResetView(navController) }
				composable(
					"home",
					enterTransition = {
						when (initialState.destination.route) {
							"project" -> slideInHorizontally(
								initialOffsetX = { -it },
								animationSpec = tween(200)
							)

							else      -> null
						}
					},
					exitTransition = {
						when (targetState.destination.route) {
							"project" -> slideOutHorizontally(
								targetOffsetX = { -it },
								animationSpec = tween(200)
							)

							else      -> null
						}
					},
				) { HomePage() }
				composable(
					"project",
					enterTransition = {
						when (initialState.destination.route) {
							"home" -> slideInHorizontally(
								initialOffsetX = { it },
								animationSpec = tween(200)
							)

							else   -> null
						}
					},
					exitTransition = {
						when (targetState.destination.route) {
							"home" -> slideOutHorizontally(
								targetOffsetX = { it },
								animationSpec = tween(200)
							)

							else   -> null
						}
					},
				) { DetailProject() }
				composable("calendar") {
					val JSON_FACTORY = GsonFactory.getDefaultInstance()
					val TOKEN_DIR_PATH = "../token"
					val SCOPES = listOf("https://www.googleapis.com/auth/calendar.events.owned")
					val CREDENTIALS_FILE_PATH = "/credentials.json"

					val stream = MainActivity::class.java.classLoader?.getResourceAsStream(CREDENTIALS_FILE_PATH)
					println(
						stream
							?.bufferedReader()
							?.readLines()
							?.joinToString("\n")
					)
				}
				composable("profile") { ProfileScreen(navController) }
			}
		}
	}
}