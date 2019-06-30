package com.zalesskyi.android.memorye.network.beans.translation

import com.fasterxml.jackson.annotation.JsonProperty

data class WordSourceBean(@JsonProperty("normalizedSource")
                          val source: String,
                          @JsonProperty("translations")
                          val translations: List<WordTranslationBean>)