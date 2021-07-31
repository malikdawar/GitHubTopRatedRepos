package com.example.trendings.data.remote

import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val webService: ApiInterface
) {
    suspend fun getTrendingRepos() = webService.getTrendingRepos()
}