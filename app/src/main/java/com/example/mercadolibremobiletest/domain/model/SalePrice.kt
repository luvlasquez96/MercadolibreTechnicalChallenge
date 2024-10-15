package com.example.mercadolibremobiletest.domain.model

data class SalePrice(
    val amount: Double,
    val currencyId: String,
    val paymentMethodType: String,
    val priceId: String,
    val regularAmount: Double,
    val type: String
)
