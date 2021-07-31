package com.example.trendings

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.trendings.core.extensions.checkIsNight
import dagger.hilt.android.HiltAndroidApp

/**
 * The App.kt, Application class
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDayNightMode()
        instance = this
    }

    fun setupDayNightMode() {
        // Get UI mode and set
        val mode = if (checkIsNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    companion object {
        var instance: App? = null
        fun getAppContext(): Context {
            return instance as Context
        }
    }
}