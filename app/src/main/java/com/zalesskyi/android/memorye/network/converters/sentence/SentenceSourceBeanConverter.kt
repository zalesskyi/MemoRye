package com.zalesskyi.android.memorye.network.converters.sentence

import com.zalesskyi.android.memorye.data.converters.BaseConverter
import com.zalesskyi.android.memorye.data.models.Language
import com.zalesskyi.android.memorye.data.models.TranslationSource
import com.zalesskyi.android.memorye.data.models.TranslationSourceModel
import com.zalesskyi.android.memorye.network.beans.SentenceSourceBean
import com.zalesskyi.android.memorye.utils.EMPTY_STRING
import java.util.*

interface SentenceBeanConverter

class SentenceSourceBeanConverterImpl : BaseConverter<SentenceSourceBean, TranslationSource>(), SentenceBeanConverter {

    private val sentenceTranslationConverter = SentenceTranslationBeanConverterImpl()

    override fun processConvertInToOut(inObject: SentenceSourceBean) = inObject.run {
        TranslationSourceModel(
                UUID.randomUUID().toString(),
                EMPTY_STRING,
                Language.valueOf(detectedLang?.language ?: EMPTY_STRING),
                sentenceTranslationConverter.convertListInToOut(translations))
    }

    override fun processConvertOutToIn(outObject: TranslationSource) =
            throw UnsupportedOperationException("No need")
}