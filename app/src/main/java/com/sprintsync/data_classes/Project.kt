package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty

data class Project(
	@JsonProperty("_id")
	val id: String,
	val name: String,
	val manager: Member,
	val statuses: List<String>,
	val labels: List<String>? = null,
	val teams: List<Team>? = null,
	val sprints: List<Sprint>? = null,
	val members: List<Member>? = null,
)