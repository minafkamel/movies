package com.minafkamel.latest.presentation.movies

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.minafkamel.latest.R
import com.minafkamel.latest.data.Response

@Composable
fun MovieList(navController: NavHostController, viewModel: MoviesViewModel = hiltViewModel()) {
    val movies = viewModel.moviesFlow.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        movies.value?.let {
            items(it.count()) { index ->
                MovieItem(navController, it[index])

                val state = rememberLazyListState()
                val isAtBottom = !state.canScrollForward
                LaunchedEffect(isAtBottom) {
                    if (isAtBottom) {
                        viewModel.fetchMovies()
                    }
                }
            }
        }

    }

    val error by viewModel.error
    if (error.isNotEmpty())
        Toast.makeText(LocalContext.current, error, Toast.LENGTH_LONG).show()
}

@Composable
fun MovieItem(navController: NavHostController, movie: Response.Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { navController.navigate("details/${movie.id}") }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = stringResource(R.string.release_date, movie.releaseDate),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
