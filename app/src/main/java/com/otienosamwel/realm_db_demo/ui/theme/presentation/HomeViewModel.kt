package com.otienosamwel.realm_db_demo.ui.theme.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otienosamwel.realm_db_demo.data.models.PostsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val isLoading = mutableStateOf(false)
    val hasNetworkError = mutableStateOf(false)


    val posts: MutableLiveData<List<PostsItem>> = MutableLiveData()

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            GetPostsUseCase().invoke().collect { resource ->
                when (resource) {
                    is Resource.Loading -> isLoading.value = true

                    is Resource.Success -> {
                        isLoading.value = false
                        hasNetworkError.value = false
                        posts.postValue(resource.data ?: listOf())
                    }

                    is Resource.Error -> {
                        isLoading.value = false
                        hasNetworkError.value = true
                        posts.postValue(resource.data ?: listOf())
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}