package com.sprintsync.data.api

import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.dtos.response.ApiResponse
import com.sprintsync.data.dtos.response.ReportChartDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface SprintAPI {
    @GET("sprints/{id}")
    suspend fun getSprint(@Path("id") id: String): ApiResponse<SprintDto>

    @GET("sprints/project/{id}")
    suspend fun getSprintsOfProject(@Path("id") id: String): ApiResponse<List<SprintDto>>

    @GET("sprints/active/project/{id}")
    suspend fun getActiveSprintByProject(@Path("id") id: String): ApiResponse<SprintDto>

    @GET("sprints/report/{id}")
    suspend fun getSprintReport(@Path("id") id: String): ApiResponse<ReportChartDto>

    @POST("sprints")
    suspend fun addSprint(@Body dto: SprintDto): ApiResponse<SprintDto>

    @PATCH("sprints")
    suspend fun updateSprint(@Body dto: SprintDto): ApiResponse<SprintDto>

    @DELETE("sprints/{id}")
    suspend fun deleteSprint(@Path("id") id: String): ApiResponse<String>
}