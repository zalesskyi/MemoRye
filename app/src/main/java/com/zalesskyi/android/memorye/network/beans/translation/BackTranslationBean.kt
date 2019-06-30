package com.zalesskyi.android.memorye.network.beans.translation

import com.fasterxml.jackson.annotation.JsonProperty

data class BackTranslationBean(@JsonProperty("normalizedText")
                               val text: String?)