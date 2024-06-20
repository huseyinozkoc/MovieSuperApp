package com.example.mylibrary.movies.di

import com.example.mylibrary.movies.data.source.MoviesDAO
import com.example.mylibrary.movies.data.source.MoviesLocalDataSourceImpl
import com.example.mylibrary.movies.data.source.remote.MovieService
import com.example.mylibrary.movies.data.source.remote.MoviesRemoteDataSourceImpl
import com.example.mylibrary.movies.domain.source.MoviesLocalDataSource
import com.example.mylibrary.movies.domain.source.MoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        moviesService: MovieService,
    ): MoviesRemoteDataSource =
        MoviesRemoteDataSourceImpl(moviesService)


    @Provides
    @Singleton
    fun provideLocalDataSource(
        moviesDAO: MoviesDAO,
    ): MoviesLocalDataSource = MoviesLocalDataSourceImpl(moviesDAO)


}