package com.sprintsync.ui.components.backlog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.sprintsync.R
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.components.TaskPoint
import com.sprintsync.ui.components.TaskProcess
import com.sprintsync.ui.theme.InProgressStatus
import com.sprintsync.ui.theme.ProductivityStatus
import com.sprintsync.ui.theme.ToDoStatus
import com.sprintsync.ui.theme.spacing

@Composable
fun TaskCard(task: TaskResDto) {
    var status = ""
    var backgroundColor: Color = Color.Transparent
    var icon = 0
    var iconTint: Color = Color.Transparent
    var iconBackgroundColor: Color = Color.Transparent
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
    //TODO: add type to task
    when ("task") {
        "Task" -> {
            icon = R.drawable.check_circle
            iconTint = MaterialTheme.colorScheme.onSecondary
            iconBackgroundColor = MaterialTheme.colorScheme.secondary
        }

        "Bug" -> {
            icon = R.drawable.bug_report
            iconTint = MaterialTheme.colorScheme.onErrorContainer
            iconBackgroundColor = MaterialTheme.colorScheme.errorContainer
        }
    }

    Row(
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = iconBackgroundColor,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .padding(MaterialTheme.spacing.small)
        ) {
            Icon(
                painter = painterResource(icon),
                tint = iconTint,
                contentDescription = null,
            )
        }
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = task.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Row(
                modifier = Modifier.height(32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default)
            ) {
                //TODO: scrummer number missing
                Text(
                    text = "Scrummer-${task.name}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                TaskPoint(
                    point = task.point,
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                )

                TaskProcess(
                    title = status,
                    color = backgroundColor.toArgb().toLong()
                )
            }
        }
        Spacer(modifier = Modifier.weight(1.0f))

        val avatar = ContextCompat
            .getDrawable(LocalContext.current, R.drawable.nice_avatar)
//        ContextCompat
//            .getDrawable(LocalContext.current, R.drawable.nice_avatar)
//            ?.let { it1 ->
//                task.assignees = mutableListOf(it1.toBitmap(), it1.toBitmap())
//            }
        Row(
            horizontalArrangement = Arrangement.spacedBy((-8).dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            task.assignees.forEachIndexed { index, _ ->
                avatar?.toBitmap()?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .zIndex(index.toFloat())
                            .width(24.dp)
                            .height(24.dp)
                    )
                }
            }
        }
    }
}
