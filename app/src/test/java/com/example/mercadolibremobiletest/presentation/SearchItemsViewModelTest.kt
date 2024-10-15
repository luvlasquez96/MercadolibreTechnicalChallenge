package com.example.mercadolibremobiletest.presentation

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.example.mercadolibremobiletest.MainDispatcherRule
import com.example.mercadolibremobiletest.domain.model.CategoryDetails
import com.example.mercadolibremobiletest.domain.model.Item
import com.example.mercadolibremobiletest.domain.model.SearchResult
import com.example.mercadolibremobiletest.domain.model.SearchResultUI
import com.example.mercadolibremobiletest.domain.usecase.GetCategoryDetailsUseCase
import com.example.mercadolibremobiletest.domain.usecase.GetItemInfoUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchItemsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getItemInfoUseCase: GetItemInfoUseCase

    @MockK
    private lateinit var getCategoriesDetailsUseCase: GetCategoryDetailsUseCase

    @MockK
    private lateinit var viewModel: SearchItemsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = SearchItemsViewModel(
            getItemInfoUseCase = getItemInfoUseCase,
            getCategoriesDetailsUseCase = getCategoriesDetailsUseCase
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GIVEN a valid query WHEN getItemsList is called THEN emits Loading and Success states`() {
        runTest {
            val mockItemResponse = mockk<Item>(relaxed = true) {
                every { searchResultResponse } returns listOf(
                    mockk<SearchResult>(relaxed = true) {
                        every { categoryId } returns "fakeCategoryId"
                    })
            }
            val mockSearchResultUI = mockk<SearchResultUI>(relaxed = true) {
                every { acceptsMercadopago } returns false
                every { availableQuantity } returns 0
                every { catalogListing } returns false
                every { categoryName } returns "fakeCategoryName"
                every { officialStoreId } returns 0
                every { price } returns 0.0
            }

            coEvery { getItemInfoUseCase("query") } returns Result.success(mockItemResponse)
            coEvery { getCategoriesDetailsUseCase("fakeCategoryId") } returns Result.success(mockk<CategoryDetails>(
                relaxed = true
            ) {
                every { name } returns "fakeCategoryName"
            })

            turbineScope {
                viewModel.getItemsList("query")

                viewModel.viewState.test {
                    assertEquals(
                        mockSearchResultUI.categoryName,
                        (awaitItem() as SearchItemsViewModel.ViewState.SearchResultLoaded).searchResult[0].categoryName
                    )
                    cancelAndIgnoreRemainingEvents()
                }
            }

        }
    }

    @Test
    fun `GIVEN an invalid query WHEN getItemsList is called THEN emits an error state`() {

        runTest {
            val exception = mockk<Exception>(relaxed = true) {
                every { message } returns "Unable to fetch item list"
            }

            coEvery { getItemInfoUseCase("query") } returns Result.failure(exception)
            viewModel.getItemsList("query")
            viewModel.viewState.test {
                assertEquals(
                    SearchItemsViewModel.ViewState.Error("Unable to fetch item list"),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
