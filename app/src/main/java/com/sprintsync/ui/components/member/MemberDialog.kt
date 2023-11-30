package com.sprintsync.ui.components.member

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.R
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.data.dtos.TeamDto
import com.sprintsync.data.view_models.TeamViewModel
import com.sprintsync.ui.components.backlog.DatePickerDialog
import com.sprintsync.ui.components.backlog.InputGroup
import com.sprintsync.ui.theme.spacing
import java.time.LocalDateTime


@Composable
fun MemberDialog(onAddMember: (String) -> Unit) {
    var isTaskDialogOpen by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }

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

        Text(text = "Add Member")
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
                        title = "Email",
                        value = email,
                        onValueChange = { email = it })


                    Button(
                        onClick = {
                            //TODO: team,assignor,asignees,parent,attachments ko goi dc do ko co assignor
                            onAddMember(email)
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
fun TeamDialog(onAddTeam: (String) -> Unit){
    var isSprintDialogOpen by remember { mutableStateOf(false) }
    var teamName by remember { mutableStateOf("") }

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

        Text(text = "Create New Team")
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
                        text = "Add Team",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
                    ) {
                        InputGroup(
                            title = "Team Name",
                            value = teamName,
                            onValueChange = { teamName = it })

                    }

                    Button(
                        onClick = {
                            //TODO: team,assignor,asignees,parent,attachments ko goi dc do ko co assignor
                            onAddTeam(teamName)
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
