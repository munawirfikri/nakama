package com.munawirfikri.made_submission2.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.munawirfikri.made_submission2.core.data.source.local.entity.AnimeEntity
import com.munawirfikri.made_submission2.core.data.source.local.entity.MangaEntity

@Database(entities = [AnimeEntity::class, MangaEntity::class],
    version = 1,
    exportSchema = false
    )

abstract class AnimeDatabase : RoomDatabase() { abstract fun animeDao(): AnimeDao }