package com.sprintsync

import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sprintsync.auth.AuthState
import com.sprintsync.auth.AuthViewModel
import com.sprintsync.auth.Authenticator
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
		val splashScreen = installSplashScreen()
		setContent { MainContent() }
	}
}

@Composable
fun MainContent() {
	val navController = rememberNavController()

	var showBottomBar by remember { mutableStateOf(false) }
	navController.addOnDestinationChangedListener { _, dest, _ ->
		showBottomBar = dest.route !in listOf("splash", "sign_in", "sign_up", "password_reset")
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
				startDestination = "splash",
				enterTransition = { EnterTransition.None },
				exitTransition = { ExitTransition.None }
			) {
				composable("splash") {
					LaunchedEffect(Unit) {
						navController.navigate(if (Authenticator.signedIn) "home" else "sign_in")
					}

					Box(
						modifier = Modifier
							.fillMaxSize()
							.background(MaterialTheme.colorScheme.background),
						contentAlignment = Alignment.Center
					) {
						Image(
							painter = painterResource(id = R.drawable.logo),
							contentDescription = null
						)
					}
				}
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
				composable("calendar") { TODO("Have not implement calendar view") }
				composable("profile") { ProfileScreen(navController) }
			}
		}
	}
}