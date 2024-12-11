package com.arnav.home.data.di

import com.arnav.core.data.network.NetworkClient
import com.arnav.home.data.HomeServices
import com.arnav.home.data.property.HomeRepositoryImpl
import com.arnav.home.domain.property.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeService(): HomeServices {
        return NetworkClient.createService(HomeServices::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeServices: HomeServices): HomeRepository {
        return HomeRepositoryImpl(homeServices)
    }

}