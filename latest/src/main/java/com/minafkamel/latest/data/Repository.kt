package com.minafkamel.latest.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    val valuesFlow = localDataSource.moviesFlow

    private var page = 0

    suspend fun getMovies() {
        page++
        val response = remoteDataSource.getMovies(page)
        if (response.body() != null) {
            val bodyResponse = response.body() as Response
            localDataSource.saveMovies(bodyResponse.movies)
        } else {
            throw Exception()
        }
    }

    suspend fun filter(search: String) = localDataSource.filter(search)

    fun getMovieById(id: Long) = localDataSource.getMovieBy(id)
}
