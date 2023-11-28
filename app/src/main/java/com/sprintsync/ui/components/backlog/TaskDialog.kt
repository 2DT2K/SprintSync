package com.sprintsync.ui.components.backlog

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sprintsync.R
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.ui.components.profile.edit_profile.DateWheelPicker
import com.sprintsync.ui.components.profile.edit_profile.ProfileInfoCard
import com.sprintsync.ui.theme.Grey80
import com.sprintsync.ui.theme.Purple40
import com.sprintsync.ui.theme.spacing
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDateTime
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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

                    TaskInputGroup(
                        title = "Name",
                        value = taskName,
                        onValueChange = { taskName = it })

                    TaskInputGroup(
                        title = "Description",
                        value = taskDescription,
                        onValueChange = { taskDescription = it })

                    TaskInputGroup(
                        title = "Deadline",
                        value = taskDeadline,
                        content = { DatePickerDialog { taskDeadline = it } })


                    TaskInputGroup(
                        title = "Point",
                        value = if (taskPoint != -1) taskPoint.toString() else "",
                        onValueChange = {
                            taskPoint = it.toIntOrNull() ?: -1
                        })

                    Button(
                        onClick = {
                            val task = sprint.id?.let {
//                                TaskDto(
//                                    name = taskName,
//                                    description = taskDescription,
//                                    sprint = it,
//                                    team = team.id ?: "",
//                                    assignor = assignor.id ?: "",
//                                    assignees = assignees.map { it.id ?: "" },
//                                    parentTask = parentTask,
//                                    attachments = attachments?.map { it.id ?: "" },
//                                    statusIndex = statusIndex,
//                                    deadline = deadline,
//                                    point = point,
//                                    comments = comments?.map { it.id ?: "" },
//                                    labels = labels,
//                                )
                            }
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
