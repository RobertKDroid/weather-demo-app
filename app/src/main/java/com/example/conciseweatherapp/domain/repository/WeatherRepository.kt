package com.example.conciseweatherapp.domain.repository

import com.example.conciseweatherapp.data.networking.WeatherApiService
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService
) {

    fun getWeatherData() = weatherApiService.getWeatherData()
        .subscribeOn(Schedulers.io())

}
