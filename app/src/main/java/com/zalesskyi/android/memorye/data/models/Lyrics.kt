package com.zalesskyi.android.memorye.data.models

import kotlinx.android.parcel.Parcelize

interface Lyrics : Model<Long> {
    val checksum: String
    val text: String
    val songTitle: String
    val artistName: String
}

@Parcelize
data class LyricsModel(override var id: Long?,
                       override val checksum: String,
                       override val text: String,
                       override val songTitle: String,
                       override val artistName: String) : Lyrics