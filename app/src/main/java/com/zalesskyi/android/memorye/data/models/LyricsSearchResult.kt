package com.zalesskyi.android.memorye.data.models

import kotlinx.android.parcel.Parcelize

interface LyricsSearchResult : Model<Long> {
    val lyricChecksum: String?
    val songRank: Int
}

@Parcelize
data class LyricsSearchResultModel(override var id: Long?,
                                   override val lyricChecksum: String?,
                                   override val songRank: Int) : LyricsSearchResult