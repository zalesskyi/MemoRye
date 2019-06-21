package com.zalesskyi.android.memorye.data.models

import kotlinx.android.parcel.Parcelize

interface Translation : Model<String> {
    val source: String
    val lang: Language
    val tag: String?
    val backTranslations: List<String>?
}

@Parcelize
data class TranslationModel(override var id: String?,
                            override val source: String,
                            override val lang: Language,
                            override val tag: String? = null,
                            override val backTranslations: List<String>? = null) : Translation