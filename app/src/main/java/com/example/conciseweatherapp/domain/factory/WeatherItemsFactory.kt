package com.example.conciseweatherapp.domain.factory

import com.example.conciseweatherapp.data.model.DailyWeather
import com.example.conciseweatherapp.presentation.fragment.weatherDetails.WeatherItem
import javax.inject.Inject

class WeatherItemsFactory @Inject constructor() {

    fun createDailyWeatherItems(list: List<DailyWeather>, onClick: (DailyWeather) -> Unit): List<WeatherItem> {
        return list.map { item ->
            WeatherItem(item, onClick)
        }
    }

}
