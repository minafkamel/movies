package com.minafkamel.latest.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minafkamel.latest.data.Repository
import com.minafkamel.latest.data.Response
import com.minafkamel.latest.data.Response.Movie
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModel.Factory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted id: Long,
    private val repository: Repository
) : ViewModel() {

    private val _movie = mutableStateOf<Movie?>(null)
    val movie: State<Movie?> = _movie

    init {
        viewModelScope.launch {
            val movieDetails = repository.getMovieById(id)
            _movie.value = movieDetails
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Long): DetailsViewModel
    }
}