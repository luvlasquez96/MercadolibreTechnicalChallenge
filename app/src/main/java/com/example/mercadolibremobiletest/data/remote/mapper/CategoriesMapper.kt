package com.example.mercadolibremobiletest.data.remote.mapper

import com.example.mercadolibremobiletest.data.remote.model.CategoriesListResponse
import com.example.mercadolibremobiletest.domain.model.CategoriesItem

fun CategoriesListResponse.toCategoriesItem(): List<CategoriesItem> {
    return this.map { categoryResponseItem ->
        with(categoryResponseItem) {
            CategoriesItem(id, name)
        }
    }
}