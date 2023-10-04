package com.sprintsync.ui.views.project_view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.R

data class ProjectList(
    val avatar: String,
    val projectName: String,
    val projectKey: String,
    var isStarred: Boolean
)

val projectList = listOf(
    ProjectList("Student 1", "khoidz", "khoidz", true),
    ProjectList("Student 1", "khoidz", "khoidz", true),
    ProjectList("Student 1", "khoidz", "khoidz", false),
    ProjectList("Student 1", "khoidz", "khoidz", false),
    ProjectList("Student 1", "khoidz", "khoidz", true),
    ProjectList("Student 1", "khoidz", "khoidz", true),
    ProjectList("Student 1", "khoidz", "khoidz", true),
    ProjectList("Student 1", "khoidz", "khoidz", true),


    )

@Composable
fun ProjectList(modifier: Modifier = Modifier) {
    var searchTerm by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Surface() {
        Column(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            StarredProjectList(projects = projectList)
            AllProjectList(projects = projectList)
        }
    }
}

@Composable
fun StarredProjectList(modifier: Modifier = Modifier, projects: List<ProjectList>) {
    Surface() {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = "Starred")
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
               projects.forEach() { project ->
                    if (project.isStarred) projectCard(project = project)
                }
            }
        }
    }
}

@Composable
fun AllProjectList(modifier: Modifier = Modifier, projects: List<ProjectList>) {
    Surface() {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = "All projects")
            Column(
                modifier = modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                projects.forEach() { project ->
                    projectCard(project = project)
                }
            }
        }
    }

}

@Composable
fun projectCard(modifier: Modifier = Modifier, project: ProjectList) {
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
        }) {
            if (isStarred) Icon(
                painter = painterResource(id = R.drawable.selected_star),
                contentDescription = "starred"
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


