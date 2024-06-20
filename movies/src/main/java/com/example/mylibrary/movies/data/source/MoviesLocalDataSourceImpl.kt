package com.example.mylibrary.movies.data.source


import com.example.mylibrary.movies.data.entities.Movie
import com.example.mylibrary.movies.domain.source.MoviesLocalDataSource
import kotlinx.coroutines.flow.first

class MoviesLocalDataSourceImpl(private val moviesDAO: MoviesDAO) : MoviesLocalDataSource {

    override suspend fun getTop100Movies(): List<Movie> {
        return moviesDAO.getAllMovies().first()
    }

    override suspend fun insertAll(movies: List<Movie>) {
        moviesDAO.insertAll(movies)
    }

    override suspend fun deleteAllMovies() {
        moviesDAO.deleteAllMovies()
    }
}