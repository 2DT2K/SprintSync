package com.sprintsync.ui.view_models

import androidx.lifecycle.ViewModel
import com.sprintsync.data.view_models.BacklogViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskListViewModel : ViewModel() {
    val _taskListUiState = MutableStateFlow(
        listOf(
            BacklogViewModel.Sprint(
                1,
                "thisfsdfafads",
                1,
                "",
                "",
                listOf(
                    BacklogViewModel.Task(
                        1,
                        "Task 1",
                        "play",
                        mutableListOf(),
                        100,
                        1,
                        listOf("HomePage", "FE"),
                        "Task"
                    ),
                    BacklogViewModel.Task(
                        1,
                        "Task 1",
                        "play",
                        mutableListOf(),
                        100,
                        2,
                        listOf("HomePage", "FE"),
                        "Bug"
                    ),
                    BacklogViewModel.Task(
                        1,
                        "Task 1",
                        "play",
                        mutableListOf(),
                        100,
                        3,
                        listOf("HomePage", "FE"),
                        "Task"
                    ),
                    BacklogViewModel.Task(
                        1,
                        "Task 1",
                        "play",
                        mutableListOf(),
                        100,
                        1,
                        listOf("HomePage", "FE"),
                        "Bug"
                    )
                ),
                true
            )
        )
    )

    val uiState: StateFlow<List<BacklogViewModel.Sprint>> = _taskListUiState.asStateFlow()

    init {

    }
}