package com.example.mercadolibremobiletest.domain.usecase

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: MercadoLibreRepository
) {
    suspend operator fun invoke(): Result<List<CategoriesItem>> {
        return repository.getCategoriesListAsync()
    }
}