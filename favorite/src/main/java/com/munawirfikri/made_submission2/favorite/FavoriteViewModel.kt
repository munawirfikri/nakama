package com.munawirfikri.made_submission2.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.munawirfikri.made_submission2.core.domain.usecase.AnimeUseCase

class FavoriteViewModel(animeUseCase: AnimeUseCase) : ViewModel() {
    val getFavoriteAnime = animeUseCase.getFavoriteAnime().asLiveData()
    val getFavoriteManga = animeUseCase.getFavoriteManga().asLiveData()
}