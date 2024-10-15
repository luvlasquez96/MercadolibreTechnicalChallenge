package com.example.mercadolibremobiletest.data.remote.model

import com.google.gson.annotations.SerializedName

data class AttributeResponse(
    val id: String,
    val name: String,
    @SerializedName("value_name") val valueName: String?,
)