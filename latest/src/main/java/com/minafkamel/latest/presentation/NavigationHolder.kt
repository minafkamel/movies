package com.minafkamel.latest.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.minafkamel.latest.presentation.details.DetailsScreen
import com.minafkamel.latest.presentation.movies.MoviesScreen

@Composable
fun NavigationHolder() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "movies") {
        composable("movies") { MoviesScreen(navController) }
        composable("details/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: "0"
            DetailsScreen(movieId.toLong())
        }
    }
}