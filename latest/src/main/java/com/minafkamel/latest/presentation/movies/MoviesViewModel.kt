package com.minafkamel.latest.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minafkamel.latest.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val moviesFlow = repository.valuesFlow

    private val _error = mutableStateOf("")
    val error: State<String> = _error

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                repository.getMovies()
                _error.value = ""
            } catch (ex: Exception) {
                _error.value = "Error fetching movies"
            }
        }
    }

    fun filter(search: String) = viewModelScope.launch { repository.filter(search) }
}