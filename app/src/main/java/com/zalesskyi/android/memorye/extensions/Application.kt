package com.zalesskyi.android.memorye.extensions

import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.zalesskyi.android.memorye.MemoApp

fun getStringApp(resId: Int) = MemoApp.instance.getString(resId)

fun getAppString(@StringRes stringId: Int, vararg formatArgs: Any) =
    MemoApp.instance.getString(stringId, *formatArgs)

fun getStringArray(@ArrayRes id: Int) = MemoApp.instance.resources.getStringArray(id)

fun getDrawableApp(@DrawableRes resId: Int) = ContextCompat.getDrawable(MemoApp.instance, resId)

fun getColorApp(@ColorRes colorRes: Int) = ContextCompat.getColor(MemoApp.instance, colorRes)
