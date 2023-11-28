package com.sprintsync.data.api

import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.dtos.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MemberAPI {
	@GET("members/{id}")
	suspend fun getMember(@Path("id") id: String): ApiResponse<MemberDto>

	@GET("members/project/{id}")
	suspend fun getMembersOfProject(@Path("id") id: String): ApiResponse<List<MemberDto>>

	@GET("members/team/{id}")
	suspend fun getMembersOfTeam(@Path("id") id: String): ApiResponse<List<MemberDto>>

	@GET("members/me")
	suspend fun getMe(): ApiResponse<MemberDto>

	@GET("members/role/?project={projectId}&member={memberId}")
	suspend fun getMemberRole(
		@Path("projectId") projectId: String,
		@Path("memberId") memberId: String
	): ApiResponse<String>

	@PATCH("members")
	suspend fun updateMember(@Body dto: MemberDto): ApiResponse<MemberDto>

	@DELETE("members/me")
	suspend fun deleteMe(): ApiResponse<String>
}