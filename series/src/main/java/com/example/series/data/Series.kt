package com.example.series.data

data class Series(
    val rank: Long,
    val title: String,
    val description: String,
    val image: String,
    val bigImage: String,
    val genre: List<String>,
    val thumbnail: String,
    val rating: Double,
    val id: String,
    val year: String,
    val imdbid: String,
    val imdbLink: String,
)
