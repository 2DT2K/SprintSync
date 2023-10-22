package com.sprintsync.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.components.TwoIconTopAppBar
import com.sprintsync.ui.components.reportview.Problem
import com.sprintsync.ui.components.reportview.ReportChart


@Composable
fun ReportView() {
    Scaffold(
        topBar = {
            TwoIconTopAppBar(title = "ReportView")
        },
        bottomBar = {
            BottomNavigation()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            ReportChart()
            Problem(
                title = "Incomplete problem",
                incompleProblems = listOf(fakeData, fakeData, fakeData, fakeData)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Divider()
            }
            Problem(
                title = "Completed problems",
                incompleProblems = listOf(fakeData, fakeData, fakeData, fakeData)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportViewPreview(){
    ReportView()
}