package com.sprintsync.ui.views.project_view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.R
import com.sprintsync.ui.components.CustomTextField
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.theme.Yellow80
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
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
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
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
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

@Composable
fun ProjectCard(
    modifier: Modifier = Modifier,
    index: Int = -1,
    project: ProjectViewViewModel.ProjectList,
    onChange: ((Int) -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        )
        {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF444444),
                        shape = RoundedCornerShape(size = 7.dp)
                    ), contentAlignment = Alignment.Center
            )
            {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "project avatar",
                    contentScale = ContentScale.Crop,
                )
            }

            Column(modifier = Modifier) {
                Text(
                    text = project.projectName,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = project.projectKey,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                    ), textAlign = TextAlign.Center
                )
            }
        }

        IconButton(onClick = {
            if (onChange != null) {
                onChange(index)
            }
        }) {
            if (project.isStarred) Icon(
                painter = painterResource(id = R.drawable.selected_star),
                contentDescription = "starred",
                tint = Yellow80
            )
            else Icon(
                painter = painterResource(id = R.drawable.unselected_star),
                contentDescription = "unstarred"
            )
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


