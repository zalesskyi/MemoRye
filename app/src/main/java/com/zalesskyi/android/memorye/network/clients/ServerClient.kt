package com.zalesskyi.android.memorye.network.clients

import com.zalesskyi.android.memorye.BuildConfig
import com.zalesskyi.android.memorye.network.api.TranslatorApi
import com.zalesskyi.android.memorye.network.modules.NetworkModule.mapper
import com.zalesskyi.android.memorye.network.modules.TranslatorModuleImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class ServerClient {

    companion object {
        private const val TIMEOUT_IN_SECONDS = 30L
    }

    private val retrofitBuilder = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .client(createHttpClient())

    private val retrofitTranslator = retrofitBuilder
        .baseUrl(BuildConfig.TRANSLATOR_API_ENPOINT)
        .build()

    val translator by lazy { TranslatorModuleImpl(retrofitTranslator.create(TranslatorApi::class.java)) }

    private fun createHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
    }.build()
}
