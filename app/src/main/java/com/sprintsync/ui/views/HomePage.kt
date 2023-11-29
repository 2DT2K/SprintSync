package com.sprintsync.ui.views


import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sprintsync.data.view_models.MemberViewModel
import androidx.navigation.NavController
import com.sprintsync.data.dtos.ProjectDto
import com.sprintsync.ui.components.homepage.HomePageIssue
import com.sprintsync.ui.components.homepage.HomePageViews
import com.sprintsync.ui.theme.spacing

@Composable
fun HomePage(
    navController: NavController? = null,
    projectList: List<ProjectDto>,
    getMyProjects: () -> Unit,
) {
    val context = LocalContext.current
    val memberVM = hiltViewModel<MemberViewModel>()
    LaunchedEffect(Unit) {
        val sharedPref = context.getSharedPreferences("device", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null) ?: return@LaunchedEffect

        memberVM.addDevice(token)
        val editor = sharedPref.edit()
        editor.remove("token")
        editor.apply()
    }
    Surface {
        Column(
            Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.large,
                Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HomePageViews(navController)
            HomePageIssue(navController, projectList, getMyProjects)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomePagePreview() {
//    HomePage()
//}