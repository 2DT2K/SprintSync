package com.sprintsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.views.BoardView
import com.sprintsync.ui.views.HomePage
import com.sprintsync.ui.views.auth.LogInScreen
import com.sprintsync.ui.views.auth.SignUpScreen
import com.sprintsync.ui.views.project_view.DetailProject

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val navController = rememberNavController()
			val (currentRoute, setCurrentRoute) = remember { mutableStateOf(navController.currentDestination?.route) }

			navController.addOnDestinationChangedListener() { _, destination, _ ->
				setCurrentRoute(destination.route)
			}

			Scaffold(
				bottomBar = {
					if (currentRoute != "login" && currentRoute != "signup") {
						BottomNavigation(navController = navController)
					}
				}
			) {
				Surface(modifier = Modifier.padding(it)) {
					NavHost(navController = navController, startDestination = "home") {
						composable("login") { LogInScreen() }
						composable("signup") { SignUpScreen() }
						composable("home") { HomePage() }
						composable("project") { DetailProject() }
						composable("calendar") { TODO("Have not implement calendar view") }
						composable("profile") { TODO("Have not implement profile view") }
					}
				}
			}
		}

//			BoardView()

	}
}

@Preview
@Composable
fun GreetingPreview() {
	BoardView()
}