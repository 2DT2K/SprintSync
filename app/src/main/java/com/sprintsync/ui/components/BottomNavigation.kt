package com.sprintsync.ui.components

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sprintsync.R
import com.sprintsync.ui.navigation.Screens


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
                label = {
                    Text(
                        stringResource(id = screen.resourceId),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                onClick = {
                    navController?.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
//						popUpTo(navController.graph.findStartDestination().id) {
//							saveState = true
//						}
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = when (screen) {
                                Screen.Home     -> {
                                    if (currentDestination?.hierarchy?.any {
                                            it.route == screen
                                                .route
                                        } == true)
                                        R.drawable.home_fill
                                    else
                                        R.drawable.home
                                }

                                Screen.Project  -> {
                                    if (currentDestination?.hierarchy?.any {
                                            it.route == screen
                                                .route
                                        } == true)
                                        R.drawable.folder_fill
                                    else
                                        R.drawable.folder
                                }

                                Screen.Calendar -> {
                                    if (currentDestination?.hierarchy?.any {
                                            it.route == screen
                                                .route
                                        } == true)
                                        R.drawable.calendar_fill
                                    else
                                        R.drawable.calendar_month
                                }

                                Screen.Profile  -> {
                                    if (currentDestination?.hierarchy?.any {
                                            it.route == screen
                                                .route
                                        } == true)
                                        R.drawable.profile_fill
                                    else
                                        R.drawable.profile
                                }

                                else            -> R.drawable.nice_avatar
                            }
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                },
                alwaysShowLabel = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            )
        }
    }
}

enum class Screen(val route: String, @StringRes val resourceId: Int) {
    Home(Screens.Homepage.route, R.string.home),
    Project(Screens.ProjectRoute.route, R.string.project),
    Calendar(Screens.Calendar.route, R.string.calendar),
    Profile(Screens.Profile.route, R.string.profile)
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavigation()
}