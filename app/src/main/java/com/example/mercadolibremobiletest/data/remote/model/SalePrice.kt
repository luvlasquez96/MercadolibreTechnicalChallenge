package com.example.mercadolibremobiletest.data.remote.model

import com.example.mercadolibremobiletest.data.remote.model.Conditions
import com.example.mercadolibremobiletest.data.remote.model.MetadataX

data class SalePrice(
    val amount: Double,
    val conditions: Conditions,
    val currency_id: String,
    val exchange_rate: Any,
    val metadata: MetadataX,
    val payment_method_prices: List<Any>,
    val payment_method_type: String,
    val price_id: String,
    val regular_amount: Double,
    val type: String
)