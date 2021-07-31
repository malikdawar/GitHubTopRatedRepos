package com.example.trendings.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trendings.App
import com.example.trendings.data.local.models.Trending
import com.example.trendings.data.remote.ApiInterface
import com.example.trendings.database.dao.TrendingDAO
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * The AppDatabase.kt, trending db
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@Database(entities = [Trending::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trendingDao(): TrendingDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context = App.getAppContext()): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "trending_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}