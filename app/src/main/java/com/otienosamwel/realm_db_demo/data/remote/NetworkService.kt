package com.otienosamwel.realm_db_demo.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private val loggingInterceptor = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    val service: ApiEndpoints = retrofit.create(ApiEndpoints::class.java)
}