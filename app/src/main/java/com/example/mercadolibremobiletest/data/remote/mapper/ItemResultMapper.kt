package com.example.mercadolibremobiletest.data.remote.mapper

import com.example.mercadolibremobiletest.data.remote.model.ItemResponse
import com.example.mercadolibremobiletest.data.remote.model.PagingResponse
import com.example.mercadolibremobiletest.data.remote.model.ResultResponse
import com.example.mercadolibremobiletest.data.remote.model.SalePriceResponse
import com.example.mercadolibremobiletest.data.remote.model.SellerResponse
import com.example.mercadolibremobiletest.domain.model.Item
import com.example.mercadolibremobiletest.domain.model.Paging
import com.example.mercadolibremobiletest.domain.model.Result
import com.example.mercadolibremobiletest.domain.model.SalePrice
import com.example.mercadolibremobiletest.domain.model.Seller

fun ItemResponse.toItem(): Item{
    return Item(
        pagingResponse = this.pagingResponse.toPaging(),
        query = this.query,
        resultResponses = this.resultResponses.map { it.toResult() },
        siteId = this.siteId
    )
}

fun ResultResponse.toResult(): Result{
    return Result(
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
        sanitizedTitle = this.sanitizedTitle
    )
}

fun SalePriceResponse.toSalePrice(): SalePrice{
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