package com.munawirfikri.made_submission2.core.domain.repository

import com.munawirfikri.made_submission2.core.domain.model.Anime
import com.munawirfikri.made_submission2.core.domain.model.Manga
import com.munawirfikri.made_submission2.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getAllAnime(): Flow<Resource<List<Anime>>>
    fun getAllManga(): Flow<Resource<List<Manga>>>
    fun getFavoriteAnime(): Flow<List<Anime>>
    fun getFavoriteManga(): Flow<List<Manga>>
    fun getAnimeById(id: String): Flow<Resource<Anime>>
    fun getMangaById(id: String): Flow<Resource<Manga>>
    fun setAnimeFavorite(anime: Anime, state: Boolean)
    fun setMangaFavorite(manga: Manga, state: Boolean)
}