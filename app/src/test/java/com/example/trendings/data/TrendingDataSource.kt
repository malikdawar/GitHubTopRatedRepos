package com.example.trendings.data

import com.example.trendings.data.local.models.Trending
import com.example.trendings.data.remote.model.Item
import com.example.trendings.data.remote.model.Owner
import com.example.trendings.data.remote.model.TrendingResponse

object TrendingDataSource {

    val trendingResponse: TrendingResponse
        get() = TrendingResponse(
            totalCount = 1,
            incompleteResults = false,
            items = arrayListOf(
                Item(
                    id = 23096959,
                    name = "go",
                    language = "Go",
                    description = "The Go programming language",
                    stargazersCount = 88292,
                    owner = Owner(
                        login = "golang",
                        avatarUrl = "https://avatars.githubusercontent.com/u/4314092?v=4"
                    )
                )
            )
        )

    fun createTrendingList(): List<Trending> {
        val list = ArrayList<Trending>(1)
        for (i in 0..1)
            list.add(
                Trending(
                    id = 23096959,
                    username = "golang",
                    libraryName = "go",
                    language = "Go",
                    stars = 88292,
                    imageUrl = "https://avatars.githubusercontent.com/u/4314092?v=4"
                )
            )
        return list
    }
}