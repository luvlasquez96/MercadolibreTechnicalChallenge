package com.example.mercadolibremobiletest.data.remote.model

data class SettingsResponse(
    val buying_modes: List<String>,
    val immediate_payment: String,
    val minimum_price: Int,
    val status: String
)