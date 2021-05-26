package com.munawirfikri.made_submission2.core.utils

import com.munawirfikri.made_submission2.core.data.source.local.entity.AnimeEntity
import com.munawirfikri.made_submission2.core.data.source.local.entity.MangaEntity
import com.munawirfikri.made_submission2.core.data.source.remote.response.AnimeResponse
import com.munawirfikri.made_submission2.core.data.source.remote.response.MangaResponse
import com.munawirfikri.made_submission2.core.domain.model.Anime
import com.munawirfikri.made_submission2.core.domain.model.Manga

object DataMapper {

    fun mapAnimeResponsesToEntities(input: List<AnimeResponse>) : List<AnimeEntity>  {
        val animeList = ArrayList<AnimeEntity>()
        input.map {
            val anime = AnimeEntity(
                it.id, it.title, it.status, it.synopsis, it.episodes, it.startDate, it.endDate, it.score, it.poster, it.type, false
            )
            animeList.add(anime)
        }
        return animeList
    }

    fun mapMangaResponsesToEntities(input: List<MangaResponse>) : List<MangaEntity>  {
        val mangaList = ArrayList<MangaEntity>()
        input.map {
            val manga = MangaEntity(
                it.id, it.title, it.status, it.synopsis, it.volumes, it.startDate, it.endDate, it.score, it.poster, it.type, false
            )
            mangaList.add(manga)
        }
        return mangaList
    }

    fun mapAnimeEntitiesToDomain(input: List<AnimeEntity>) : List<Anime> =
        input.map {
            Anime(it.id, it.title, it.status, it.synopsis, it.episodes, it.startDate, it.endDate, it.score, it.poster, it.type, it.isFavorite )
        }

    fun mapMangaEntitiesToDomain(input: List<MangaEntity>) : List<Manga> =
        input.map {
            Manga(it.id, it.title, it.status, it.synopsis, it.volumes, it.startDate, it.endDate, it.score, it.poster, it.type, it.isFavorite )
        }


    fun mapAnimeEntityToDomain(input: AnimeEntity) = Anime (
        input.id,input.title, input.status, input.synopsis, input.episodes, input.startDate, input.endDate, input.score, input.poster, input.type, input.isFavorite
    )

    fun mapMangaEntityToDomain(input: MangaEntity) = Manga (
        input.id, input.title, input.status, input.synopsis, input.volumes, input.startDate, input.endDate, input.score, input.poster, input.type, input.isFavorite
    )


    fun mapAnimeDomainToEntity(input: Anime) = AnimeEntity(
        input.id, input.title, input.status, input.synopsis, input.episodes, input.startDate, input.endDate, input.score, input.poster, input.type, input.isFavorite
    )

    fun mapMangaDomainToEntity(input: Manga) = MangaEntity(
        input.id, input.title, input.status, input.synopsis, input.volumes, input.startDate, input.endDate, input.score, input.poster, input.type, input.isFavorite
    )
}