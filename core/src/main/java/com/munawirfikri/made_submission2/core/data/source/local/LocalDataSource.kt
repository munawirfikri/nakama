package com.munawirfikri.made_submission2.core.data.source.local

import com.munawirfikri.made_submission2.core.data.source.local.entity.AnimeEntity
import com.munawirfikri.made_submission2.core.data.source.local.entity.MangaEntity
import com.munawirfikri.made_submission2.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mAnimeDao: AnimeDao) {
    fun getAllAnime(): Flow<List<AnimeEntity>> = mAnimeDao.getAnime()
    fun getAllManga(): Flow<List<MangaEntity>> = mAnimeDao.getManga()
    fun getFavoriteAnime(): Flow<List<AnimeEntity>> = mAnimeDao.getFavoriteAnime()
    fun getFavoriteManga(): Flow<List<MangaEntity>> = mAnimeDao.getFavoriteManga()
    fun getAnimeById(animeId: String): Flow<AnimeEntity> = mAnimeDao.getAnimeById(animeId)
    fun getMangaById(mangaId: String): Flow<MangaEntity> = mAnimeDao.getMangaById(mangaId)
    suspend fun insertAnime(anime: List<AnimeEntity>) = mAnimeDao.insertAnime(anime)
    suspend fun insertManga(manga: List<MangaEntity>) = mAnimeDao.insertManga(manga)
    fun setAnimeFavorite(anime: AnimeEntity, newState: Boolean) {
        anime.isFavorite = newState
        mAnimeDao.updateAnime(anime)
    }
    fun setMangaFavorite(manga: MangaEntity, newState: Boolean){
        manga.isFavorite = newState
        mAnimeDao.updateManga(manga)
    }
}