package com.zalesskyi.android.memorye.network.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class SentenceSourceBean(@JsonProperty("detectedLanguage")
                              val detectedLang: SentenceLanguageBean?,
                              @JsonProperty("translations")
                              val translations: List<SentenceTranslationBean>?)