package com.zalesskyi.android.memorye.network.beans.lyrics

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "SearchLyricResult", strict = false)
data class LyricsSearchResultBean
@JvmOverloads constructor(@field:Element(name = "TrackId", required = false)
                          var trackId: Long? = null,
                          @field:Element(name = "LyricChecksum", required = false)
                          var lyricChecksum: String? = null,
                          @field:Element(name = "LyricId", required = false)
                          var lyricId: Long? = null,
                          @field:Element(name = "SongUrl", required = false)
                          var songUrl: String? = null,
                          @field:Element(name = "ArtistUrl", required = false)
                          var artistUrl: String? = null,
                          @field:Element(name = "Artist", required = false)
                          var artist: String? = null,
                          @field:Element(name = "Song", required = false)
                          var song: String? = null,
                          @field:Element(name = "SongRank", required = false)
                          var songRank: Int? = null)