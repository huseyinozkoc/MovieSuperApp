package com.example.mylibrary.movies.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylibrary.movies.data.entities.Movie
import com.example.mylibrary.movies.domain.GetTop100MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getTop100MoviesUseCase: GetTop100MoviesUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow(MoviesViewState())
    val movies: StateFlow<MoviesViewState> get() = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        _movies.value = MoviesViewState(isLoading = true)
        viewModelScope.launch {
            try {
                getTop100MoviesUseCase().collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _movies.value = MoviesViewState(isLoading = true)
                        }
                        is Resource.Success -> {
                            _movies.value = MoviesViewState(movies = resource.data, isLoading = false)
                        }
                        is Resource.Error -> {
                            _movies.value = MoviesViewState(error = resource.throwable,isLoading = false)
                        }

                        else -> {}
                    }
                }
            } catch (e: Exception) {
                _movies.value = MoviesViewState(error = e)
            }
        }
    }
}


// MoviesViewState.kt
data class MoviesViewState(
    val isLoading: Boolean = true,
    val movies: List<Movie>? = null,
    val error: Throwable? = null
)