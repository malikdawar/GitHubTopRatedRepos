package com.example.trendings.data.remote

import com.example.trendings.data.remote.model.TrendingResponse
import retrofit2.http.GET

interface ApiInterface {
    @GET("search/repositories?q=language=+sort:stars")
    suspend fun getTrendingRepos(): TrendingResponse?
}