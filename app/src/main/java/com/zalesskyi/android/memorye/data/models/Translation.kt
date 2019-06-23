package com.zalesskyi.android.memorye.data.models

import kotlinx.android.parcel.Parcelize

interface Translation : Model<String> {
    val source: String
    var lang: Language
    var tag: String?
    val backTranslations: List<String>?
}

@Parcelize
data class TranslationModel(override var id: String?,
                            override val source: String,
                            override var lang: Language,
                            override var tag: String? = null,
                            override val backTranslations: List<String>? = null) : Translation