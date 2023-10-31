package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty

data class Comment(
	@JsonProperty("_id")
	val id: String,
	val commenter: Member,
	val content: String,
	val attachments: List<Attachment>? = null,
)