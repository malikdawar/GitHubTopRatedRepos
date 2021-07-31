package com.example.trendings.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import com.example.trendings.R
import com.example.trendings.adapters.TrendingAdapter
import com.example.trendings.base.BaseFragment
import com.example.trendings.core.extensions.gone
import com.example.trendings.core.extensions.visible
import com.example.trendings.core.utils.InternetMonitor
import com.example.trendings.databinding.FragmentTrendingReposBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The TrendingFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class TrendingFragment : BaseFragment<FragmentTrendingReposBinding>(),
    InternetMonitor.OnInternetConnectivityListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTrendingReposBinding
        get() = FragmentTrendingReposBinding::inflate

    private var trendingMenu: Menu? = null
    private val viewModel: TrendingViewModel by viewModels()
    private lateinit var trendingAdapter: TrendingAdapter
    private val errorView by lazy {
        ErrorView(binding.uiError)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        trendingAdapter = TrendingAdapter().also {
            binding.homeTrendingRecyclerView.adapter = it
        }
        viewModel.getTrendingRepos()
        initObservations()

        errorView.setOnRetryClick {
            viewModel.getTrendingRepos()
        }
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
                    trendingMenu?.getItem(0)?.isVisible = true   // can refresh
                    errorView.hideNoInternetView()

                }
                TrendingUIState.InternetFailure -> {  // for now nothing happens when local data is there and internet is gone this use case has been excluded on purpose.
                    trendingMenu?.getItem(0)?.isVisible = false  // cannot refresh
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

    override fun onInternetLost() {
        viewModel.onInternetLost()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        trendingMenu = menu
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.getTrendingRepos(refresh = true)
                true
            }
            R.id.action_clear -> {
                viewModel.deleteTrendingRepos()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

