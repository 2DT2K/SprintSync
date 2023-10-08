package com.sprintsync.ui.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.log

class ProjectViewViewModel : ViewModel() {
    // TODO: implement data, state update cho project view sau, tam thoi update trong UI

    data class ProjectList(
        val avatar: String,
        val projectName: String,
        val projectKey: String,
        var isStarred: Boolean
    )

    val projectList = mutableStateListOf<ProjectList>()

    init {
        val list = listOf(
            ProjectList("Student 1", "khoidz2", "khoidz", true),
            ProjectList("Student 1", "khoidz1", "khoidz", true),
            ProjectList("Student 1", "khoidz2", "khoidz", false),
            ProjectList("Student 1", "khoidz3", "khoidz", false),
            ProjectList("Student 1", "khoidz4", "khoidz", true),
            ProjectList("Student 1", "khoidz5", "khoidz", true),
            ProjectList("Student 1", "khoidz6", "khoidz", true),
            ProjectList("Student 1", "khoi2dz2", "khoidz", true),
        )
        projectList.addAll(list)
    }

    fun updateProjectList(index: Int) {
        val project = projectList[index]
        val isStarred = project.isStarred
        projectList[index] = project.copy(isStarred = !isStarred)
    }
}

