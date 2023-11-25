package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.HorizontalDivider
import com.sprintsync.ui.components.taskview.Attachment
import com.sprintsync.ui.components.taskview.ChangeTaskStateButton
import com.sprintsync.ui.components.taskview.MoreInformation
import com.sprintsync.ui.components.taskview.SubTask
import com.sprintsync.ui.components.taskview.TaskAttachment
import com.sprintsync.ui.components.taskview.TaskComments
import com.sprintsync.ui.components.taskview.TaskDescription
import com.sprintsync.ui.components.taskview.TaskviewTitle
import com.sprintsync.ui.theme.spacing


data class Task(
    val name: String,
    val taskNavigation: String,
    val taskState: String,
    val description: String,
    val assignor: String,
    val assignees: List<String>,
    val point: Int,
    val comments: List<TaskComments>,
    val issueType: String? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskView(task: Task) {
    Column(
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        TaskviewTitle(taskNavigation = task.taskNavigation, taskAssignList = mutableListOf())
        ChangeTaskStateButton(taskState = task.taskState)
        TaskDescription(taskDescription = task.description)
        HorizontalDivider()
        SubTask(subTaskList = subTask)
        HorizontalDivider()
        TaskAttachment(attachmentList = attachmentList)
        HorizontalDivider()
        MoreInformation(
            point = fakeData.point,
            assigneeList = fakeData.assignees,
            taskTag = listOf("FE", "HomePage"),
            reporter = fakeData.assignor
        )
        TaskComments(commentList = fakeData.comments)
    }
}

var subTask1 = SubTask(
    status = "In progress",
    taskName = "Study MonggoDB",
    taskNavigation = "SCRUMMER-1",
    assignees = mutableListOf(),
)
var subTask2 = SubTask(
    status = "Productivity",
    taskName = "Some longgggggggggggggggggggggggggggggggggggggggggg",
    taskNavigation = "SCRUMMER-1",
    assignees = mutableListOf(),
)
var subTask3 = SubTask(
    status = "Todo",
    taskName = "Play dota",
    taskNavigation = "SCRUMMER-1",
    assignees = mutableListOf(),
)
var subTask = listOf<SubTask>(subTask1, subTask2, subTask3)

var firstAttachment = Attachment("Test", "pdf", 6.8)
var secondAttachment = Attachment("Anhdepquadithoilmao1234", "png", 0.6)
var attachmentList = listOf<Attachment>(firstAttachment, secondAttachment)

var fakeCmt1 = TaskComments(
    commenter = "Vo Tin Du",
    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquet convallis iaculis. Donec pharetra gravida libero lacinia finibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. ",
    commentTime = "now",
)
var fakeCmt2 = TaskComments(
    commenter = "Nguyen Hai Dan",
    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquet convallis iaculis. Donec pharetra gravida libero lacinia finibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. ",
    commentTime = "5 days ago"
)
val fakeData = Task(
    name = "Code homepage",
    taskNavigation = "SCRUMMER-5",
    taskState = "In Progress",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquet convallis iaculis. Donec pharetra gravida libero lacinia finibus. Interdum et malesuada fames ac ante ipsum primis in faucibus.",
    assignor = "Vo Tin Du",
    assignees = listOf("Tran Chien Thang", "Nguyen Hai Dan"),
    point = 70,
    comments = listOf(fakeCmt1, fakeCmt2),
    issueType = "Task"
)

@Preview(showBackground = true)
@Composable
fun TaskViewPreview() {
    TaskView(fakeData)
}