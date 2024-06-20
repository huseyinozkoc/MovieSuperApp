package com.example.mylibrary.movies.data.source.remote

import com.example.mylibrary.movies.domain.source.MoviesRemoteDataSource
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MoviesRemoteDataSource {


    override suspend fun getTop100Movies() = movieService.get100MovieList()


}