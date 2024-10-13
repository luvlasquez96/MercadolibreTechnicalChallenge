package com.example.mercadolibremobiletest.data.dataAccess

import com.example.mercadolibremobiletest.data.remote.model.CategoriesListResponse
import com.example.mercadolibremobiletest.data.remote.model.ItemResponse
import com.example.mercadolibremobiletest.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadoLibreService {

    @GET(Constants.CATEGORIES_URL)
    suspend fun getCategoriesListAsync(): Response<CategoriesListResponse>

    @GET(Constants.ITEMS_URL)
    suspend fun getItemsListAsync(@Query("q") query: String): Response<ItemResponse>

    @GET(Constants.CATEGORY_BY_ID_URL)
    suspend fun getCategoryByIdAsync(@Path("id") id: String): Response<CategoriesListResponse>
}