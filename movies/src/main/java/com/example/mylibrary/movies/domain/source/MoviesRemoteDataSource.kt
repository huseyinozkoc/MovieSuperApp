package com.example.mylibrary.movies.domain.source

import com.example.mylibrary.movies.data.entities.Movie

interface MoviesRemoteDataSource {

    suspend fun getTop100Movies(): List<Movie>

}