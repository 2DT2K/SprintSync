package com.sprintsync.data.view_models

import com.sprintsync.data.api.CommentAPI
import com.sprintsync.data.dtos.CommentDto
import com.sprintsync.data.dtos.response.CommentResDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val service: CommentAPI
) : AbstractViewModel<CommentResDto>() {
    fun getComment(id: String) {
        scope.launch {
            val response = service.getComment(id)
            update(State(dto = response.data, error = response.err))
        }
    }

    fun getCommentsOfTask(id: String) {
        setLoading(true)
        scope.launch {
            val response = service.getCommentsOfTask(id)
            update(State(dtoList = response.data, error = response.err))
            setLoading(false)
        }
    }

    fun addComment(dto: CommentDto) {
        scope.launch {
            val response = service.addComment(dto)
            update(State(dto = response.data, error = response.err))
        }
    }

    fun addCommentToTask(dto: CommentDto, id: String) {
        setLoading(true)
        scope.launch {
            val response = service.addCommentToTask(dto, id)
            update(State(dto = response.data, dtoList = state.value.dtoList, error = response.err))
            response.data?.let { addToDtoList(it) }
            setLoading(false)
        }
    }

    fun updateComment(dto: CommentDto) {
        scope.launch {
            val response = service.updateComment(dto)
            update(State(dto = response.data, error = response.err))
        }
    }

    fun deleteComment(id: String) {
        scope.launch {
            val response = service.deleteComment(id)
            update(State(message = response.data, error = response.err))
        }
    }
}