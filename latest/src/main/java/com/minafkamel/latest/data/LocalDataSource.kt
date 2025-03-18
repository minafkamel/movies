package com.minafkamel.latest.data

import javax.inject.Inject

class LocalDataSource @Inject constructor(private val apiCache: ApiCache) {

    fun saveMovies(movies: List<Response.Movie>) {
        movies.forEach { apiCache.save(it.id.toString(), it) }
    }

    fun getMovieBy(id: Long) = apiCache.get<Response.Movie>(id.toString())
}
