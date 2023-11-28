package com.sprintsync.ui.navigation

sealed class Screens (val route: String) {
//    Authentication Route
    data object AuthRoute : Screens("auth")
    data object Signin : Screens("sign_in")
    data object Signup : Screens("sign_up")
    data object PasswordReset : Screens("password_reset")
    data object VerifyAccount : Screens("verify_account")
//    Homepage Route
    data object HomeRoute : Screens("home")
    data object Homepage : Screens("homepage")
//    Project Route
    data object ProjectRoute : Screens("project")
    data object Project : Screens("project_list")
    data object DetailProject : Screens("detail_project")
//    DetailsProject Route
    data object DetailsProjectRoute : Screens("details_project")
    data object Board : Screens("board")
    data object Backlog : Screens("backlog")
    data object Timeline : Screens("timeline")
    data object Tasks : Screens("tasks")
    data object Task : Screens("task")
    data object Files : Screens("files")
    data object Members : Screens("members")
    data object Reports : Screens("report")

    data object Calendar : Screens("calendar")
    data object Profile : Screens("profile")
}