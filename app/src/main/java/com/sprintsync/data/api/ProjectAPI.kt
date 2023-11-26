package com.sprintsync.data.api

import com.sprintsync.data.dtos.ProjectDto
import com.sprintsync.data.dtos.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ProjectAPI {
	@GET("projects/{id}")
	suspend fun getProject(@Path("id") id: String): ApiResponse<ProjectDto>

	@GET("projects/me")
	suspend fun getMyProjects(): ApiResponse<List<ProjectDto>>

	@POST("projects")
	suspend fun addProject(@Body dto: ProjectDto): ApiResponse<ProjectDto>

	@PATCH("projects")
	suspend fun updateProject(@Body dto: ProjectDto): ApiResponse<ProjectDto>

	@DELETE("projects/{id}")
	suspend fun deleteProject(@Path("id") id: String): ApiResponse<String>
}