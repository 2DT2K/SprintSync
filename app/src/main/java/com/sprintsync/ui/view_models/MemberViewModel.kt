package com.sprintsync.ui.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprintsync.api.ExampleApi
import com.sprintsync.data_classes.Member
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MemberViewModel : ViewModel() {
	//    THIS IS EXAMPLE CODE TO SHOW HOW TO USE RETROFIT
	val retrofit = Retrofit
		.Builder()
		.baseUrl("Dien api vao day")
		.addConverterFactory(GsonConverterFactory.create())
		.build()

	val apiService = retrofit.create(ExampleApi::class.java)
	private val _state = MutableStateFlow(emptyList<Member>())

	val posts: StateFlow<List<Member>>
		get() = _state

	init {
		viewModelScope.launch {
			try {
				_state.value = apiService.getPosts()
			}
			catch (e: Exception) {
				e.printStackTrace()
			}
		}
	}

	fun deletePost(postId: String) {
		viewModelScope.launch {
			try {
				apiService.deletePost(postId)
				_state.value = _state.value.filter { it.id != postId }
				Log.e("PostViewModel", "deletePost code: $postId")
			}
			catch (e: Exception) {
				e.printStackTrace()
			}
		}
	}
}