package com.sprintsync.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun TopAppBar(
    route: String,
    navController: NavController? = null
) {
    when (route) {
        "homepage"       -> {
            AvatarTopAppBar(title = "Home")
        }

        "project_list"   -> {
            AvatarTopAppBar(title = "Project")
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
            AvatarTopAppBar(title = "Calendar")
        }

        "profile"        -> {
            AvatarTopAppBar(title = "Profile", turnAvatar = false)
        }

        "task/{taskId}"  -> {
            TwoIconTopAppBar(
                title = "Task $",
                navigationOnclick = { navController?.popBackStack()
            })
        }

        else             -> {
            Log.d("TopAppBar", "Route not found")
        }
    }
}