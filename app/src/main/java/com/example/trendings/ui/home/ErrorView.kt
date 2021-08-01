package com.example.trendings.ui.home

import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.example.trendings.R
import com.example.trendings.core.extensions.gone
import com.example.trendings.core.extensions.visible
import com.example.trendings.databinding.UiErrorBinding

/**
 * The ErrorView.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class ErrorView(private val uiErrorBinding: UiErrorBinding) {

    private lateinit var onClickRetryButton: () -> Unit
    private val context = uiErrorBinding.root.context

    init {
        uiErrorBinding.btnErrorRetry.setOnClickListener {
            onClickRetryButton()
        }
        uiErrorBinding.errorItemImageView.repeatCount = LottieDrawable.INFINITE
    }

    private fun setRetryButtonShown(shown: Boolean) {
        uiErrorBinding.btnErrorRetry.visibility = if (shown) View.VISIBLE else View.GONE
    }

    private fun show() {
        uiErrorBinding.root.visible()
        uiErrorBinding.errorItemImageView.playAnimation()
    }

    fun hide() {
        uiErrorBinding.root.gone()
        uiErrorBinding.errorItemImageView.pauseAnimation()
    }

    fun showMessage(message: String? = null) {
        uiErrorBinding.tvErrorDescription.text = if (!message.isNullOrBlank()) {
            message
        } else {
            context.getString(R.string.an_alien_is_probably_blocking_your_signal)
        }
        show()
    }

    /**
     * This is left non-intrusive on purpose. UI shouldn't be blocked in case of no internet.
     * Showing internet view is optional it may appear if the UI is already there else not.
     */

    fun showNoInternetView() {
        setRetryButtonShown(false)
        uiErrorBinding.tvErrorDescription.setText(R.string.no_internet)
    }

    fun hideNoInternetView() {
        setRetryButtonShown(true)
        uiErrorBinding.tvErrorDescription.setText(R.string.internet_available)
    }

    fun setOnRetryClick(onRetryClick: () -> Unit) {
        onClickRetryButton = onRetryClick
    }
}