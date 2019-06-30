package com.zalesskyi.android.memorye.network.beans.lyrics

import org.simpleframework.xml.Element

data class LyricsBean
@JvmOverloads constructor(@field:Element(name = "TrackId", required = false)
                          var trackId: Long? = null,
                          @field:Element(name = "LyricChecksum", required = false)
                          var lyricChecksum: String? = null,
                          @field:Element(name = "LyricId", required = false)
                          var lyricId: Long? = null,
                          @field:Element(name = "LyricSong", required = false)
                          var song: String? = null,
                          @field:Element(name = "LyricArtist", required = false)
                          var artist: String? = null,
                          @field:Element(name = "LyricRank", required = false)
                          var lyricRank: Int? = null,
                          @field:Element(name = "Lyric", required = false)
                          var text: String? = null)