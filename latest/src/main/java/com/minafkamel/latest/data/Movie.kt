package com.minafkamel.latest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


class Response(@SerializedName("results") val movies: List<Movie>) {
    @Parcelize
    class Movie(
        val id: Long,
        val title: String,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("poster_path") val posterPath: String,
        val overview: String,
    ) : Parcelable
}
