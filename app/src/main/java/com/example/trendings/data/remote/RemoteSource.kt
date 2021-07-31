package com.example.trendings.data.remote

import javax.inject.Inject

/**
 * The RemoteSource.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class RemoteSource @Inject constructor(
    private val webService: ApiInterface
) {
    suspend fun getTrendingRepos() = webService.getTrendingRepos()
}