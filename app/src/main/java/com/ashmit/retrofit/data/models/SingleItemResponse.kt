package com.ashmit.retrofit.data.models

// Represents the response from the API for fetching a single item by ID
data class SingleItemResponse(
    val data: UserItem,
    val message: String,
    val status: String
)
