package com.futureprocessing.weatherapp.networking

import io.reactivex.Single
import retrofit2.http.GET

interface WeatherApiService {
@GET("d0f4f1a8f982f8e0350f")
fun getWeatherData(): Single<List<WeatherData>>
}