package com.minafkamel.latest.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideBaseUrl(): String = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(
            HttpLoggingInterceptor().apply {
                HttpLoggingInterceptor.Level.BODY
            }
        )
        addInterceptor(KeyInterceptor)
    }.build()

    @Provides
    @Singleton
    fun providesGsonAdapter() = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): RemoteDataSource =
        retrofit.create(RemoteDataSource::class.java)

    @Provides
    @Singleton
    fun provideApiCache() = ApiCache()
}
