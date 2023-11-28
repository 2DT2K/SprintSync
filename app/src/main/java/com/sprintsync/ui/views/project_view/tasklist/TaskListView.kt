package com.sprintsync.ui.views.project_view.tasklist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sprintsync.data.view_models.SprintViewModel
import com.sprintsync.ui.components.SearchBar
import com.sprintsync.ui.components.tasklist.TaskListSprintCard
import com.sprintsync.ui.theme.SprintSyncTheme
import com.sprintsync.ui.theme.spacing

@Composable
fun TaskListView(projectID: String?, navController: NavController? = null) {
    val sprintVM = hiltViewModel<SprintViewModel>()
    val sprintState by sprintVM.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (projectID != null) {
            sprintVM.getSprintsOfProject(projectID)
        }
    }

    Surface {
        Column {
            SearchBar(placeHolder = "Search a task")
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            sprintState.dtoList?.forEach() {
                TaskListSprintCard(it, navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListViewPreview() {
    SprintSyncTheme {

    }
}