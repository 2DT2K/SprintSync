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
    val team: String?,
    val assignor: MemberDto,
    val assignees: List<MemberDto>,
    val parentTask: String? = null,
    val attachments: List<String>? = null,
    val statusIndex: Int,
    val deadline: String? = null,
    val point: Int = 0,
    val comments: List<String>? = null,
    val labels: List<String>? = null
) {
    fun toDto(): TaskDto {
        return TaskDto(
            id = id,
            name = name,
            description = description,
            sprint = sprint,
            team = team?:"",
            assignor = assignor.id ?: "",
            assignees = assignees.map { it.id ?: "" },
            parentTask = parentTask,
            attachments = attachments,
            statusIndex = statusIndex,
            deadline = deadline,
            point = point,
            comments = comments,
            labels = labels,
        )
    }
}

