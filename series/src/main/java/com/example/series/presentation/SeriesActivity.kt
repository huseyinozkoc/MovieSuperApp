package com.example.series.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.*
import com.example.series.R
import com.example.series.data.Series
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesActivity : ComponentActivity() {
    private val viewModel: SeriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeriesList(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesList(viewModel: SeriesViewModel) {
    val seriesState = viewModel.series.collectAsState()
    val searchQuery = remember { mutableStateOf("") }
    val selectedGenre = remember { mutableStateOf("All") }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(start = 8.dp)
            ) {
                val genres = listOf("All") + viewModel.getGenres()
                genres.forEach { genre ->
                    FilterChip(
                        onClick = { selectedGenre.value = genre },
                        label = { Text(genre) },
                        selected = selectedGenre.value == genre
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            when {
                seriesState.value.isLoading -> {
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                seriesState.value.series != null -> {
                    val filteredSeries = seriesState.value.series!!.filter { series ->
                        series.title.contains(searchQuery.value, ignoreCase = true) &&
                                (selectedGenre.value == "All" || series.genre.contains(selectedGenre.value))
                    }
                    if (filteredSeries.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "No Search Result! :(",
                                    style = TextStyle(
                                        fontSize = 27.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 64.dp)
                                )
                                Image(
                                    painter = rememberAsyncImagePainter(model = R.drawable.error),
                                    contentScale = ContentScale.Fit,
                                    contentDescription = "No Results Image",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(1f)
                                        .padding(8.dp),
                                )
                            }
                        }
                    } else {
                        LazyColumn {
                            items(filteredSeries) { series ->
                                val isVisible =
                                    series.title.contains(searchQuery.value, ignoreCase = true) &&
                                            (selectedGenre.value == "All" || series.genre.contains(
                                                selectedGenre.value
                                            ))
                                AnimatedVisibility(
                                    visible = isVisible,
                                    enter = slideInVertically(initialOffsetY = { -100 }),
                                    exit = slideOutHorizontally(targetOffsetX = { -1000 })
                                ) {
                                    SeriesCard(series = series)
                                }
                            }
                        }
                    }
                }

                seriesState.value.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "An Error Occurred in Network! :(",
                                style = TextStyle(
                                    fontSize = 27.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 64.dp)
                            )
                            Image(
                                painter = rememberAsyncImagePainter(model = R.drawable.errornetwork),
                                contentScale = ContentScale.Fit,
                                contentDescription = "Error Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(1f)
                                    .padding(8.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SeriesCard(series: Series) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                Image(
                    painter = rememberAsyncImagePainter(model = series.image),
                    contentDescription = "Series Image",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(text = series.title, modifier = Modifier.padding(8.dp))
                Text(text = series.description, modifier = Modifier.padding(8.dp))

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(start = 8.dp)
                ) {
                    series.genre.forEach { genre ->
                        SuggestionChip(
                            onClick = { /* Handle click here */ },
                            label = { Text(genre) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    SuggestionChip(
                        onClick = { /* Handle click here */ },
                        label = { Text(series.year.toString()) }
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
                    text = series.rank.toString(),
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}