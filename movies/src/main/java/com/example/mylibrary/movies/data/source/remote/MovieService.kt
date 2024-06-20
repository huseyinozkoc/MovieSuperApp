package com.example.mylibrary.movies.data.source.remote

import com.example.mylibrary.movies.data.entities.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("/")
    suspend fun get100MovieList(): List<Movie>

    @GET("top{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): Movie
}