package com.sprintsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sprintsync.data.auth.Authenticator
import com.sprintsync.data.view_models.MemberViewModel
import com.sprintsync.data.view_models.ProjectViewModel
import com.sprintsync.data.view_models.ProjectViewViewModel
import com.sprintsync.data.view_models.TaskViewModel
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.components.TopAppBar
import com.sprintsync.ui.components.fileview.AddFileFAB
import com.sprintsync.ui.components.projectlist.AddProjectFAB
import com.sprintsync.ui.navigation.Screens
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.views.BoardView
import com.sprintsync.ui.views.CalendarView
import com.sprintsync.ui.views.HomePage
import com.sprintsync.ui.views.ReportView
import com.sprintsync.ui.views.TaskView
import com.sprintsync.ui.views.auth.PasswordResetView
import com.sprintsync.ui.views.auth.SignInView
import com.sprintsync.ui.views.auth.SignUpView
import com.sprintsync.ui.views.auth.VerifyAccount
import com.sprintsync.ui.views.fakeTask
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
        installSplashScreen()
        setContent {
            SprintSyncTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val projectVM = hiltViewModel<ProjectViewModel>()
    val memberVM = hiltViewModel<MemberViewModel>()
    val projectState by projectVM.state.collectAsStateWithLifecycle()
    val memberState by memberVM.state.collectAsStateWithLifecycle()
    val memberList = memberState.dtoList
    val chosenProject = projectState.dto

    val navController = rememberNavController()

    var showBottomAndTopBar by remember { mutableStateOf(false) }
    var showFAB by remember { mutableStateOf(false) }
    var route by remember { mutableStateOf("") }

    navController.addOnDestinationChangedListener { _, dest, _ ->
        showBottomAndTopBar =
            dest.route !in listOf("sign_in", "sign_up", "password_reset", "verify_account")
        route = dest.route ?: ""
        showFAB = dest.route in listOf("project_list", "files", "tasks", "people", "team")
    }

    Scaffold(
        topBar = {
            if (showBottomAndTopBar) {
                TopAppBar(route, navController)
            }
        },
        bottomBar = {
            if (showBottomAndTopBar) BottomNavigation(navController)
        },
        floatingActionButton = {
            if (showFAB) {
                if (route == "files") AddFileFAB() else AddProjectFAB()
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                ),
        ) {
            NavHost(
                navController = navController,
                startDestination = if (Authenticator.signedIn) Screens.Homepage.route else Screens.Signin.route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                composable(Screens.Signin.route) { SignInView(navController) }
                composable(Screens.Signup.route) { SignUpView(navController) }
                composable(Screens.PasswordReset.route) { PasswordResetView(navController) }
                composable(Screens.VerifyAccount.route) { VerifyAccount(navController) }
                composable(
                    Screens.Homepage.route,
                    enterTransition = {
                        return@composable fadeIn(tween(500))
                    },
                    exitTransition = {
                        return@composable fadeOut(tween(500))
                    },
                    popEnterTransition = {
                        return@composable scaleIn(tween(500))
                    },
                    popExitTransition = {
                        return@composable fadeOut(tween(500))
                    },
                ) { HomePage(navController) }
                navigation(
                    startDestination = Screens.Project.route,
                    route = Screens.ProjectRoute.route,
                ) {
                    composable(
                        Screens.Project.route,
                        enterTransition = {
                            return@composable fadeIn(tween(500))
                        },
                        exitTransition = {
                            return@composable fadeOut(tween(500))
                        },
                        popEnterTransition = {
                            return@composable scaleIn(tween(500))
                        },
                        popExitTransition = {
                            return@composable fadeOut(tween(500))
                        },
                    ) {
                        ProjectList(
                            navController, projectState.dtoList ?: emptyList(),
                            getMyProjects = { projectVM.getMyProjects() },
                            choseProject = { projectVM.choseProject(it) }
                        )
                    }
                    navigation(
                        startDestination = Screens.DetailProject.route,
                        route = Screens.DetailsProjectRoute.route
                    ) {
                        composable(
                            Screens.DetailProject.route,
                            enterTransition = {
                                return@composable fadeIn(tween(500))
                            },
                            exitTransition = {
                                return@composable fadeOut(tween(500))
                            },
                            popEnterTransition = {
                                return@composable slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(500)
                                )
                            },
                        ) { DetailProject(navController) }
                        composable(
                            Screens.Board.route,
                            enterTransition = {
                                return@composable fadeIn(tween(500))
                            },
                            exitTransition = {
                                return@composable fadeOut(tween(500))
                            },
                            popExitTransition = {
                                return@composable slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(500)
                                )
                            }
                        ) { BoardView(chosenProject?.id, chosenProject?.labels) }
                        composable(
                            Screens.Backlog.route,
                            enterTransition = {
                                return@composable fadeIn(tween(500))
                            },
                            exitTransition = {
                                return@composable fadeOut(tween(500))
                            },
                            popExitTransition = {
                                return@composable slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(500)
                                )
                            }
                        ) { Backlog(projectState.dto?.id) }
                        composable(
                            Screens.Files.route,
                            enterTransition = {
                                return@composable fadeIn(tween(500))
                            },
                            exitTransition = {
                                return@composable fadeOut(tween(500))
                            },
                            popExitTransition = {
                                return@composable slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(500)
                                )
                            }
                        ) { FileView() }
                        composable(
                            Screens.Tasks.route,
                            enterTransition = {
                                return@composable fadeIn(tween(500))
                            },
                            exitTransition = {
                                return@composable fadeOut(tween(500))
                            },
                            popExitTransition = {
                                return@composable slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(500)
                                )
                            }
                        ) { TaskListView(chosenProject?.id, navController) }
                        composable(
                            Screens.Task.route,
                            enterTransition = {
                                return@composable fadeIn(tween(500))
                            },
                            exitTransition = {
                                return@composable fadeOut(tween(500))
                            },
                            popExitTransition = {
                                return@composable slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(500)
                                )
                            }
                        ) {
                            it.arguments?.getString("taskId")
                                ?.let { it1 ->
                                    projectState.dto?.let { it2 ->
                                        TaskView(
                                            it1,
                                            it2.statuses
                                        )
                                    }
                                }
                        }
                        composable(
                            Screens.Members.route,
                            enterTransition = {
                                return@composable fadeIn(tween(500))
                            },
                            exitTransition = {
                                return@composable fadeOut(tween(500))
                            },
                            popExitTransition = {
                                return@composable slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(500)
                                )
                            }
                        ) { Member(chosenProject?.id) }
                        composable(
                            Screens.Reports.route,
                            enterTransition = {
                                return@composable fadeIn(tween(500))
                            },
                            exitTransition = {
                                return@composable fadeOut(tween(500))
                            },
                            popExitTransition = {
                                return@composable slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(500)
                                )
                            }
                        ) { ReportView(chosenProject?.id, chosenProject?.statuses) }
                        composable(
                            Screens.Timeline.route,
                            enterTransition = {
                                return@composable fadeIn(tween(500))
                            },
                            exitTransition = {
                                return@composable fadeOut(tween(500))
                            },
                            popExitTransition = {
                                return@composable slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(500)
                                )
                            }
                        ) {
                            //TODO: CAN NOT DO THIS
                        }
                    }
                }
                composable(
                    Screens.Calendar.route,
                    enterTransition = {
                        return@composable fadeIn(tween(500))
                    },
                    exitTransition = {
                        return@composable fadeOut(tween(500))
                    },
                    popEnterTransition = {
                        return@composable scaleIn(tween(500))
                    },
                ) { CalendarView(navController, chosenProject?.statuses) }
                composable(
                    Screens.Profile.route,
                    enterTransition = {
                        return@composable fadeIn(tween(500))
                    },
                    exitTransition = {
                        return@composable fadeOut(tween(500))
                    },
                    popEnterTransition = {
                        return@composable scaleIn(tween(500))
                    },
                ) { ProfileScreen(navController) }
            }

        }
    }
}