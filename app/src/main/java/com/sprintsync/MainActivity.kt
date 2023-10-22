package com.sprintsync

import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sprintsync.auth.AuthViewModel
import com.sprintsync.auth.Authenticator
import com.sprintsync.ui.theme.SprintSyncTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SprintSyncTheme {
				MainContent()
			}
		}
	}
}

@Composable
fun MainContent() {
	val scope = rememberCoroutineScope()

	val authenticator = Authenticator(context = LocalContext.current)

	val viewModel = viewModel<AuthViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()

	val navController = rememberNavController()

	NavHost(
		navController = navController,
		startDestination = "sign_in"
	) {
		composable("sign_in") {
			LaunchedEffect(Unit) {
				if (authenticator.signedIn)
					navController.navigate("profile")
			}

			LaunchedEffect(state.signedIn) {
				if (state.signedIn)
					navController.navigate("profile")
			}

			val launcher = rememberLauncherForActivityResult(
				contract = ActivityResultContracts.StartIntentSenderForResult()
			) { result ->
				if (result.resultCode == RESULT_OK) {
					scope.launch {
						authenticator
							.signInWithIntent(result.data ?: return@launch)
							.let { viewModel.update(it) }
					}
				}
			}

			SignIn(
				signInWithPassword = { email, password ->
					scope.launch {
						authenticator
							.signIn(email, password)
							.let { viewModel.update(it) }
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
				resetPassword = { navController.navigate("reset_password") },
				signUp = { navController.navigate("sign_up") }
			)
		}

		composable("reset_password") {
			ResetPassword(
				resetPassword = { email ->
					scope.launch {
						authenticator.resetPassword(email)
					}
				},
				back = { navController.popBackStack() }
			)
		}

		composable("sign_up") {
			SignUp(
				signUpWithPassword = { email, password ->
					scope.launch {
						authenticator
							.signUp(email, password)
							.let { viewModel.update(it) }
						navController.navigate("sign_in")
					}
				},
				signIn = { navController.popBackStack() }
			)
		}

		composable("profile") {
			Profile(
				data = authenticator.signedInUser,
				signOut = {
					scope.launch {
						viewModel.reset()
						authenticator.signOut()
						navController.navigate("sign_in")
					}
				},
				verifyEmail = {
					scope.launch {
						authenticator.verifyEmail()
					}
				}
			)
		}
	}
}