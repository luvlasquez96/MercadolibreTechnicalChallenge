package com.example.mercadolibremobiletest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibremobiletest.domain.model.CategoryDetails
import com.example.mercadolibremobiletest.domain.model.Item
import com.example.mercadolibremobiletest.domain.model.SearchResultUI
import com.example.mercadolibremobiletest.domain.usecase.GetCategoryDetailsUseCase
import com.example.mercadolibremobiletest.domain.usecase.GetItemInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemsViewModel @Inject constructor(
    private var getItemInfoUseCase: GetItemInfoUseCase,
    private var getCategoriesDetailsUseCase: GetCategoryDetailsUseCase
) : ViewModel() {

    private var _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private fun getCategoryDetails(categoryId: String) {
        viewModelScope.launch {
            getCategoriesDetailsUseCase(id = categoryId)
        }
    }
    fun getItemsList(query: String) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            getItemInfoUseCase(query)
                .onSuccess { result ->
                    if (result.resultResponses.isEmpty()) {
                        _viewState.value = ViewState.Error("No results found for '$query'")
                    } else {
                        val searchResultUIList = result.resultResponses.map {
                            var categoryDetail = viewModelScope.async {
                                getCategoriesDetailsUseCase(it.categoryId).getOrThrow()
                            }
                            SearchResultUI(
                                acceptsMercadopago = it.acceptsMercadopago,
                                availableQuantity = it.availableQuantity,
                                buyingMode = it.buyingMode,
                                catalogListing = it.catalogListing,
                                categoryName = categoryDetail.await().name,
                                condition = it.condition,
                                id = it.id,
                                listingTypeId = it.listingTypeId,
                                officialStoreId = it.officialStoreId ?: 0,
                                officialStoreName = it.officialStoreName ?: "null",
                                permalink = it.permalink ?: "null",
                                price = it.price,
                                salePriceResponse = it.salePriceResponse,
                                sellerResponse = it.sellerResponse,
                                thumbnail = it.thumbnail,
                                title = it.title
                            )
                        }
                        _viewState.value = ViewState.SearchResultLoaded(searchResultUIList)
                    }
                }
                .onFailure {
                    _viewState.value = ViewState.Error(it.message.toString())
                }
        }
    }
    fun clearSearchResults() {
        _viewState.value = ViewState.SearchResultLoaded(emptyList())
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Error(val errorMessage: String) : ViewState()
        data class SearchResultLoaded(val searchResult: List<SearchResultUI>) : ViewState()
    }
}