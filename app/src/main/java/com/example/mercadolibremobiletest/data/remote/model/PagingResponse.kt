package com.example.mercadolibremobiletest.data.remote.model

import com.google.gson.annotations.SerializedName

data class PagingResponse(
    val limit: Int,
    val offset: Int,
    @SerializedName("primary_results") val primaryResults: Int,
    val total: Int
)