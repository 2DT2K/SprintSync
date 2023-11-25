package com.sprintsync.dtos

data class SprintDto(
	val id: String? = null,
	val startDate: String,
	val endDate: String,
	val sprintNumber: Int,
	val project: String
)