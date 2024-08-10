package com.ashmit.retrofit.data.models

// Represents the response from the API for fetching the whole table data
data class ApiResponse(
    val data: List<UserItem>,
    val message: String,
    val status: String
)
