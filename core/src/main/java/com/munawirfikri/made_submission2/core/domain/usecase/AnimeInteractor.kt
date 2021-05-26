package com.munawirfikri.made_submission2.core.domain.usecase

import com.munawirfikri.made_submission2.core.domain.model.Anime
import com.munawirfikri.made_submission2.core.domain.model.Manga
import com.munawirfikri.made_submission2.core.domain.repository.IAnimeRepository
import com.munawirfikri.made_submission2.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(private val animeRepository: IAnimeRepository) : AnimeUseCase {
    override fun getAllAnime(): Flow<Resource<List<Anime>>> = animeRepository.getAllAnime()

    override fun getAllManga(): Flow<Resource<List<Manga>>> = animeRepository.getAllManga()

    override fun getFavoriteAnime(): Flow<List<Anime>> = animeRepository.getFavoriteAnime()

    override fun getFavoriteManga(): Flow<List<Manga>> = animeRepository.getFavoriteManga()

    override fun getAnimeById(id: String): Flow<Resource<Anime>> = animeRepository.getAnimeById(id)

    override fun getMangaById(id: String): Flow<Resource<Manga>> = animeRepository.getMangaById(id)

    override fun setAnimeFavorite(anime: Anime, state: Boolean) = animeRepository.setAnimeFavorite(anime, state)

    override fun setMangaFavorite(manga: Manga, state: Boolean) = animeRepository.setMangaFavorite(manga, state)

}