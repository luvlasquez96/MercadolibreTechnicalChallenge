package com.example.mercadolibremobiletest.domain.model

import com.example.mercadolibremobiletest.data.remote.model.SalePriceResponse
import com.example.mercadolibremobiletest.data.remote.model.SellerResponse

data class Result(
    val acceptsMercadopago: Boolean,
    val availableQuantity: Int,
    val buyingMode: String,
    val catalogListing: Boolean,
    val categoryId: String,
    val condition: String,
    val currencyId: String,
    val domainId: String,
    val id: String,
    val inventoryId: String,
    val listingTypeId: String,
    val officialStoreId: Int,
    val officialStoreName: String,
    val permalink: String,
    val price: Double,
    val salePriceResponse: SalePriceResponse,
    val sanitizedTitle: String,
    val sellerResponse: SellerResponse,
    val siteId: String,
    val thumbnail: String,
    val title: String,
)

