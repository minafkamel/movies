package com.minafkamel.latest.data

import retrofit2.Response
import retrofit2.http.GET

interface RemoteDataSource {

    @GET(ENDPOINT)
    suspend fun getMovies(): Response<com.minafkamel.latest.data.Response>

    companion object {
        private const val ENDPOINT = "discover/movie"
    }
}
