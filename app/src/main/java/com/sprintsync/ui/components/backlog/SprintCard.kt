package com.sprintsync.ui.components.backlog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sprintsync.R
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.components.TaskPoint
import com.sprintsync.ui.theme.DonePoint
import com.sprintsync.ui.theme.Grey40
import com.sprintsync.ui.theme.InProgressPoint
import com.sprintsync.ui.theme.TodoPoint
import com.sprintsync.ui.theme.spacing
import java.time.LocalDateTime

@Composable
fun SprintCard(
    sprint: SprintDto,
    taskList: List<TaskResDto>,
    isActive: Boolean = false,
    onAddTask: (TaskDto) -> Unit,
    updateSprint: (SprintDto) -> Unit = {},
    navController: NavController? = null
) {
    var isOpen by remember { mutableStateOf(false) }
    var isSprintStatusExpanded by remember { mutableStateOf(false) }

    val tasksOfThisSprint = taskList.filter { it.sprint == sprint.id }

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
            val arrowIcon = if (isOpen) R.drawable.arrow_down_2 else R.drawable.arrow_up
            Icon(
                modifier = Modifier
                    .size(28.dp),
                painter = painterResource(id = arrowIcon),
                tint = Grey40,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = "Sprint ${sprint.sprintNumber}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "Problems: ${tasksOfThisSprint.count()}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(modifier = Modifier.weight(1.0f))

            if (tasksOfThisSprint.isNotEmpty()) {
                TaskPoint(
                    point = tasksOfThisSprint.filter { it.statusIndex == 0 }.sumOf { it.point },
                    modifier = Modifier.background(
                        color = TodoPoint,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))

                TaskPoint(
                    point = tasksOfThisSprint.filter { it.statusIndex == 1 }.sumOf { it.point },
                    modifier = Modifier.background(
                        color = InProgressPoint,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))

                TaskPoint(
                    point = tasksOfThisSprint.filter { it.statusIndex == 2 }.sumOf { it.point },
                    modifier = Modifier.background(
                        color = DonePoint,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            //3dots icon editing status of sprint
            Box(modifier = Modifier) {
                Icon(
                    painter = painterResource(R.drawable.more),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        isSprintStatusExpanded = true
                    }
                )
                DropdownMenu(
                    expanded = isSprintStatusExpanded,
                    onDismissRequest = { isSprintStatusExpanded = false }
                ) {
                    if (sprint.isActive) {
                        DropdownMenuItem(
                            text = { Text("Complete Sprint") },
                            onClick = {
                                isSprintStatusExpanded = false
                                updateSprint(
                                    sprint.copy(
                                        isActive = false,
                                        completeDate = LocalDateTime.now().toString()
                                    )
                                )
                            })
                    } else {
                        DropdownMenuItem(
                            text = { Text("Active Sprint") },
                            onClick = {
                                isSprintStatusExpanded = false
                                updateSprint(sprint.copy(
                                    isActive = true,
                                ))
                            })
                    }
                }
            }
        }

        AnimatedVisibility(visible = isOpen) {
            Column(
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.default,
                    end = MaterialTheme.spacing.default
                )
            ) {
                tasksOfThisSprint.forEach { task ->
                    TaskCard(task = task, navController = navController)
                }

                // row for creating task
                if (isActive) {
                    TaskDialog(sprint) { task ->
                        onAddTask(task)
                    }
                }
            }
        }
    }
}