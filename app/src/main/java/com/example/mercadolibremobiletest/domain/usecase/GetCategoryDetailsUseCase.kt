package com.example.mercadolibremobiletest.domain.usecase

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import com.example.mercadolibremobiletest.domain.model.CategoryDetails
import javax.inject.Inject

class GetCategoryDetailsUseCase @Inject constructor(
    private val repository: MercadoLibreRepository
) {
    suspend operator fun invoke(id: String): Result<CategoryDetails> {
        return repository.getCategoryByIdAsync(id)
    }
}