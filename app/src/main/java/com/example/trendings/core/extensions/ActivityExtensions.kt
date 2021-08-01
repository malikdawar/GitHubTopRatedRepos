package com.example.trendings.core.extensions

import android.app.Activity
import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Returns a [Lazy] delegate to access the Activity's [ViewBinding] with
 * specified layout inflater without thread safety mode.
 *
 * This property must be used lazily after views are settled.
 * @author malik dawar, malikdawar@hotmail.com
 */
@MainThread
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T,
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

fun Activity.getTag(): String = javaClass.simpleName

/**
 * Used for getting attr color from style
 */
fun Activity.getAttrColor(vararg elements: Int): Int {
    return theme.obtainStyledAttributes(elements).getColor(0, 0)
}
