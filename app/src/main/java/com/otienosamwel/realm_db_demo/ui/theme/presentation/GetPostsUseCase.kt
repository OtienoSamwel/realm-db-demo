package com.otienosamwel.realm_db_demo.ui.theme.presentation

import com.otienosamwel.realm_db_demo.data.local.RealmRepository
import com.otienosamwel.realm_db_demo.data.local.insertPosts
import com.otienosamwel.realm_db_demo.data.models.PostsItem
import com.otienosamwel.realm_db_demo.data.remote.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPostsUseCase {
    operator fun invoke(): Flow<Resource<List<PostsItem>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val posts = NetworkService.service.getPosts()

                //insert into local db
                posts.insertPosts()

                emit(Resource.Success(posts))
            } catch (e: Exception) {
                emit(
                    Resource.Error(
                        message = e.message ?: "An error occurred",
                        data = RealmRepository.getLocalPosts()
                    )
                )
            }
        }
    }
}


sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}