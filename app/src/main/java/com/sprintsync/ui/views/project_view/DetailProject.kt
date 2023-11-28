package com.sprintsync.ui.views.project_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.ui.components.CustomButton
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.projectlist.ProjectAvatar
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing
import androidx.compose.ui.text.toLowerCase

data class GridItem(val id: Int, val text: String)

@Composable
fun DetailProject(navController: NavController? = null) {

    val gridItems = listOf(
        GridItem(R.drawable.dashboard, "Board"),
        GridItem(R.drawable.backlog, "Backlog"),
        GridItem(R.drawable.timelapse, "Timeline"),
        GridItem(R.drawable.tasks, "Tasks"),
        GridItem(R.drawable.files, "Files"),
        GridItem(R.drawable.people, "Members"),
        GridItem(R.drawable.report, "Report"),
    )

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
            ProjectAvatar()
            Text(
                text = "SprintSync",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        SearchBar(placeHolder = "Search in this project", onValueChange = { text = it })
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
                        navController?.navigate(it.text.toLowerCase(Locale.current))
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