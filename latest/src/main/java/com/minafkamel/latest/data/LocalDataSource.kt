package com.minafkamel.latest.data

import com.minafkamel.latest.data.Response.Movie
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val apiCache: ApiCache) {

    fun saveMovies(movies: List<Movie>) {
        movies.forEach { apiCache.save(it.id.toString(), it) }
    }

    fun getMovieBy(id: Long) = apiCache.get<Movie>(id.toString())

    fun getAllMovies() = apiCache.getAll<Movie>()
}
