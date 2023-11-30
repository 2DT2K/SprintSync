package com.sprintsync.data.dtos.response


data class ReportChartDto(
    val id: String? = null,
    val sprintNumber: Int = 0,
    val listOfTask: List<List<TaskResDto>> = emptyList(),
)