package com.example.assignment.repository

import com.example.assignment.api.RetrofitInstance
import com.example.assignment.models.Post
import retrofit2.Response
import retrofit2.http.POST

class ProductRepository{
    suspend fun getDetails(post: Post): Response<com.example.assignment.models.Response>{
        return RetrofitInstance.api.getDetails(post)
    }
}