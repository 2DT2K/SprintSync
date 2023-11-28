package com.sprintsync.ui.views.project_view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.data.view_models.ProjectViewViewModel
import androidx.navigation.NavController
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.projectlist.ProjectCard
import com.sprintsync.ui.navigation.Screens
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing

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
                onClick = {navController?.navigate(Screens.DetailProject.route)}
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
                    )
                ) ProjectCard(
                    project = project,
                    index = index,
                    onClick = {navController?.navigate(Screens.DetailProject.route)}
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


