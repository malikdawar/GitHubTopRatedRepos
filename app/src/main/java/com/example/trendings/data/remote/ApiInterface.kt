package com.example.trendings.data.remote

import com.example.trendings.data.remote.model.TrendingResponse
import retrofit2.http.GET

/**
 * The ApiInterface.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

interface ApiInterface {
    @GET("search/repositories?q=language=+sort:stars")
    suspend fun getTrendingRepos(): TrendingResponse?
}