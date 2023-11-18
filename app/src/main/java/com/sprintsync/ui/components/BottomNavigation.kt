package com.sprintsync.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sprintsync.R


@Composable
fun BottomNavigation(navController: NavController? = null) {
	val screens = listOf(
		Screens.Home,
		Screens.Project,
		Screens.Calendar,
		Screens.Profile
	)
	NavigationBar {
		val navBackStackEntry = navController?.currentBackStackEntryAsState()
		val currentDestination = navBackStackEntry?.value?.destination
		screens.forEach { screen ->
			NavigationBarItem(
				selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
				label = { Text(stringResource(id = screen.resourceId)) },
				onClick = {
					navController?.navigate(screen.route) {
						// Pop up to the start destination of the graph to
						// avoid building up a large stack of destinations
						// on the back stack as users select items
						popUpTo(navController.graph.findStartDestination().id) {
							saveState = true
						}
						// Avoid multiple copies of the same destination when
						// re-selecting the same item
						launchSingleTop = true
						// Restore state when re-selecting a previously selected item
						restoreState = true
					}
				},
				icon = {
					Image(
						painter = painterResource(
							id = when (screen) {
								Screens.Home     -> R.drawable.home
								Screens.Project  -> R.drawable.folder_share
								Screens.Calendar -> R.drawable.calendar_month
								Screens.Profile  -> R.drawable.profile
								else             -> R.drawable.nice_avatar
							}
						), contentDescription = null
					)
				},
				alwaysShowLabel = currentDestination?.hierarchy?.any { it.route == screen.route } == true
			)
		}
	}
//	NavigationBar {
//		items.forEachIndexed { index, item ->
//			NavigationBarItem(
//				selected = selectedItem == index,
//				onClick = { selectedItem = index },
//				icon = {
//					when (item) {
//						"Home" -> Image(
//							painter = painterResource(id = R.drawable.home),
//							contentDescription = null
//						)
//
//						"Project" -> Image(
//							painter = painterResource(id = R.drawable.folder_share),
//							contentDescription = null
//						)
//
//						"Calendar" -> Image(
//							painter = painterResource(id = R.drawable.calendar_month),
//							contentDescription = null
//						)
//
//						"Profile" -> Image(
//							painter = painterResource(id = R.drawable.profile),
//							contentDescription = null
//						)
//					}
//				},
//				// Add this to make item only show label when selected
//				alwaysShowLabel = selectedItem == index,
//				label = { Text(item) }
//			)
//		}
//	}
}

sealed class Screens(val route: String, @StringRes val resourceId: Int) {
	object Home : Screens("home", R.string.home)
	object Project : Screens("project", R.string.project)
	object Calendar : Screens("calendar", R.string.calendar)
	object Profile : Screens("profile", R.string.profile)
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
	BottomNavigation()
}