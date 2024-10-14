package com.example.mercadolibremobiletest.domain.model

data class CategoryDetails(
    val attributable: Boolean,
    val attributeTypes: String,
    val id: String,
    val name: String,
    val pathFromRootResponse: List<PathFromRoot>,
    val permalink: Any?,
    val picture: Any?,
    val totalItemsInThisCategory: Int
)
