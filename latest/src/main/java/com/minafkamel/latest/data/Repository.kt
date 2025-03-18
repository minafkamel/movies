package com.minafkamel.latest.data

import com.minafkamel.latest.data.Response.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    private var page = 0
    suspend fun getMovies() : List<Movie> {
        page++
        val response = remoteDataSource.getMovies(page)
        if (response.body() != null) {
            val bodyResponse = response.body() as Response
            localDataSource.saveMovies(bodyResponse.movies)
            return localDataSource.getAllMovies() as List<Movie>
        } else {
            throw Exception()
        }
    }

    fun getMovieById(id: Long) = localDataSource.getMovieBy(id)
}
