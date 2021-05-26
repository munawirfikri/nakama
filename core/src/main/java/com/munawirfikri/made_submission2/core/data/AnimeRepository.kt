package com.munawirfikri.made_submission2.core.data

import com.munawirfikri.made_submission2.core.data.source.local.LocalDataSource
import com.munawirfikri.made_submission2.core.data.source.remote.network.ApiResponse
import com.munawirfikri.made_submission2.core.data.source.remote.network.RemoteDataSource
import com.munawirfikri.made_submission2.core.data.source.remote.response.AnimeResponse
import com.munawirfikri.made_submission2.core.data.source.remote.response.MangaResponse
import com.munawirfikri.made_submission2.core.domain.model.Anime
import com.munawirfikri.made_submission2.core.domain.model.Manga
import com.munawirfikri.made_submission2.core.domain.repository.IAnimeRepository
import com.munawirfikri.made_submission2.core.utils.AppExecutors
import com.munawirfikri.made_submission2.core.utils.DataMapper
import com.munawirfikri.made_submission2.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors)
    : IAnimeRepository {

    override fun getAllAnime(): Flow<Resource<List<Anime>>> {
        return object : com.munawirfikri.made_submission2.core.data.NetworkBoundResource<List<Anime>, List<AnimeResponse>>(appExecutors) {
            public override fun loadFromDB(): Flow<List<Anime>> {
                return localDataSource.getAllAnime().map { DataMapper.mapAnimeEntitiesToDomain(it) }

            }
            override suspend fun shouldFetch(data: List<Anime>?): Boolean =
                data == null || data.isEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> =
                remoteDataSource.getAllAnime()


            override suspend fun saveCallResult(data: List<AnimeResponse>) {
                val animeList = DataMapper.mapAnimeResponsesToEntities(data)
                localDataSource.insertAnime(animeList)
            }
        }.asFlow()
    }

    override fun getAllManga(): Flow<Resource<List<Manga>>> {
        return object : com.munawirfikri.made_submission2.core.data.NetworkBoundResource<List<Manga>, List<MangaResponse>>(appExecutors){
            override fun loadFromDB(): Flow<List<Manga>> {
                return localDataSource.getAllManga().map { DataMapper.mapMangaEntitiesToDomain(it) }
            }

            override suspend fun shouldFetch(data: List<Manga>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MangaResponse>>> =
                remoteDataSource.getAllManga()

            override suspend fun saveCallResult(data: List<MangaResponse>) {
                val mangaList = DataMapper.mapMangaResponsesToEntities(data)
                localDataSource.insertManga(mangaList)
            }
        }.asFlow()
    }

    override fun getFavoriteAnime(): Flow<List<Anime>> {

        return localDataSource.getFavoriteAnime().map { DataMapper.mapAnimeEntitiesToDomain(it) }
    }



    override fun getFavoriteManga(): Flow<List<Manga>> {
        return localDataSource.getFavoriteManga().map { DataMapper.mapMangaEntitiesToDomain(it) }
    }


    override fun getAnimeById(id: String): Flow<Resource<Anime>> {
        return object : com.munawirfikri.made_submission2.core.data.NetworkBoundResource<Anime, List<AnimeResponse>>(appExecutors){
            override fun loadFromDB(): Flow<Anime> =
                localDataSource.getAnimeById(id).map{ DataMapper.mapAnimeEntityToDomain(it) }


            override suspend fun shouldFetch(data: Anime?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> =
                remoteDataSource.getAllAnime()

            override suspend fun saveCallResult(data: List<AnimeResponse>) {
                for (response in data){
                    if (response.id.toString() == id){
                        Anime(response.id,
                            response.title,
                            response.status,
                            response.synopsis,
                            response.episodes,
                            response.startDate,
                            response.endDate,
                            response.score,
                            response.poster,
                            response.type,
                        false
                        )
                    }
                }
            }
        }.asFlow()
    }

    override fun getMangaById(id: String): Flow<Resource<Manga>> {
        return object : com.munawirfikri.made_submission2.core.data.NetworkBoundResource<Manga, List<MangaResponse>>(appExecutors){
            override fun loadFromDB(): Flow<Manga> =
               localDataSource.getMangaById(id).map{ DataMapper.mapMangaEntityToDomain(it) }

            override suspend fun shouldFetch(data: Manga?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MangaResponse>>> =
                remoteDataSource.getAllManga()

            override suspend fun saveCallResult(data: List<MangaResponse>) {
                for (response in data){
                    if (response.id.toString() == id){
                        Manga(response.id,
                            response.title,
                            response.status,
                            response.synopsis,
                            response.volumes,
                            response.startDate,
                            response.endDate,
                            response.score,
                            response.poster,
                            response.type,
                        false
                        )
                    }
                }
            }
        }.asFlow()
    }

    override fun setAnimeFavorite(anime: Anime, state: Boolean) {
        val animeEntity = DataMapper.mapAnimeDomainToEntity(anime)
        appExecutors.diskIO().execute {localDataSource.setAnimeFavorite(animeEntity, state)}

    }


    override fun setMangaFavorite(manga: Manga, state: Boolean) {
        val mangaEntity = DataMapper.mapMangaDomainToEntity(manga)
        appExecutors.diskIO().execute {localDataSource.setMangaFavorite(mangaEntity, state)}
    }
}