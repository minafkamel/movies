package com.minafkamel.latest.data

import com.minafkamel.latest.data.Response.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val apiCache: ApiCache) {

    private val _moviesFlow = MutableStateFlow<List<Movie>?>(null)
    val moviesFlow: StateFlow<List<Movie>?> = _moviesFlow

    suspend fun saveMovies(movies: List<Movie>) {
        movies.forEach { apiCache.save(it.id.toString(), it) }
        _moviesFlow.emit(apiCache.getAll<Movie>().map { it as Movie })
    }

    suspend fun filter(search: String) {
        _moviesFlow.emit(
            apiCache.getAll<Movie>()
            .map { it as Movie }
            .filter { it.title.contains(search, ignoreCase = true) })
    }

    fun getMovieBy(id: Long) = apiCache.get<Movie>(id.toString())
}
