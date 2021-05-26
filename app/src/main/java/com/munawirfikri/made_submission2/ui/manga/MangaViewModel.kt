package com.munawirfikri.made_submission2.ui.manga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.munawirfikri.made_submission2.core.domain.usecase.AnimeUseCase

class MangaViewModel(animeUseCase: AnimeUseCase): ViewModel() {
    val mangaUseCase = animeUseCase.getAllManga().asLiveData()
}