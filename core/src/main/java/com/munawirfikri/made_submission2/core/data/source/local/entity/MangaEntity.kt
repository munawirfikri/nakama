package com.munawirfikri.made_submission2.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mangaentities")
data class MangaEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "mal_id")
        var id: Int,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "status")
        var status: String? = null,

        @ColumnInfo(name = "synopsis")
        var synopsis: String? = null,

        @ColumnInfo(name = "volumes")
        var volumes: Int? = null,

        @ColumnInfo(name = "start_date")
        var startDate: String? = null,

        @ColumnInfo(name = "end_date")
        var endDate: String? = null,

        @ColumnInfo(name = "score")
        var score: Double? = null,

        @ColumnInfo(name = "image_url")
        var poster: String? = null,

        @ColumnInfo(name = "type")
        var type: String? = null,

        @ColumnInfo(name= "isFavorite")
        var isFavorite: Boolean = false
        )