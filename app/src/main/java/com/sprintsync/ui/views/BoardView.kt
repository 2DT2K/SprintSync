package com.sprintsync.ui.views

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.data.view_models.SprintViewModel
import com.sprintsync.data.view_models.TaskViewModel
import com.sprintsync.ui.components.boardview.BoardViewCategory
import com.sprintsync.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BoardView(projectID: String?, statusList: List<String>?) {
    val taskViewVM = hiltViewModel<TaskViewModel>()
    val sprintVM = hiltViewModel<SprintViewModel>()
    val tasksState by taskViewVM.state.collectAsStateWithLifecycle()
    val sprintState by sprintVM.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        if (projectID != null) {
            sprintVM.getActiveSprintByProject(projectID)
        }
    }

    LaunchedEffect(sprintState.dto) {
        sprintState.dto?.id?.let { taskViewVM.getTasksOfSprint(it) }
    }


    val pageCount = 3

    @OptIn(ExperimentalFoundationApi::class)
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    Box(
        Modifier
            .fillMaxHeight()
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(MaterialTheme.spacing.medium),
        ) {
            val taskList = tasksState.dtoList
            val taskListWithStatus = mutableListOf<TaskResDto>()
            var numberOfTaskWithStatus = 0;
            taskList?.forEach { it1 ->
                if (it1.statusIndex == it) {
                    numberOfTaskWithStatus += 1;
                    taskListWithStatus.add(it1)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BoardViewCategory(
                    categoryName = statusList?.get(it),
                    numberOfTask = numberOfTaskWithStatus,
                    taskList = taskListWithStatus
                )
            }

        }
        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BoardViewPreview() {
    BoardView("6524172bb9f63b47c37b739e", listOf("Todo"))
}