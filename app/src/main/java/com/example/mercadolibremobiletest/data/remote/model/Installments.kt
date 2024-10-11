package com.example.mercadolibremobiletest.data.remote.model

data class Installments(
    val amount: Double,
    val currency_id: String,
    val metadata: Metadata,
    val quantity: Int,
    val rate: Double
)