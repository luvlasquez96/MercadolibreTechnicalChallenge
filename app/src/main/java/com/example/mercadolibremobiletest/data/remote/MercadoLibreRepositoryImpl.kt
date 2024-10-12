package com.example.mercadolibremobiletest.data.remote

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import com.example.mercadolibremobiletest.data.local.LocalDataSource
import com.example.mercadolibremobiletest.data.remote.mapper.toCategoriesItem
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import javax.inject.Inject

class MercadoLibreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MercadoLibreRepository {

    override suspend fun getCategoriesListAsync(): Result<List<CategoriesItem>>{
        val response = remoteDataSource.getCategoriesListAsync()

        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.toCategoriesItem())
        } else {
            Result.failure(Throwable())
        }
    }

    override suspend fun getItemsListAsync(query: String): List<String> {
        val request = remoteDataSource.getItemsListAsync(query)

        return listOf()
    }
}