package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.data.dtos.response.ReportChartDto
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.ui.components.reportview.Problem
import com.sprintsync.ui.components.reportview.ReportChart
import com.sprintsync.ui.theme.spacing


@Composable
fun ReportView() {
    val incompleteProblemsList = mutableListOf<TaskResDto>()
    val completeProblemsList = mutableListOf<TaskResDto>()
    var incompleteProblemsListIndex = 0;
    var completeProblemsListIndex = 0;
    reportChartDetails.listOfTask.lastOrNull()?.forEach {
        if (statusList[it.statusIndex] != "Done") {
            incompleteProblemsList.add(incompleteProblemsListIndex, it)
            incompleteProblemsListIndex += 1
        } else {
            completeProblemsList.add(completeProblemsListIndex, it)
            completeProblemsListIndex += 1
        }
    }
    Column(
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.largeDefault,
            Alignment.Top
        ),
        horizontalAlignment = Alignment.Start,
    ) {
        ReportChart(chartData = reportChartDetails, statusList = statusList)
        Problem(
            title = "Incomplete problem",
            incompleProblems = incompleteProblemsList
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start,
        ) {
            Divider()
        }
        Problem(
            title = "Completed problems",
            incompleProblems = completeProblemsList
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReportViewPreview() {
    ReportView()
}