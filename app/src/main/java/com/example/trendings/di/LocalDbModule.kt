package com.example.trendings.di

import com.example.trendings.database.AppDatabase
import com.example.trendings.database.dao.TrendingDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module to provide the instances of [TrendingDAO], and [AppDatabase] classes.
 * @author Malik Dawar
 */
@Module
@InstallIn(SingletonComponent::class)
class LocalDbModule {

    @Singleton
    @Provides
    fun providesTrendingDb(): AppDatabase {
        return AppDatabase.getDatabase()
    }

    @Singleton
    @Provides
    fun providesTrendingDAO(): TrendingDAO {
        return providesTrendingDb().trendingDao()
    }
}
