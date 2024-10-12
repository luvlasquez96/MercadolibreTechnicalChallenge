package com.example.mercadolibremobiletest.data.remote.model

data class PagingResponse(
    val limit: Int,
    val offset: Int,
    val primary_results: Int,
    val total: Int
)