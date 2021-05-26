package com.munawirfikri.made_submission2.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime (
    var id: Int,
    var title: String? = null,
    var status: String? = null,
    var synopsis: String? = null,
    var episodes: Int? = null,
    var startDate: String? = null,
    var endDate: String? = null,
    var score: Double? = null,
    var poster: String? = null,
    var type: String? = null,
    var isFavorite: Boolean
): Parcelable