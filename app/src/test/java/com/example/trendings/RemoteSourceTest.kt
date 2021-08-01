package com.example.trendings

import com.example.trendings.data.local.models.Trending
import com.example.trendings.data.mappers.TrendingMapper
import com.example.trendings.data.remote.RemoteSource
import com.example.trendings.data.remote.model.Item
import com.example.trendings.data.remote.model.TrendingResponse
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteSourceTest {

    private val trendingMapper = TrendingMapper()

    @Test
    fun verifyTrendingAPIResponseSuccess() {
        val remoteSource = mockk<RemoteSource>()
        val trendingResponse = mockk<TrendingResponse>()
        coEvery { remoteSource.getTrendingRepos() } returns trendingResponse
        val trendingList = mockk<MutableList<Trending>>()
        val items = mockk<List<Item>>()
        every { trendingResponse.items } returns items
        every { trendingMapper.mapper(items) } returns trendingList
        every { trendingList.isEmpty() } returns false
        val result = trendingList.isNotEmpty()
        Assert.assertTrue(result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun verifyTrendingAPIResponseFail() {
        val remoteSource = mockk<RemoteSource>()
        coEvery { remoteSource.getTrendingRepos() } returns null
        runBlocking {
            val result = remoteSource.getTrendingRepos()
            Assert.assertTrue(result == null)
        }
    }
}