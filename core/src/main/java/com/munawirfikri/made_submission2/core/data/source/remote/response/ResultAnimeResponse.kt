package com.munawirfikri.made_submission2.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResultAnimeResponse
    (
    @field:SerializedName("results")
    val result: List<AnimeResponse>
            )