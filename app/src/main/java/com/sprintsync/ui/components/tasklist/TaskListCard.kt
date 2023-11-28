package com.sprintsync.ui.components.tasklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.sprintsync.R
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.components.TaskProcess
import com.sprintsync.ui.components.TaskTag
import com.sprintsync.ui.theme.BugType
import com.sprintsync.ui.theme.InProgressStatus
import com.sprintsync.ui.theme.ProductivityStatus
import com.sprintsync.ui.theme.ToDoStatus
import com.sprintsync.ui.theme.spacing


@Composable
fun TaskListCard(task: TaskResDto, onClick: () -> Unit = { }) {
    var status = ""
    var backgroundColor: Color = Color.Transparent
    var typeColor: Color = Color.Transparent

    val avatar = ContextCompat
        .getDrawable(LocalContext.current, R.drawable.nice_avatar)

    when (task.statusIndex) {
        1 -> {
            status = "To Do"
            backgroundColor = ToDoStatus
        }

        2 -> {
            status = "In Progress"
            backgroundColor = InProgressStatus
        }

        3 -> {
            status = "Productivity"
            backgroundColor = ProductivityStatus
        }
    }
    when ("Task") {
        "Task" -> {
            typeColor = MaterialTheme.colorScheme.onPrimaryContainer
        }

        "Bug" -> {
            typeColor = BugType
        }
    }

    Surface(onClick = onClick) {
        Row(
            modifier = Modifier.height(64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1.05f),
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.medium
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(MaterialTheme.spacing.default)
                        .background(color = typeColor)
                )

                Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)) {
                    Text(
                        text = task.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            MaterialTheme.spacing.default
                        )
                    ) {
                        task.labels?.forEach() {
                            TaskTag(tagName = it)
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.weight(0.8f),
                horizontalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.medium
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
//        ContextCompat
//            .getDrawable(LocalContext.current, R.drawable.nice_avatar)
//            ?.let { it1 ->
//                task.assignees = mutableListOf(it1.toBitmap(), it1.toBitmap())
//            }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            (-14).dp,
                            Alignment.End
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        task.assignees.forEachIndexed { index, _ ->
                            avatar?.toBitmap()?.let {
                                Image(
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .zIndex(index.toFloat())
                                        .size(32.dp)
                                )
                            }
                        }
                    }
                }
                TaskProcess(
                    title = status,
                    color = backgroundColor
                        .toArgb()
                        .toLong(),
                )
            }
        }
    }
}
