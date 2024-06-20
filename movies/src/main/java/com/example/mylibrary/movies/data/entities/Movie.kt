package com.example.mylibrary.movies.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity

data class Movie(
    @PrimaryKey(autoGenerate = true)
    var movieId: Int = 0,
    val rank: Long,
    val title: String,
    val description: String,
    val image: String,
    val big_image: String,
    val genre: List<String>,
    val thumbnail: String,
    val rating: String,
    val id: String,
    val year: Long,
    val imdbid: String,
    val imdb_link: String
)