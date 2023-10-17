package com.sprintsync

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.sprintsync.auth.GoogleAuthUiClient
import com.sprintsync.auth.SignInViewModel
import com.sprintsync.ui.theme.SprintSyncTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	private val googleAuthUiClient by lazy {
		GoogleAuthUiClient(
			context = applicationContext,
			oneTapClient = Identity.getSignInClient(applicationContext)
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SprintSyncTheme {
				val navController = rememberNavController()
				NavHost(navController = navController, startDestination = "sign_in") {
					composable("sign_in") {
						val viewModel = viewModel<SignInViewModel>()
						val state by viewModel.state.collectAsStateWithLifecycle()

						LaunchedEffect(Unit) {
							googleAuthUiClient
								.getSignedInUser()
								?.let {
									navController.navigate("profile")
								}
						}

						val launcher = rememberLauncherForActivityResult(
							contract = ActivityResultContracts.StartIntentSenderForResult()
						) { result ->
							if (result.resultCode == RESULT_OK) {
								lifecycleScope.launch {
									googleAuthUiClient
										.signInWithIntent(result.data ?: return@launch)
										.let { viewModel.onSignInResult(it) }
								}
							}
						}

						LaunchedEffect(state.isSignInSuccessful) {
							if (state.isSignInSuccessful) {
								Toast
									.makeText(applicationContext, "Signed in", Toast.LENGTH_LONG)
									.show()

								navController.navigate("profile")
								viewModel.resetState()
							}
						}

						SignInScreen(state = state) {
							lifecycleScope.launch {
								googleAuthUiClient
									.signIn()
									.let {
										launcher.launch(
											IntentSenderRequest
												.Builder(it ?: return@launch)
												.build()
										)
									}
							}
						}
					}
					composable("profile") {
						ProfileScreen(googleAuthUiClient.getSignedInUser()) {
							lifecycleScope.launch {
								googleAuthUiClient.signOut()
								navController.popBackStack()
							}
						}
					}
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MainContent() {
}