package com.example.mercadolibremobiletest.domain.model

import com.google.gson.annotations.SerializedName

data class SalePrice(
    val amount: Double,
    val currencyId: String,
    val paymentMethodType: String,
    val priceId: String,
    val regularAmount: Double,
    val type: String
)
