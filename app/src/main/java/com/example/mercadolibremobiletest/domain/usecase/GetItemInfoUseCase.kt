package com.example.mercadolibremobiletest.domain.usecase

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import javax.inject.Inject

class GetItemInfoUseCase @Inject constructor(
    private val repository: MercadoLibreRepository
){
}