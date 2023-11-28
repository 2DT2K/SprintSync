package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.dtos.TeamDto
import com.sprintsync.data.dtos.response.CommentResDto
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.components.HorizontalDivider
import com.sprintsync.ui.components.taskview.ChangeTaskStateButton
import com.sprintsync.ui.components.taskview.MoreInformation
import com.sprintsync.ui.components.taskview.SubTask
import com.sprintsync.ui.components.taskview.TaskAttachment
import com.sprintsync.ui.components.taskview.TaskComments
import com.sprintsync.ui.components.taskview.TaskDescription
import com.sprintsync.ui.components.taskview.TaskviewTitle



@Composable
fun TaskView(task: TaskResDto, statusList: List<String>) {
    val taskState by remember {
        mutableStateOf(statusList[task.statusIndex])
    }
    Surface {
        Column(
            modifier = Modifier
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            TaskviewTitle(taskNavigation = "SCRUMMER", taskAssignor = task.assignor)
            ChangeTaskStateButton(taskState = taskState)
            TaskDescription(taskDescription = task.description)
            HorizontalDivider()
            SubTask(subTaskList = task.subTasks, statusList = statusList)
            HorizontalDivider()
            TaskAttachment(attachmentList = task.attachments)
            HorizontalDivider()
            MoreInformation(
                point = task.point,
                assigneeList = task.assignees,
                taskTag = task.labels,
                reporter = task.assignor,
            )
            TaskComments(commentList = task.comments)
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


@Preview(showBackground = true)
@Composable
fun TaskViewPreview() {
    TaskView(fakeTask, listOf("To do","In Progress"))
}