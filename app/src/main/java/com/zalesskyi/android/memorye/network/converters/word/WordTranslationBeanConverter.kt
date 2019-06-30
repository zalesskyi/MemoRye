package com.zalesskyi.android.memorye.network.converters.word

import com.zalesskyi.android.memorye.data.converters.BaseConverter
import com.zalesskyi.android.memorye.data.models.Language
import com.zalesskyi.android.memorye.data.models.Translation
import com.zalesskyi.android.memorye.data.models.TranslationModel
import com.zalesskyi.android.memorye.network.beans.translation.WordTranslationBean
import com.zalesskyi.android.memorye.utils.EMPTY_STRING
import java.util.*

interface WordTranslationBeanConverter

class WordTranslationBeanConverterImpl : BaseConverter<WordTranslationBean, Translation>(), WordTranslationBeanConverter {

    override fun processConvertInToOut(inObject: WordTranslationBean) = inObject.run {
        TranslationModel(
                UUID.randomUUID().toString(),
                text ?: EMPTY_STRING,
                Language.UNKNOWN,
                tag,
                backTranslations?.mapNotNull { it.text })
    }

    override fun processConvertOutToIn(outObject: Translation): WordTranslationBean =
            throw UnsupportedOperationException("No need")
}