package com.sprintsync.data.dtos

data class ProjectDto(
	val id: String? = null,
	val name: String,
	val code: String,
	val manager: String,
	val statuses: List<String> = listOf("To Do", "In Progress", "Done"),
	val labels: List<String>? = null
)