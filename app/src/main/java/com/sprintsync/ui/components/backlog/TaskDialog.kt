package com.sprintsync.ui.components.backlog

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sprintsync.R
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.ui.theme.spacing

@Composable
fun TaskDialog(sprint: SprintDto, onAddTask: (TaskDto) -> Unit) {
    var isTaskDialogOpen by remember { mutableStateOf(false) }
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskDeadline by remember { mutableStateOf("") }
    var taskPoint by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isTaskDialogOpen = !isTaskDialogOpen
            }
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "create task icon",
            tint = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "Create")
    }

    if (isTaskDialogOpen) {
        Dialog(
            onDismissRequest = {
                isTaskDialogOpen = !isTaskDialogOpen
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Card(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                elevation = CardDefaults.cardElevation(10.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = MaterialTheme.spacing.default,
                            horizontal = MaterialTheme.spacing.medium
                        ),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Add Task",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    InputGroup(
                        title = "Name",
                        value = taskName,
                        onValueChange = { taskName = it })

                    InputGroup(
                        title = "Description",
                        value = taskDescription,
                        onValueChange = { taskDescription = it })

                    InputGroup(
                        title = "Deadline",
                        value = taskDeadline,
                        content = { DatePickerDialog { taskDeadline = it } })


                    InputGroup(
                        type = "number",
                        title = "Point",
                        value = if (taskPoint != -1) taskPoint.toString() else ""
                    ) {
                        taskPoint = it.toIntOrNull() ?: -1
                    }

                    Button(
                        onClick = {
                            //TODO: team,assignor,asignees,parent,attachments ko goi dc do ko co assignor
                            val task = sprint.id?.let {
                                TaskDto(
                                    name = taskName,
                                    description = taskDescription,
                                    sprint = sprint.id,
                                    team = "",
                                    assignor = "it1.uid",
                                    assignees = listOf(""),
                                    parentTask = "",
                                    attachments = listOf(""),
                                    statusIndex = 0,
                                    deadline = taskDeadline,
                                    point = taskPoint,
                                    comments = listOf(""),
                                    labels = listOf(""),
                                )
                            }
                            task?.let { onAddTask(it) }
                            isTaskDialogOpen = !isTaskDialogOpen
                        },
                        modifier = Modifier
                            .border(
                                0.dp,
                                Color.Transparent,
                                RoundedCornerShape(3.dp)
                            )
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            "Done",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SprintDialog(projectID: String, onAddSprint: (SprintDto) -> Unit) {
    var isSprintDialogOpen by remember { mutableStateOf(false) }
    var sprintNumber by remember { mutableIntStateOf(-1) }
    var sprintStartDate by remember { mutableStateOf("") }
    var sprintEndDate by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isSprintDialogOpen = !isSprintDialogOpen
            }
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "create task icon",
            tint = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "Create")
    }

    if (isSprintDialogOpen) {
        Dialog(
            onDismissRequest = {
                isSprintDialogOpen = !isSprintDialogOpen
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
        ) {
            Card(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .width(360.dp)
                    .padding(MaterialTheme.spacing.medium),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = MaterialTheme.spacing.default,
                            horizontal = MaterialTheme.spacing.medium
                        ),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Add Sprint",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
                    ) {
                        InputGroup(
                            type = "number",
                            title = "Name",
                            initialValue = "Sprint ",
                            value = if (sprintNumber != -1) "$sprintNumber" else "",
                            onValueChange = {
                                sprintNumber = it.toIntOrNull() ?: -1
                            }
                        )

                        InputGroup(
                            title = "Start Date",
                            value = sprintStartDate,
                            content = { DatePickerDialog { sprintStartDate = it } }
                        )


                        InputGroup(
                            title = "End Date",
                            value = sprintEndDate,
                            content = { DatePickerDialog { sprintEndDate = it } }
                        )
                    }

                    Button(
                        onClick = {
                            //TODO: team,assignor,asignees,parent,attachments ko goi dc do ko co assignor
                            val sprint = SprintDto(
                                id = null,
                                startDate = sprintStartDate,
                                endDate = sprintEndDate,
                                sprintNumber = sprintNumber,
                                project = projectID
                            )
                            onAddSprint(sprint)
                            isSprintDialogOpen = !isSprintDialogOpen
                        },
                        modifier = Modifier
                            .border(
                                0.dp,
                                Color.Transparent,
                                RoundedCornerShape(4.dp)
                            )
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            "Done",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}
