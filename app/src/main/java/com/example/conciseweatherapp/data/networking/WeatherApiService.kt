package com.example.conciseweatherapp.data.networking

import com.example.conciseweatherapp.data.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherApiService {

    @GET("d0f4f1a8f982f8e0350f")
    fun getWeatherData(): Single<WeatherResponse>

}
