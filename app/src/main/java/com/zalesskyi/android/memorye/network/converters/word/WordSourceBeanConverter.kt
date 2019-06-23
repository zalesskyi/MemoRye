package com.zalesskyi.android.memorye.network.converters.word

import com.zalesskyi.android.memorye.data.converters.BaseConverter
import com.zalesskyi.android.memorye.data.models.Language
import com.zalesskyi.android.memorye.data.models.TranslationSource
import com.zalesskyi.android.memorye.data.models.TranslationSourceModel
import com.zalesskyi.android.memorye.network.beans.WordSourceBean
import java.util.*

interface WordBeanConverter

class WordSourceBeanConverterImpl : BaseConverter<WordSourceBean, TranslationSource>(), WordBeanConverter {

    private val wordTranslationConverter = WordTranslationBeanConverterImpl()

    override fun processConvertInToOut(inObject: WordSourceBean) = inObject.run {
        TranslationSourceModel(
                UUID.randomUUID().toString(),
                source,
                Language.UNKNOWN,
                wordTranslationConverter.convertListInToOut(translations))
    }

    override fun processConvertOutToIn(outObject: TranslationSource) =
            throw UnsupportedOperationException("No need")
}