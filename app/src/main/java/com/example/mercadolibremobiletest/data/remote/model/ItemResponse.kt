package com.example.mercadolibremobiletest.data.remote.model

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("paging") val pagingResponse: PagingResponse,
    val query: String,
    @SerializedName("results") val resultResponses: List<ResultResponse>,
    @SerializedName("site_id") val siteId: String,
)