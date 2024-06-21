package com.example.series.domain.source

import com.example.series.data.Series

interface SeriesRemoteDataSource {

    suspend fun getTop100Series(): List<Series>

}