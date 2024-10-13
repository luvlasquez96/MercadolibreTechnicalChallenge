package com.example.mercadolibremobiletest.domain.usecase

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import com.example.mercadolibremobiletest.domain.model.CategoryDetails
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

class GetCategoryDetailsUseCaseTest{
    @MockK
    private lateinit var repository: MercadoLibreRepository

    @InjectMockKs
    private lateinit var getCategoryDetailsUseCase: GetCategoryDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCategoryDetailsUseCase = GetCategoryDetailsUseCase(repository)
    }

    @Test
    fun `GIVEN a categoryId WHEN invoke THEN return a CategoryDetails`() {
        runTest {
            val expectedCategoryDetails = mockk<CategoryDetails>(relaxed = true)

            coEvery {
                repository.getCategoryByIdAsync("MLA388817")
            } returns Result.success(expectedCategoryDetails)

            val result = getCategoryDetailsUseCase.invoke("MLA388817")
            assertTrue(result.isSuccess)
            result.onSuccess {
                assertEquals(it, expectedCategoryDetails)
            }
            coVerify(exactly = 1) { repository.getCategoryByIdAsync("MLA388817") }
        }
    }

    @Test
    fun `GIVEN a categoryId WHEN invoke THEN return an error`() {
        runTest {
            val expectedThrowable = Throwable("Error")
            coEvery {
                repository.getCategoryByIdAsync("MLA388817")
            } returns Result.failure(expectedThrowable)

            val result = getCategoryDetailsUseCase.invoke("MLA388817")
            assertTrue(result.isFailure)
            result.onFailure {
                assertEquals(it, expectedThrowable)
            }
            coVerify(exactly = 1) { repository.getCategoryByIdAsync("MLA388817") }
        }
    }
}