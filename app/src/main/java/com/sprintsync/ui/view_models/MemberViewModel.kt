package com.sprintsync.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprintsync.api.MemberAPI
import com.sprintsync.api.RetrofitSingleton
import com.sprintsync.data_classes.Member
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MemberViewModel : ViewModel() {
	//    THIS IS EXAMPLE CODE TO SHOW HOW TO USE RETROFIT
	private val apiService = RetrofitSingleton
		.getInstance()
		.createService(MemberAPI::class.java)

	private val _state = MutableStateFlow(emptyList<Member>())

	val posts: StateFlow<List<Member>>
		get() = _state

//	init {
//		viewModelScope.launch {
//			try {
//				_state.value = apiService.getMembers()
//			} catch (e: Exception) {
//				e.printStackTrace()
//			}
//		}
//	}

	fun getMembers() {
		viewModelScope.launch {
			try {
				_state.value = apiService.getMembers()
			}
			catch (e: Exception) {
				e.printStackTrace()
			}
		}
	}

//    fun deletePost(postId: String) {
//        viewModelScope.launch {
//            try {
//                apiService.deletePost(postId)
//                _state.value = _state.value.filter { it.id != postId }
//                Log.e("PostViewModel", "deletePost code: $postId")
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}