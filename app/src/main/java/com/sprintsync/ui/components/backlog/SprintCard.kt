package com.sprintsync.ui.components.backlog

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sprintsync.R
import com.sprintsync.ui.components.CustomText
import com.sprintsync.ui.components.StoryPoint
import com.sprintsync.ui.theme.*
import com.sprintsync.ui.view_models.BacklogViewModel

@Composable
fun SprintCard(sprint: BacklogViewModel.Sprint, isActive: Boolean = false) {
    var isOpen by remember { mutableStateOf(false) }
    var isSprintStatusExpanded by remember { mutableStateOf(false) }
    var isCreateTaskExpanded by remember { mutableStateOf(false) }

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
                .padding(vertical = 8.dp)
                .height(40.dp),
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
                CustomText(
                    text = sprint.sprintName,
                    fontWeight = FontWeight.Bold,
                    color = Purple20
                )
                CustomText(
                    text = "Problems: ${sprint.task.size}",
                    color = Grey60,
                    fontWeight = FontWeight(500)
                )
            }

            Spacer(modifier = Modifier.weight(1.0f))

            StoryPoint(point = 70)
            Spacer(modifier = Modifier.width(8.dp))

            StoryPoint(point = 70, Cyan80)
            Spacer(modifier = Modifier.width(8.dp))

            StoryPoint(point = 70, Green80)
            Spacer(modifier = Modifier.width(8.dp))

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
                    DropdownMenuItem(
                        text = { Text("Refresh") },
                        onClick = { /* Handle refresh! */ })
                    DropdownMenuItem(
                        text = { Text("Refresh") },
                        onClick = { /* Handle refresh! */ })
                    DropdownMenuItem(
                        text = { Text("Refresh") },
                        onClick = { /* Handle refresh! */ })
                }
            }
        }

        if (isOpen) {
            sprint.task.forEach { task ->
                TaskCard(task = task)
            }

            // row for creating task
            if (isActive) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .height(64.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "create task icon"
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    CustomText(text = "Create")

                    Spacer(modifier = Modifier.weight(1f))

                    TaskComposer(isCreateTaskExpanded)
                }
            }
        }
    }
}