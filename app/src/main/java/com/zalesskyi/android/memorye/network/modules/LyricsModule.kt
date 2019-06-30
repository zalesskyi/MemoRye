package com.zalesskyi.android.memorye.network.modules

import com.zalesskyi.android.memorye.data.models.Lyrics
import com.zalesskyi.android.memorye.data.models.LyricsSearchResult
import com.zalesskyi.android.memorye.network.api.LyricsApi
import com.zalesskyi.android.memorye.network.base.BaseRxModule
import com.zalesskyi.android.memorye.network.beans.lyrics.LyricsBean
import com.zalesskyi.android.memorye.network.beans.lyrics.LyricsSearchResultBean
import com.zalesskyi.android.memorye.network.converters.lyrics.LyricsBeanConverterImpl
import com.zalesskyi.android.memorye.network.converters.lyrics.LyricsSearchBeanConverterImpl
import io.reactivex.Single

interface LyricsModule {
    fun searchByLyricsText(lyricsText: String): Single<List<LyricsSearchResult>>

    fun getLyrics(lyricId: Long, lyricChecksum: String): Single<Lyrics>
}

class LyricsModuleImpl(api: LyricsApi) :
        BaseRxModule<LyricsApi, LyricsBean, Lyrics>(api, LyricsBeanConverterImpl()),
        LyricsModule {

    private val lyricsSearchConverter = LyricsSearchBeanConverterImpl()

    override fun searchByLyricsText(lyricsText: String): Single<List<LyricsSearchResult>> =
        api.searchByLyricsText(lyricsText)
                .map { it.resultsList ?: listOf<LyricsSearchResultBean>() }
                .compose(lyricsSearchConverter.listOUTtoINSingle())

    override fun getLyrics(lyricId: Long, lyricChecksum: String): Single<Lyrics> =
        api.getLyrics(lyricId, lyricChecksum)
                .compose(converter.singleOUTtoINSingle())
}