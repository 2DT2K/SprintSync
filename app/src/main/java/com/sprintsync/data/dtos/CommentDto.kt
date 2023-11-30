package com.sprintsync.data.dtos

data class CommentDto(
    val id: String? = null,
    val commenter: String? = null,
    var content: String,
    var createdAt: String,
    val attachments: List<String>? = null,
)