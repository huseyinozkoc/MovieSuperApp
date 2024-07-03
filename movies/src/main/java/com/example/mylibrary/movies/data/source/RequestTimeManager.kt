package com.example.mylibrary.movies.data.source

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mylibrary.movies.data.entities.FavMovie
import com.example.mylibrary.movies.data.entities.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

//Data Storage for the last request time
class RequestTimeManager(var context: Context) {

    // Create the dataStore
    private val Context.dataStore by preferencesDataStore("request_time")

    // Create a key for the last request time
    companion object {
        val LAST_REQUEST_TIME_KEY = longPreferencesKey("LAST_REQUEST_TIME")
        val FAVORITE_MOVIES_KEY = stringPreferencesKey("FAVORITE_MOVIES")
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Store the last request time
    suspend fun storeLastRequestTime(time: Long) {
        context.dataStore.edit { preferences ->
            preferences[LAST_REQUEST_TIME_KEY] = time
        }
    }

    // Get the last request time
    suspend fun getLastRequestTime(): Long? {
        return context.dataStore.data.first()[LAST_REQUEST_TIME_KEY]
    }

    // Create a flow for the last request time
    val lastRequestTimeFlow: Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[LAST_REQUEST_TIME_KEY] ?: 0L
    }

    //----------------------------------------------------------------------------------------------------------------------
    suspend fun storeFavoriteMovies(movie: FavMovie) {
        val currentMovies = getFavoriteMovies() ?: emptyList()
        val updatedMovies = currentMovies + movie
        val moviesJson = Json.encodeToString(updatedMovies)
        context.dataStore.edit { preferences ->
            preferences[FAVORITE_MOVIES_KEY] = moviesJson
        }
    }

    suspend fun deleteFavoriteMovie(movie: FavMovie) {
        val currentMovies = getFavoriteMovies() ?: emptyList()
        val updatedMovies = currentMovies.filter { it != movie }
        val moviesJson = Json.encodeToString(updatedMovies)
        context.dataStore.edit { preferences ->
            preferences[FAVORITE_MOVIES_KEY] = moviesJson
        }
    }

    suspend fun getFavoriteMovies(): List<FavMovie>? {
        val moviesJson = context.dataStore.data.first()[FAVORITE_MOVIES_KEY]
        return moviesJson?.let { Json.decodeFromString<List<FavMovie>>(it) }
    }

    val favoriteMoviesFlow: Flow<List<FavMovie>?> = context.dataStore.data.map { preferences ->
        val moviesJson = preferences[FAVORITE_MOVIES_KEY]
        moviesJson?.let { Json.decodeFromString<List<FavMovie>>(it) }
    }

    //----------------------------------------------------------------------------------------------------------------------

}