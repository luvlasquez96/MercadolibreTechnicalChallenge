package com.example.mercadolibremobiletest.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @Expose @SerializedName("accepts_mercadopago") val acceptsMercadopago: Boolean,
    @Expose @SerializedName("available_quantity") val availableQuantity: Int,
    @Expose @SerializedName("buying_mode") val buyingMode: String,
    @Expose @SerializedName("catalog_listing") val catalogListing: Boolean,
    @Expose @SerializedName("category_id") val categoryId: String,
    @Expose val condition: String,
    @Expose @SerializedName("currency_id") val currencyId: String,
    @Expose @SerializedName("domain_id") val domainId: String,
    @Expose val id: String,
    @Expose @SerializedName("inventory_id") val inventoryId: String?,
    @Expose @SerializedName("listing_type_id") val listingTypeId: String,
    @Expose @SerializedName("official_store_id") val officialStoreId: Int?,
    @SerializedName("official_store_name") val officialStoreName: String?,
    @Expose @SerializedName("permalink") val permalink: String?,
    @Expose val price: Double,
    @Expose @SerializedName("sale_price") val salePriceResponse: SalePriceResponse,
    @Expose @SerializedName("sanitized_title") val sanitizedTitle: String,
    @Expose @SerializedName("seller") val sellerResponse: SellerResponse,
    @Expose @SerializedName("site_id") val siteId: String,
    @Expose val thumbnail: String,
    @Expose val title: String,
)