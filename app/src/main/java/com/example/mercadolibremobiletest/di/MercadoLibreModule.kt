package com.example.mercadolibremobiletest.di

import com.example.mercadolibremobiletest.data.MercadoLibreRepository
import com.example.mercadolibremobiletest.data.dataAccess.MercadoLibreService
import com.example.mercadolibremobiletest.data.local.LocalDataSource
import com.example.mercadolibremobiletest.data.remote.MercadoLibreRepositoryImpl
import com.example.mercadolibremobiletest.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MercadoLibreModule {
    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): MercadoLibreService {
        return retrofit.create(MercadoLibreService::class.java)
    }

    @Singleton
    @Provides
    fun provideMercadoLibreRepository(
        remoteDataSource: RemoteDataSource,
    ): MercadoLibreRepository {
        return MercadoLibreRepositoryImpl(remoteDataSource)
    }
}
