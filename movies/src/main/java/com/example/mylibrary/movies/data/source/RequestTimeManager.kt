package com.example.mylibrary.movies.data.source

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//Data Storage for the last request time
class RequestTimeManager(var context: Context) {

    // Create the dataStore
    private val Context.dataStore by preferencesDataStore("request_time")

    // Create a key for the last request time
    companion object {
        val LAST_REQUEST_TIME_KEY = longPreferencesKey("LAST_REQUEST_TIME")
    }

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
}