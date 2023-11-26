package com.sprintsync.data.dtos

data class TeamDto(
	val id: String? = null,
	val name: String,
	val leader: String,
	val members: List<String>,
	val project: String
)