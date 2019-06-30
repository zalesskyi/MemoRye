package com.zalesskyi.android.memorye.network.clients

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.zalesskyi.android.memorye.BuildConfig
import com.zalesskyi.android.memorye.network.api.LyricsApi
import com.zalesskyi.android.memorye.network.api.TranslatorApi
import com.zalesskyi.android.memorye.network.modules.LyricsModuleImpl
import com.zalesskyi.android.memorye.network.modules.NetworkModule.mapper
import com.zalesskyi.android.memorye.network.modules.TranslatorModuleImpl
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

class ServerClient {

    companion object {
        private const val TIMEOUT_IN_SECONDS = 30L
    }

    private val retrofitTranslator = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createHttpClient())
            .baseUrl(BuildConfig.TRANSLATOR_API_ENPOINT)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build()

    @Suppress("DEPRECATION")
    private val retrofitLyrics =  Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createHttpClient())
            .baseUrl(BuildConfig.LYRICS_API_ENPOINT)
            .addConverterFactory(SimpleXmlConverterFactory
                    .createNonStrict(Persister(AnnotationStrategy())))
            .build()

    val translator by lazy { TranslatorModuleImpl(retrofitTranslator.create(TranslatorApi::class.java)) }

    val lyrics by lazy { LyricsModuleImpl(retrofitLyrics.create(LyricsApi::class.java)) }

    private fun createHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Request>>>>")
                    .response("Response<<<<")
                    .build())
        }
    }.build()
}