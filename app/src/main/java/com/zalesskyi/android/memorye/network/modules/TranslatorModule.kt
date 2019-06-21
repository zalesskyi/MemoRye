package com.zalesskyi.android.memorye.network.modules

import com.zalesskyi.android.memorye.data.models.Language
import com.zalesskyi.android.memorye.data.models.TranslationSource
import com.zalesskyi.android.memorye.extensions.printLogE
import com.zalesskyi.android.memorye.network.api.TranslatorApi
import com.zalesskyi.android.memorye.network.base.BaseRxModule
import com.zalesskyi.android.memorye.network.beans.WordSourceBean
import com.zalesskyi.android.memorye.network.converters.sentence.SentenceSourceBeanConverterImpl
import com.zalesskyi.android.memorye.network.converters.word.WordSourceBeanConverterImpl
import com.zalesskyi.android.memorye.network.requests.TranslateRequestBean
import io.reactivex.Single

interface TranslatorModule {

    fun translateWord(fromLanguage: Language, toLanguage: Language, word: String): Single<TranslationSource>

    fun translateSentence(toLanguage: Language, sentence: String): Single<TranslationSource>
}

class TranslatorModuleImpl(api: TranslatorApi) :
        BaseRxModule<TranslatorApi, WordSourceBean, TranslationSource>(api, WordSourceBeanConverterImpl()),
        TranslatorModule {

    private val sentenceSourceConverter = SentenceSourceBeanConverterImpl()

    override fun translateWord(fromLanguage: Language, toLanguage: Language, word: String): Single<TranslationSource> =
            api.translateWord(fromLanguage.code, toLanguage.code, listOf(TranslateRequestBean(word)))
                    .map { it.first() }
                    .onErrorResumeNext {
                        it.printLogE()
                        Single.just(WordSourceBean("", listOf()))
                    }
                    .compose(converter.singleOUTtoINSingle())

    override fun translateSentence(toLanguage: Language, sentence: String): Single<TranslationSource> =
            api.translateSentence(toLanguage.code, listOf(TranslateRequestBean(sentence)))
                    .map { it.first() }
                    .compose(sentenceSourceConverter.singleOUTtoINSingle())
}