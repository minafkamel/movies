package com.minafkamel.latest.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.minafkamel.latest.R

@Composable
fun DetailsScreen(id: Long) {

    val viewModel = hiltViewModel<DetailsViewModel, DetailsViewModel.Factory>(
        key = id.toString(),
        creationCallback = { factory -> factory.create(id = id) }
    )

    val movie = viewModel.movie.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 48.dp, end = 16.dp)
    ) {

        AsyncImage(
            stringResource(R.string.image_path_base) + movie?.posterPath,
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = movie?.title.orEmpty(),
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = stringResource(R.string.release_date, movie?.releaseDate.orEmpty()),
            style = TextStyle(fontSize = 16.sp),
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = movie?.overview.orEmpty(),
            style = TextStyle(fontSize = 16.sp),
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}