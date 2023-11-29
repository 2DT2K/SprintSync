package com.sprintsync.data.view_models

import android.util.Log
import com.sprintsync.data.api.TeamAPI
import com.sprintsync.data.dtos.TeamDto
import com.sprintsync.data.dtos.response.TeamResDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val service: TeamAPI
) : AbstractViewModel<TeamResDto>() {
    fun getTeam(id: String) {
        setLoading(true)
        scope.launch {
            val response = service.getTeam(id)
            update(State(dto = response.data, error = response.err))
            setLoading(false)
        }
    }

    fun getTeamsOfProject(id: String) {
        setLoading(true)
        scope.launch {
            val response = service.getTeamsOfProject(id)
            update(State(dtoList = response.data, error = response.err))
            setLoading(false)
        }
    }

    fun addTeam(dto: TeamDto) {
        scope.launch {
            val response = service.addTeam(dto)
            update(State(dto = response.data, dtoList = state.value.dtoList, error = response.err))
            response.data?.let { addToDtoList(it) }
        }
    }

    fun addMember(email: String, teamId: String) {
        scope.launch {
            val response = service.addMember(email, teamId)
            update(State(dto = response.data,dtoList = state.value.dtoList, error = response.err))
            response.data?.let { addToDtoList(it) }
        }
    }

    fun updateTeam(dto: TeamDto) {
        scope.launch {
            val response = service.updateTeam(dto)
            update(State(dto = response.data, error = response.err))
        }
    }

    fun deleteTeam(id: String) {
        scope.launch {
            val response = service.deleteTeam(id)
            update(State(message = response.data, error = response.err))
        }
    }
}