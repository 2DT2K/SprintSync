package com.sprintsync.data.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

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
		)
		projectList.addAll(list)
	}

	fun updateProjectList(index: Int) {
		val project = projectList[index]
		val isStarred = project.isStarred
		projectList[index] = project.copy(isStarred = !isStarred)
	}
}

