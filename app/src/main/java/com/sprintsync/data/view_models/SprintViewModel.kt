package com.sprintsync.data.view_models

import android.annotation.SuppressLint
import com.sprintsync.data.api.SprintAPI
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.dtos.response.ReportChartDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SprintViewModel @Inject constructor(
    private val service: SprintAPI
) : AbstractViewModel<SprintDto>() {
    private val _report = MutableStateFlow(ReportChartDto())
    val report = _report.asStateFlow()

    private fun updateReport(dto: ReportChartDto) = _report.update { dto }

    fun getSprint(id: String) {
        scope.launch {
            val response = service.getSprint(id)
            update(State(dto = response.data, error = response.err))
        }
    }

    fun getSprintsOfProject(id: String) {
        setLoading(true)
        scope.launch {
            val response = service.getSprintsOfProject(id)
            update(State(dto = state.value.dto, dtoList = response.data, error = response.err))
            setLoading(false)
        }
    }

    fun getSprintReport(id: String) {
        setLoading(true)
        scope.launch {
            val response = service.getSprintReport(id)
            response.data?.let { updateReport(it) }
            setLoading(false)
        }
    }

    fun getActiveSprintByProject(id:String) {
        setLoading(true)
		scope.launch {
			val response = service.getActiveSprintByProject(id)
            update(State(dto = response.data, dtoList = state.value.dtoList ,error = response.err))
            setLoading(false)
		}
	}

    fun getInitialSprintReport(projectId: String) {
        setLoading(true)
        scope.launch {
            val response = service.getSprintsOfProject(projectId)
            update(State(dtoList = response.data, error = response.err))
            val sprints = response.data ?: emptyList()
            val initialSprintsId = sprints.lastOrNull()?.id
            val sprintReport = initialSprintsId?.let { service.getSprintReport(it) }
            if (sprintReport != null) {
                sprintReport.data?.let { updateReport(it) }
            }
            setLoading(false)
        }
    }

    fun addSprint(dto: SprintDto) {
        scope.launch {
            val response = service.addSprint(dto)
            update(State(dto = response.data, dtoList = state.value.dtoList, error = response.err))
            response.data?.let { addToDtoList(it) }
        }
    }

    fun updateSprint(dto: SprintDto) {
        scope.launch {
            val response = service.updateSprint(dto)
            update(State(dto = response.data, dtoList = state.value.dtoList, error = response.err))
        }
    }

    fun deleteSprint(id: String) {
        scope.launch {
            val response = service.deleteSprint(id)
            update(State(message = response.data, error = response.err))
        }
    }

}