package com.sprintsync.ui.views.project_view.tasklist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.tasklist.TaskListSprintCard
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.view_models.TaskListViewModel

@Composable
fun TaskListView(navController: NavController? = null) {
    val taskListViewModel = TaskListViewModel()
    val taskListState by taskListViewModel.uiState.collectAsState()

    Surface {
        Column {
            SearchBar(placeHolder = "Search a task")
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            taskListState.forEach() {
                TaskListSprintCard(it, navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListViewPreview() {
    SprintSyncTheme {
        TaskListView()
    }
}