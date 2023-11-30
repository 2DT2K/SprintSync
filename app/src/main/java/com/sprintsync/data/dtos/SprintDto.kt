package com.sprintsync.data.dtos

data class SprintDto(
	val id: String? = null,
	val startDate: String,
	val endDate: String,
	val completeDate: String? = null,
	val sprintNumber: Int,
	val project: String
)