package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty

data class Team(
	@JsonProperty("_id")
	val id: String,
	val name: String,
	val teamLeader: String,
	val members: List<String>,
	val project: String
)