package com.zalesskyi.android.memorye.network.clients

import com.zalesskyi.android.memorye.network.NetworkModule.mapper
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class ServerClient {

    companion object {

        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val AUTHORIZATION_PREF = "Bearer"

        private const val TIMEOUT_IN_SECONDS = 30L
        private const val MAX_REPEAT_TOKEN_REFRESH_COUNT = 2
        private const val DEFAULT_RESPONSE_COUNT = 1
        private const val UNAUTHORIZED = 401
        private const val BAD_TOKEN = 400

    }

    private val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .baseUrl("")  // todo
        .client(createHttpClient())
        .build()

    private fun responseCount(response: Response): Int {
        var result = DEFAULT_RESPONSE_COUNT
        var lResponse: Response? = response
        while ({ lResponse = lResponse?.priorResponse(); lResponse }() != null) {
            result++
        }
        return result
    }

    private fun createHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
    }.build()
}
