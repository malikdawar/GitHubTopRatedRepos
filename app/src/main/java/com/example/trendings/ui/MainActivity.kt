package com.example.trendings.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.example.trendings.R
import com.example.trendings.base.BaseActivity
import com.example.trendings.core.extensions.replaceFragmentSafely
import com.example.trendings.core.extensions.viewBinding
import com.example.trendings.databinding.ActivityMainBinding
import com.example.trendings.ui.home.TrendingFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity.kt, Main activity class, launcher activity
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun getLayout(): ViewBinding = binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragmentSafely(TrendingFragment())
    }

}