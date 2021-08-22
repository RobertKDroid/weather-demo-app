package com.example.conciseweatherapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyWeather(
    @Json(name = "dt")
    val date: Long,
    @Json(name = "temp")
    val temperatures: Temperatures,
    @Json(name = "speed")
    val windSpeed: Float,
    val clouds: Float,
    val humidity: Float,
    val pressure: Float,
    @Json(name = "weather")
    val weatherDetails: List<WeatherDetails>
) : Parcelable
