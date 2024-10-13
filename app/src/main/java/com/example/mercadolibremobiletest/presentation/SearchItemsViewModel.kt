package com.example.mercadolibremobiletest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import com.example.mercadolibremobiletest.domain.model.Item
import com.example.mercadolibremobiletest.domain.usecase.GetCategoriesUseCase
import com.example.mercadolibremobiletest.domain.usecase.GetCategoryDetailsUseCase
import com.example.mercadolibremobiletest.domain.usecase.GetItemInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemsViewModel @Inject constructor(
    private var getCategoriesUseCase: GetCategoriesUseCase,
    private var getItemInfoUseCase: GetItemInfoUseCase,
    private var getCategoriesDetilsUseCase: GetCategoryDetailsUseCase
) : ViewModel() {

    private var _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState = _viewState.asStateFlow()


    fun getItemsList(query: String) {
        viewModelScope.launch {
            getItemInfoUseCase(query)
                .onSuccess { result ->
                    val categoryDetails = viewModelScope.async {
                        getCategoriesDetilsUseCase(result.resultResponses.first().categoryId).getOrThrow()
                    }.await()
                    _viewState.value = ViewState.ItemsListLoaded(result)
                }
                .onFailure {
                    _viewState.value = ViewState.Error(it.message.toString())
                }
        }
    }

    fun getCategoriesList() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .onSuccess {
                    val categoryDetails = viewModelScope.async {
                        getCategoriesDetilsUseCase(it.first().id).getOrThrow()
                    }.await()
                    _viewState.value = ViewState.CategoriesLoaded(it)
                }.onFailure {
                    _viewState.value = ViewState.Error(it.message.toString())
                }
        }
    }


    sealed class ViewState {
        object Loading : ViewState()
        data class Error(val errorMessage: String) : ViewState()

        data class CategoriesLoaded(val categoriesList: List<CategoriesItem>) : ViewState()
        data class ItemsListLoaded(val itemsList: Item) : ViewState()
    }
}