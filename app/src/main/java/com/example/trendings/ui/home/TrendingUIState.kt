package com.example.trendings.ui.home

import com.example.trendings.data.local.models.Trending

/**
 * This class represents UI and it's state for trending repositories data.
 */
sealed class TrendingUIState {
    object Loading : TrendingUIState()
    class Success(val trending: MutableList<Trending>) : TrendingUIState()
    object Clear : TrendingUIState()
    object InternetFailure : TrendingUIState()
    object InternetRestore : TrendingUIState()
    class Failed(val error: String) : TrendingUIState()
}