package com.example.series.data

import com.example.series.domain.source.SeriesRemoteDataSource
import javax.inject.Inject

class SeriesRemoteDataSourceImpl @Inject constructor(
    private val seriesService: SeriesService
) : SeriesRemoteDataSource {


    override suspend fun getTop100Series() = seriesService.get100SeriesList()


}