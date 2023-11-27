package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.data.view_models.ProjectViewModel
import com.sprintsync.data.view_models.SprintViewModel
import com.sprintsync.ui.components.reportview.ReportChart
import com.sprintsync.ui.theme.spacing


@Composable
fun ReportView(projectID: String?) {
    val sprintVM = hiltViewModel<SprintViewModel>()
    val projectVM = hiltViewModel<ProjectViewModel>()
    val reportState by sprintVM.report.collectAsStateWithLifecycle()
    val sprintState by sprintVM.state.collectAsStateWithLifecycle()
    val projectState by projectVM.state.collectAsStateWithLifecycle()

    val allTaskInSprint = reportState?.listOfTask
    val statusList = projectState.dto?.statuses

    val incompleteProblemsList = mutableListOf<TaskResDto>()
    val completeProblemsList = mutableListOf<TaskResDto>()
    var incompleteProblemsListIndex = 0;
    var completeProblemsListIndex = 0;

    LaunchedEffect(Unit) {
        if (projectID != null) {
            sprintVM.getInitialSprintReport(projectID)
        }
    }
    allTaskInSprint?.lastOrNull()?.forEach {
        if (statusList?.get(it.statusIndex) != "Done") {
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
        ReportChart(
            chartData = allTaskInSprint,
            statusList = statusList,
            updateReport = { id -> sprintVM.getSprintReport(id) },
            sprintList = sprintState.dtoList
        )
//        Problem(
//            title = "Incomplete problem",
//            incompleProblems = incompleteProblemsList
//        )
//        Column(
//            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
//            horizontalAlignment = Alignment.Start,
//        ) {
//            Divider()
//        }
//        Problem(
//            title = "Completed problems",
//            incompleProblems = completeProblemsList
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReportViewPreview() {
    ReportView("6524172bb9f63b47c37b739e")
}