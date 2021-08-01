package com.example.trendings

import com.example.trendings.data.remote.ApiInterface
import com.example.trendings.utils.ApiAbstract
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class TrendingNetworkDataSourceTest : ApiAbstract<ApiInterface>() {

    private val sut = createService(ApiInterface::class.java)

    @Test
    fun `given API init is called with trending_search_trending_repo_response_200 then it should be successful`() {
        runBlocking {
            enqueueResponse("trending_search_trending_repo_response_200.json")
            sut.getTrendingRepos()
            val request = mockWebServer.takeRequest()
            assertEquals("GET", request.method)
            assertTrue(request.failure?.message == null)
            assertEquals(ApiInterface.GET_TRENDING_REPOS, request.path)
        }
    }

    /*@Test
    fun `given API init is called with trending_search_trending_repo_response_200 then validate data`() {
        runBlocking {
            enqueueResponse("trending_search_trending_repo_response_200.json")

            val responseModel = sut.getTrendingRepos()
            val request = mockWebServer.takeRequest()

            println(request.)

            assertEquals(
                TrendingDataSource.trendingResponse.totalCount,
                responseModel?.totalCount
            )

            assertEquals(
                TrendingDataSource.trendingResponse.items?.size,
                responseModel?.items?.size
            )

            assertEquals(
                TrendingDataSource.trendingResponse.items?.get(0)?.id,
                responseModel?.items?.get(0)?.id
            )

            assertEquals(
                TrendingDataSource.trendingResponse.items?.get(0)?.description,
                responseModel?.items?.get(0)?.description
            )

            assertEquals(
                TrendingDataSource.trendingResponse.items?.get(0)?.name,
                responseModel?.items?.get(0)?.name
            )

            assertEquals(
                TrendingDataSource.trendingResponse.items?.get(0)?.language,
                responseModel?.items?.get(0)?.language
            )

            assertEquals(
                TrendingDataSource.trendingResponse.items?.get(0)?.stargazersCount,
                responseModel?.items?.get(0)?.stargazersCount
            )

            assertEquals(
                TrendingDataSource.trendingResponse.items?.get(0)?.owner?.login,
                responseModel?.items?.get(0)?.owner?.login
            )

            assertEquals(
                TrendingDataSource.trendingResponse.items?.get(0)?.owner?.avatarUrl,
                responseModel?.items?.get(0)?.owner?.avatarUrl
            )
        }
    }*/
}
