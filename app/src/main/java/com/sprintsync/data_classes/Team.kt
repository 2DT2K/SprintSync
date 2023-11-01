package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty

data class Team(
	@JsonProperty("_id")
	val id: String,
	val name: String,
	val teamLeader: Member,
	val members: List<Member>,
	val project: Project
)