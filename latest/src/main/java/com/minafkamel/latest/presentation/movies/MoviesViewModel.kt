package com.minafkamel.latest.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minafkamel.latest.data.Repository
import com.minafkamel.latest.data.Response.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _movies = mutableStateOf<List<Movie>>(emptyList())
    val movies: State<List<Movie>> = _movies

    private val _error = mutableStateOf<String>("")
    val error: State<String> = _error

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            try{
                _movies.value = repository.getMovies()
            }catch (ex: Exception){
                _error.value = "Error fetching movies"
            }

        }
    }
}