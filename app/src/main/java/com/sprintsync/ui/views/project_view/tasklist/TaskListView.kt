package com.sprintsync.ui.views.project_view.tasklist

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.tasklist.TaskListSprintCard
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.view_models.TaskListViewModel

@Composable
fun TaskListView() {
    val taskListViewModel = TaskListViewModel()
    val taskListState by taskListViewModel.uiState.collectAsState()

    Surface {
        Column {
            SearchBar(placeHolder = "Search a task")

            taskListState.forEach() {
                TaskListSprintCard(it)
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