package com.example.trendings

import com.example.trendings.data.TrendingDataSource
import com.example.trendings.data.mappers.TrendingMapper
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

class TrendingMapperTest {

    private val sut: TrendingMapper = TrendingMapper()

    @Test
    fun `given the TrendingMapper is called with the trendingResponse then the mapped data is not empty`() {
        val inValue = TrendingDataSource.trendingResponse
        val mappedData = sut.mapper(inValue.items!!)
        Assert.assertTrue(mappedData.isEmpty().not())
    }

    @Test
    fun `given the TrendingMapper is called with the trendingResponse then verify the mapped data`() {
        val inValue = TrendingDataSource.trendingResponse
        val mappedData = sut.mapper(inValue.items!!)

        assertEquals(
            inValue.items?.size,
            mappedData.size
        )

        assertEquals(
            inValue.items?.get(0)?.id,
            mappedData[0].id
        )

        assertEquals(
            inValue.items?.get(0)?.description,
            mappedData[0].description
        )

        assertEquals(
            inValue.items?.get(0)?.name,
            mappedData[0].libraryName
        )

        assertEquals(
            inValue.items?.get(0)?.language,
            mappedData[0].language
        )

        assertEquals(
            inValue.items?.get(0)?.stargazersCount,
            mappedData[0].stars
        )

        assertEquals(
            inValue.items?.get(0)?.owner?.login,
            mappedData[0].username
        )

        assertEquals(
            inValue.items?.get(0)?.owner?.avatarUrl,
            mappedData[0].imageUrl
        )
    }
}
