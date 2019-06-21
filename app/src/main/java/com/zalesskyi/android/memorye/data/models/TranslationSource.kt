package com.zalesskyi.android.memorye.data.models

import kotlinx.android.parcel.Parcelize

interface TranslationSource : Model<String> {
    val source: String
    val sourceLang: Language
    val translations: List<Translation>
}

@Parcelize
data class TranslationSourceModel(override var id: String?,
                                  override val source: String,
                                  override val sourceLang: Language,
                                  override val translations: List<Translation>) : TranslationSource