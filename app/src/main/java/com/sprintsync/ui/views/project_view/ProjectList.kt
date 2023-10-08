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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.sprintsync.ui.theme.Yellow40

data class ProjectList(
    val avatar: String,
    val projectName: String,
    val projectKey: String,
    var isStarred: Boolean
)

val projectList = listOf(
    ProjectList("Student 1", "khoidz2", "khoidz", true),
    ProjectList("Student 1", "khoidz1", "khoidz", true),
    ProjectList("Student 1", "khoidz2", "khoidz", false),
    ProjectList("Student 1", "khoidz3", "khoidz", false),
    ProjectList("Student 1", "khoidz4", "khoidz", true),
    ProjectList("Student 1", "khoidz5", "khoidz", true),
    ProjectList("Student 1", "khoidz6", "khoidz", true),
    ProjectList("Student 1", "khoi2dz2", "khoidz", true),
)

@Composable
fun ProjectList(modifier: Modifier = Modifier) {
    var searchTerm by remember { mutableStateOf("") }
    var projects by remember { mutableStateOf(projectList) }
    var index by rememberSaveable {
        mutableStateOf(-1)
    }

    Surface() {
        Column(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "",
                onValueChange = {
                    searchTerm = it
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                )
            )
            StarredProjectList(
                projects = projects,
                searchTerm = searchTerm
            ) {
                index = it
                projectList[it].copy(isStarred = !projects[it].isStarred)
                projects = projectList
            }
            AllProjectList(
                projects = projects,
                searchTerm = searchTerm
            ) {
                index = it
                projectList[it].copy(isStarred = !projects[it].isStarred)
                projects = projectList
            }
        }
    }
}

@Composable
fun StarredProjectList(
    modifier: Modifier = Modifier,
    searchTerm: String = "",
    projects: List<ProjectList>,
    onChange: ((Int) -> Unit)? = null
) {
    Surface() {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = "Starred")
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                projects.forEachIndexed() { index, project ->
                    val isStarred by remember {
                        mutableStateOf(project.isStarred)
                    }
                    if (isStarred && project.projectName.contains(
                            searchTerm,
                            true
                        )
                    ) projectCard(project = project, index = index, onChange = {
                        if (onChange != null) {
                            onChange(it)
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun AllProjectList(
    modifier: Modifier = Modifier,
    searchTerm: String = "",
    projects: List<ProjectList>,
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
                    ) projectCard(project = project, index = index, onChange = {
                        if (onChange != null) {
                            onChange(it)
                        }
                    })
                }
            }
        }
    }

}

@Composable
fun projectCard(
    modifier: Modifier = Modifier,
    index: Int = -1,
    project: ProjectList,
    onChange: ((Int) -> Unit)? = null
) {
    var isStarred by remember { mutableStateOf(project.isStarred) }

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
            project.isStarred = !project.isStarred
            isStarred = !isStarred
            if (onChange != null) {
                onChange(index)
            }
        }) {
            if (isStarred) Icon(
                painter = painterResource(id = R.drawable.selected_star),
                contentDescription = "starred",
                tint = Yellow40
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
        ProjectList(Modifier)
    }
}


