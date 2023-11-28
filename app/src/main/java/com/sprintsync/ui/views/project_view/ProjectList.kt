package com.sprintsync.ui.views.project_view

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.data.view_models.ProjectViewViewModel
import androidx.navigation.NavController
import com.sprintsync.data.dtos.ProjectDto
import com.sprintsync.data.view_models.ProjectViewModel
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.projectlist.ProjectCard
import com.sprintsync.ui.navigation.Screens
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing

@Composable
fun ProjectList(
    navController: NavController? = null,
    projectList: List<ProjectDto>,
    getMyProjects: () -> Unit,
    choseProject: (project: ProjectDto) -> Unit
) {
    var searchTerm by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        getMyProjects()
    }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        SearchBar(placeHolder = "Find Projects", onValueChange = { searchTerm = it })
        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)) {
            projectList.let { project ->
                StarredProjectList(
                    searchTerm = searchTerm,
                    projects = project.toList(),
                    onChange = {

                    },
                    onChooseProject = {
                        Log.d("log-bug", it.toString())
                        choseProject(it)
                        navController?.navigate(Screens.DetailProject.route)
                    }
                )
                AllProjectList(
                    projects = projectList,
                    searchTerm = searchTerm,
                    onChange = {

                    },
                    onChooseProject = {
                        Log.d("log-bug", it.toString())
                        choseProject(it)
                        navController?.navigate(Screens.DetailProject.route)
                    }
                )
            }
        }
    }
}


@Composable
fun StarredProjectList(
    searchTerm: String = "",
    projects: List<ProjectDto>,
    onChange: ((Int) -> Unit)? = null,
    onChooseProject: (project: ProjectDto) -> Unit
) {
    //TODO: get the star project later
    val currentStarredList = projects.none { project -> true }
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
            if (project.name.contains(
                    searchTerm,
                    true
                )
            //&& project.isStarred
            ) ProjectCard(
                project = project,
                index = index,
                onClick = { onChooseProject(project)  }
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
    projects: List<ProjectDto>?,
    onChange: ((Int) -> Unit)? = null,
    onChooseProject: (project: ProjectDto) -> Unit
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
            projects?.forEachIndexed() { index, project ->
                if (project.name.contains(
                        searchTerm,
                        true
                    )
                //&& !project.isStarred
                ) ProjectCard(
                    project = project,
                    index = index,
                    onClick = { onChooseProject(project) }
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

    }
}


