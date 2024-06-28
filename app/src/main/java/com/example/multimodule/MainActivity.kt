package com.example.multimodule

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.multimodule.ui.theme.MultiModuleTheme
import com.example.mylibrary.movies.presentation.MoviesActivity
import com.example.series.presentation.SeriesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //val viewModel: MainViewModel = hiltViewModel()
    //private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            MultiModuleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        item {
                            CardView(
                                title = "Movies Module",
                                description = "Click to navigate to Movies Module",
                                imageResource = com.example.series.R.drawable.error,
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
                                imageResource = com.example.series.R.drawable.errornetwork,
                                onClick = {
                                    val intent =
                                        Intent(this@MainActivity, SeriesActivity::class.java)
                                    startActivity(intent)
                                }
                            )
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
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description)
            }
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