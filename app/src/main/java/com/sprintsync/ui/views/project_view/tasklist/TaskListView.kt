package com.sprintsync.ui.views.project_view.tasklist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.sprintsync.R
import com.sprintsync.data_classes.Sprint
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.StoryPoint
import com.sprintsync.ui.components.TaskProcess
import com.sprintsync.ui.components.TaskTag
import com.sprintsync.ui.components.backlog.TaskCard
import com.sprintsync.ui.components.backlog.TaskComposer
import com.sprintsync.ui.components.tasklist.TaskListSprintCard
import com.sprintsync.ui.theme.Cyan80
import com.sprintsync.ui.theme.Green80
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.Grey60
import com.sprintsync.ui.theme.InProgressStatus
import com.sprintsync.ui.theme.ProductivityStatus
import com.sprintsync.ui.theme.Purple20
import com.sprintsync.ui.theme.Purple80
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.ToDoStatus
import com.sprintsync.ui.theme.Yellow80
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.view_models.BacklogViewModel
import com.sprintsync.ui.view_models.TaskListViewModel
import kotlinx.coroutines.flow.StateFlow

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
    val data = listOf<Sprint>()
    SprintSyncTheme {
        TaskListView()
    }
}