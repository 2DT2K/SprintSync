package com.sprintsync.data.dtos.response

import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.data.dtos.TeamDto

data class TaskResDto(
    val id: String? = null,
    val name: String,
    val description: String? = null,
    val sprint: String,
    val team: TeamDto,
    val assignor: MemberDto,
    val assignees: List<MemberDto>,
    val parentTask: String? = null,
    val subTasks: List<TaskResDto>? = null,
    val attachments: List<AttachmentDto>? = null,
    val statusIndex: Int,
    val deadline: String? = null,
    val point: Int = 0,
    val comments: List<CommentResDto>? = null,
    val labels: List<String>? = null
) {
    fun toDto(): TaskDto {
        return TaskDto(
            id = id,
            name = name,
            description = description,
            sprint = sprint,
            team = team.id ?: "",
            assignor = assignor.id ?: "",
            assignees = assignees.map { it.id ?: "" },
            parentTask = parentTask,
            attachments = attachments?.map { it.id ?: "" },
            statusIndex = statusIndex,
            deadline = deadline,
            point = point,
            comments = comments?.map { it.id ?: "" },
            labels = labels,
        )
    }
}

