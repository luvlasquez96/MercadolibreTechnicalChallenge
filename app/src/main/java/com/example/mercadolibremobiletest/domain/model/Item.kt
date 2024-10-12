package com.example.mercadolibremobiletest.domain.model

data class Item(
    val pagingResponse: Paging,
    val query: String,
    val resultResponses: List<Result>,
    val siteId: String,
)
