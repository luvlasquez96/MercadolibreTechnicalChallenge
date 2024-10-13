package com.example.mercadolibremobiletest.data.remote.model

import com.google.gson.annotations.SerializedName

data class CategoryDetailsResponse(
    val attributable: Boolean,
    @SerializedName("attribute_types") val attributeTypes: String,
    val id: String,
    val name: String,
    @SerializedName("path_from_root") val pathFromRootResponse: List<PathFromRootResponse>,
    val permalink: Any,
    val picture: Any,
    @SerializedName("total_items_in_this_category") val totalItemsInThisCategory: Int
)