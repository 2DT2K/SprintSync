package com.sprintsync.data.dtos.response

import com.sprintsync.data.dtos.MemberDto

data class TeamResDto(
	val id: String? = null,
	val name: String,
	val leader: MemberDto,
	val members: List<MemberDto>,
	val project: String
)