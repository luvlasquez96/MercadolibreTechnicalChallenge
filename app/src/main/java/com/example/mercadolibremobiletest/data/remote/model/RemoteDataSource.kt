package com.example.mercadolibremobiletest.data.remote.model

import com.example.mercadolibremobiletest.data.dataAccess.MercadoLibreService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val mercadoLibreService: MercadoLibreService) {

    suspend fun getCategoriesListAsync() = mercadoLibreService.getCategoriesListAsync()

    suspend fun getItemsListAsync(query: String) = mercadoLibreService.getItemsListAsync(query)
}