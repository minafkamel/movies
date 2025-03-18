package com.minafkamel.latest.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET(ENDPOINT)
    suspend fun getMovies(@Query("page") page: Int): Response<com.minafkamel.latest.data.Response>

    companion object {
        private const val ENDPOINT = "discover/movie"
    }
}
