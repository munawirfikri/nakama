package com.munawirfikri.made_submission2.core.data.source.local.room

import androidx.room.*
import com.munawirfikri.made_submission2.core.data.source.local.entity.AnimeEntity
import com.munawirfikri.made_submission2.core.data.source.local.entity.MangaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM animeentities")
    fun getAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM mangaentities")
    fun getManga(): Flow<List<MangaEntity>>

    @Query("SELECT * FROM animeentities WHERE mal_id = :animeId")
    fun getAnimeById(animeId: String): Flow<AnimeEntity>

    @Query("SELECT * FROM mangaentities WHERE mal_id = :mangaId")
    fun getMangaById(mangaId: String): Flow<MangaEntity>

    @Query("SELECT * FROM animeentities WHERE isFavorite = 1")
    fun getFavoriteAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM mangaentities WHERE isFavorite = 1")
    fun getFavoriteManga(): Flow<List<MangaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: List<AnimeEntity>)

    @Update
    fun updateAnime(anime: AnimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManga(manga: List<MangaEntity>)

    @Update
    fun updateManga(manga: MangaEntity)

}