@file:Suppress("UNUSED_EXPRESSION")

package com.sprintsync.ui.views.project_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.view_models.BacklogViewModel
import com.sprintsync.ui.views.BoardView
import com.sprintsync.ui.views.ReportView
import com.sprintsync.ui.views.TaskView
import com.sprintsync.ui.views.project_view.backlog.Backlog
import com.sprintsync.ui.views.project_view.file_view.FileView
import com.sprintsync.ui.views.project_view.member.Member

data class GridItem(val id: Int, val text: String)

@Composable
fun DetailProject() {
    val navController = rememberNavController()

    val gridItems = listOf(
        GridItem(R.drawable.dashboard, "Board"),
        GridItem(R.drawable.backlog, "BackLog"),
        GridItem(R.drawable.timelapse, "Timeline"),
        GridItem(R.drawable.tasks, "Tasks"),
        GridItem(R.drawable.files, "Files"),
        GridItem(R.drawable.people, "People"),
        GridItem(R.drawable.team, "Team"),
        GridItem(R.drawable.report, "Report"),
    )

    val backlogViewModel = BacklogViewModel("")

    NavHost(navController = navController, startDestination = "Project") {
        composable("Project") { ProjectView(gridItems, navController) }
        composable("Board") { BoardView() }
        composable("Backlog") { Backlog(backlogViewModel) }
        composable("Files") { FileView() }
        composable("People") { Member() }
        composable("Report") { ReportView() }
        composable("Timeline") {
            //TODO: CAN NOT DO THIS
        }
        composable("tasks") {
            //TODO: KHOI IS COOKING
        }
        composable("team") {
            //TODO: IS DOING
        }
    }
}

@Composable
fun ProjectView(gridItems: List<GridItem>, navController: NavController? = null) {
    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraLarge)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(
                        width = 1.5.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(size = 8.dp)
                    ), contentAlignment = Alignment.Center
            )
            {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.nice_avatar),
                    contentDescription = "project avatar",
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                text = "SprintSync",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search in this project") },
            leadingIcon = {
                Icon(painterResource(id = R.drawable.search), contentDescription = "search bar")
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(size = 20.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
        ) {
            items(gridItems) {
                CustomButton(
                    surfaceModifier = Modifier.height(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    onClick = {
                        navController?.navigate(it.text)
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = it.id),
                            contentDescription = it.text,
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = it.text,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailProjectPreview() {
    SprintSyncTheme {
        DetailProject()
    }
}