package com.munawirfikri.made_submission2.core.data.source.remote.network

import com.munawirfikri.made_submission2.core.data.source.remote.response.ResultAnimeResponse
import com.munawirfikri.made_submission2.core.data.source.remote.response.ResultMangaResponse
import retrofit2.http.GET

interface ApiService {
    @GET("search/anime?q=&order_by=members&sort=desc&page=1&type=tv")
    suspend fun getListAnime(): ResultAnimeResponse
    @GET("search/manga?q=&order_by=members&sort=desc&page=1&type=manga")
    suspend fun getListManga(): ResultMangaResponse
}