package com.example.mylibrary.movies.domain

import Resource
import com.example.mylibrary.movies.data.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getTop100Movies(): Flow<Resource<List<Movie>>>
}