package com.example.mercadolibremobiletest.data.remote.mapper

import com.example.mercadolibremobiletest.data.remote.model.AttributeResponse
import com.example.mercadolibremobiletest.data.remote.model.ItemResponse
import com.example.mercadolibremobiletest.data.remote.model.PagingResponse
import com.example.mercadolibremobiletest.data.remote.model.SalePriceResponse
import com.example.mercadolibremobiletest.data.remote.model.SearchResultResponse
import com.example.mercadolibremobiletest.data.remote.model.SellerResponse
import com.example.mercadolibremobiletest.domain.model.Attribute
import com.example.mercadolibremobiletest.domain.model.Item
import com.example.mercadolibremobiletest.domain.model.Paging
import com.example.mercadolibremobiletest.domain.model.SalePrice
import com.example.mercadolibremobiletest.domain.model.SearchResult
import com.example.mercadolibremobiletest.domain.model.SearchResultUI
import com.example.mercadolibremobiletest.domain.model.Seller

fun ItemResponse.toItem(): Item {
    return Item(
        pagingResponse = this.pagingResponse.toPaging(),
        query = this.query,
        searchResultResponse = this.searchResultResponse.map { it.toResult() },
        siteId = this.siteId
    )
}

fun SearchResultResponse.toResult(): SearchResult {
    return SearchResult(
        id = this.id,
        title = this.title,
        price = this.price,
        thumbnail = this.thumbnail,
        siteId = this.siteId,
        sellerResponse = this.sellerResponse.toSeller(),
        condition = this.condition,
        availableQuantity = this.availableQuantity,
        permalink = this.permalink,
        currencyId = this.currencyId,
        buyingMode = this.buyingMode,
        listingTypeId = this.listingTypeId,
        officialStoreId = this.officialStoreId,
        officialStoreName = this.officialStoreName,
        catalogListing = this.catalogListing,
        acceptsMercadopago = this.acceptsMercadopago,
        domainId = this.domainId,
        categoryId = this.categoryId,
        inventoryId = this.inventoryId,
        salePriceResponse = this.salePriceResponse.toSalePrice(),
        sanitizedTitle = this.sanitizedTitle,
        attributeResponses = this.attributeResponses.map { it.toAttribute() }
    )
}

fun SalePriceResponse.toSalePrice(): SalePrice {
    return SalePrice(
        currencyId = this.currencyId,
        amount = this.amount,
        paymentMethodType = this.paymentMethodType,
        priceId = this.priceId,
        regularAmount = this.regularAmount,
        type = this.type
    )
}

fun SellerResponse.toSeller(): Seller {
    return Seller(
        id = this.id,
        nickname = this.nickname
    )
}

fun PagingResponse.toPaging(): Paging {
    return Paging(
        limit = this.limit,
        offset = this.offset,
        primaryResults = this.primaryResults,
        total = this.total
    )
}

fun AttributeResponse.toAttribute(): Attribute {
    return Attribute(
        id = this.id,
        name = this.name,
        valueName = this.valueName
    )
}

fun SearchResult.toSearchResultUI(): SearchResultUI {
    return SearchResultUI(
        acceptsMercadopago = this.acceptsMercadopago,
        availableQuantity = this.availableQuantity,
        buyingMode = this.buyingMode,
        catalogListing = this.catalogListing,
        categoryName = this.categoryId,
        condition = this.condition,
        id = this.id,
        listingTypeId = this.listingTypeId,
        officialStoreId = this.officialStoreId ?: 0,
        officialStoreName = this.officialStoreName ?: "",
        permalink = this.permalink ?: "",
        price = this.price,
        salePriceResponse = this.salePriceResponse,
        sellerResponse = this.sellerResponse,
        thumbnail = this.thumbnail,
        title = this.title,
        attribute = this.attributeResponses
    )
}