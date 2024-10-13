package com.example.mercadolibremobiletest.data.remote

import com.example.mercadolibremobiletest.data.dataAccess.MercadoLibreService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val mercadoLibreService: MercadoLibreService) {

    suspend fun getCategoriesListAsync() = mercadoLibreService.getCategoriesListAsync()

    suspend fun getItemsListAsync(query: String) = mercadoLibreService.getItemsListAsync(query)

    suspend fun getCategoryByIdAsync(id: String) = mercadoLibreService.getCategoryByIdAsync(id)
}