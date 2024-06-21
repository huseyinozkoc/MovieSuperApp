package com.example.mylibrary.movies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mylibrary.movies.R
import com.example.mylibrary.movies.data.entities.Movie
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


    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            moviesState.value.isLoading -> {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }

            moviesState.value.movies != null -> {
                LazyColumn {
                    items(moviesState.value.movies!!) { movie ->
                        MovieCard(movie = movie)
                    }
                }
            }

            moviesState.value.error != null -> {
                // Display error UI
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                Image(
                    painter = rememberAsyncImagePainter(model = movie.image),
                    contentDescription = "Movie Image",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(text = movie.title, modifier = Modifier.padding(8.dp))
                Text(text = movie.description, modifier = Modifier.padding(8.dp))

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(start = 8.dp)
                ) {
                    movie.genre.forEach { genre ->
                        SuggestionChip(
                            onClick = { /* Handle click here */ },
                            label = { Text(genre) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    SuggestionChip(
                        onClick = { /* Handle click here */ },
                        label = { Text(movie.year.toString()) }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = movie.rank.toString(),
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}