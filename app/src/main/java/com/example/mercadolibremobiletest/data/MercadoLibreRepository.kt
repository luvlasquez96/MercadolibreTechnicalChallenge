package com.example.mercadolibremobiletest.data

import com.example.mercadolibremobiletest.data.remote.model.CategoriesListResponse
import com.example.mercadolibremobiletest.domain.model.CategoriesItem

interface MercadoLibreRepository {

    suspend fun getCategoriesListAsync(): Result<List<CategoriesItem>>

    suspend fun getItemsListAsync(query: String): List<String>
}