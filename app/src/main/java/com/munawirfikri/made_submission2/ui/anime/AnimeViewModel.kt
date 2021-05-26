package com.munawirfikri.made_submission2.ui.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.munawirfikri.made_submission2.core.domain.usecase.AnimeUseCase

class AnimeViewModel(animeUseCase: AnimeUseCase) : ViewModel() { val animeUseCase = animeUseCase.getAllAnime().asLiveData() }