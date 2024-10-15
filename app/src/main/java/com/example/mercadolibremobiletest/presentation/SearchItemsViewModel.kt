package com.example.mercadolibremobiletest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibremobiletest.data.remote.mapper.toSearchResultUI
import com.example.mercadolibremobiletest.domain.model.SearchResultUI
import com.example.mercadolibremobiletest.domain.usecase.GetCategoryDetailsUseCase
import com.example.mercadolibremobiletest.domain.usecase.GetItemInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchItemsViewModel @Inject constructor(
    private var getItemInfoUseCase: GetItemInfoUseCase,
    private var getCategoriesDetailsUseCase: GetCategoryDetailsUseCase
) : ViewModel() {

    private var _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    fun getItemsList(query: String) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            getItemInfoUseCase(query)
                .onSuccess { result ->
                    try {
                        if (result.searchResultResponse.isEmpty()) {
                            _viewState.value =
                                ViewState.NoItemsFound("No results found for '$query'")
                        } else {
                            val searchResultUIList = result.searchResultResponse.map {
                                val categoryDetail = viewModelScope.async {
                                    getCategoriesDetailsUseCase(it.categoryId).getOrThrow()
                                }
                                it.toSearchResultUI().copy(
                                    categoryName = categoryDetail.await().name
                                )
                            }
                            _viewState.value = ViewState.SearchResultLoaded(searchResultUIList)
                        }
                    } catch (e: Exception) {
                        _viewState.value = ViewState.Error("Unable to fetch category details")
                    }
                }
                .onFailure {
                    Timber.e(it, "Error fetching item list for '$query'")
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

        data class NoItemsFound(val message: String) : ViewState()
    }
}