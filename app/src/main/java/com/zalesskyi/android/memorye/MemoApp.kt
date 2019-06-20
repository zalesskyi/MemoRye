package com.zalesskyi.android.memorye

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.facebook.stetho.Stetho
import com.securepreferences.SecurePreferences

class MemoApp : Application() {

    companion object {
        lateinit var instance: MemoApp
            private set
        lateinit var prefs: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            getSharedPreferences()
        } else {
            getSecurePreferences()
        }
    }

    private fun getSharedPreferences() =
        getSharedPreferences(BuildConfig.SECURE_PREF_NAME, Context.MODE_PRIVATE)

    private fun getSecurePreferences() = SecurePreferences(this,
        BuildConfig.SECURE_PREF_PASSWORD,
        BuildConfig.SECURE_PREF_NAME)
}