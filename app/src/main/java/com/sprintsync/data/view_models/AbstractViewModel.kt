package com.sprintsync.data.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprintsync.data.view_models.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class AbstractViewModel<T> : ViewModel() {
    protected val scope = viewModelScope

    private val _state = MutableStateFlow(State<T>())
    val state = _state.asStateFlow()

    protected fun update(newState: State<T>) = _state.update { newState }
    protected fun addToDtoList(item: T) = _state.update {
        State(
            dto = _state.value.dto, dtoList = _state.value.dtoList?.plus(
                item
            ),
            message = _state.value.message,
            error = _state.value.error
        )
    }
}