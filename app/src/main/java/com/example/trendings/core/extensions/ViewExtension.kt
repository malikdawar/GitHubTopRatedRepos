package com.example.trendings.core.extensions

import android.view.View
import java.util.*

/**
 * An Extension to make view Visible
 * @author Dawar Malik.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * An Extension to make view Gone
 * @author Dawar Malik.
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Returns [Boolean] based on current time.
 * Returns true if hours are between 06:00 pm - 07:00 am
 */
fun checkIsNight(): Boolean {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return (currentHour <= 7 || currentHour >= 18)
}