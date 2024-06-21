package com.example.series.data

import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesService {

    @GET("series")
    suspend fun get100SeriesList(): List<Series>

    @GET("series/top{id}")
    suspend fun getSeriesDetail(@Path("id") id: Int): Series
}