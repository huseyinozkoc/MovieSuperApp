package com.example.series.domain

import com.example.series.data.Series
import kotlinx.coroutines.flow.Flow
import com.example.series.common.Resource

interface SeriesRepository {

    fun getTop100Series(): Flow<Resource<List<Series>>>
}