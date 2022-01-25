package com.example.assignment.api

import com.example.assignment.models.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductAPI {
    @POST("posts")
    suspend fun getDetails(
        @Body post: Post
    ) : Response<com.example.assignment.models.Response>
}