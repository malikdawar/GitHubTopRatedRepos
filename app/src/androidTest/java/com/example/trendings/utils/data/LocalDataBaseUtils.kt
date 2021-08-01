package com.example.trendings.utils.data

import com.example.trendings.data.local.models.Trending

object LocalDataBaseUtils {

    fun createTrendingList(count: Int): List<Trending> {
        val list = ArrayList<Trending>(count)
        for (i in 1..count)
            list.add(
                Trending(
                    id = i,
                    username = "malikDawar$i",
                    libraryName = "DrawRoute $i",
                    language = "Kotlin",
                    stars = 70 + i
                )
            )
        return list
    }
}