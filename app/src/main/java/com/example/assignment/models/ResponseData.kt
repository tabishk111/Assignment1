package com.example.assignment.models

import com.example.assignment.models.Data
import com.example.assignment.models.Pagination

data class ResponseData(
    val data: Data,
    val message: String,
    val pagination: Pagination,
    val status: String,
    val statusCode: Int
)