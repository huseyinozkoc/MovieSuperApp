package com.example.mylibrary.movies.data.repository

import Resource
import androidx.room.withTransaction
import com.example.mylibrary.movies.data.entities.Movie
import com.example.mylibrary.movies.data.source.MoviesDAO
import com.example.mylibrary.movies.data.source.MoviesRoomDB
import com.example.mylibrary.movies.domain.MoviesRepository
import com.example.mylibrary.movies.domain.source.MoviesLocalDataSource
import com.example.mylibrary.movies.domain.source.MoviesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MoviesRepositoryImpl constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRoomDB: MoviesRoomDB,
    ) : MoviesRepository {

    override fun getTop100Movies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading)
        val cache = moviesLocalDataSource.getTop100Movies()
        if (cache.isNotEmpty()) {
            emit(Resource.Success(cache))
        }
        val response = try {
            moviesRemoteDataSource.getTop100Movies()
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
            null
        }
        response?.let { data ->
            moviesRoomDB.withTransaction {
                moviesLocalDataSource.deleteAllMovies()
                moviesLocalDataSource.insertAll(data)
            }
            emit(Resource.Success(moviesLocalDataSource.getTop100Movies()))
        }
    }

}
