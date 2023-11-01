package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class Task(
	@JsonProperty("_id")
	val id: String,
	val name: String,
	val description: String = "",
	val sprint: Sprint,
	val team: Team,
	val assignor: Member,
	val assignees: List<Member>,
	val parentTask: Task? = null,
	val attachments: List<Attachment>? = null,
	val statusIndex: Int,
	val deadline: LocalDateTime? = null,
	val point: Int = 0,
	val comments: List<Comment>? = null,
	val label: String? = null
)