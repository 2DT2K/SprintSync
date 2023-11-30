package com.sprintsync.ui.components.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.data.dtos.ProjectDto
import com.sprintsync.data.view_models.TaskViewModel
import com.sprintsync.ui.components.IssueItem
import com.sprintsync.ui.theme.spacing
import java.time.LocalDateTime

data class MenuIssue(
    val issueType: String,
    val issueName: String,
    val issueTime: String
)

@Composable
fun HomePageIssue(
    navController: NavController? = null,
    projectList: List<ProjectDto>,
    getMyProjects: () -> Unit
) {
    val taskVM = hiltViewModel<TaskViewModel>()
    val taskState by taskVM.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        getMyProjects()
        taskVM.getMyTasks()
    }


    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.default,
            Alignment.Top
        ),
        horizontalAlignment = Alignment.Start,

        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Recent Issues",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(8.dp)
                )
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(36.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.three_dot),
                        contentDescription = ""
                    )
                }
            }

        }
        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .heightIn(min = 360.dp, max = 400.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(MaterialTheme.spacing.default)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.small,
                Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            taskState.dtoList?.forEachIndexed { index, task ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    task.deadline?.let {
                        IssueItem(
                            issueType = "Task",
                            issueDescription = task.name,
                            issueTimeLine = it,
                            onClick = {
                                navController?.navigate("task")
                            }
                        )
                    }
                    if (taskState.dtoList!!.size - 1 >= 1 &&
                        task != taskState.dtoList!![taskState.dtoList!!.size - 1]
                    ) {
                        Divider(modifier = Modifier.fillMaxWidth(0.9f))
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MenuIssuePreview() {
//    HomePageIssue()
//}