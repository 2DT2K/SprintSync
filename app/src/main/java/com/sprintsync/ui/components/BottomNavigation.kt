package com.sprintsync.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
		Screen.Home,
		Screen.Project,
		Screen.Calendar,
		Screen.Profile
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
}

enum class Screen(val route: String, @StringRes val resourceId: Int) {
	Home("home", R.string.home),
	Project("project", R.string.project),
	Calendar("calendar", R.string.calendar),
	Profile("profile", R.string.profile)
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
	BottomNavigation()
}