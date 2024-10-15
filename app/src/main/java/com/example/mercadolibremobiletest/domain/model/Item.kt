package com.example.mercadolibremobiletest.domain.model

data class Item(
    val pagingResponse: Paging,
    val query: String,
    val searchResultResponse: List<SearchResult>,
    val siteId: String,
)
