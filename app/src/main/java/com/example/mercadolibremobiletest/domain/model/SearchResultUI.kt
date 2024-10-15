package com.example.mercadolibremobiletest.domain.model

data class SearchResultUI(
    val acceptsMercadopago: Boolean,
    val availableQuantity: Int,
    val buyingMode: String,
    val catalogListing: Boolean,
    val categoryName: String,
    val condition: String,
    val id: String,
    val listingTypeId: String,
    val officialStoreId: Int,
    val officialStoreName: String,
    val permalink: String,
    val price: Double,
    val salePriceResponse: SalePrice,
    val sellerResponse: Seller,
    val thumbnail: String,
    val title: String,
    val attribute: List<Attribute>
)
