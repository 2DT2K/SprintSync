package com.sprintsync.ui.views.project_view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sprintsync.R
import com.sprintsync.data_classes.Project
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.project_list.ProjectCard
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.view_models.ProjectViewViewModel
import com.sprintsync.ui.views.BoardView
import com.sprintsync.ui.views.ReportView
import com.sprintsync.ui.views.project_view.backlog.Backlog
import com.sprintsync.ui.views.project_view.file_view.FileView
import com.sprintsync.ui.views.project_view.member.Member

@Composable
fun ProjectList(projectViewViewModel: ProjectViewViewModel, navController: NavController? = null) {
    var searchTerm by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        SearchBar(placeHolder = "Find Projects", onValueChange = { searchTerm = it })
        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)) {
            StarredProjectList(
                searchTerm = searchTerm,
                projects = projectViewViewModel.projectList,
                onChange = {
                    projectViewViewModel.updateProjectList(it)
                },
                navController = navController
            )
            AllProjectList(
                projects = projectViewViewModel.projectList,
                searchTerm = searchTerm,
                onChange = {
                    projectViewViewModel.updateProjectList(it)
                },
                navController = navController
            )
        }
    }
}


@Composable
fun StarredProjectList(
    searchTerm: String = "",
    projects: List<ProjectViewViewModel.ProjectList>,
    onChange: ((Int) -> Unit)? = null,
    navController: NavController? = null
) {
    val currentStarredList = projects.none { project -> project.isStarred }
    if (!currentStarredList) Column(
        modifier = Modifier.animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = "Starred",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        projects.forEachIndexed() { index, project ->
            if (project.projectName.contains(
                    searchTerm,
                    true
                ) && project.isStarred
            ) ProjectCard(
                project = project,
                index = index,
                onClick = {navController?.navigate("detail_project")}
            ) {
                if (onChange != null) {
                    onChange(it)
                }
            }
        }
    }
}

@Composable
fun AllProjectList(
    modifier: Modifier = Modifier,
    searchTerm: String = "",
    projects: List<ProjectViewViewModel.ProjectList>,
    onChange: ((Int) -> Unit)? = null,
    navController: NavController? = null
) {
    Column(
        modifier = Modifier.animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = "All projects",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Column(
            modifier = modifier.wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            projects.forEachIndexed() { index, project ->
                if (project.projectName.contains(
                        searchTerm,
                        true
                    ) && !project.isStarred
                ) ProjectCard(
                    project = project,
                    index = index,
                    onClick = {navController?.navigate("detail_project")}
                ) {
                    if (onChange != null) {
                        onChange(it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProjectListPreview() {
    SprintSyncTheme {
        ProjectList(ProjectViewViewModel())
    }
}


