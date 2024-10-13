package com.example.mercadolibremobiletest.domain.usecase

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetCategoriesUseCaseTest{
    @MockK
    private lateinit var repository: MercadoLibreRepository

    @InjectMockKs
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCategoriesUseCase = GetCategoriesUseCase(repository)
    }

    @Test
    fun `GIVEN a call to service WHEN invoke THEN return a list of categories`() {
        runTest {
            val expectedCategories = listOf(mockk<CategoriesItem>())
            coEvery {
                repository.getCategoriesListAsync()
            } returns Result.success(expectedCategories)

            val result = getCategoriesUseCase.invoke()
            assertTrue(result.isSuccess)
            result.onSuccess{
                assertEquals(it, expectedCategories)
            }
            coVerify (exactly = 1){ repository.getCategoriesListAsync()}
        }
    }

    @Test
    fun `GIVEN a call to service WHEN invoke THEN return an error`() {
        runTest {
            val expectedThrowable = Throwable("Error")
            coEvery {
                repository.getCategoriesListAsync()
            } returns Result.failure(expectedThrowable)

            val result = getCategoriesUseCase.invoke()
            assertTrue(result.isFailure)
            result.onFailure {
                assertEquals(it, expectedThrowable)
            }
            coVerify (exactly = 1){ repository.getCategoriesListAsync()}
        }
    }
}