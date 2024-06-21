package com.example.series.data

import androidx.room.withTransaction
import com.example.series.common.Resource
import com.example.series.domain.SeriesRepository
import com.example.series.domain.source.SeriesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SeriesRepositoryImpl constructor(
    private val seriesRemoteDataSource: SeriesRemoteDataSource,

) : SeriesRepository {
    override fun getTop100Series(): Flow<Resource<List<Series>>> = flow {
        emit(Resource.Loading)
        val response = try {
            seriesRemoteDataSource.getTop100Series()
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
            null
        }
        response?.let { data ->
            emit(Resource.Success(data))
        }
    }


}
