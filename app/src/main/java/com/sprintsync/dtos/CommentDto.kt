package com.sprintsync.dtos

data class CommentDto(
	val id: String? = null,
	val commenter: String,
	val content: String,
	val createdAt: String,
	val attachments: List<String>? = null,
)