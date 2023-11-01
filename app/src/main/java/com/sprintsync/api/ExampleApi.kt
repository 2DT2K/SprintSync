package com.sprintsync.api

import com.sprintsync.data_classes.Member
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ExampleApi {
    @GET("members")
    suspend fun getPosts(): List<Member>
    @GET("members/{id}")
    suspend fun getPost(@Path("id") postid: Int): List<Member>

    @POST("members")
    suspend fun createPost(@Body member: Member): Response<Member>

    @DELETE("members/{id}")
    suspend fun deletePost(@Path("id") postId: String)
}