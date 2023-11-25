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
import com.sprintsync.R
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.projectlist.ProjectCard
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.view_models.ProjectViewViewModel

@Composable
fun ProjectList(projectViewViewModel: ProjectViewViewModel) {
    var searchTerm by remember { mutableStateOf("") }

    Surface() {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = searchTerm,
                onValueChange = { searchTerm = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Find Projects") },
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.search), contentDescription = "search bar")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(size = 24.dp)
            )

            StarredProjectList(
                searchTerm = searchTerm,
                projects = projectViewViewModel.projectList
            ) {
                projectViewViewModel.updateProjectList(it)
            }

            AllProjectList(
                projects = projectViewViewModel.projectList,
                searchTerm = searchTerm,
            ) {
                projectViewViewModel.updateProjectList(it)
            }
        }
    }
}


@Composable
fun StarredProjectList(
    searchTerm: String = "",
    projects: List<ProjectViewViewModel.ProjectList>,
    onChange: ((Int) -> Unit)? = null
) {
    val currentStarredList = projects.none { project -> project.isStarred }
    if (!currentStarredList) Surface() {
        Column(
            modifier = Modifier.animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Starred")
            projects.forEachIndexed() { index, project ->
                if (project.projectName.contains(
                        searchTerm,
                        true
                    ) && project.isStarred
                ) ProjectCard(project = project, index = index) {
                    if (onChange != null) {
                        onChange(it)
                    }
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
    onChange: ((Int) -> Unit)? = null
) {
    Surface() {
        Column(
            modifier = Modifier.animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "All projects")
            Column(
                modifier = modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                projects.forEachIndexed() { index, project ->
                    if (project.projectName.contains(
                            searchTerm,
                            true
                        )
                    ) ProjectCard(project = project, index = index) {
                        if (onChange != null) {
                            onChange(it)
                        }
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


