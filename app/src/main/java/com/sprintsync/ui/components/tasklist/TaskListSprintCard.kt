package com.sprintsync.ui.components.tasklist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.view_models.TaskViewModel
import com.sprintsync.ui.theme.spacing


@Composable
fun TaskListSprintCard(sprint: SprintDto, navController: NavController? = null) {
    val taskVM = hiltViewModel<TaskViewModel>()
    val taskState by taskVM.state.collectAsStateWithLifecycle()
    var isOpen by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        sprint.id?.let {
            taskVM.getTasksOfSprint(it)
        }
    }

    Column(
        modifier = Modifier
            .animateContentSize()
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isOpen = !isOpen }
                .padding(vertical = MaterialTheme.spacing.default),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sprint ${sprint.sprintNumber}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(modifier = Modifier.weight(1.0f))

            val arrowIcon = if (isOpen) R.drawable.arrow_down_2 else R.drawable.arrow_up
            Icon(
                modifier = Modifier
                    .size(28.dp),
                painter = painterResource(id = arrowIcon),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = null
            )
        }

        AnimatedVisibility(visible = isOpen) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.default
                )
            ) {
                taskState.dtoList?.forEach {
                    TaskListCard(it) {
                        navController?.navigate("task")
                    }
                }
            }
        }
    }
}
