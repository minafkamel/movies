package com.minafkamel.latest.data

import android.os.Parcelable

class ApiCache {

    private val cache = mutableMapOf<String, Parcelable>()

    fun <T : Parcelable> save(key: String, response: T) {
        cache[key] = response
    }

    fun <T : Parcelable> get(key: String): T? {
        return cache[key] as? T
    }
}
