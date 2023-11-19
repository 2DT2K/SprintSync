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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.sprintsync.ui.views.project_view.DetailProject
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContent { MainContent() }
    }
}

@Composable
fun MainContent() {
    val scope = rememberCoroutineScope()

    val authenticator = Authenticator(context = LocalContext.current)

	val authVM = viewModel<AuthViewModel>()
	val authState by authVM.state.collectAsStateWithLifecycle()

    val navController = rememberNavController()
    val (currentRoute, setCurrentRoute) = remember {
        mutableStateOf(navController.currentDestination?.route)
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        setCurrentRoute(destination.route)
    }

	Scaffold(
		bottomBar = {
			if (currentRoute !in listOf("sign_in", "sign_up", "password_reset")) {
				BottomNavigation(navController = navController)
			}
		}
	) { paddingValues ->
		Surface(modifier = Modifier.padding(paddingValues)) {
			NavHost(navController = navController, startDestination = "sign_in") {
				composable("sign_in") {
					LaunchedEffect(Unit) {
						authVM.update(AuthState(authenticator.isSignedIn))
					}

					LaunchedEffect(authState.signedIn) {
						if (authState.signedIn) navController.navigate("home")
					}

					val launcher = rememberLauncherForActivityResult(
						contract = ActivityResultContracts.StartIntentSenderForResult()
					) { result ->
						if (result.resultCode == RESULT_OK) {
							scope.launch {
								authenticator
									.signInWithIntent(result.data ?: return@launch)
									.let { authVM.update(it) }
							}
						}
					}

					SignInView(
						signInWithPassword = { email, password ->
							scope.launch {
								authenticator
									.signIn(email, password)
									.let { authVM.update(it) }
							}
						},
						signInWithGoogle = {
							scope.launch {
								authenticator
									.getSignInIntentSender()
									.let {
										launcher.launch(
											IntentSenderRequest
												.Builder(it ?: return@launch)
												.build()
										)
									}
							}
						},
						resetPassword = { navController.navigate("password_reset") },
						signUp = { navController.navigate("sign_up") }
					)
				}
				composable("sign_up") {
					SignUpView(
						signUpWithPassword = { email, password ->
							scope.launch {
								authenticator
									.signUp(email, password)
									.let { authVM.update(it) }
							}
						},
						signIn = { navController.popBackStack() }
					)
				}
				composable("password_reset") {
					PasswordResetView(
						resetPassword = { email ->
							scope.launch {
								authenticator.resetPassword(email)
								navController.popBackStack()
							}
						}
					)
				}
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
				) {
//                    EnterAnimation {
					HomePage()
//                    }
				}
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
				) {
//                    EnterAnimation {
					DetailProject()
//                    }
				}
				composable("calendar") { TODO("Have not implement calendar view") }
				composable("profile") { TODO("Have not implement profile view") }
			}
		}
	}
}