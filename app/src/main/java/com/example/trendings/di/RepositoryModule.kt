package com.example.trendings.di

import com.example.trendings.data.remote.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 * @author Malik Dawar
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideImagineRepository(apiService: ApiInterface): PhotosRepository {
        return PhotosRepositoryImpl(apiService)
    }
}
