package com.sprintsync.dtos.response

import com.sprintsync.dtos.MemberDto

data class TeamResDto(
    val id: String? = null,
    val name: String,
    val leader: MemberDto,
    val members: List<String>,
    val project: String
)