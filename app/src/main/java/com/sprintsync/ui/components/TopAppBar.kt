package com.sprintsync.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun TopAppBar(
	route: String,
	navController: NavController? = null
) {
	when (route) {
		"password_reset" -> TwoIconTopAppBar(title = "", navigationOnclick = { navController?.popBackStack() })
		"homepage"       -> AvatarTopAppBar(title = "Home")
		"project_list"   -> AvatarTopAppBar(title = "Project")
		"detail_project" -> TwoIconTopAppBar(title = "Project", navigationOnclick = { navController?.popBackStack() })
		"board"          -> TwoIconTopAppBar(title = "Board", navigationOnclick = { navController?.popBackStack() })
		"backlog"        -> TwoIconTopAppBar(title = "Backlog", navigationOnclick = { navController?.popBackStack() })
		"timeline"       -> TwoIconTopAppBar(title = "Timeline", navigationOnclick = { navController?.popBackStack() })
		"tasks"          -> TwoIconTopAppBar(title = "Tasks", navigationOnclick = { navController?.popBackStack() })
		"files"          -> TwoIconTopAppBar(title = "Files", navigationOnclick = { navController?.popBackStack() })
		"people"         -> TwoIconTopAppBar(title = "Member", navigationOnclick = { navController?.popBackStack() })
		"report"         -> TwoIconTopAppBar(title = "Report", navigationOnclick = { navController?.popBackStack() })
		"team"           -> TwoIconTopAppBar(title = "Team", navigationOnclick = { navController?.popBackStack() })
		"calendar"       -> AvatarTopAppBar(title = "Calendar")
		"profile"        -> AvatarTopAppBar(title = "Profile")
	}
}