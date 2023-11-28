package com.sprintsync.data.view_models

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BacklogViewModel(private val backlogRepository: String) : ViewModel() {
    data class Sprint(
        val id: Int,
        val sprintName: String,
        val sprintNumber: Int,
        val startDate: String,
        val endDate: String,
        val task: List<Task>,
        val isDone: Boolean
    )

    data class Task(
        val id: Int,
        val name: String,
        val title: String,
        var assignees: MutableList<Bitmap>,
        val point: Int,
        val status: Int,
        var label: List<String>,
        var type: String
    )

    data class Member(val id: Int, val name: String, val email: String, val password: String)

    data class SprintUiState(val activeSprint: List<Sprint>, val doneSprints: List<Sprint>)

    //demo
    private val _uiState = MutableStateFlow(
        SprintUiState(
            emptyList(), emptyList()
        )
    )

    //demo
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<SprintUiState> = _uiState.asStateFlow()

    init {
        val members = listOf(
            Member(1, "John Doe", "john@example.com", "password123"),
            Member(2, "Alice Smith", "alice@example.com", "securepass")
        )

        val task1 = Task(
            1, "Task 1", "play", mutableListOf(), 100, 1, listOf("HomePage", "FE"),
            "Task"
        )
        val task2 = Task(2, "Task 2", "study", mutableListOf(), 100, 2, listOf("FE"), "Bug")

        val doneSprints = listOf(
            Sprint(
                id = 1,
                sprintName = "Sprint 1",
                sprintNumber = 1,
                startDate = "2023-12-01",
                endDate = "2023-12-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 2",
                sprintNumber = 1,
                startDate = "2023-10-01",
                endDate = "2023-10-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 1",
                sprintNumber = 1,
                startDate = "2023-12-01",
                endDate = "2023-12-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 2",
                sprintNumber = 1,
                startDate = "2023-10-01",
                endDate = "2023-10-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 1",
                sprintNumber = 1,
                startDate = "2023-12-01",
                endDate = "2023-12-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 2",
                sprintNumber = 1,
                startDate = "2023-10-01",
                endDate = "2023-10-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 1",
                sprintNumber = 1,
                startDate = "2023-12-01",
                endDate = "2023-12-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 2",
                sprintNumber = 1,
                startDate = "2023-10-01",
                endDate = "2023-10-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 1",
                sprintNumber = 1,
                startDate = "2023-12-01",
                endDate = "2023-12-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 2",
                sprintNumber = 1,
                startDate = "2023-10-01",
                endDate = "2023-10-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 1",
                sprintNumber = 1,
                startDate = "2023-12-01",
                endDate = "2023-12-15",
                task = listOf(task1, task2),
                isDone = true
            ),
            Sprint(
                id = 1,
                sprintName = "Sprint 2",
                sprintNumber = 1,
                startDate = "2023-10-01",
                endDate = "2023-10-15",
                task = listOf(task1, task2),
                isDone = true
            ),
        )

        val activeSprint = listOf(
            Sprint(
                id = 3,
                sprintName = "Sprint 3",
                sprintNumber = 3,
                startDate = "2023-12-01",
                endDate = "2023-12-15",
                task = listOf(
                    task1,
                    task2,
                    task1,
                    task2,
                    task1,
                    task2,
                    task1
                ),
                isDone = true
            ), Sprint(
                id = 3,
                sprintName = "Sprint 4",
                sprintNumber = 3,
                startDate = "2023-12-01",
                endDate = "2023-12-15",
                task = listOf(
                    task1,
                    task2,
                    task1,
                    task2,
                    task1,
                ),
                isDone = true
            )
        )

        _uiState.value = SprintUiState(activeSprint, doneSprints)
    }

    fun updateBacklog(index: Int) {

    }
}