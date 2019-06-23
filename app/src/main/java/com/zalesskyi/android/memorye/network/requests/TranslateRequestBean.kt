package com.zalesskyi.android.memorye.network.requests

import com.fasterxml.jackson.annotation.JsonProperty

data class TranslateRequestBean(@JsonProperty("text")
                                val text: String)