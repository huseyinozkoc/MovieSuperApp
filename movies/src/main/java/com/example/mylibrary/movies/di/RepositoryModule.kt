package com.example.mylibrary.movies.di

import com.example.mylibrary.movies.data.repository.MoviesRepositoryImpl
import com.example.mylibrary.movies.data.source.MoviesRoomDB
import com.example.mylibrary.movies.domain.MoviesRepository
import com.example.mylibrary.movies.domain.source.MoviesLocalDataSource
import com.example.mylibrary.movies.domain.source.MoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource,
        moviesRoomDB: MoviesRoomDB,
    ): MoviesRepository =
        MoviesRepositoryImpl(moviesRemoteDataSource,moviesLocalDataSource,moviesRoomDB)
}