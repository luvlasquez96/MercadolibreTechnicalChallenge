package com.example.mercadolibremobiletest.data.dataAccess

import com.example.mercadolibremobiletest.data.remote.CategoriesListResponse
import com.example.mercadolibremobiletest.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoLibreService {

    @GET(Constants.CATEGORIES_URL)
    suspend fun getCategoriesListAsync(): Response<CategoriesListResponse>
}