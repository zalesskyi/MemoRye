package com.zalesskyi.android.memorye.network.errors

import com.zalesskyi.android.memorye.R
import com.zalesskyi.android.memorye.extensions.getStringApp

class NoNetworkException : Exception() {

    companion object {
        private val ERROR_MESSAGE = getStringApp(R.string.no_internet_connection_error)
    }

    override val message: String = ERROR_MESSAGE
}
