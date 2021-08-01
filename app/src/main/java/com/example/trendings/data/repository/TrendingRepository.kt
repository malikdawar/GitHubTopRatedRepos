package com.example.trendings.data.repository

import com.example.trendings.data.local.LocalSource
import com.example.trendings.data.mappers.TrendingMapper
import com.example.trendings.data.remote.RemoteSource
import com.example.trendings.data.remote.model.TrendingRepoCallBack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This repository is used for fetching trending github repos from a data source.
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class TrendingRepository @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
    private val mapper: TrendingMapper
) {
    /**
     * This webservice doesn't support pagination.
     * Assuming minimum api calls, a single call will update the DB and return locally afterwards.
     * Even if pagination is applied locally, the data isn't enough for multiple pages.
     * Usually 10 - 50 items per page are considered normal.
     * But APIs total number of items appear to be always 30 so it makes sense to fetch all items in a single call.
     * What is the right time or state to call the API once its data is locally stored ?
     * Many such things are missing from the problem statement.
     * This function will attempt to load data from API in case of refresh or in 1st invocation and from local DB in subsequent invocations.
     * There would be a refresh option in the menu to trigger an explicit call.
     * An explicit override is therefore set.
     * @param refresh optionally pass true to update the local DB.
     * @return API's response or error.
     */
    suspend fun getTrendingRepos(refresh: Boolean = false): TrendingRepoCallBack =
        withContext(Dispatchers.IO) {
            try {
                var trending = localSource.getTrendingRepos()
                if (refresh || trending.isEmpty()) {
                    remoteSource.getTrendingRepos()?.items?.run {
                        trending = mapper.mapper(this).toMutableList()
                        localSource.insertTrendingRepos(trending)
                    }
                }
                TrendingRepoCallBack.Repositories(trending)
            } catch (e: Exception) {
                e.printStackTrace()
                TrendingRepoCallBack.Error(e.toString())
            }
        }

    fun deleteLocalTrendingRepos() = localSource.clear()
}