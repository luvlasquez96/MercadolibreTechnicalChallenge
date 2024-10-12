package com.example.mercadolibremobiletest.domain.model

data class Paging(
    val limit: Int,
    val offset: Int,
    val primaryResults: Int,
    val total: Int
)
