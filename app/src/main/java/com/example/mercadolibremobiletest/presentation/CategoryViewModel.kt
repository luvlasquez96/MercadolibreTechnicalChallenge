package com.example.mercadolibremobiletest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import com.example.mercadolibremobiletest.domain.usecase.GetCategoriesUseCase
import com.example.mercadolibremobiletest.domain.usecase.GetCategoryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private var getCategoriesUseCase: GetCategoriesUseCase,
    private var getCategoriesDetilsUseCase: GetCategoryDetailsUseCase
) : ViewModel() {
    private var _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

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
    }
}