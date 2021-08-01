package com.example.trendings.data.remote

import com.example.trendings.data.remote.model.TrendingResponse
import retrofit2.http.GET

/**
 * The ApiInterface.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

interface ApiInterface {
    @GET(GET_TRENDING_REPOS)
    suspend fun getTrendingRepos(): TrendingResponse?

    companion object{
        const val GET_TRENDING_REPOS = "/search/repositories?q=language=+sort:stars"
    }
}