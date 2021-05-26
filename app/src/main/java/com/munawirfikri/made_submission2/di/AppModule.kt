package com.munawirfikri.made_submission2.di

import com.munawirfikri.made_submission2.core.domain.usecase.AnimeInteractor
import com.munawirfikri.made_submission2.core.domain.usecase.AnimeUseCase
import com.munawirfikri.made_submission2.ui.anime.AnimeViewModel
import com.munawirfikri.made_submission2.ui.detail.DetailViewModel
import com.munawirfikri.made_submission2.ui.manga.MangaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { AnimeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { MangaViewModel(get()) }
}