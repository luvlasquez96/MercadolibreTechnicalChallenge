package com.example.mercadolibremobiletest.domain.usecase

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import com.example.mercadolibremobiletest.domain.model.Item
import javax.inject.Inject

class GetItemInfoUseCase @Inject constructor(
    private val repository: MercadoLibreRepository
){
    suspend operator fun invoke(query: String): Result<Item> {
        return repository.getItemsListAsync(query)
    }
}