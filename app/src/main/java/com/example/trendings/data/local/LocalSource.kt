package com.example.trendings.data.local

import com.example.trendings.data.local.models.Trending
import com.example.trendings.database.dao.TrendingDAO
import javax.inject.Inject

class LocalSource @Inject constructor(
    private val trendingDAO: TrendingDAO
) {
    fun getTrendingRepos() = trendingDAO.getTrendingRepos()
    fun clear() = trendingDAO.delete()
    fun insertTrendingRepos(repos: List<Trending>) = trendingDAO.insertAll(repos)
}