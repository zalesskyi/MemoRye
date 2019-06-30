package com.zalesskyi.android.memorye.providers

import com.zalesskyi.android.memorye.data.models.Lyrics
import com.zalesskyi.android.memorye.data.models.LyricsSearchResult
import com.zalesskyi.android.memorye.extensions.safeLet
import com.zalesskyi.android.memorye.network.modules.LyricsModule
import com.zalesskyi.android.memorye.network.modules.NetworkModule
import com.zalesskyi.android.memorye.providers.base.BaseOnlineProvider
import io.reactivex.Observable
import io.reactivex.Single

interface LyricsProvider {

    fun searchByLyricsText(lyricsText: String): Single<List<LyricsSearchResult>>

    fun getLyrics(lyricsId: Long, lyricsChecksum: String): Single<Lyrics>

    fun getLyricsByText(lyricsText: String): Single<List<Lyrics>>
}

class LyricsProviderImpl : BaseOnlineProvider<Lyrics, LyricsModule>(), LyricsProvider {

    companion object {
        private const val LYRICS_MAX_COUNT = 15L
    }

    override fun initNetworkModule() = NetworkModule.client.lyrics

    override fun searchByLyricsText(lyricsText: String): Single<List<LyricsSearchResult>> =
            networkModule.searchByLyricsText(lyricsText)

    override fun getLyrics(lyricsId: Long, lyricsChecksum: String): Single<Lyrics> =
            networkModule.getLyrics(lyricsId, lyricsChecksum)

    override fun getLyricsByText(lyricsText: String): Single<List<Lyrics>> =
            searchByLyricsText(lyricsText).toObservable()
                    .flatMapIterable { it }
                    .take(LYRICS_MAX_COUNT)
                    .filter { it.id != null && it.lyricChecksum != null }
                    .flatMap { searchResult ->
                        searchResult.run {
                            safeLet(id, lyricChecksum) { lyricId, checksum ->
                                getLyrics(lyricId, checksum).toObservable()
                            } ?: Observable.error(IllegalArgumentException("ID and checksum must not be null"))
                        }
                    }
                    .toList()
}