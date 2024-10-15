package com.example.mercadolibremobiletest.domain.model

data class SearchResult(
    val acceptsMercadopago: Boolean,
    val availableQuantity: Int,
    val attributeResponses: List<Attribute>,
    val buyingMode: String,
    val catalogListing: Boolean,
    val categoryId: String,
    val condition: String,
    val currencyId: String,
    val domainId: String,
    val id: String,
    val inventoryId: String?,
    val listingTypeId: String,
    val officialStoreId: Int?,
    val officialStoreName: String?,
    val permalink: String?,
    val price: Double,
    val salePriceResponse: SalePrice,
    val sanitizedTitle: String,
    val sellerResponse: Seller,
    val siteId: String,
    val thumbnail: String,
    val title: String,
)

