package com.sprintsync.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.sprintsync.ui.navigation.Screens

@Composable
fun TopAppBar(
    route: String,
    navController: NavController? = null
) {
    when (route) {
        "homepage"       -> {
            AvatarTopAppBar(
                title = "Home",
                navigationOnclick = {
                    navController?.navigate(Screens.Profile.route)
                }
            )
        }

        "project_list"   -> {
            AvatarTopAppBar(
                title = "Project",
                navigationOnclick = {
                    navController?.navigate(Screens.Profile.route)
                }
            )
        }

        "detail_project" -> {
            TwoIconTopAppBar(
                title = "Project",
                navigationOnclick = { navController?.popBackStack() })
        }

        "board"          -> {
            TwoIconTopAppBar(title = "Board", navigationOnclick = { navController?.popBackStack() })
        }

        "backlog"        -> {
            TwoIconTopAppBar(
                title = "Backlog",
                navigationOnclick = { navController?.popBackStack() }
            )
        }

        "timeline"       -> {
            TwoIconTopAppBar(
                title = "Timeline",
                navigationOnclick = { navController?.popBackStack() })
        }

        "tasks"          -> {
            TwoIconTopAppBar(title = "Tasks", navigationOnclick = { navController?.popBackStack() })
        }

        "files"          -> {
            TwoIconTopAppBar(title = "Files", navigationOnclick = { navController?.popBackStack() })
        }

        "members"        -> {
            TwoIconTopAppBar(
                title = "Member",
                navigationOnclick = { navController?.popBackStack() })
        }

        "report"         -> {
            TwoIconTopAppBar(
                title = "Report",
                navigationOnclick = { navController?.popBackStack() })
        }

        "calendar"       -> {
            AvatarTopAppBar(
                title = "Calendar",
                navigationOnclick = {
                    navController?.navigate(Screens.Profile.route)
                }
            )
        }

        "profile"        -> {
            AvatarTopAppBar(
                title = "Profile",
                turnAvatar = false,
                navigationOnclick = {
                    navController?.navigate(Screens.Profile.route)
                }
            )
        }

        "task/{taskId}"  -> {
            TwoIconTopAppBar(
                title = "Task",
                navigationOnclick = {
                    navController?.popBackStack()
                })
        }

        else             -> {
            Log.d("TopAppBar", "Route not found")
        }
    }
}