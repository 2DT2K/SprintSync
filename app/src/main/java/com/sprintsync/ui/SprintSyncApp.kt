package com.sprintsync.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sprintsync.ui.view_models.ProjectViewViewModel
import com.sprintsync.ui.views.auth.LogInScreen
import com.sprintsync.ui.views.auth.SignUpScreen
import com.sprintsync.ui.views.project_view.DetailProject
import com.sprintsync.ui.views.project_view.ProjectList

@Composable
fun SprintSyncApp() {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            Modifier.padding(innerPadding)
        ) {
            loginGraph(navController = navController)
            projectGraph(navController = navController)
            composable("calendar") {

            }
            profileGraph(navController = navController)
        }
    }

}

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(startDestination = "username", route = "login") {
        composable("username") {
            LogInScreen()

        }
        composable("registration") {
            SignUpScreen()
        }
    }
}

fun NavGraphBuilder.projectGraph(navController: NavController) {
    navigation(startDestination = "list", route = "projects") {
        composable("list") {
            ProjectList(projectViewViewModel = ProjectViewViewModel())
        }
        composable("detail/$id") {
            DetailProject()
        }
        composable("board") {

        }
        composable("tasks") {

        }
        composable("backlog") {

        }
        composable("file") {

        }
        composable("member") {

        }
        composable("timeline") {

        }
        composable("report") {

        }
    }
}

fun NavGraphBuilder.profileGraph(navController: NavController) {
    navigation(startDestination = "info", route = "profile") {

    }
}