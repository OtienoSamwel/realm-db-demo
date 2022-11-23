package com.otienosamwel.realm_db_demo.data.remote

import com.otienosamwel.realm_db_demo.data.models.PostsItem
import retrofit2.http.GET

interface ApiEndpoints {

    @GET("/posts")
    suspend fun getPosts(): List<PostsItem>
}