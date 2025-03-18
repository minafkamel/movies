package com.minafkamel.latest.presentation.movies

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MoviesScreen(navController: NavHostController){

    MovieList(navController)
}