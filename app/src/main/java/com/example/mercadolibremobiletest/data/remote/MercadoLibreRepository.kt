package com.example.mercadolibremobiletest.data.remote

import com.example.mercadolibremobiletest.data.local.LocalDataSource
import com.example.mercadolibremobiletest.data.remote.model.RemoteDataSource
import javax.inject.Inject

class MercadoLibreRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun getCategoriesListAsync() = remoteDataSource.getCategoriesListAsync()

    suspend fun getItemsListAsync(query: String) = remoteDataSource.getItemsListAsync(query)
}