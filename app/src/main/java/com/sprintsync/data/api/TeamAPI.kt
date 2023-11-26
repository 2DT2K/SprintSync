package com.sprintsync.data.api

import com.sprintsync.data.dtos.TeamDto
import com.sprintsync.data.dtos.response.ApiResponse
import com.sprintsync.data.dtos.response.TeamResDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TeamAPI {
	@GET("teams/{id}")
	suspend fun getTeam(@Path("id") id: String): ApiResponse<TeamResDto>

	@GET("teams/project/{id}")
	suspend fun getTeamsOfProject(@Path("id") id: String): ApiResponse<List<TeamResDto>>

	@POST("teams")
	suspend fun addTeam(@Body dto: TeamDto): ApiResponse<TeamResDto>

	@PATCH("teams")
	suspend fun updateTeam(@Body dto: TeamDto): ApiResponse<TeamResDto>

	@DELETE("teams/{id}")
	suspend fun deleteTeam(@Path("id") id: String): ApiResponse<String>
}