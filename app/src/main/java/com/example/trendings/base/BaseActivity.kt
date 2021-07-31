package com.example.trendings.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.trendings.core.extensions.gone
import com.example.trendings.core.extensions.visible
import com.example.trendings.core.utils.InternetMonitor
import com.example.trendings.databinding.UiInternetMonitorBinding

/**
 * The BaseActivity.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayout(): ViewBinding

    private lateinit var internetMessageBinding: UiInternetMonitorBinding

    //internet monitor, to monitor the internet state to to update the app
    private val internetMonitor = InternetMonitor(object :
        InternetMonitor.OnInternetConnectivityListener {
        override fun onInternetAvailable() {
            //getting the data in Background thread and showing it to the view on UI thread
            runOnUiThread {
                internetMessageBinding.topConnectionShower.gone()
            }
        }

        override fun onInternetLost() {
            //onInternetLost, getting the data in Background thread and showing it to the view on UI thread
            runOnUiThread {
                internetMessageBinding.topConnectionShower.visible()
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout().root)
        internetMessageBinding = UiInternetMonitorBinding.inflate(layoutInflater)
    }

    //registering the internet monitor on resume
    override fun onResume() {
        super.onResume()
        internetMonitor.register(this)
    }

    //unregistering the internet monitor on pause
    override fun onPause() {
        super.onPause()
        internetMonitor.unregister(this)
    }
}