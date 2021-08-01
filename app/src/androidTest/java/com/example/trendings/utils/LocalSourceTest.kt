package com.example.trendings.utils

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.trendings.data.local.LocalSource
import com.example.trendings.database.AppDatabase
import com.example.trendings.utils.data.LocalDataBaseUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LocalSourceTest {
    private val localSource = LocalSource(AppDatabase.getDatabase().trendingDao())

    @Before
    fun init() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun insertTrendingRepos() {
        val idsList =
            runBlocking { localSource.insertTrendingRepos(LocalDataBaseUtils.createTrendingList(10)) }
        assertTrue(idsList.also { Log.d("IdsList", it.joinToString()) }.isNotEmpty())
    }

    @Test
    fun getTrendingRepos() {
        val reposFound = runBlocking {
            localSource.insertTrendingRepos(LocalDataBaseUtils.createTrendingList(10))
            localSource.getTrendingRepos().also { Log.d("IdsList get", it.joinToString()) }
                .isNotEmpty()
        }

        assertTrue(reposFound)
    }

    @Test
    fun deleteTrendingRepos() {
        val deleted = runBlocking {
            val dummyList = LocalDataBaseUtils.createTrendingList(10)
            if (localSource.insertTrendingRepos(dummyList).isNotEmpty()) {
                localSource.clear()
                localSource.getTrendingRepos().isEmpty()
            } else false
        }

        assertTrue(deleted.also { Log.d("IdsList", "Deleted.") })
    }
}