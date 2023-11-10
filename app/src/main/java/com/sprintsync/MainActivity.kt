package com.sprintsync

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
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

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent { MainContent() }
	}
}

@Composable
fun MainContent() {
	val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
		"No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
	}
	val authVM = viewModel<AuthViewModel>(viewModelStoreOwner = viewModelStoreOwner)

	val navController = rememberNavController()
	var currentRoute by remember { mutableStateOf(navController.currentDestination?.route) }

	navController.addOnDestinationChangedListener { _, destination, _ ->
		currentRoute = destination.route
	}

	Scaffold(
		bottomBar = {
			if (currentRoute !in listOf("splash", "sign_in", "sign_up", "password_reset")) {
				BottomNavigation(navController = navController)
			}
		}
	) { paddingValues ->
		Surface(modifier = Modifier.padding(paddingValues)) {
			NavHost(navController = navController, startDestination = "splash") {
				composable("splash") {
					LaunchedEffect(Unit) {
						Authenticator.isSignedIn.let {
							Log.d("Splash", "isSignedIn: $it")
							authVM.update(AuthState(it))
							navController.navigate(if (it) "home" else "sign_in")
						}
					}
				}
				composable("sign_in") {
					CompositionLocalProvider(
						LocalViewModelStoreOwner provides viewModelStoreOwner
					) {
						SignInView(
							context = LocalContext.current,
							navController = navController,
						)
					}
				}
				composable("sign_up") {
					CompositionLocalProvider(
						LocalViewModelStoreOwner provides viewModelStoreOwner
					) {
						SignUpView(
							context = LocalContext.current,
							navController = navController
						)
					}
				}
				composable("password_reset") {
					CompositionLocalProvider(
						LocalViewModelStoreOwner provides viewModelStoreOwner
					) {
						PasswordResetView(
							context = LocalContext.current,
							navController = navController
						)
					}
				}
				composable("home") { HomePage() }
				composable("project") { DetailProject() }
				composable("calendar") { TODO("Have not implement calendar view") }
				composable("profile") { TODO("Have not implement profile view") }
			}
		}
	}
}