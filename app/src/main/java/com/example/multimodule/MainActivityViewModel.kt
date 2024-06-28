package com.example.multimodule

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mylibrary.movies.data.entities.Movie
import com.example.mylibrary.movies.data.source.MoviesDAO
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
) : ViewModel() {

    private val _movies = MutableStateFlow(listOf<Movie>())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() = viewModelScope.launch {
        val allMovies = moviesDAO.getAllMovies()
        Log.d("MainViewModel", "All movies: $allMovies")
        allMovies.collect { moviesList ->
            _movies.value = moviesList
        }
    }
}