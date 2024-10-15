package com.example.mercadolibremobiletest.data.remote

import com.example.mercadolibremobiletest.data.remote.model.ItemResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MercadoLibreRepositoryImplTest {
    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    @InjectMockKs
    private lateinit var mercadoLibreRepositoryImpl: MercadoLibreRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mercadoLibreRepositoryImpl = MercadoLibreRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `GIVEN a query WHEN getItemsListAsync THEN return success result`() {
        runTest {
            val mockItemResponse = mockk<ItemResponse>(relaxed = true)

            coEvery {
                remoteDataSource.getItemsListAsync("query")
            } returns Response.success(mockItemResponse)

            val result = mercadoLibreRepositoryImpl.getItemsListAsync("query")

            assertTrue(result.isSuccess)
            coVerify(exactly = 1) { remoteDataSource.getItemsListAsync("query") }
        }
    }

    @Test
    fun `GIVEN a query WHEN getItemsListAsync THEN return failure result`() {
        runTest {
            coEvery {
                remoteDataSource.getItemsListAsync("query")
            } returns Response.error(404, mockk(relaxed = true))

            val result = mercadoLibreRepositoryImpl.getItemsListAsync("query")

            assertTrue(result.isFailure)
            coVerify(exactly = 1) { remoteDataSource.getItemsListAsync("query") }
        }
    }
}