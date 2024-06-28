package com.example.multimodule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberAsyncImagePainter
import com.example.multimodule.ui.theme.MultiModuleTheme
import com.example.mylibrary.movies.data.entities.Movie
import com.example.mylibrary.movies.presentation.MoviesActivity
import com.example.series.presentation.SeriesActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //val viewModel: MainViewModel = hiltViewModel()
    //private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val movies = remember { mutableStateOf(listOf<Movie>()) }

            LaunchedEffect(viewModel.movies) {
                viewModel.movies
                    .onEach { newMovies ->
                        Log.d("MainActivity", "New movies: $newMovies")
                        movies.value = newMovies
                    }
                    .catch { exception ->
                        // Handle the exception here
                        Log.e("MainActivity", "Error occurred: ${exception.message}")
                    }
                    .collect()
            }
            MultiModuleTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        item {
                            CardView(
                                title = "Movies Module",
                                description = "Click to navigate to Movies Module",
                                imageResource = R.drawable.movies,
                                onClick = {
                                    val intent =
                                        Intent(this@MainActivity, MoviesActivity::class.java)
                                    startActivity(intent)
                                }
                            )
                        }
                        item {
                            CardView(
                                title = "Series Module",
                                description = "Click to navigate to Series Module",
                                imageResource = R.drawable.series,
                                onClick = {
                                    val intent =
                                        Intent(this@MainActivity, SeriesActivity::class.java)
                                    startActivity(intent)
                                }
                            )
                        }

                        if (movies.value.isNotEmpty()) {
                            item {
                                Text(
                                    text = "Movies",
                                    fontSize = 24.sp,
                                    modifier = Modifier.padding(start = 16.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                LazyRow {
                                    items(movies.value.size) { index ->
                                        val movie = movies.value[index]
                                        MovieCardView(movie = movie, onClick = {})
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardView(title: String, description: String, imageResource: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun MovieCardView(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(275.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(model = movie.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiModuleTheme {
        CardView("Android", "", onClick = {}, imageResource = 1)
    }
}