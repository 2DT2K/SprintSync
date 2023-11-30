package com.sprintsync.ui.components.backlog

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.R
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.data.dtos.response.TeamResDto
import com.sprintsync.data.view_models.MemberViewModel
import com.sprintsync.data.view_models.TeamViewModel
import com.sprintsync.ui.theme.spacing
import java.time.LocalDateTime

@Composable
fun TaskDialog(sprint: SprintDto, onAddTask: (TaskDto) -> Unit) {
    val teamVM = hiltViewModel<TeamViewModel>()
    val memberVM = hiltViewModel<MemberViewModel>()
    val teamState = teamVM.state.collectAsStateWithLifecycle()
    val memberState = memberVM.state.collectAsStateWithLifecycle()
    val me = memberState.value.dto
    var isTaskDialogOpen by remember { mutableStateOf(false) }
    var isDropdownMenu by remember { mutableStateOf(false) }
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var chosenTeam by remember {
        mutableStateOf<TeamResDto>(
            TeamResDto(
                id = null,
                name = "",
                leader = MemberDto(
                    id = null,
                    name = "",
                    uid = "",
                    email = ""
                ),
                members = emptyList(),
                project = sprint.project
            )
        )
    }
    var isTeamDialogOpen by remember { mutableStateOf(false) }
    var assignees by remember { mutableStateOf<List<MemberDto>>(emptyList<MemberDto>()) }
    var isAssigneeDialogOpen by remember { mutableStateOf(false) }
    var taskDeadline by remember { mutableStateOf(LocalDateTime.now().toString()) }
    var taskPoint by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        teamVM.getTeamsOfProject(sprint.project)
        memberVM.getMe()
    }

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

        Text(text = "Create Task")
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

//                    Button(onClick = { isTeamDialogOpen = !isTeamDialogOpen }) {
//                    }
                    Button(onClick = {
                        if (me != null) {
                            assignees =
                                if (assignees.contains(me)) assignees.filter { it != me } else assignees + me
                        }
                    }) {
                        Text(text = if (assignees.contains(me)) "Un-assign me" else "Assign me")
                    }

                    Box(
                        contentAlignment = Alignment.CenterStart,
                    ) {
//                        Text(text = if (chosenTeam.name != "") chosenTeam.name else "Choose Team")
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.onSurface,
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(4.dp)
                                .clickable(onClick = {
                                    isTeamDialogOpen = !isTeamDialogOpen
                                }),
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = if (chosenTeam.name != "") chosenTeam.name else "Choose Team")
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_down),
                                contentDescription = ""
                            )
                        }
                        DropdownMenu(
                            expanded = isTeamDialogOpen,
                            onDismissRequest = { isTeamDialogOpen = !isTeamDialogOpen }) {
                            AnimatedVisibility(visible = isTeamDialogOpen) {
                                Column {
                                    teamState.value.dtoList?.forEach {
                                        DropdownMenuItem(onClick = {
                                            chosenTeam = it
                                            assignees = emptyList()
                                            isTeamDialogOpen = !isTeamDialogOpen
                                        }) {
                                            Text(text = it.name)
                                        }
                                    }
                                }
                            }
                        }
                    }

//                    Button(onClick = { isAssigneeDialogOpen = !isAssigneeDialogOpen }) {
//                        Text(text = if (assignees.isNotEmpty()) assignees.joinToString(", ") { it.name } else "Choose Assignees")
//                    }
//                    AnimatedVisibility(visible = isAssigneeDialogOpen) {
//                        Column {
//                            chosenTeam.members.forEach { member ->
//                                Row(modifier = Modifier.clickable {
//                                    assignees = if (assignees.contains(member)) {
//                                        assignees.filter { it != member }
//                                    } else {
//                                        assignees + member
//                                    }
//                                }) {
//                                    if (assignees.contains(member)) {
//                                        Icon(
//                                            painter = painterResource(id = R.drawable.check_circle),
//                                            contentDescription = "check icon",
//                                            tint = MaterialTheme.colorScheme.onSurface,
//                                        )
//                                    }
//                                    Text(text = member.name)
//                                }
//                            }
//                        }
//                    }
                    Box(
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.onSurface,
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(4.dp)
                                .clickable(onClick = {
                                    isAssigneeDialogOpen = !isAssigneeDialogOpen
                                }),
                            horizontalArrangement = Arrangement.spacedBy(
                                8.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = if (assignees.isNotEmpty()) assignees.joinToString(", ") { it.name } else "Choose Assignees")
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_down),
                                contentDescription = ""
                            )
                        }
                        DropdownMenu(
                            expanded = isAssigneeDialogOpen,
                            onDismissRequest = { isAssigneeDialogOpen = !isAssigneeDialogOpen }) {
                            AnimatedVisibility(visible = isAssigneeDialogOpen) {
                                Column {
                                    chosenTeam.members.forEach { member ->
                                        DropdownMenuItem(onClick = {
                                            assignees = if (assignees.contains(member)) {
                                                assignees.filter { it != member }
                                            } else {
                                                assignees + member
                                            }
                                        }) {
                                            if (assignees.contains(member)) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.check_circle),
                                                    contentDescription = "check icon",
                                                    tint = MaterialTheme.colorScheme.onSurface,
                                                )
                                            }
                                            Text(text = member.name)
                                        }
                                    }
                                }
                            }
                        }
                    }
//

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
                            if (chosenTeam.id != null && chosenTeam.id != "" && assignees.isNotEmpty()) {
                                //TODO: team,assignor,asignees,parent,attachments ko goi dc do ko co assignor
                                val task = sprint.id?.let {
                                    TaskDto(
                                        name = taskName,
                                        description = taskDescription,
                                        sprint = sprint.id,
                                        // TODO: add a team id (necessary)
                                        // This is only a temporary solution
                                        team = chosenTeam.id!!,
                                        assignor = null,
                                        assignees = assignees.map { it.id!! },
                                        parentTask = null,
                                        attachments = emptyList(),
                                        statusIndex = 0,
                                        deadline = taskDeadline,
                                        point = taskPoint,
                                        comments = emptyList(),
                                        labels = emptyList(),
                                    )
                                }
                                task?.let { onAddTask(it) }
                                isTaskDialogOpen = !isTaskDialogOpen
                            }
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
fun SprintDialog(projectID: String, sprints: List<SprintDto>, onAddSprint: (SprintDto) -> Unit) {
    var isSprintDialogOpen by remember { mutableStateOf(false) }
    val sprintNumber = if (sprints.isEmpty()) 0 else sprints.maxOf { it.sprintNumber } + 1
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

        Text(text = "Create Sprint")
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
                        Text(text = "Sprint $sprintNumber")

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
                                isActive = false,
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
