package com.munawirfikri.made_submission2.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MangaResponse (
    @field:SerializedName("mal_id")
    var id: Int,

    @field:SerializedName("rank")
    var rank: Int? = null,

    @field:SerializedName("title")
    var title: String? = null,

    @field:SerializedName("publishing")
    var status: String? = null,

    @field:SerializedName("synopsis")
    var synopsis: String? = null,

    @field:SerializedName("volumes")
    var volumes: Int? = null,

    @field:SerializedName("start_date")
    var startDate: String? = null,

    @field:SerializedName("end_date")
    var endDate: String? = null,

    @field:SerializedName("score")
    var score: Double? = null,

    @field:SerializedName("image_url")
    var poster: String? = null,

    @field:SerializedName("type")
    var type: String? = null,

    ): Parcelable