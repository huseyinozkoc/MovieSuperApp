package com.example.series.di

import com.example.series.data.SeriesRemoteDataSourceImpl
import com.example.series.data.SeriesService
import com.example.series.domain.source.SeriesRemoteDataSource
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
       seriesService: SeriesService,
    ): SeriesRemoteDataSource =
        SeriesRemoteDataSourceImpl(seriesService)

}