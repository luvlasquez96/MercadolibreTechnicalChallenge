package com.example.mercadolibremobiletest.data.remote.model

import com.google.gson.annotations.SerializedName

data class SalePriceResponse(
    val amount: Double,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("exchange_rate") val exchangeRate: Any,
    @SerializedName("payment_method_prices") val paymentMethodPrices: List<Any>,
    @SerializedName("payment_method_type") val paymentMethodType: String,
    @SerializedName("price_id") val priceId: String,
    @SerializedName("regular_amount") val regularAmount: Double,
    val type: String
)