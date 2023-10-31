package com.sprintsync

import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sprintsync.auth.AuthViewModel
import com.sprintsync.auth.Authenticator
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.views.HomePage
import com.sprintsync.ui.views.auth.SignInScreen
import com.sprintsync.ui.views.auth.SignUpScreen
import com.sprintsync.ui.views.project_view.DetailProject
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent { MainContent() }
	}
}

@Composable
fun MainContent() {
	val scope = rememberCoroutineScope()

	val authenticator = Authenticator(context = LocalContext.current)

	val viewModel = viewModel<AuthViewModel>()
	val state by viewModel.state.collectAsStateWithLifecycle()

	val navController = rememberNavController()
	val (currentRoute, setCurrentRoute) = remember {
		mutableStateOf(navController.currentDestination?.route)
	}

	navController.addOnDestinationChangedListener { _, destination, _ ->
		setCurrentRoute(destination.route)
	}

	Scaffold(
		bottomBar = {
			if (currentRoute !in listOf("sign_in", "sign_up")) {
				BottomNavigation(navController = navController)
			}
		}
	) { paddingValues ->
		Surface(modifier = Modifier.padding(paddingValues)) {
			NavHost(navController = navController, startDestination = "sign_in") {
				composable("sign_in") {
					LaunchedEffect(state.signedIn) {
						if (state.signedIn) navController.navigate("home")
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

					SignInScreen(
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
						resetPassword = { navController.navigate("password_reset") },
						signUp = { navController.navigate("sign_up") }
					)
				}
				composable("sign_up") { SignUpScreen() }
				composable("password_reset") { TODO("Have not implement password reset view") }
				composable("home") { HomePage() }
				composable("project") { DetailProject() }
				composable("calendar") { TODO("Have not implement calendar view") }
				composable("profile") { TODO("Have not implement profile view") }
			}
		}
	}
}