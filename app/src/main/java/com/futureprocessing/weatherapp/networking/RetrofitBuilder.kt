package com.futureprocessing.weatherapp.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitBuilder @Inject constructor() {

    companion object {
        fun build(): Retrofit {
            val client = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                .addInterceptor(
                    HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }
                )
                .build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.npoint.io/")
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()
        }
    }
}