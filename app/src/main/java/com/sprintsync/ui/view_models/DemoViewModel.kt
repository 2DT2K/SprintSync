package com.sprintsync.ui.view_models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DemoViewModel : ViewModel() {
	private val _name = MutableStateFlow("Default val")
	val name: StateFlow<String> = _name.asStateFlow()

	fun onChangeName(newName: String) {
		_name.update { newName }
	}
}