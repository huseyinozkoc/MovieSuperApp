package com.example.series.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.series.R
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

@Composable
fun SeriesList(viewModel: SeriesViewModel) {
    val seriesState = viewModel.series.collectAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            seriesState.value.isLoading -> {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }

            seriesState.value.series != null -> {
                LazyColumn {
                    items(seriesState.value.series!!) { series ->
                        Text(text = series.title)
                    }
                }
            }

            seriesState.value.error != null -> {
                // Display error UI
            }
        }
    }
}