package com.zalesskyi.android.memorye.network.beans.lyrics

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ArrayOfSearchLyricResult", strict = false)
data class LyricsSearchResultsBean
@JvmOverloads constructor(@field:ElementList(entry = "SearchLyricResult", inline = true, required = false)
                          var resultsList: MutableList<LyricsSearchResultBean>? = null)