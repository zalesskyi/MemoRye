package com.zalesskyi.android.memorye.network.api

import com.zalesskyi.android.memorye.BuildConfig
import com.zalesskyi.android.memorye.network.SENTENCE_TRANSLATE_ENDPOINT
import com.zalesskyi.android.memorye.network.TRANSLATOR_API_VERSION
import com.zalesskyi.android.memorye.network.WORD_TRANSLATE_ENDPOINT
import com.zalesskyi.android.memorye.network.beans.SentenceSourceBean
import com.zalesskyi.android.memorye.network.beans.WordSourceBean
import com.zalesskyi.android.memorye.network.requests.TranslateRequestBean
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TranslatorApi {

    @Headers("Ocp-Apim-Subscription-Key: ${BuildConfig.TRANSLATOR_API_KEY}",
            "Content-Type: application/json")
    @POST("$WORD_TRANSLATE_ENDPOINT?api-version=$TRANSLATOR_API_VERSION")
    fun translateWord(@Query("from") from: String,
                      @Query("to") to: String,
                      @Body source: List<TranslateRequestBean>): Single<List<WordSourceBean>>

    @Headers("Ocp-Apim-Subscription-Key: ${BuildConfig.TRANSLATOR_API_KEY}",
            "Content-Type: application/json")
    @POST("$SENTENCE_TRANSLATE_ENDPOINT?api-version=$TRANSLATOR_API_VERSION")
    fun translateSentence(@Query("to") to: String,
                          @Body source: List<TranslateRequestBean>): Single<List<SentenceSourceBean>>
}