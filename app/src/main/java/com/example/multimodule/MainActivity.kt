package com.example.multimodule

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    Column {
                        Greeting(
                            name = "From App Module To Movies Module",
                            modifier = Modifier.padding(innerPadding).clickable {
                                val intent = Intent(this@MainActivity, MoviesActivity::class.java
                                )
                                startActivity(intent)
                            }
                        )

                        Greeting(
                            name = "From App Module To Series Module",
                            modifier = Modifier.padding(innerPadding).clickable {
                                val intent = Intent(this@MainActivity, SeriesActivity::class.java
                                )
                                startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiModuleTheme {
        Greeting("Android")
    }
}