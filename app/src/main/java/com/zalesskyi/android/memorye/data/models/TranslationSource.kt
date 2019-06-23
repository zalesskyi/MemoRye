package com.zalesskyi.android.memorye.data.models

import kotlinx.android.parcel.Parcelize

interface TranslationSource : Model<String> {
    var source: String
    var sourceLang: Language
    val translations: List<Translation>
}

@Parcelize
data class TranslationSourceModel(override var id: String?,
                                  override var source: String,
                                  override var sourceLang: Language,
                                  override val translations: List<Translation>) : TranslationSource