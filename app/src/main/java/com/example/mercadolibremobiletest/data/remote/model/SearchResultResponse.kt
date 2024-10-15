package com.example.mercadolibremobiletest.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResultResponse(
    @SerializedName("accepts_mercadopago") val acceptsMercadopago: Boolean,
    @SerializedName("available_quantity") val availableQuantity: Int,
    @Expose @SerializedName("attributes") val attributeResponses: List<AttributeResponse>,
    @SerializedName("buying_mode") val buyingMode: String,
    @SerializedName("catalog_listing") val catalogListing: Boolean,
    @SerializedName("category_id") val categoryId: String,
    val condition: String,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("domain_id") val domainId: String,
    val id: String,
    @SerializedName("inventory_id") val inventoryId: String?,
    @SerializedName("listing_type_id") val listingTypeId: String,
    @SerializedName("official_store_id") val officialStoreId: Int?,
    @SerializedName("official_store_name") val officialStoreName: String?,
    @SerializedName("permalink") val permalink: String?,
    val price: Double,
    @SerializedName("sale_price") val salePriceResponse: SalePriceResponse,
    @SerializedName("sanitized_title") val sanitizedTitle: String,
    @SerializedName("seller") val sellerResponse: SellerResponse,
    @SerializedName("site_id") val siteId: String,
    val thumbnail: String,
    val title: String,
)