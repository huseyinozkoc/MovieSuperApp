package com.example.mylibrary.movies.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieList(viewModel)
        }
    }
}

@Composable
fun MovieList(viewModel: MoviesViewModel) {
    val moviesState = viewModel.movies.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            moviesState.value.isLoading -> {
                Text(text = "Loading...", modifier = Modifier.align(Alignment.Center))
            }
            moviesState.value.movies != null -> {
                LazyColumn {
                    items(moviesState.value.movies!!) { movie ->
                        Text(text = movie.title)
                    }
                }
            }
            moviesState.value.error != null -> {
                // Display error UI
            }
        }
    }
}