package com.zalesskyi.android.memorye.network.converters.lyrics

import com.zalesskyi.android.memorye.data.converters.BaseConverter
import com.zalesskyi.android.memorye.data.models.LyricsSearchResult
import com.zalesskyi.android.memorye.data.models.LyricsSearchResultModel
import com.zalesskyi.android.memorye.network.beans.lyrics.LyricsSearchResultBean

interface LyricsSearchBeanConverter

class LyricsSearchBeanConverterImpl : BaseConverter<LyricsSearchResultBean, LyricsSearchResult>(), LyricsSearchBeanConverter {

    companion object {
        private const val DEFAULT_SONG_RANK = 0
    }

    override fun processConvertInToOut(inObject: LyricsSearchResultBean) = inObject.run {
        LyricsSearchResultModel(lyricId, lyricChecksum, songRank ?: DEFAULT_SONG_RANK)
    }

    override fun processConvertOutToIn(outObject: LyricsSearchResult) =
            throw UnsupportedOperationException("No need")
}