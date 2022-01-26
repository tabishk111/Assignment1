package com.example.assignment.repository

import com.example.assignment.api.RetrofitInstance
import com.example.assignment.models.Post
import retrofit2.Response

class ProductRepository{
    suspend fun getDetails(post: Post): Response<com.example.assignment.models.ResponseData>{
        return RetrofitInstance.api.getDetails(post)
    }
}