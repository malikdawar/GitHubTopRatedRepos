package com.example.trendings.data.remote.model

import com.example.trendings.data.local.models.Trending

/**
 * The TrendingRepoCallBack.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

sealed class TrendingRepoCallBack {
    class Error(val error: String) : TrendingRepoCallBack()
    class Repositories(val trending: MutableList<Trending>) : TrendingRepoCallBack()
}