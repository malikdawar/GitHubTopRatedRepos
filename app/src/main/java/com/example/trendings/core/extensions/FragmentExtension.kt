package com.example.trendings.core.extensions

import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.trendings.R

/**
 * The FragmentExtension.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

/**
 * Extension function to replace Fragment Safely from AppCompatActivity
 * @param fragment destination Fragment
 * @param tag as String default value is fragment.javaClass.name
 * @param containerViewId as frame layout id default value is R.id.container
 * @param enterAnimation as starting animation
 * @param exitAnimation ending animation
 * @param popEnterAnimation if any
 * @param popExitAnimation if any
 * @author Dawar Malik.
 */
fun AppCompatActivity.replaceFragmentSafely(
    fragment: Fragment,
    tag: String = fragment.javaClass.name,
    allowStateLoss: Boolean = true,
    addToBackStack: Boolean = false,
    @IdRes containerViewId: Int = R.id.container,
    @AnimRes enterAnimation: Int = 0,
    @AnimRes exitAnimation: Int = 0,
    @AnimRes popEnterAnimation: Int = 0,
    @AnimRes popExitAnimation: Int = 0
) {
    val ft = supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        .replace(containerViewId, fragment, tag)

    if (addToBackStack) {
        ft.addToBackStack(null)
    }

    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}