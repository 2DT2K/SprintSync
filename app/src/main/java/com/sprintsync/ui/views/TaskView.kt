package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.data.view_models.AttachmentViewModel
import com.sprintsync.data.view_models.CommentViewModel
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
    val attachmentVM = hiltViewModel<AttachmentViewModel>()
    val commentVM = hiltViewModel<CommentViewModel>()

    val taskDetailsState by taskVM.state.collectAsStateWithLifecycle()
    val subtasksDetails by taskVM.subtasks.collectAsStateWithLifecycle()
    val attachmentDetailsState by attachmentVM.state.collectAsStateWithLifecycle()
    val commentDetailsState by commentVM.state.collectAsStateWithLifecycle()

    val taskDetails = taskDetailsState.dto
    val attachmentDetails = attachmentDetailsState.dtoList
    val commentDetails = commentDetailsState.dtoList

    LaunchedEffect(Unit) {
        taskVM.getTask(taskId)
        taskVM.getSubTasks(taskId)
        attachmentVM.getAttachmentsOfTask(taskId)
        commentVM.getCommentsOfTask(taskId)
    }
    val taskState by remember {
        mutableStateOf(if (taskDetails != null) statusList[taskDetails.statusIndex] else "Loading...")
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
                SubTask(subTaskList = subtasksDetails, statusList = statusList)
            }
            HorizontalDivider()
            if (taskDetails != null) {
                TaskAttachment(attachmentList = attachmentDetails)
            }
            HorizontalDivider()
            if (taskDetails != null) {
                MoreInformation(
                    updateState = { taskVM.updateTask(it) },
                    taskDetails = taskDetails,
                )
            }
            if (taskDetails != null) {
                TaskComments(commentList = commentDetails)
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun TaskViewPreview() {
//    TaskView(fakeTask, listOf("To do", "In Progress"))
//}