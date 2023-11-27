package com.sprintsync.data.dtos.response



data class ReportChartDto(
    val id: String? = null,
    val sprintNumber: Int,
    val listOfTask: List<List<TaskResDto>>,
)