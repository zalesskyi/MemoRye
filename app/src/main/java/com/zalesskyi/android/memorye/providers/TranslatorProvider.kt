package com.zalesskyi.android.memorye.providers

import com.zalesskyi.android.memorye.data.models.Language
import com.zalesskyi.android.memorye.data.models.TranslationSource
import com.zalesskyi.android.memorye.network.modules.NetworkModule
import com.zalesskyi.android.memorye.network.modules.TranslatorModule
import com.zalesskyi.android.memorye.providers.base.BaseOnlineProvider
import io.reactivex.Single

interface TranslatorProvider {

    fun translateWord(fromLanguage: Language,
                      toLanguage: Language,
                      word: String): Single<TranslationSource>

    fun translateSentence(toLanguage: Language,
                          sentence: String): Single<TranslationSource>
}

class TranslatorProviderImpl : BaseOnlineProvider<TranslationSource, TranslatorModule>(),
    TranslatorProvider {

    override fun initNetworkModule() = NetworkModule.client.translator

    override fun translateWord(fromLanguage: Language, toLanguage: Language, word: String): Single<TranslationSource> =
        networkModule.translateWord(fromLanguage, toLanguage, word)
            .map { translationSource ->
                translationSource.apply {
                    sourceLang = fromLanguage
                    translations.forEach { translation ->
                        translation.lang = toLanguage
                    }
                }
            }

    override fun translateSentence(toLanguage: Language, sentence: String): Single<TranslationSource> =
        networkModule.translateSentence(toLanguage, sentence)
            .map { translationSource ->
                translationSource.apply { source = sentence }
            }
}