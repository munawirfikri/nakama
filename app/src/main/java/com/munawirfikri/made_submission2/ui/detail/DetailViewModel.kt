package com.munawirfikri.made_submission2.ui.detail

import androidx.lifecycle.*
import com.munawirfikri.made_submission2.core.domain.model.Anime
import com.munawirfikri.made_submission2.core.domain.model.Manga
import com.munawirfikri.made_submission2.core.domain.usecase.AnimeUseCase
import com.munawirfikri.made_submission2.core.vo.Resource

class DetailViewModel(private val animeUseCase: AnimeUseCase): ViewModel() {

    private val detailId = MutableLiveData<String>()
    private val animeEntity = MutableLiveData<Anime>()
    private val mangaEntity = MutableLiveData<Manga>()

    fun setSelectedMovie(detailId: String){
        this.detailId.value = detailId
    }

    fun getAnime(): LiveData<Resource<Anime>> = Transformations.switchMap(detailId) { detail ->
        animeUseCase.getAnimeById(detail).asLiveData()
    }

    fun getManga(): LiveData<Resource<Manga>> = Transformations.switchMap(detailId) { detail ->
        animeUseCase.getMangaById(detail).asLiveData()
    }

    fun setAnimeResource(anime: Anime){
        this.animeEntity.value = anime
    }

    fun setMangaResource(manga: Manga){
        this.mangaEntity.value = manga
    }

    fun setFavoriteAnime(){
        val resource = animeEntity.value
        if (resource != null){
            val newState = !resource.isFavorite
            animeUseCase.setAnimeFavorite(resource, newState)
        }
    }

    fun setFavoriteManga(){
        val resource = mangaEntity.value
        if (resource != null){
            val newState = !resource.isFavorite
            animeUseCase.setMangaFavorite(resource, newState)
        }
    }


}