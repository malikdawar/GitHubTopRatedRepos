package com.example.trendings.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.trendings.data.local.models.Trending

@Dao
interface TrendingDAO : BaseDAO<Trending> {
    @Query("SELECT * FROM trending ORDER BY stars DESC")
    fun getTrendingRepos(): MutableList<Trending>

    @Query("DELETE FROM trending")
    fun delete()
}