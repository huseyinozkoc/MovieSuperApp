package com.example.series.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.series.domain.GetTop100SeriesUseCase
import com.example.series.data.Series
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.series.common.Resource

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getTop100SeriesUseCase: GetTop100SeriesUseCase
) : ViewModel() {

    private val _series = MutableStateFlow(SeriesViewState())
    val series: StateFlow<SeriesViewState> get() = _series

    init {
        fetchSeries()
    }

    fun getGenres(): List<String> {
        val series = _series.value.series
        return series?.flatMap { it.genre }?.distinct() ?: emptyList()
    }


    private fun fetchSeries() {
        _series.value = SeriesViewState(isLoading = true)
        viewModelScope.launch {
            try {
                getTop100SeriesUseCase().collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _series.value = SeriesViewState(isLoading = true)
                        }
                        is Resource.Success -> {
                            _series.value = SeriesViewState(series = resource.data, isLoading = false)
                        }
                        is Resource.Error -> {
                            _series.value = SeriesViewState(error = resource.throwable, isLoading = false)
                        }
                        else -> {}
                    }
                }
            } catch (e: Exception) {
                _series.value = SeriesViewState(error = e)
            }
        }
    }
}

data class SeriesViewState(
    val isLoading: Boolean = true,
    val series: List<Series>? = null,
    val error: Throwable? = null
)