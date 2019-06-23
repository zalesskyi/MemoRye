package com.zalesskyi.android.memorye.network.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class SentenceLanguageBean(@JsonProperty("language")
                                val language: String?,
                                @JsonProperty("score")
                                val score: Int?)