package com.munawirfikri.made_submission2.core.di

import androidx.room.Room
import com.munawirfikri.made_submission2.core.data.source.local.LocalDataSource
import com.munawirfikri.made_submission2.core.data.source.local.room.AnimeDatabase
import com.munawirfikri.made_submission2.core.data.source.remote.network.ApiService
import com.munawirfikri.made_submission2.core.data.source.remote.network.RemoteDataSource
import com.munawirfikri.made_submission2.core.domain.repository.IAnimeRepository
import com.munawirfikri.made_submission2.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<AnimeDatabase>().animeDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AnimeDatabase::class.java, "Anime.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IAnimeRepository> {
        com.munawirfikri.made_submission2.core.data.AnimeRepository(
            get(),
            get(),
            get()
        )
    }
}