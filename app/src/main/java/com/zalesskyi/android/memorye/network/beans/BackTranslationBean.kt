package com.zalesskyi.android.memorye.network.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class BackTranslationBean(@JsonProperty("normalizedText")
                               val text: String?)