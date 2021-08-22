package com.example.conciseweatherapp.domain.model

import android.os.Parcelable
import com.example.conciseweatherapp.data.model.City
import com.example.conciseweatherapp.data.model.DailyWeather
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherData(
    val city: City,
    val dailyWeatherList: List<DailyWeather>,
    val averageTemperature: Float
) : Parcelable
