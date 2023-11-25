package com.sprintsync.dtos.response

import com.sprintsync.dtos.MemberDto
import com.sprintsync.dtos.TeamDto

data class TaskResDto(
	val id: String? = null,
	val name: String,
	val description: String? = null,
	val sprint: String,
	val team: TeamDto,
	val assignor: MemberDto,
	val assignees: List<MemberDto>,
	val parentTask: String? = null,
	val attachments: List<String>? = null,
	val statusIndex: Int,
	val deadline: String? = null,
	val point: Int = 0,
	val comments: List<String>? = null,
	val labels: List<String>? = null
)