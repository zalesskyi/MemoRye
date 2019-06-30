package com.zalesskyi.android.memorye.network.converters.lyrics

import com.zalesskyi.android.memorye.data.converters.BaseConverter
import com.zalesskyi.android.memorye.data.models.Lyrics
import com.zalesskyi.android.memorye.data.models.LyricsModel
import com.zalesskyi.android.memorye.network.beans.lyrics.LyricsBean
import com.zalesskyi.android.memorye.utils.EMPTY_STRING

interface LyricsBeanConverter

class LyricsBeanConverterImpl : BaseConverter<LyricsBean, Lyrics>(), LyricsBeanConverter {

    override fun processConvertInToOut(inObject: LyricsBean) = inObject.run {
        LyricsModel(lyricId,
                lyricChecksum ?: EMPTY_STRING,
                text ?: EMPTY_STRING,
                song ?: EMPTY_STRING,
                artist ?: EMPTY_STRING)
    }

    override fun processConvertOutToIn(outObject: Lyrics) =
            throw UnsupportedOperationException("No need")
}