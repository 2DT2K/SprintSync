package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.data.view_models.SprintViewModel
import com.sprintsync.ui.components.reportview.Problem
import com.sprintsync.ui.components.reportview.ReportChart
import com.sprintsync.ui.theme.spacing


@Composable
fun ReportView(projectID: String?,statusList:List<String>?) {
    val sprintVM = hiltViewModel<SprintViewModel>()
    val reportState by sprintVM.report.collectAsStateWithLifecycle()
    val sprintState by sprintVM.state.collectAsStateWithLifecycle()


    val allTaskInSprint = reportState.listOfTask

    val incompleteProblemsList = mutableListOf<TaskResDto>()
    val completeProblemsList = mutableListOf<TaskResDto>()

    LaunchedEffect(Unit) {
        if (projectID != null) {
            sprintVM.getInitialSprintReport(projectID)
        }
    }
    allTaskInSprint.lastOrNull()?.forEach {
        if (statusList?.get(it.statusIndex) != "Done") {
            incompleteProblemsList.add(it)
        } else {
            completeProblemsList.add(it)
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
        ReportChart(
            chartData = allTaskInSprint,
            statusList = statusList,
            updateReport = { id -> sprintVM.getSprintReport(id) },
            sprintList = sprintState.dtoList
        )
        Problem(
            title = "Incomplete problem",
            incompleteProblems = incompleteProblemsList,
            statusList = statusList
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start,
        ) {
            Divider()
        }
        Problem(
            title = "Completed problems",
            incompleteProblems = completeProblemsList,
            statusList = statusList
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ReportViewPreview() {
//    ReportView("6524172bb9f63b47c37b739e")
//}