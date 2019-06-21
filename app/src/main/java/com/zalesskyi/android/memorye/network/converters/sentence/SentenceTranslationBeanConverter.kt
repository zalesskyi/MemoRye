package com.zalesskyi.android.memorye.network.converters.sentence

import com.zalesskyi.android.memorye.data.converters.BaseConverter
import com.zalesskyi.android.memorye.data.models.Language
import com.zalesskyi.android.memorye.data.models.Translation
import com.zalesskyi.android.memorye.data.models.TranslationModel
import com.zalesskyi.android.memorye.network.beans.SentenceTranslationBean
import com.zalesskyi.android.memorye.utils.EMPTY_STRING
import java.util.*

interface SentenceTranslationBeanConverter

class SentenceTranslationBeanConverterImpl : BaseConverter<SentenceTranslationBean, Translation>(), SentenceTranslationBeanConverter {

    override fun processConvertInToOut(inObject: SentenceTranslationBean) = inObject.run {
        TranslationModel(
                UUID.randomUUID().toString(),
                text ?: EMPTY_STRING,
                Language.valueOf(lang ?: EMPTY_STRING))
    }

    override fun processConvertOutToIn(outObject: Translation) =
            throw UnsupportedOperationException("No need")
}