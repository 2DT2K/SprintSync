package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.dtos.TeamDto
import com.sprintsync.data.dtos.response.CommentResDto
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.data.view_models.TaskViewModel
import com.sprintsync.ui.components.HorizontalDivider
import com.sprintsync.ui.components.taskview.ChangeTaskStateButton
import com.sprintsync.ui.components.taskview.MoreInformation
import com.sprintsync.ui.components.taskview.SubTask
import com.sprintsync.ui.components.taskview.TaskAttachment
import com.sprintsync.ui.components.taskview.TaskComments
import com.sprintsync.ui.components.taskview.TaskDescription
import com.sprintsync.ui.components.taskview.TaskviewTitle
import com.sprintsync.ui.theme.spacing


@Composable
fun TaskView(taskId: String, statusList: List<String>) {
    val taskVM = hiltViewModel<TaskViewModel>()
    val taskDetailsState by taskVM.state.collectAsState()
    val taskDetails = taskDetailsState.dto
    LaunchedEffect(Unit) {
        taskVM.getTask(taskId)
    }

    val taskState by remember {
        mutableStateOf(statusList[taskDetails?.statusIndex!!])
    }
    Surface {
        Column(
            modifier = Modifier
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.default,
                Alignment.Top
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            taskDetails?.assignor?.let {
                TaskviewTitle(
                    taskNavigation = "SCRUMMER",
                    taskAssignor = it
                )
            }
            ChangeTaskStateButton(
                taskState = taskState,
                updateState = { taskVM.updateTask(it) },
                statusList = statusList,
                taskDetails = taskDetails,
            )
            if (taskDetails != null) {
                TaskDescription(taskDescription = taskDetails.description)
            }
            HorizontalDivider()
            if (taskDetails != null) {
                SubTask(subTaskList = taskDetails.subTasks, statusList = statusList)
            }
            HorizontalDivider()
            if (taskDetails != null) {
                TaskAttachment(attachmentList = taskDetails.attachments)
            }
            HorizontalDivider()
            if (taskDetails != null) {
                MoreInformation(
                    updateState = { taskVM.updateTask(it) },
                    taskDetails = taskDetails,
                )
            }
            if (taskDetails != null) {
                TaskComments(commentList = taskDetails.comments)
            }
        }
    }
}

var fakeTask1: TaskResDto = TaskResDto(
    id = null,
    name = "play dota",
    description = "We cooking to 5k mmr",
    sprint = "abcd",
    team = TeamDto(
        "", "", "",
        listOf(""), ""
    ),
    assignor = MemberDto(null, "1234", "Vo Tin Du", "du@deptrai"),
    assignees = listOf(MemberDto(null, "2134", "Tran Chien Thang", "thoivay@gmail")),
    parentTask = null,
    subTasks = null,
    attachments = listOf<AttachmentDto>(
        AttachmentDto(
            "",
            "Test",
            "pdf",
            6,
            "20/11/2023",
            ByteArray(12),
            "project1"
        )
    ),
    statusIndex = 1,
    deadline = "20/11/2023",
    point = 40,
    comments = listOf(
        CommentResDto(
            "",
            MemberDto(null, "", "Vo Tin Du", "votindu@gmail,com", "12/10/2003"),
            "Good job",
            "20/10/2023"
        ),
        CommentResDto(
            "",
            MemberDto(null, "", "Nguyen Hai Dan", "haidan@gmail,com", "12/8/2003"),
            "Nice asjdh jksahdj ksahjdkhasjkdh asjkdhjkashja sdbfnjadsbfnas dmbfnasdb fna, mdsbfna",
            "19/10/2023"
        )
    ),
    labels = listOf("FE", "HomePage")
)


var fakeTask: TaskResDto = TaskResDto(
    id = null,
    name = "play dota",
    description = "We cooking to 5k mmr",
    sprint = "abcd",
    team = TeamDto(
        "", "", "",
        listOf(""), ""
    ),
    assignor = MemberDto(null, "1234", "Vo Tin Du", "du@deptrai"),
    assignees = listOf(MemberDto(null, "2134", "Tran Chien Thang", "thoivay@gmail")),
    parentTask = null,
    subTasks = listOf(fakeTask1, fakeTask1),
    attachments = listOf<AttachmentDto>(
        AttachmentDto(
            "",
            "Test",
            "pdf",
            6,
            "20/11/2023",
            ByteArray(12),
            "project1"
        )
    ),
    statusIndex = 1,
    deadline = "20/11/2023",
    point = 40,
    comments = listOf(
        CommentResDto(
            "",
            MemberDto(null, "", "Vo Tin Du", "votindu@gmail,com", "12/10/2003"),
            "Good job",
            "20/10/2023"
        ),
        CommentResDto(
            "",
            MemberDto(null, "", "Nguyen Hai Dan", "haidan@gmail,com", "12/8/2003"),
            "Nice asjdh jksahdj ksahjdkhasjkdh asjkdhjkashja sdbfnjadsbfnas dmbfnasdb fna, mdsbfna",
            "19/10/2023"
        )
    ),
    labels = listOf("FE", "HomePage")
)


//@Preview(showBackground = true)
//@Composable
//fun TaskViewPreview() {
//    TaskView(fakeTask, listOf("To do", "In Progress"))
//}