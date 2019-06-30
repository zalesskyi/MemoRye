package com.zalesskyi.android.memorye.network.beans.translation

import com.fasterxml.jackson.annotation.JsonProperty

data class WordTranslationBean(@JsonProperty("normalizedTarget")
                               val text: String?,
                               @JsonProperty("posTag")
                               val tag: String?,
                               @JsonProperty("backTranslations")
                               val backTranslations: List<BackTranslationBean>?)