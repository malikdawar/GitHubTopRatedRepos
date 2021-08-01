package com.example.trendings.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendings.base.BaseViewModel
import com.example.trendings.data.remote.model.TrendingRepoCallBack
import com.example.trendings.data.repository.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * The TrendingViewModel.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val trendingRepository: TrendingRepository) : BaseViewModel() {

    private val trendingUIData = MutableLiveData<TrendingUIState>()

    fun getTrendingUIData(): LiveData<TrendingUIState> = trendingUIData

    fun getTrendingRepos(refresh: Boolean = false) {
        viewModelScope.launch {
            trendingUIData.postValue(TrendingUIState.Loading)
            when (val repos = trendingRepository.getTrendingRepos(refresh)) {
                is TrendingRepoCallBack.Repositories -> {
                    trendingUIData.postValue(TrendingUIState.Success(repos.trending))
                }
                is TrendingRepoCallBack.Error -> {
                    trendingUIData.postValue(TrendingUIState.Failed(repos.error))
                }
            }
        }
    }

    fun onInternet() {
        trendingUIData.postValue(TrendingUIState.InternetRestore)
    }

    fun onInternetLost() {
        trendingUIData.postValue(TrendingUIState.InternetFailure)
    }

    fun deleteTrendingRepos() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                trendingRepository.deleteLocalTrendingRepos()
            }
            trendingUIData.postValue(TrendingUIState.Clear)
        }
    }
}