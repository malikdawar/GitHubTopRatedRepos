package com.example.trendings.data.mappers

import com.example.trendings.data.local.models.Trending
import com.example.trendings.data.remote.model.Item
import javax.inject.Inject

class TrendingMapper @Inject constructor() {

    /**
     * Mappert response model list to local db model list.
     */
    fun mapper(inValue: List<Item>): List<Trending> {
        return inValue.map {
            Trending(
                id = it.id,
                username = it.owner?.login,
                libraryName = it.name,
                language = it.language,
                description = it.description,
                imageUrl = it.owner?.avatarUrl,
                stars = it.stargazersCount
            )
        }
    }
}