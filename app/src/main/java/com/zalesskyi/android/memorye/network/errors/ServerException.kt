package com.zalesskyi.android.memorye.network.errors

import com.zalesskyi.android.memorye.R
import com.zalesskyi.android.memorye.extensions.getStringApp

class ServerException(statusCode: Int? = null,
                      v: String? = null,
                      message: String? = null,
                      errors: List<ValidationError>? = null,
                      stacktrace: String? = null) : ApiException(statusCode, v, message, errors, stacktrace) {

    companion object {
        private val ERROR_MESSAGE = getStringApp(R.string.server_error)
        private const val STATUS_CODE = 500
    }

    override val message: String = ERROR_MESSAGE
    override var statusCode: Int? = STATUS_CODE
}
