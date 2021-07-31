package com.example.trendings.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * The BaseFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _bindingInflater: VB? = null
    protected val binding: VB get() = _bindingInflater!!

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _bindingInflater = bindingInflater(inflater, container, false)
        return _bindingInflater!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingInflater = null
    }
}
