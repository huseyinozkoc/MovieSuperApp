package com.example.multimodule

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mylibrary.movies.data.entities.FavMovie
import com.example.mylibrary.movies.data.entities.Movie
import com.example.mylibrary.movies.data.source.MoviesDAO
import com.example.mylibrary.movies.data.source.RequestTimeManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesDAO: MoviesDAO,
    private val requestTimeManager: RequestTimeManager,
) : ViewModel() {

    private val _movies = MutableStateFlow(listOf<Movie>())
    val movies: StateFlow<List<Movie>> = _movies


    private val _favoriteMovies = MutableStateFlow(listOf<FavMovie>())
    val favoriteMovies: StateFlow<List<FavMovie>> = _favoriteMovies

    init {
        fetchMovies()
        fetchFavoriteMovies()
    }

    private fun fetchMovies() = viewModelScope.launch {
        val allMovies = moviesDAO.getAllMovies()
        Log.d("MainViewModel", "All movies: $allMovies")
        allMovies.collect { moviesList ->
            _movies.value = moviesList
        }
    }
    fun fetchFavoriteMovies() = viewModelScope.launch {
        val allFavoriteMovies = requestTimeManager.getFavoriteMovies()
        Log.d("MainViewModel", "All favorite movies: $allFavoriteMovies")
        _favoriteMovies.value = allFavoriteMovies ?: emptyList()
    }

}