package com.example.mercadolibremobiletest.data

import com.example.mercadolibremobiletest.data.remote.model.CategoriesListResponse
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import com.example.mercadolibremobiletest.domain.model.CategoryDetails
import com.example.mercadolibremobiletest.domain.model.Item

interface MercadoLibreRepository {

    suspend fun getCategoriesListAsync(): Result<List<CategoriesItem>>

    suspend fun getItemsListAsync(query: String): Result<Item>

    suspend fun getCategoryByIdAsync(id: String): Result<CategoryDetails>
}