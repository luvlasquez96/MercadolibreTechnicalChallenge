package com.example.mercadolibremobiletest.data.remote.model

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    val pagingResponse: PagingResponse,
    val query: String,
    val resultResponses: List<ResultResponse>,
    @SerializedName("site_id") val siteId: String,
)