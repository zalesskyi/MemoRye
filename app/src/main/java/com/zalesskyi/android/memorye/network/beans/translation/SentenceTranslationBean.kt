package com.zalesskyi.android.memorye.network.beans.translation

import com.fasterxml.jackson.annotation.JsonProperty

data class SentenceTranslationBean(@JsonProperty("text")
                                   val text: String?,
                                   @JsonProperty("to")
                                   val lang: String?)