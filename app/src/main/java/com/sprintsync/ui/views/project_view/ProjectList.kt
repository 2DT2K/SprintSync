package com.sprintsync.ui.views.project_view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.project_list.ProjectCard
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.view_models.ProjectViewViewModel

@Composable
fun ProjectList(projectViewViewModel: ProjectViewViewModel) {
	var searchTerm by remember { mutableStateOf("") }

	Surface() {
		Column(
			modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .verticalScroll(rememberScrollState())
		) {
			SearchBar(placeHolder = "Find Projects", onValueChange = { searchTerm = it })
			Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
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
			verticalArrangement = Arrangement.spacedBy(16.dp)
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
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			Text(text = "All projects")
			Column(
				modifier = modifier.wrapContentHeight(),
				verticalArrangement = Arrangement.spacedBy(16.dp)
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


