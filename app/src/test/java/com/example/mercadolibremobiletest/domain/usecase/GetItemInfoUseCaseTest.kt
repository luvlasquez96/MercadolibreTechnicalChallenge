package com.example.mercadolibremobiletest.domain.usecase

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import com.example.mercadolibremobiletest.domain.model.Item
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetItemInfoUseCaseTest {
    @MockK
    private lateinit var repository: MercadoLibreRepository

    @InjectMockKs
    private lateinit var getItemInfoUseCase: GetItemInfoUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getItemInfoUseCase = GetItemInfoUseCase(repository)
    }

    @Test
    fun `GIVEN a query WHEN invoke THEN return an item`() {
        runTest {
            val expectedItem = mockk<Item>()
            coEvery {
                repository.getItemsListAsync("query")
            } returns Result.success(expectedItem)

            val result = getItemInfoUseCase.invoke("query")
            assertTrue(result.isSuccess)
            result.onSuccess {
                assertEquals(it, expectedItem)
            }
            coVerify(exactly = 1) { repository.getItemsListAsync("query") }
        }
    }

    @Test
    fun `GIVEN a query WHEN invoke THEN return an error`() {
        runTest {
            val expectedThrowable = Throwable("Error")
            coEvery {
                repository.getItemsListAsync("query")
            } returns Result.failure(expectedThrowable)

            val result = getItemInfoUseCase.invoke("query")
            assertTrue(result.isFailure)
            result.onFailure {
                assertEquals(it, expectedThrowable)
            }
            coVerify(exactly = 1) { repository.getItemsListAsync("query") }
        }
    }
}