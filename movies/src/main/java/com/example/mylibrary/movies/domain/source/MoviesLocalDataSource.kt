package com.example.mylibrary.movies.domain.source

import com.example.mylibrary.movies.data.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    suspend fun getTop100Movies(): List<Movie>

    suspend fun insertAll(movies: List<Movie>)

    suspend fun deleteAllMovies()
}