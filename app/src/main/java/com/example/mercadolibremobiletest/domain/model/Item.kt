package com.example.mercadolibremobiletest.domain.model

import com.example.mercadolibremobiletest.data.remote.model.PagingResponse

data class Item(
    val pagingResponse: PagingResponse,
    val query: String,
    val resultResponses: List<Result>,
    val siteId: String,
)
