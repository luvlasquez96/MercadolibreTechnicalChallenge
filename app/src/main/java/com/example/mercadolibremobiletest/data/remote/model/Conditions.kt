package com.example.mercadolibremobiletest.data.remote.model

data class Conditions(
    val context_restrictions: List<String>,
    val eligible: Boolean,
    val end_time: String,
    val start_time: String
)