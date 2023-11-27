package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.sprintsync.data.dtos.response.ReportChartDto
import com.sprintsync.ui.theme.spacing


@Composable
fun ReportChart(chartData: ReportChartDto, statusList: List<String>) {
    val listOfCompleteTask = mutableListOf<FloatEntry>()
    val listOfIncompleteTask = mutableListOf<FloatEntry>()
    var listOfTaskIndex = 0
    chartData.listOfTask.forEach {
        var completeTask = 0;
        it.forEach { it1 ->
            if (statusList[it1.statusIndex] == "Done") {
                completeTask += 1
            }
        }
        listOfCompleteTask.add(listOfTaskIndex, FloatEntry(0f, completeTask.toFloat()))
        listOfIncompleteTask.add(
            listOfTaskIndex,
            FloatEntry(0f, (it.size - completeTask).toFloat())
        )
        listOfTaskIndex += 1
    }
    val chartEntryModel = entryModelOf(listOfCompleteTask, listOfIncompleteTask)
    var sprintName by remember {
        mutableStateOf("Sprint 1")
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.medium,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()
    ) {
        ChartTitle(
            sprintName = sprintName,
            onSprintNameChange = {
                sprintName = it
            }
        )
        MainChart(chartEntryModel = chartEntryModel)
        ChartInfor(
            remaining = 10, completed = 20, remainingColor = Color(0xFF04BFDA),
            completedColor = Color(0xFF04BFDA)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReportChartPreview() {
    ReportChart()
}

