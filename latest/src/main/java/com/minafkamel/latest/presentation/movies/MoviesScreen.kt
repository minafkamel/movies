package com.minafkamel.latest.presentation.movies

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MoviesScreen(navController: NavHostController, viewModel: MoviesViewModel = hiltViewModel()){

    var query = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBox(query = query.value, onQueryChanged = {
            query.value = it
            viewModel.filter(it)
        })
        Spacer(modifier = Modifier.height(16.dp))
        MovieList(navController)
    }
}