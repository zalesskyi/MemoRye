package com.zalesskyi.android.memorye.network.api

import com.zalesskyi.android.memorye.network.beans.lyrics.LyricsBean
import com.zalesskyi.android.memorye.network.beans.lyrics.LyricsSearchResultsBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LyricsApi {

    @GET("SearchLyricText")
    fun searchByLyricsText(@Query("lyricText") lyricText: String): Single<LyricsSearchResultsBean>

    @GET("GetLyric")
    fun getLyrics(@Query("lyricId") lyricId: Long, @Query("lyricChecksum") checksum: String): Single<LyricsBean>
}