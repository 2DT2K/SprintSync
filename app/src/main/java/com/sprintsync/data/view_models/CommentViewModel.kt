package com.sprintsync.data.view_models

import android.util.Log
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
        setLoading(true)
        scope.launch {
            try {
                val response = service.getComment(id)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("CommentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }

    }

    fun getCommentsOfTask(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getCommentsOfTask(id)
                update(State(dtoList = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("CommentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addComment(dto: CommentDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.addComment(dto)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("CommentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addCommentToTask(dto: CommentDto, id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.addCommentToTask(dto, id)
                update(
                    State(
                        dto = response.data,
                        dtoList = state.value.dtoList,
                        error = response.err
                    )
                )
                response.data?.let { addToDtoList(it) }
            } catch (e: Exception) {
                Log.e("CommentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun updateComment(dto: CommentDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.updateComment(dto)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("CommentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun deleteComment(id: String) {
        setLoading(true)
        scope.launch {
            try{
                val response = service.deleteComment(id)
                update(State(message = response.data, error = response.err))
            } catch (e: Exception){
                Log.e("CommentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }
}