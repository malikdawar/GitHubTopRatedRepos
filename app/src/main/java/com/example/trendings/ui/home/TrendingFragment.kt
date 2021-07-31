package com.example.trendings.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.trendings.R
import com.example.trendings.adapters.TrendingAdapter
import com.example.trendings.base.BaseFragment
import com.example.trendings.core.extensions.gone
import com.example.trendings.core.extensions.visible
import com.example.trendings.core.utils.InternetMonitor
import com.example.trendings.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingFragment : BaseFragment<FragmentHomeBinding>(),
    InternetMonitor.OnInternetConnectivityListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: TrendingViewModel by viewModels()
    private lateinit var trendingAdapter: TrendingAdapter
    private val errorView by lazy {
        ErrorView(binding.uiError)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeTrendingRecyclerView.adapter = trendingAdapter
        viewModel.getTrendingRepos()
        initObservations()
    }

    private fun initObservations() {
        viewModel.getTrendingUIData().observe(viewLifecycleOwner) { uiData ->
            when (uiData) {
                TrendingUIState.Loading -> {
                    binding.homeTrendingsShimmerLayout.visible()
                    binding.homeTrendingRecyclerView.gone()
                    errorView.hide()
                }
                is TrendingUIState.Success -> {
                    binding.homeTrendingsShimmerLayout.gone()
                    binding.homeTrendingRecyclerView.visible()

                    val trending = uiData.trending
                    if (trending.isEmpty()) {
                        errorView.showMessage()
                        return@observe
                    }
                    trendingAdapter.setItems(trending)
                }
                is TrendingUIState.Failed -> {
                    binding.homeTrendingsShimmerLayout.gone()
                    errorView.showMessage(uiData.error)
                }
                TrendingUIState.InternetRestore -> {
                    mMenu?.getItem(0)?.isVisible = true   // can refresh
                    errorView.hideNoInternetView()

                }
                TrendingUIState.InternetFailure -> {  // for now nothing happens when local data is there and internet is gone this use case has been excluded on purpose.
                    mMenu?.getItem(0)?.isVisible = false  // cannot refresh
                    errorView.showNoInternetView()
                }
                TrendingUIState.Clear -> {
                    errorView.showMessage(getString(R.string.label_db_cleared))
                }
            }
        }
    }

    override fun onInternetAvailable() {
        viewModel.onInternet()
    }

    override fun onInternetUnavailable() {
        viewModel.onInternetLost()
    }
}

