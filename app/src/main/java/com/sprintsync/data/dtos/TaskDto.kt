package com.sprintsync.data.dtos

data class TaskDto(
	val id: String? = null,
	val name: String,
	val description: String? = null,
	val sprint: String,
	val team: String,
	val assignor: String,
	val assignees: List<String>,
	val parentTask: String? = null,
	val attachments: List<String>? = null,
	var statusIndex: Int,
	val deadline: String? = null,
	val point: Int = 0,
	val comments: List<String>? = null,
	var labels: List<String>? = null
)