package com.zalesskyi.android.memorye.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.zalesskyi.android.memorye.utils.NO_FLAGS
import androidx.fragment.app.Fragment as SupportFragment

fun Context.showKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        toggleSoftInput(InputMethodManager.SHOW_FORCED, NO_FLAGS)
    }
}

fun SupportFragment.showKeyboard() = activity?.showKeyboard()

fun Activity.hideKeyboard(view: View? = null) = (this as Context).hideKeyboard(view
    ?: currentFocus)

fun Context.hideKeyboard(view: View? = null) = view?.let {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        hideSoftInputFromWindow(it.windowToken, NO_FLAGS)
    }
}

fun SupportFragment.hideKeyboard(view: View? = null) = activity?.hideKeyboard(view)