package com.sprintsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.api.client.json.gson.GsonFactory
import com.sprintsync.data.auth.Authenticator
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.components.Calendar
import com.sprintsync.ui.components.TopAppBar
import com.sprintsync.ui.navigation.Screens
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.view_models.BacklogViewModel
import com.sprintsync.ui.view_models.ProjectViewViewModel
import com.sprintsync.ui.views.BoardView
import com.sprintsync.ui.views.CalendarView
import com.sprintsync.ui.views.HomePage
import com.sprintsync.ui.views.ReportView
import com.sprintsync.ui.views.auth.PasswordResetView
import com.sprintsync.ui.views.auth.SignInView
import com.sprintsync.ui.views.auth.SignUpView
import com.sprintsync.ui.views.auth.VerifyAccount
import com.sprintsync.ui.views.profile.ProfileScreen
import com.sprintsync.ui.views.project_view.DetailProject
import com.sprintsync.ui.views.project_view.ProjectList
import com.sprintsync.ui.views.project_view.backlog.Backlog
import com.sprintsync.ui.views.project_view.file_view.FileView
import com.sprintsync.ui.views.project_view.member.Member
import com.sprintsync.ui.views.project_view.tasklist.TaskListView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContent {
            SprintSyncTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()

    var showBottomAndTopBar by remember { mutableStateOf(false) }
    var route by remember { mutableStateOf("") }

    navController.addOnDestinationChangedListener { _, dest, _ ->
        showBottomAndTopBar =
            dest.route !in listOf("sign_in", "sign_up", "password_reset", "verify_account")
        route = dest.route?:""
    }

    Scaffold(
        topBar = { if (showBottomAndTopBar) TopAppBar(route, navController) },
        bottomBar = { if (showBottomAndTopBar) BottomNavigation(navController) }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = if (Authenticator.signedIn) Screens.HomeRoute.route else Screens.Signin.route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                composable(Screens.Signin.route) { SignInView(navController) }
                composable(Screens.Signup.route) { SignUpView(navController) }
                composable(Screens.PasswordReset.route) { PasswordResetView(navController) }
                composable(Screens.VerifyAccount.route) { VerifyAccount(navController) }
                navigation(
                    startDestination = Screens.Homepage.route,
                    route = Screens.HomeRoute.route
                ) {
                    composable(
                        Screens.Homepage.route,
                        enterTransition = {
                            when (initialState.destination.route) {
                                Screens.Project.route  -> slideInHorizontally(
                                    initialOffsetX = { -it },
                                    animationSpec = tween(100)
                                )

                                Screens.Calendar.route -> slideInHorizontally(
                                    initialOffsetX = { -it * 2 },
                                    animationSpec = tween(100)
                                )

                                Screens.Profile.route  -> slideInHorizontally(
                                    initialOffsetX = { -it * 3 },
                                    animationSpec = tween(100)
                                )

                                else                   -> null
                            }
                        },
                        exitTransition = {
                            when (targetState.destination.route) {
                                Screens.Project.route  -> slideOutHorizontally(
                                    targetOffsetX = { -it },
                                    animationSpec = tween(100)
                                )

                                Screens.Calendar.route -> slideOutHorizontally(
                                    targetOffsetX = { -it * 2 },
                                    animationSpec = tween(100)
                                )

                                Screens.Profile.route  -> slideOutHorizontally(
                                    targetOffsetX = { -it * 3 },
                                    animationSpec = tween(100)
                                )

                                else                   -> null
                            }
                        }
                    ) { HomePage() }
                    navigation(
                        startDestination = Screens.Project.route,
                        route = Screens.ProjectRoute.route
                    ) {
                        composable(
                            Screens.Project.route,
                            enterTransition = {
                                when (initialState.destination.route) {
                                    Screens.Homepage.route -> slideInHorizontally(
                                        initialOffsetX = { it },
                                        animationSpec = tween(100)
                                    )

                                    Screens.Calendar.route -> slideInHorizontally(
                                        initialOffsetX = { -it },
                                        animationSpec = tween(100)
                                    )

                                    Screens.Profile.route  -> slideInHorizontally(
                                        initialOffsetX = { -it * 2 },
                                        animationSpec = tween(100)
                                    )

                                    else                   -> null
                                }
                            },
                            exitTransition = {
                                when (targetState.destination.route) {
                                    Screens.Homepage.route -> slideOutHorizontally(
                                        targetOffsetX = { it },
                                        animationSpec = tween(100)
                                    )

                                    Screens.Calendar.route -> slideOutHorizontally(
                                        targetOffsetX = { -it },
                                        animationSpec = tween(100)
                                    )

                                    Screens.Profile.route  -> slideOutHorizontally(
                                        targetOffsetX = { -it * 2 },
                                        animationSpec = tween(100)
                                    )

                                    else                   -> null
                                }
                            }
                        ) { ProjectList(ProjectViewViewModel(), navController) }
                        navigation(
                            startDestination = Screens.DetailProject.route,
                            route = Screens.DetailsProjectRoute.route
                        ) {
                            composable(Screens.DetailProject.route) { DetailProject(navController) }
                            composable(Screens.Board.route) { BoardView() }
                            composable(Screens.Backlog.route) { Backlog(BacklogViewModel("")) }
                            composable(Screens.Files.route) { FileView() }
                            composable(Screens.Tasks.route) { TaskListView() }
                            composable(Screens.People.route) { Member() }
                            composable(Screens.Reports.route) { ReportView() }
                            composable(Screens.Timeline.route) {
                                //TODO: CAN NOT DO THIS
                            }
                            composable(Screens.Team.route) {
                                //TODO: IS DOING
                            }
                        }
                    }
                    composable(
                        Screens.Calendar.route,
                        enterTransition = {
                            when (initialState.destination.route) {
                                Screens.Homepage.route -> slideInHorizontally(
                                    initialOffsetX = { it * 2 },
                                    animationSpec = tween(100)
                                )

                                Screens.Project.route  -> slideInHorizontally(
                                    initialOffsetX = { it },
                                    animationSpec = tween(100)
                                )

                                Screens.Profile.route  -> slideInHorizontally(
                                    initialOffsetX = { -it },
                                    animationSpec = tween(100)
                                )

                                else                   -> null
                            }
                        },
                        exitTransition = {
                            when (targetState.destination.route) {
                                Screens.Homepage.route -> slideOutHorizontally(
                                    targetOffsetX = { it * 2 },
                                    animationSpec = tween(100)
                                )

                                Screens.Project.route  -> slideOutHorizontally(
                                    targetOffsetX = { it },
                                    animationSpec = tween(100)
                                )

                                Screens.Profile.route  -> slideOutHorizontally(
                                    targetOffsetX = { -it },
                                    animationSpec = tween(100)
                                )

                                else                   -> null
                            }
                        }
                    ) { CalendarView(navController) }
                    composable(
                        Screens.Profile.route,
                        enterTransition = {
                            when (initialState.destination.route) {
                                Screens.Homepage.route -> slideInHorizontally(
                                    initialOffsetX = { it * 3 },
                                    animationSpec = tween(100)
                                )

                                Screens.Project.route  -> slideInHorizontally(
                                    initialOffsetX = { it * 2 },
                                    animationSpec = tween(100)
                                )

                                Screens.Calendar.route -> slideInHorizontally(
                                    initialOffsetX = { it },
                                    animationSpec = tween(100)
                                )

                                else       -> null
                            }
                        },
                        exitTransition = {
                            when (targetState.destination.route) {
                                Screens.Homepage.route -> slideOutHorizontally(
                                    targetOffsetX = { it * 3 },
                                    animationSpec = tween(100)
                                )

                                Screens.Project.route  -> slideOutHorizontally(
                                    targetOffsetX = { it * 3 },
                                    animationSpec = tween(100)
                                )

                                Screens.Calendar.route -> slideOutHorizontally(
                                    targetOffsetX = { it },
                                    animationSpec = tween(100)
                                )

                                else       -> null
                            }
                        }
                    ) { ProfileScreen(navController) }
                }
            }
        }
    }
}