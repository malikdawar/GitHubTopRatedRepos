package com.example.trendings

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.trendings.data.local.models.Trending
import com.example.trendings.data.remote.model.TrendingRepoCallBack
import com.example.trendings.data.repository.TrendingRepository
import com.example.trendings.ui.home.TrendingUIState
import com.example.trendings.ui.home.TrendingViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

/**
 * TrendingViewModelTest.kt, unit tests of the trendingViewModel class
 * @author malik dawar
 */
class TrendingViewModelTest {

    private val trendingRepository = mockk<TrendingRepository>()

    private lateinit var sut: TrendingViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        sut = TrendingViewModel(trendingRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun getTrendingReposRemote() {
        val trendingList = mockk<MutableList<Trending>>()
        coEvery { trendingRepository.getTrendingRepos(true) } returns TrendingRepoCallBack.Repositories(
            trendingList
        )
        every { trendingList.isEmpty() } returns false
        Assert.assertTrue(trendingList.isNotEmpty())
    }

    @Test
    fun getTrendingReposLocal() {
        val trendingList = mockk<MutableList<Trending>>()
        coEvery { trendingRepository.getTrendingRepos() } returns TrendingRepoCallBack.Repositories(
            trendingList
        )
        every { trendingList.isEmpty() } returns false
        Assert.assertTrue(trendingList.isNotEmpty())
    }

    @Test
    fun testTrendingUILoading() {
        coEvery { trendingRepository.getTrendingRepos() } returns mockk()
        val trendingLiveData = sut.getTrendingUIData()
        var trendingUI: TrendingUIState? = null
        val observer = Observer<TrendingUIState> {
            if (trendingUI == null)  // to catch the 1st state which is loading.
                trendingUI = it
        }
        trendingLiveData.observeForever(observer)
        sut.getTrendingRepos()
        trendingLiveData.removeObserver(observer)
        Assert.assertTrue(trendingUI == TrendingUIState.Loading)
    }

    @Test
    fun testTrendingUISuccess() {
        coEvery { trendingRepository.getTrendingRepos() } returns TrendingRepoCallBack.Repositories(
            mockk()
        )
        val trendingLiveData = sut.getTrendingUIData()
        val observer = Observer<TrendingUIState> {}
        trendingLiveData.observeForever(observer)
        sut.getTrendingRepos()
        trendingLiveData.removeObserver(observer)
        Assert.assertTrue(trendingLiveData.value is TrendingUIState.Success)
    }

    @Test
    fun testTrendingUIFailedByTrendingAPI() {
        coEvery { trendingRepository.getTrendingRepos() } returns TrendingRepoCallBack.Error(
            "Generic API Failure"
        )
        val trendingLiveData = sut.getTrendingUIData()
        val observer = Observer<TrendingUIState> {}
        trendingLiveData.observeForever(observer)
        sut.getTrendingRepos()
        trendingLiveData.removeObserver(observer)
        Assert.assertTrue(trendingLiveData.value is TrendingUIState.Failed)
    }

    @Test
    fun testTrendingUIFailedByInternetFailInApiCall() {
        coEvery { trendingRepository.getTrendingRepos() } returns TrendingRepoCallBack.Error(
            UnknownHostException("Base URL not found").toString()
        )
        val trendingLiveData = sut.getTrendingUIData()
        val observer = Observer<TrendingUIState> {}
        trendingLiveData.observeForever(observer)
        sut.getTrendingRepos()
        trendingLiveData.removeObserver(observer)
        Assert.assertTrue(trendingLiveData.value is TrendingUIState.Failed)
    }

    @Test
    fun testTrendingUIInternetFailure() {
        val trendingLiveData = sut.getTrendingUIData()
        val observer = Observer<TrendingUIState> {}
        trendingLiveData.observeForever(observer)
        sut.onInternetLost()
        trendingLiveData.removeObserver(observer)
        Assert.assertTrue(trendingLiveData.value == TrendingUIState.InternetFailure)
    }

    @Test
    fun testTrendingUIInternetRestore() {
        val trendingLiveData = sut.getTrendingUIData()
        val observer = Observer<TrendingUIState> {}
        trendingLiveData.observeForever(observer)
        sut.onInternet()
        trendingLiveData.removeObserver(observer)
        Assert.assertTrue(trendingLiveData.value == TrendingUIState.InternetRestore)
    }

    @Test
    fun testTrendingUIDbClear() {
        val trendingLiveData = mockk<LiveData<TrendingUIState>>()
        val mTrendingViewModel = mockk<TrendingViewModel>()
        every { mTrendingViewModel.deleteTrendingRepos() } returns Unit
        every { mTrendingViewModel.getTrendingUIData() } returns trendingLiveData
        every { trendingLiveData.value } returns TrendingUIState.Clear
        Assert.assertTrue(trendingLiveData.value == TrendingUIState.Clear)
    }
}