package com.minafkamel.latest.data

import com.minafkamel.latest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object KeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
            .build()

        return chain.proceed(newRequest)
    }
}
