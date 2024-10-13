package com.example.mercadolibremobiletest.data.remote

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import com.example.mercadolibremobiletest.data.local.LocalDataSource
import com.example.mercadolibremobiletest.data.remote.mapper.toCategoriesItem
import com.example.mercadolibremobiletest.data.remote.mapper.toCategoryDetails
import com.example.mercadolibremobiletest.data.remote.mapper.toItem
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import com.example.mercadolibremobiletest.domain.model.CategoryDetails
import com.example.mercadolibremobiletest.domain.model.Item
import javax.inject.Inject

class MercadoLibreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MercadoLibreRepository {

    override suspend fun getCategoriesListAsync(): Result<List<CategoriesItem>> {
        val response = remoteDataSource.getCategoriesListAsync()

        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.toCategoriesItem())
        } else {
            Result.failure(Throwable())
        }
    }

    override suspend fun getItemsListAsync(query: String): Result<Item> {
        val request = remoteDataSource.getItemsListAsync(query)

        return if (request.isSuccessful && request.body() != null) {
            Result.success(request.body()!!.toItem())
        } else {
            Result.failure(Throwable())
        }
    }

    override suspend fun getCategoryByIdAsync(id: String): Result<CategoryDetails> {
        val request = remoteDataSource.getCategoryByIdAsync(id)

        return if (request.isSuccessful && request.body() != null) {
            Result.success(request.body()!!.toCategoryDetails())
        } else {
            Result.failure(Throwable())
        }
    }
}