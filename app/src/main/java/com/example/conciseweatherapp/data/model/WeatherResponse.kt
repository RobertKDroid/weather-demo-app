package com.example.conciseweatherapp.data.model

import com.squareup.moshi.Json

data class WeatherResponse(
    val city: City,
    @Json(name = "list")
    val dailyWeather: List<DailyWeather>
)
