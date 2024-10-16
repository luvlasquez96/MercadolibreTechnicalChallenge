package com.example.mercadolibremobiletest.presentation

import app.cash.turbine.test
import com.example.mercadolibremobiletest.MainDispatcherRule
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import com.example.mercadolibremobiletest.domain.model.CategoryDetails
import com.example.mercadolibremobiletest.domain.usecase.GetCategoriesUseCase
import com.example.mercadolibremobiletest.domain.usecase.GetCategoryDetailsUseCase
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

@OptIn(ExperimentalCoroutinesApi::class)
class CategoryViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @MockK
    private lateinit var getCategoryDetailsUseCase: GetCategoryDetailsUseCase

    private lateinit var categoryViewModel: CategoryViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        categoryViewModel = CategoryViewModel(
            getCategoriesUseCase = getCategoriesUseCase,
            getCategoriesDetilsUseCase = getCategoryDetailsUseCase
        )
    }

    @Test
    fun `GIVEN categories list WHEN getCategoriesList is called THEN emits Loading and CategoriesLoaded states`() =
        runTest {
            val mockCategories = listOf(
                mockk<CategoriesItem>(relaxed = true) { every { id } returns "category1" },
                mockk<CategoriesItem>(relaxed = true) { every { id } returns "category2" }
            )

            val mockCategoryDetails1 =
                mockk<CategoryDetails>(relaxed = true) { every { name } returns "Category1" }
            val mockCategoryDetails2 =
                mockk<CategoryDetails>(relaxed = true) { every { name } returns "Category2" }

            coEvery { getCategoriesUseCase() } returns Result.success(mockCategories)
            coEvery { getCategoryDetailsUseCase("category1") } returns Result.success(
                mockCategoryDetails1
            )
            coEvery { getCategoryDetailsUseCase("category2") } returns Result.success(
                mockCategoryDetails2
            )

            categoryViewModel.getCategoriesList()

            categoryViewModel.viewState.test {
                assertEquals(
                    mockCategoryDetails1.name,
                    (awaitItem() as CategoryViewModel.ViewState.CategoriesLoaded).categoriesList[0].name
                )
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN empty categories list WHEN getCategoriesList is called THEN emits an error state`() =
        runTest {
            coEvery { getCategoriesUseCase() } returns Result.success(emptyList())

            categoryViewModel.getCategoriesList()

            categoryViewModel.viewState.test {
                assertEquals(CategoryViewModel.ViewState.Error("No categories found"), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN categories fetching failure WHEN getCategoriesList is called THEN emits an error state`() =
        runTest {
            val exception = mockk<Exception>(relaxed = true) {
                every { message } returns "Unable to fetch categories"
            }

            coEvery { getCategoriesUseCase() } returns Result.failure(exception)

            categoryViewModel.getCategoriesList()

            categoryViewModel.viewState.test {
                assertEquals(
                    CategoryViewModel.ViewState.Error("Unable to fetch categories"),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
        }
}