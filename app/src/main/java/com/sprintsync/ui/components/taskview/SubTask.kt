package com.sprintsync.ui.components.taskview

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sprintsync.R
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.components.TaskProcess
import com.sprintsync.ui.theme.spacing
import com.sprintsync.ui.views.fakeTask


class SubTask(
    val status: String,
    val taskNavigation: String,
    val taskName: String,
    val assignees: MutableList<Bitmap>,
)


@Composable
fun SubTask(subTaskList: List<TaskResDto>?, statusList:List<String>) {
    val context = LocalContext.current
    // Add ImageBitmap objects to the list

    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.small,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.small)
    ) {
        Text(
            text = "Sub Tasks",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        subTaskList?.forEach {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.check_box),
                        contentDescription = "",
                        contentScale = ContentScale.None,
                    )
                    Text(
                        text = "SCURMMER",
                        style = MaterialTheme.typography.bodyMedium,
                        color= MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = it.name,
                        modifier = Modifier.width(110.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color= MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy((-14).dp, Alignment.End),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
//                        ContextCompat
//                            .getDrawable(context, R.drawable.nice_avatar)
//                            ?.let { it1 ->
//                                it.assignees.add(
//                                    it1.toBitmap()
//                                )
//                            }
                        it.assignees.forEachIndexed { index, image ->
                            Image(
                                painterResource(id = R.drawable.nice_avatar),
                                contentDescription = "",
                                modifier = Modifier
                                    .zIndex(index.toFloat())
                                    .width(MaterialTheme.spacing.large)
                                    .height(MaterialTheme.spacing.large)
                            )
                        }
                    }
                    when (statusList[it.statusIndex]) {
                        "In progress" -> TaskProcess(title = "In progress", color = 0xFF4CF590)
                        "Done" -> TaskProcess(title = "Productivity", color = 0xFF00B383)
                        "To Do" -> TaskProcess(title = "Todo", color = 0xFF4F8FF5)
                    }
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun SubTaskPreview() {
//    SubTask(fakeTask.subTask, listOf("To do","In Progress"))
//}